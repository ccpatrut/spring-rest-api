create schema if not exists blog;

create table if not exists blog.posts
(
    id            uuid primary key,
    title         varchar(255) not null,
    author         varchar(255) not null,
    content       text         not null,
    creation_time timestamp    not null,
    update_time   timestamp
);

create table if not exists blog.comments
(
    id            uuid primary key,
    title         varchar(255) not null,
    content       text         not null,
    approved      boolean default false,
    creation_time timestamp    not null,
    update_time   timestamp,
    post_id       uuid         not null references blog.posts
);
