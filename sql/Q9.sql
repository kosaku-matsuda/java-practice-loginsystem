SELECT
z.category_name, sum(x.item_price) total_price
FROM
item x
INNER JOIN
item_category z
ON 
z.category_id = x.category_id
GROUP BY
x.category_id
ORDER BY 
total_price DESC;