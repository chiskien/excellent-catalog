create table genre
(
    id                 bigserial primary key not null,
    name               varchar(255)          not null,
    created_date       timestamp             not null,
    last_modified_date timestamp             not null,
    version            int                   not null
)