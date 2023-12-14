CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       name VARCHAR(255),
                       email VARCHAR(255),
                       password VARCHAR(255),
                       created TIMESTAMP,
                       modified TIMESTAMP,
                       last_login TIMESTAMP,
                       token VARCHAR(255),
                       isActive BOOLEAN
);

CREATE TABLE phone_numbers (
                              id UUID PRIMARY KEY,
                              number VARCHAR(255),
                              city_code VARCHAR(255),
                              country_code VARCHAR(255)
);

CREATE TABLE user_phone (
                            user_id UUID,
                            phone_id UUID,
                            PRIMARY KEY (user_id, phone_id),
                            FOREIGN KEY (user_id) REFERENCES users(id),
                            FOREIGN KEY (phone_id) REFERENCES phone_numbers(id)
);