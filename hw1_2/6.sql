select employee_id, last_name, first_name, salary from employees where salary > (select avg(salary) from employees);