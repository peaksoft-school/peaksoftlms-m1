create table task (id int8 not null,
                   code varchar(255),
                   image varchar(255),
                   link varchar(255),
                   name varchar(255),
                   text varchar(255),
                   file_id int8, primary key (id));