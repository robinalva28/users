INSERT INTO users (id, name, email, password, created, modified, last_login, token, isActive)
VALUES (
           '11111111-1111-1111-1111-111111111111',
           'User 1',
           'user1@email.com',
           'password1',
           '2023-01-01 12:00:00',
           '2023-01-01 12:00:00',
           '2023-01-01 12:00:00',
           'token_user_1',
           true
       );

INSERT INTO phone_numbers (id, number, city_code, country_code)
VALUES (
           '22222222-2222-2222-2222-222222222222',
           '123456789',
           '123',
           '456'
       );

INSERT INTO user_phone (user_id, phone_id)
VALUES (
           '11111111-1111-1111-1111-111111111111',
           '22222222-2222-2222-2222-222222222222'
       );

INSERT INTO users (id, name, email, password, created, modified, last_login, token, isActive)
VALUES (
           '33333333-3333-3333-3333-333333333333',
           'User 2',
           'user2@email.com',
           'password2',
           '2023-02-01 12:00:00',
           '2023-02-01 12:00:00',
           '2023-02-01 12:00:00',
           'token_user_2',
           true
       );

INSERT INTO phone_numbers (id, number, city_code, country_code)
VALUES (
           '44444444-4444-4444-4444-444444444444',
           '987654321',
           '987',
           '654'
       );

INSERT INTO user_phone (user_id, phone_id)
VALUES (
           '33333333-3333-3333-3333-333333333333',
           '44444444-4444-4444-4444-444444444444'
       );

INSERT INTO users (id, name, email, password, created, modified, last_login, token, isActive)
VALUES (
           '55555555-5555-5555-5555-555555555555',
           'User 3',
           'user3@email.com',
           'password3',
           '2023-03-01 12:00:00',
           '2023-03-01 12:00:00',
           '2023-03-01 12:00:00',
           'token_user_3',
           true
       );

INSERT INTO phone_numbers (id, number, city_code, country_code)
VALUES (
           '66666666-6666-6666-6666-666666666666',
           '987654321',
           '543',
           '216'
       );

INSERT INTO user_phone (user_id, phone_id)
VALUES (
           '55555555-5555-5555-5555-555555555555',
           '66666666-6666-6666-6666-666666666666'
       );
