create database job4j_cars;

create table advertisement(
    id serial primary key,
    description text,
    created timestamp not null,
    sold boolean default false,
    id_car smallint references car(id),
    id_customer smallint not null references customer(id),
    constraint unique_id_car_id_customer unique (id_car, id_customer));

create table car(
    id serial primary key,
    brand varchar(20) not null,
    model varchar(20) not null,
    constraint unique_brand_model unique (brand, model));

create table customer(
    id serial primary key,
    name varchar(20),
    surname varchar(20),
    email varchar(20) unique not null,
    password varchar(16) not null);

create table car_history_owner(
    id smallint primary key,
    id_car smallint references car(id),
    id_customer smallint references customer(id));

drop table advertisement;
drop table car;
drop table customer;
drop table car_history_owner;
