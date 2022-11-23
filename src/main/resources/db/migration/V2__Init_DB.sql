INSERT INTO users (id, created_at, email, first_name,
                   is_active, last_name, password,
                   phone_number,
                   specialization, study_format)
VALUES (1, current_timestamp, 'admin@gmail.com', 'admin', TRUE, 'admin', 'admin', null, null,
        null) ON CONFLICT do nothing;

INSERT INTO roles (id, name)
VALUES (1, 'ROLE_ADMIN') ON CONFLICT do nothing;

INSERT INTO users_roles(user_id, roles_id)
VALUES (1, 1) ON CONFLICT do nothing;

INSERT INTO users (id, created_at, email, first_name,
                   is_active, last_name, password,
                   phone_number,
                   specialization, study_format)
VALUES (2, current_timestamp, 'instructor@gmail.com', 'instructor', TRUE, 'instructor', 'instructor', null, null,
        null) ON CONFLICT do nothing;

INSERT INTO roles (id, name)
VALUES (2, 'ROLE_INSTRUCTOR') ON CONFLICT do nothing;

INSERT INTO users_roles(user_id, roles_id)
VALUES (2, 2) ON CONFLICT do nothing;

INSERT INTO users (id, created_at, email, first_name,
                   is_active, last_name, password,
                   phone_number,
                   specialization, study_format)
VALUES (3, current_timestamp, 'student@gmail.com', 'student', TRUE, 'student', 'student', null, null,
        null) ON CONFLICT do nothing;

INSERT INTO roles (id, name)
VALUES (3, 'ROLE_STUDENT') ON CONFLICT do nothing;

INSERT INTO users_roles(user_id, roles_id)
VALUES (3, 3) ON CONFLICT do nothing;
