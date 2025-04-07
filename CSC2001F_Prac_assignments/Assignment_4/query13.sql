select productName, quantityInStock as quantityInstock, textDescription from products inner join productlines on productLine = productLines
	where quantityInStock<100     