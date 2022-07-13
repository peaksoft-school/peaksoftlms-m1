create table results (id int8 not null,
                      access_test varchar(255),
                      correct_answers float8 not null,
                      incorrect_answers float8 not null,
                      percent_of_result varchar(255),
                      time timestamp,
                      test_id int8,
                      user_id int8, primary key (id));