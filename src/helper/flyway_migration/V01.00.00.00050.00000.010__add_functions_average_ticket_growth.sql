CREATE OR REPLACE FUNCTION average_ticket_growth(
    work_mode INTEGER,
    start_date VARCHAR,
    end_date VARCHAR,
    number_transactions INTEGER,
    coefficient_increase_average_check DOUBLE PRECISION,
    maximum_churn_index DOUBLE PRECISION,
    maximum_share_transactions_discount DOUBLE PRECISION,
    acceptable_margin_share DOUBLE PRECISION
)
    RETURNS TABLE (
        Customer_ID BIGINT,
        Required_Check_Measure NUMERIC,
        Group_Name TEXT,
        Offer_Discount_Depth NUMERIC
    ) AS $$
DECLARE
    start_period_date TIMESTAMP;
    end_period_date TIMESTAMP;
BEGIN
    SET datestyle TO dmy;

    SELECT
        CASE
            WHEN start_date::TIMESTAMP < min(transaction_datetime) THEN
                min(transaction_datetime)
            ELSE start_date::TIMESTAMP
            END,
        CASE
            WHEN end_date::TIMESTAMP > max(transaction_datetime)  THEN
                max(transaction_datetime)
            ELSE end_date::TIMESTAMP
            END
    FROM purchase_history
    INTO start_period_date, end_period_date;

    RETURN QUERY
        SELECT DISTINCT
            pi.customer_id,
            round((sum(table1.tr_sum) OVER (PARTITION BY pi.customer_id)
                / count(*) OVER (PARTITION BY pi.customer_id) * coefficient_increase_average_check)::NUMERIC, 2)
                Required_Check_Measure,
            table2.group_name,
            table2.Offer_Discount_Depth
        FROM personal_information pi

                 JOIN (
            SELECT
                phv.*,
                        row_number() OVER (PARTITION BY phv.customer_id) AS rn
            FROM (
                SELECT ph.customer_id,
                    ph.transaction_id,
                    ph.transaction_datetime,
                    sum(ph.group_summ) AS tr_sum
                FROM purchase_history ph
                GROUP BY ph.customer_id, ph.transaction_id, ph.transaction_datetime
            ) phv
            WHERE phv.transaction_id IS NOT NULL
        ) table1 ON pi.customer_id = table1.customer_id AND
            (CASE
                WHEN work_mode = 1 THEN table1.transaction_datetime BETWEEN start_period_date AND end_period_date
                WHEN work_mode = 2 THEN table1.rn <= number_transactions
                END)

                 JOIN (
            SELECT g.customer_id,
                g.group_id,
                sg.group_name,
                avg((ph.group_summ - ph.group_cost) / ph.group_summ),
                ceil(g.group_minimum_discount / 0.05) * 5 as Offer_Discount_Depth,
                        row_number() OVER (PARTITION BY g.customer_id, g.group_id ORDER BY group_affinity_index DESC) AS rn
            FROM groups g
                     LEFT JOIN purchase_history ph ON ph.customer_id = g.customer_id AND
                g.group_id = ph.group_id
                     LEFT JOIN sku_groups sg ON g.group_id = sg.group_id
            WHERE g.group_churn_rate <= maximum_churn_index AND
                g.group_discount_share * 100 <= maximum_share_transactions_discount
            GROUP BY g.customer_id,
                g.group_id,
                sg.group_name,
                g.group_affinity_index,
                g.group_minimum_discount
            HAVING (avg((ph.group_summ - ph.group_cost) / ph.group_summ) * acceptable_margin_share >=
                ceil(g.group_minimum_discount / 0.05) * 5)
            ORDER BY g.customer_id,
                g.group_affinity_index DESC
        ) table2 ON table2.customer_id = table1.customer_id AND table2.rn = 1
        ORDER BY customer_id;
END;
$$ LANGUAGE plpgsql;
