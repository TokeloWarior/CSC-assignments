select customerName as CustomerName , round(sum(amount),2) as total from customers, payments 
	where payments.customerNumber=customers.customerNumber  group by customerName,payments.customerNumber