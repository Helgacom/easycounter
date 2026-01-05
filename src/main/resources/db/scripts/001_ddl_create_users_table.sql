create table users (
    id        bigserial primary key,
    name      varchar unique not null,
    login     varchar unique not null,
    password  varchar        not null
);