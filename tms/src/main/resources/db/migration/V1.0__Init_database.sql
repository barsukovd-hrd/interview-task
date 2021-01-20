create table departments
(
    id      serial primary key,
    deleted boolean,
    name    varchar(255)
);

create table usr
(
    id            serial primary key,
    deleted       boolean,
    name          varchar(255),
    department_id integer references departments (id)
);

create table tasks
(
    id           serial primary key,
    deleted      boolean,
    theme        varchar(128),
    description  varchar(512),
    status       varchar(64),
    created_date timestamp,
    author_id    integer references usr (id),
    assignee_id  integer references usr (id)
);

create table comments
(
    id        serial primary key,
    deleted   boolean,
    text      varchar(512),
    author_id integer references usr (id),
    task_id   integer references tasks (id)
);

create table attachments
(
    id      serial primary key,
    deleted boolean,
    url     varchar(512),
    task_id integer references tasks (id)
);