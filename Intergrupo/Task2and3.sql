--Task 2
SELECT d.department_id "dept_id", COUNT(e.employee_id) "count", SUM(e.salary) "sum_of_salary"
FROM departments d, employees e
WHERE d.department_id = e.department_id
GROUP BY d.department_id
ORDER BY d.department_id;

SELECT d.dept_id "dept_id", COUNT(e.emp_id) "count", SUM(e.salary) "sum_of_salary"
FROM department d, employee e
WHERE d.dept_id = e.dept_id
GROUP BY d.dept_id
ORDER BY d.dept_id;

--Task 3
create table movies (
      id integer not null,
      title varchar(40) not null,
      director varchar(40) not null,
      unique(id)
  );
  
insert into movies (id,title,director)
values(30,'Ducks','Donald');
insert into movies (id,title,director)
values(40,'We test coders','Codility');
insert into movies (id,title,director)
values(50,'Thr3e','Davut Rechnif');

select * from movies;

----
create table reservations (
      id integer not null,
      movie_id integer not null,
      number_of_tickets integer not null,
      cinema varchar(40) not null,
      unique(id)
  );
  
insert into reservations (id,movie_id,number_of_tickets,cinema)
values(1,30,30,'Cineduck');
insert into reservations (id,movie_id,number_of_tickets,cinema)
values(2,50,3,'Threenema'); 
insert into reservations (id,movie_id,number_of_tickets,cinema)
values(3,30,1,'Threenema'); 
insert into reservations (id,movie_id,number_of_tickets,cinema)
values(4,40,17,'Cinnamon'); 
insert into reservations (id,movie_id,number_of_tickets,cinema)
values(5,30,8,'Cineduck'); 
insert into reservations (id,movie_id,number_of_tickets,cinema)
values(6,40,25,'Threenema'); 
insert into reservations (id,movie_id,number_of_tickets,cinema)
values(7,30,11,'See Nema'); 

select * from reservations;

--Oracle
SELECT m.id , m.title, SUM(NVL(r.number_of_tickets, 0)) sold_tickets
FROM movies m 
LEFT JOIN reservations r 
ON m.id = r.movie_id
GROUP BY m.id, m.title
ORDER BY sold_tickets DESC, m.id;

--Posgres
SELECT m.id ,
m.title, 
CASE
     WHEN SUM(r.number_of_tickets) IS NULL THEN 0
     ELSE SUM(r.number_of_tickets)            
     END sold_tickets
FROM movies m 
LEFT JOIN reservations r 
ON m.id = r.movie_id
GROUP BY m.id, m.title
ORDER BY sold_tickets DESC, m.id;