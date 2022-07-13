INSERT INTO users (id, created, email, first_name,
                   is_active, last_name, password,
                   phone_number,
                   specialization, study_format)
VALUES (1, current_timestamp, 'admin@gmail.com', 'admin', TRUE, 'admin', 'admin', null, null, null)
ON CONFLICT do nothing;

INSERT INTO roles (id,name)
VALUES (1, 'ROLE_ADMIN')
ON CONFLICT do nothing;

INSERT INTO users_roles(user_id, roles_id)
VALUES (1, 1)
ON CONFLICT do nothing;
