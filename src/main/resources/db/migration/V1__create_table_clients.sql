CREATE TABLE CLIENTS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    phone CHAR(11) NOT NULL,
    UNIQUE KEY UK_EMAIL (email),
    UNIQUE KEY UK_PHONE (phone)
);