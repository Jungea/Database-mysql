grant all on employees.departments to 'emp_user'@'localhost' with grant option;

select * from tables_priv where user='emp_user';