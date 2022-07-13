create table lesson (id int8 not null,
                     name varchar(255),
                     course_id int8 not null,
                     link_id int8,
                     presentation_id int8,
                     task_id int8,
                     test_id int8,
                     video_lesson_id int8,
                     primary key (id));