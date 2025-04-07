select employees.email from employees, customers where employees.employeeNumber = customers.salesRepEmployeeNumber 
		group by employees.email having count(customers.customerName) <
			(select count(customerName) from customers group by salesRepEmployeeNumber having salesRepEmployeeNumber = 1166)