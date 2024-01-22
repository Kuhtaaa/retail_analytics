DROP TABLE IF EXISTS segments;

CREATE TABLE segments (
    Segment                BIGINT PRIMARY KEY NOT NULL,
    Average_Check          VARCHAR(255) NOT NULL,
    Frequency_Of_Purchases VARCHAR(255) NOT NULL,
    Churn_Probability      VARCHAR(255) NOT NULL
);

SELECT import_db('segments', ',');

CREATE OR REPLACE VIEW customers AS
WITH data_set AS (
    SELECT pi.customer_id,
        t.*
    FROM personal_information pi
             LEFT JOIN cards cr USING (customer_id)
             LEFT JOIN transactions t USING (customer_card_id)
             LEFT JOIN (SELECT MAX(analysis_formation) AS analysis_formation FROM date_of_analysis_formation LIMIT 1) da
    ON TRUE
    WHERE t.transaction_datetime <= da.analysis_formation OR transaction_datetime IS NULL
)

SELECT table2.*,
    segments.Segment AS Customer_Segment,
    table4.transaction_store_id AS Customer_Primary_Store
FROM (
    SELECT customer_id,
        Customer_Average_Check,

        CASE
            WHEN Customer_Average_Check IS NOT NULL
                THEN
                CASE
                    WHEN segment_rank <= 0.1
                        THEN 'High'
                    WHEN segment_rank <= 0.35
                        THEN 'Medium'
                    ELSE 'Low'
                    END
            END AS Customer_Average_Check_Segment,
        Customer_Frequency,

        CASE
            WHEN Customer_Average_Check IS NOT NULL
                THEN
                CASE
                    WHEN segment_freq_rank <= 0.1
                        THEN 'Often'
                    WHEN segment_freq_rank <= 0.35
                        THEN 'Occasionaly'
                    ELSE 'Rarely'
                    END
            END AS Customer_Frequency_Segment,
        customer_inactive_period,
        customer_inactive_period / customer_frequency AS Customer_Churn_Rate,

        CASE
            WHEN customer_inactive_period / customer_frequency < 2
                THEN 'Low'
            WHEN customer_inactive_period / customer_frequency <= 5
                THEN 'Medium'
            WHEN customer_inactive_period IS NOT NULL
                THEN 'High'
            END AS Customer_Churn_Segment
    FROM (
        SELECT customer_id,
            AVG(transaction_summ) AS Customer_Average_Check,

                    PERCENT_RANK()
                    OVER (PARTITION BY AVG(transaction_summ) IS NOT NULL
                        ORDER BY AVG(transaction_summ) DESC) AS segment_rank,

            (EXTRACT(EPOCH FROM (MAX(transaction_datetime)))
                    - EXTRACT(EPOCH FROM (MIN(transaction_datetime)))) / 86400 / COUNT(*) AS customer_frequency,

                    PERCENT_RANK()
                    OVER (PARTITION BY (MAX(transaction_datetime) - MIN(transaction_datetime)) IS NOT NULL
                        ORDER BY (EXTRACT(EPOCH FROM (MAX(transaction_datetime)))
                                - EXTRACT(EPOCH FROM (MIN(transaction_datetime)))) / 86400
                                / COUNT(*)) AS segment_freq_rank,

            (EXTRACT(EPOCH FROM (SELECT MAX(analysis_formation) FROM date_of_analysis_formation))
                    - EXTRACT(EPOCH FROM (MAX(transaction_datetime)))) / 86400 AS customer_inactive_period

        FROM data_set
        GROUP BY customer_id
    ) AS table1
) AS table2

         LEFT JOIN segments ON Average_Check = Customer_Average_Check_Segment AND
    Frequency_Of_Purchases = Customer_Frequency_Segment AND
    Churn_Probability = Customer_Churn_Segment

         LEFT JOIN LATERAL (
    SELECT customer_id,
        transaction_store_id
    FROM (
        SELECT DISTINCT customer_id,
            transaction_store_id,
                    MAX(transaction_datetime)
                    OVER (PARTITION BY customer_id, transaction_store_id) AS last_transaction_dt,

                    COUNT(transaction_id) OVER (PARTITION BY customer_id, transaction_store_id)::NUMERIC
                    / NULLIF(COUNT(transaction_id) OVER (PARTITION BY customer_id), 0) AS transaction_share,

            CASE
                WHEN
                    transaction_store_id = LAG(transaction_store_id)
                                           OVER (PARTITION BY customer_id ORDER BY transaction_datetime DESC) AND
                        transaction_store_id = LEAD(transaction_store_id)
                                               OVER (PARTITION BY customer_id ORDER BY transaction_datetime DESC) AND
                        2 = ROW_NUMBER() OVER (PARTITION BY customer_id ORDER BY transaction_datetime DESC)

                    THEN TRUE
                ELSE FALSE
                END AS last_three_tr
        FROM data_set
    ) AS table3
    WHERE table2.customer_id = table3.customer_id
    GROUP BY customer_id, transaction_store_id, transaction_share, last_transaction_dt, last_three_tr
    ORDER BY customer_id, last_three_tr DESC, transaction_share DESC, last_transaction_dt DESC
    LIMIT 1
    ) AS table4 ON TRUE
ORDER BY customer_id;
