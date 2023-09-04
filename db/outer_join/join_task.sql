select e.name, d.name, e.id_employee from departments d left join employees e on d.id = e.id_employee;
select * from departments d right join employees e on d.id = e.id_employee;
select * from departments d full join employees e on d.id = e.id_employee;
select * from departments d cross join employees e;

select * from departments d left join employees e on d.id = e.id_employee where e.name is null;

select d.name, e.id_employee, e.name from departments d right join employees e on d.id = e.id_employee;
select d.name, e.id_employee, e.name from employees e left join departments d on d.id = e.id_employee;






