CREATE OR REPLACE VIEW periods AS
WITH time_parameters AS (
    SELECT cards.Customer_ID                           AS Customer_ID,
        count(DISTINCT transactions.transaction_id) AS Group_Purchase,
        min(transactions.transaction_datetime)      AS First_Group_Purchase_Date,
        max(transactions.transaction_datetime)      AS Last_Group_Purchase_Date,
        cm.group_id                                 AS Group_ID
    FROM cards
             JOIN transactions USING (customer_card_id)
             JOIN checks USING (transaction_id)
             JOIN commodity_matrix cm USING (sku_id)
    GROUP BY cards.Customer_ID, cm.group_id),

    min_group_discount AS (
        SELECT cards.customer_id                  AS Customer_ID,
            cm.group_id                        AS Group_ID,
            min(ch.sku_discount / ch.sku_summ) AS min_discount
        FROM cards
                 JOIN transactions tr USING (customer_card_id)
                 JOIN checks ch ON tr.transaction_id = ch.transaction_id AND ch.sku_discount > 0
                 JOIN commodity_matrix cm USING (sku_id)
        GROUP BY cards.customer_id, cm.group_id)

SELECT tp.Customer_ID,
    tp.Group_ID,
    tp.First_Group_Purchase_Date,
    tp.Last_Group_Purchase_Date,
    tp.Group_Purchase,
    (abs(EXTRACT(EPOCH FROM first_group_purchase_date - last_group_purchase_date) / 86400) + 1)
            / tp.Group_Purchase   AS Group_Frequency,
    coalesce(min_discount, 0) AS Group_Min_Discount
FROM time_parameters tp
         LEFT JOIN min_group_discount mgd ON tp.Customer_ID = mgd.Customer_ID AND
    tp.Group_ID = mgd.Group_ID;
