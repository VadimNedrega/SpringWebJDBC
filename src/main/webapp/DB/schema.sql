DROP DATABASE K_PAC_EXAMPLE;
CREATE DATABASE  K_PAC_EXAMPLE;
USE K_PAC_EXAMPLE;

CREATE TABLE K_PAC_Set (
                           id integer NOT NULL AUTO_INCREMENT,
                           Title varchar(250) DEFAULT NULL,
                           K_PAC_ID integer DEFAULT NULL,
                           PRIMARY KEY (id)
);

CREATE TABLE K_PAC (
                       id integer NOT NULL AUTO_INCREMENT,
                       Title varchar(250) DEFAULT NULL,
                       Description varchar(2000) DEFAULT NULL,
                       Creation_date date DEFAULT NULL,
                       K_PAC_SET_ID integer DEFAULT NULL,
                       CONSTRAINT fk_person FOREIGN KEY (K_PAC_SET_ID)  REFERENCES K_PAC_Set(ID) on delete cascade,
                       PRIMARY KEY (id)
);

insert into k_pac_example.k_pac_set (id, Title, K_PAC_ID) values (1, 'new Title 1', 1);

insert into k_pac_example.k_pac (id, Title, Description, Creation_date, K_PAC_SET_ID) values (1, 'new Title1', 'Default Description', '23.08.22', 1);

alter table k_pac_set
    add constraint k_pac_set___fk
        foreign key (K_PAC_ID) references K_PAC (id) on delete cascade;