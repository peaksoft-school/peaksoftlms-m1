create table options (id int8 not null,
                      is_correct boolean not null,
                      option varchar(255),
                      questions_id int8,
                      primary key (id));