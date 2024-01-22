CREATE OR REPLACE VIEW "groups" AS

WITH affenity_index AS (
    SELECT ph.customer_id,
        p.group_id,
        group_purchase / COUNT(ph.transaction_id)::NUMERIC AS Group_Affinity_Index
    FROM purchase_history ph
             JOIN periods p USING (customer_id)
    WHERE ph.transaction_datetime BETWEEN first_group_purchase_date AND last_group_purchase_date
    GROUP BY ph.customer_id, p.group_id, group_purchase
    ORDER BY customer_id
),

    churn_rate AS (
        SELECT ph.customer_id,
            ph.group_id,
            (extract(epoch FROM(SELECT analysis_formation FROM date_of_analysis_formation))
                    - extract(epoch FROM MAX(ph.transaction_datetime)))
                    / (group_frequency) / 86400::NUMERIC AS Group_Churn_Rate
        FROM transactions t
                 JOIN purchase_history ph USING (transaction_id)
                 JOIN periods p ON ph.group_id = p.group_id AND p.customer_id = ph.customer_id
        GROUP BY ph.customer_id, ph.group_id, group_frequency
        ORDER BY customer_id, group_id
    ),

    intervals AS (
        SELECT ph.customer_id,
            ph.transaction_id,
            ph.group_id,
            ph.transaction_datetime,
            EXTRACT(DAY FROM (transaction_datetime - LAG(transaction_datetime)
                                                     OVER (PARTITION BY ph.customer_id, ph.group_id ORDER BY transaction_datetime))) AS interval
        FROM purchase_history ph
                 JOIN periods p ON p.customer_id = ph.customer_id AND p.group_id = ph.group_id
        GROUP BY ph.customer_id, transaction_id, ph.group_id, transaction_datetime
        ORDER BY customer_id, group_id
    ),

    stability_index AS (
        SELECT i.customer_id,
            i.group_id,
            avg(
                    CASE
                        WHEN (i.interval - p.group_frequency) > 0::NUMERIC THEN (i.interval - p.group_frequency)
                        ELSE (i.interval - p.group_frequency) * '-1'::INTEGER::NUMERIC
                        END / p.group_frequency
                ) AS group_stability_index
        FROM intervals i
                 JOIN periods p ON p.customer_id = i.customer_id AND i.group_id = p.group_id
        GROUP BY i.customer_id, i.group_id
        ORDER BY customer_id, group_id
    ),

    margin AS (
        SELECT customer_id,
            group_id,
            sum(group_summ_paid - group_cost)::NUMERIC AS Group_Margin
        FROM purchase_history
        GROUP BY customer_id, group_id
        ORDER BY customer_id, group_id
    ),

    count_discount_Share AS (
        SELECT DISTINCT p.customer_id,
            cm.group_id,
            CASE
                WHEN max(sku_discount) = 0 THEN count(checks.transaction_id)
                ELSE count(checks.transaction_id) FILTER (WHERE sku_discount> 0)
                END AS count_share
        FROM personal_information p
                 JOIN cards USING (customer_id)
                 JOIN transactions USING (customer_card_id)
                 JOIN checks USING (transaction_id)
                 JOIN commodity_matrix cm USING (sku_id)
        GROUP BY p.customer_id, cm.group_id
        ORDER BY customer_id
    ),

    discount_share AS (
        SELECT DISTINCT c.customer_id,
            c.group_id,
            count_share / group_purchase::NUMERIC AS Group_Discount_Share
        FROM count_discount_Share c
                 JOIN periods p ON c.group_id = p.group_id AND p.customer_id = c.customer_id
        GROUP BY c.customer_id, c.group_id, Group_Discount_Share
    ),

    minimum_discount AS (
        SELECT customer_id,
            group_id,
            min(group_min_discount) AS Group_Minimum_Discount
        FROM periods p
        GROUP BY customer_id, group_id
        ORDER BY customer_id, group_id
    ),

    group_average_discount AS (
        SELECT customer_id,
            group_id,
            avg(group_summ_paid / group_summ)::NUMERIC AS Group_Average_Discount
        FROM purchase_history
                 JOIN checks USING (transaction_id)
        WHERE sku_discount > 0
        GROUP BY customer_id, group_id
        ORDER BY customer_id, group_id
    )

SELECT DISTINCT af.customer_id,
    af.group_id,
    Group_Affinity_Index,
    Group_Churn_Rate,
    COALESCE(Group_Stability_Index, 0) AS Group_Stability_Index,
    Group_Margin,
    Group_Discount_Share,
    Group_Minimum_Discount,
    Group_Average_Discount
FROM affenity_index af
         JOIN churn_rate cr ON af.group_id = cr.group_id AND af.customer_id = cr.customer_id
         JOIN stability_index si ON si.group_id = cr.group_id AND si.customer_id = af.customer_id
         JOIN margin gm ON gm.customer_id = af.customer_id AND gm.group_id = af.group_id
         JOIN discount_share ds ON ds.group_id = cr.group_id AND ds.customer_id = cr.customer_id
         JOIN minimum_discount md ON md.group_id = af.group_id AND md.customer_id = af.customer_id
         JOIN group_average_discount gad ON gad.group_id = md.group_id AND gad.customer_id = ds.customer_id
GROUP BY af.customer_id,
    af.group_id,
    Group_Affinity_Index,
    Group_Churn_Rate,
    Group_Discount_Share,
    Group_Minimum_Discount,
    Group_Average_Discount,
    Group_Stability_Index,
    Group_Margin
ORDER BY af.customer_id, af.group_id;
