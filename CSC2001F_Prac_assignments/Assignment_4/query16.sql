select sum(quantityOrdered*priceEach) as totalCost from orderdetails
		where orderNumber in (select orderNumber from orders where customerNumber=121)