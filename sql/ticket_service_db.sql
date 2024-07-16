CREATE TYPE ticket_type AS ENUM('DAY', 'WEEK', 'MONTH', 'YEAR');

CREATE TABLE users
(
    id            SERIAL8     NOT NULL,
    username      VARCHAR(20) NOT NULL,
    creation_date DATE NOT NULL DEFAULT CURRENT_DATE,
    PRIMARY KEY (id)
);

CREATE TABLE ticket
(
    id            SERIAL8     NOT NULL,
    user_id       int8        NOT NULL,
    ticket_type ticket_type NOT NULL ,
    creation_date DATE NOT NULL DEFAULT CURRENT_DATE,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) references users (id)
);

INSERT INTO users (username, creation_date)
VALUES('Alex', '2024-02-07'),
      ('Mary', '2024-02-06'),
      ('Sam', '2024-02-10');

INSERT INTO ticket(user_id,  ticket_type, creation_date)
VALUES(1, 'DAY', '2024-02-01'),
      (2, 'WEEK', '2024-02-02'),
      (3, 'DAY', '2024-01-28');


