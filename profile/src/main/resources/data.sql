insert into student_entity (name, password, role, score, student_id)
values ('sadegh', '$2a$10$6lVRYzRNrstHbzN7BvD8J.WkgI6fquPvaZTewvTQ.A9PTxvcTzreW', '', 19.5, '9612762478');
insert into term_lesson (course_year, grade, term, course, student_entity)
VALUES (1399, 19, 1, 1, 1),
       (1399, 19, 1, 2, 1);
insert into group_entity (course, initial_capacity, remaining_capacity)
VALUES (1, 10, 10),
       (1, 10, 10),
       (2, 10, 10),
       (3, 10, 10),
       (4, 10, 10),
       (4, 10, 10),
       (5, 10, 10);