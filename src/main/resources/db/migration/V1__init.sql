create table if not exists users (
  id bigserial primary key,
  name text not null,
  email text not null unique,
  password_hash text not null,
  contact text,
  address text,
  role text not null
);

create table if not exists products (
  id bigserial primary key,
  name text not null,
  description text,
  entry_time text,
  expiry_time text,
  min_bidding_amount int not null
);

create index if not exists idx_products_name on products(name);

