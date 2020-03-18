create sequence hibernate_sequence start 1 increment 1;
create table cashbox
(
    id            int8    not null,
    address       varchar(255),
    date_create   timestamp,
    date_enter    timestamp,
    name_model    varchar(255),
    serial_number varchar(255),
    skno          boolean not null,
    client_id     int8,
    master_id     int8,
    primary key (id)
);
create table client
(
    id          int8    not null,
    contract    varchar(255),
    mail        varchar(255),
    name        varchar(255),
    nonresident boolean not null,
    vat         int4,
    primary key (id)
);
create table client_masters
(
    client_id int8 not null,
    master_id int8 not null
);
create table master
(
    id   int8 not null,
    name varchar(255),
    primary key (id)
);
create table user_roles
(
    user_id int8 not null,
    roles   varchar(255)
);
create table usr
(
    id       int8 not null,
    password varchar(255),
    username varchar(255),
    primary key (id)
);
alter table if exists cashbox
    add constraint FKq1775vohcohh3fvql05o3heok foreign key (client_id) references client;
alter table if exists cashbox
    add constraint FKitvp237rq63ejht6knwi1ah82 foreign key (master_id) references master;
alter table if exists client_masters
    add constraint FKt112pikhdepsvtgqo0svy910v foreign key (master_id) references master;
alter table if exists client_masters
    add constraint FKnxghbso6d0khhwr11e37dhxhm foreign key (client_id) references client;
alter table if exists user_roles
    add constraint FKg6agnwreityp2vf23bm2jgjm3 foreign key (user_id) references usr;