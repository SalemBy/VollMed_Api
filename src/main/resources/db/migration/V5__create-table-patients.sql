create table patients
(

    id           BIGINT       NOT NULL AUTO_INCREMENT,
    name         VARCHAR(100) NOT NULL,
    email        VARCHAR(100) NOT NULL UNIQUE,
    cpf          VARCHAR(14)  NOT NULL UNIQUE,
    street       VARCHAR(100) NOT NULL,
    neighborhood VARCHAR(100) NOT NULL,
    ZIP          VARCHAR(9)   NOT NULL,
    complement   VARCHAR(100),
    number       VARCHAR(20),
    state        CHAR(2)      NOT NULL,
    city         VARCHAR(100) NOT NULL,
    phone        VARCHAR(20)  NOT NULL,
    active       TINYINT(1)     NOT NULL,

    PRIMARY KEY (id)

);