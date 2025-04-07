select employees.firstName, employees.lastName, sum(amount) as total from customers,payments,employees,offices
	where customers.customerNumber = payments.customerNumber and customers.salesRepEmployeeNumber = employees.employeeNumber
		and employees.officeCode = offices.officeCode and offices.officeCode = 7
			group by employees.firstName, employees.lastName