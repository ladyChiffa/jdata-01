--liquibase formatted sql

--changeset author:ladyChiffa id:001
create table brands (
    id bigserial primary key,
    name varchar(255) not null
);
--rollback drop table brands;
