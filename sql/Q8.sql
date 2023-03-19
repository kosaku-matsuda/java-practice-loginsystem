SELECT
x.item_id, x.item_name, x.item_price, z.category_name
FROM
item x
INNER JOIN
item_category z
ON
z.category_id = x.category_id;