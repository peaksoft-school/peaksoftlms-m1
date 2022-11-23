create table users (id int8 not null,
                    created timestamp,
                    email varchar(255),
                    first_name varchar(255),
                    is_active boolean not null,
                    last_name varchar(255),
                    password varchar(255),
                    phone_number varchar(255),
                    specialization varchar(255),
                    study_format varchar(255),
                    primary key (id));

create table roles (id int8 not null,
                    name varchar(255),
                    primary key (id));

create table links (id int8 not null,
                   link varchar(255),
                   text varchar(255),
                   primary key (id));

create table options (id int8 not null,
                      is_correct boolean not null,
                      option varchar(255),
                      questions_id int8,
                      primary key (id));

create table course (id int8 not null,
                     description varchar(255),
                     course_img varchar(255),
                     name_course varchar(255),
                     start_course timestamp,
                     primary key (id));

create table files (id int8 not null,
                    file_name varchar(255),
                    primary key (id));

create table groups (id int8 not null,
                     description varchar(255),
                     group_name varchar(255),
                     group_img varchar(255),
                     start_date timestamp,
                     primary key (id));

create table groups_courses (course_id int8 not null,
                             group_id int8 not null);

create table lessons (id int8 not null,
                     name varchar(255),
                     course_id int8 not null,
                     link_id int8,
                     presentation_id int8,
                     task_id int8,
                     test_id int8,
                     video_lesson_id int8,
                     primary key (id));

create table presentations (id int8 not null,
                           description varchar(255),
                           file varchar(255),
                           name varchar(255),
                           primary key (id));

create table questions (id int8 not null,
                        question_title varchar(255),
                        question_type varchar(255),
                        test_id int8,
                        primary key (id));

create table results (id int8 not null,
                      access_test varchar(255),
                      correct_answers float8 not null,
                      incorrect_answers float8 not null,
                      percent_of_result varchar(255),
                      time timestamp,
                      test_id int8,
                      user_id int8, primary key (id));

create table task (id int8 not null,
                   code varchar(255),
                   image varchar(255),
                   link varchar(255),
                   name varchar(255),
                   text varchar(255),
                   file_id int8, primary key (id));

create table tests (id int8 not null,
                    is_active boolean,
                    name varchar(255),
                    primary key (id));

create table users_courses (course_id int8 not null,
                            user_id int8 not null);

create table users_groups (user_id int8 not null,
                           groups_id int8 not null);

create table users_roles (user_id int8 not null,
                          roles_id int8 not null);

create table video_lesson (id int8 not null,
                           description varchar(255),
                           link varchar(255),
                           name varchar(255),
                           primary key (id))
