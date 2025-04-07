select customerName as CustomerName , round(sum(amount),2) as total from customers, payments 
	where (payments.customerNumber=customers.customerNumber) and (customers.city = 'Paris')
		group by customerName,payments.customerNumber having count(payments.customerNumber) > 4