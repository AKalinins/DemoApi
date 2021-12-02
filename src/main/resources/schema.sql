CREATE TABLE users
(
    id   bigint PRIMARY KEY AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    type varchar(255) NOT NULL
);

CREATE TABLE contracts
(
    id         bigint PRIMARY KEY AUTO_INCREMENT,
    user_id    bigint       NOT NULL,
    type       varchar(255) NOT NULL,
    start_date date         NOT NULL,
    CONSTRAINT `FK_INSTRUCTOR`
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE NO ACTION ON UPDATE NO ACTION
);