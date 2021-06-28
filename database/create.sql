create table customer
(
    id       serial primary key,
    name     varchar(20),
    surname  varchar(20),
    email    varchar(20) unique not null,
    password varchar(16)        not null
);

create table car
(
    id          serial primary key,
    brand       varchar(20) not null,
    model       varchar(20) not null,
    id_customer smallint references customer (id),
    constraint unique_brand_model unique (brand, model)
);

create table car_history_owner
(
    id          serial primary key,
    id_car      smallint references car (id),
    id_customer smallint references customer (id)
);

create table advertisement
(
    id          serial primary key,
    description text,
    price       int,
    created     timestamp not null,
    photo       varchar unique,
    sold        boolean default false,
    id_car      smallint references car (id),
    id_customer smallint  not null references customer (id),
    constraint unique_id_car_id_customer unique (id_car, id_customer)
);