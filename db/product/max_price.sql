select name, price
from product
where price = (select max(price) from product);
