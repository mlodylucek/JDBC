create table address
(
    addressId   int not null primary key,
    street      varchar(64),
    city        varchar(64),
    postalCode  varchar(64),
    countryCode varchar(64)
);

create table customer
(
    customerId int         not null primary key,
    firstName  varchar(64),
    lastName   varchar(64) not null,
    address_id int references address (addressId)
);

create table hardware
(
    hardwareId   int          not null primary key,
    model        varchar(64),
    serialNumber varchar(64),
    name         varchar(128) not null
);

create table payment
(
    paymentId     int            not null primary key,
    formOfPayment varchar(16),
    currency      varchar(3)     not null default 'PLN',
    amount        decimal(15, 2) not null
);

create table orders
(
    orderId          int       not null primary key,
    createdDate      timestamp not null,
    customerId       int       not null references customer (customerId),
    issueDescription text,
    orderStatus      varchar(16),
    is_paid          boolean   not null default false,
    is_refunded      boolean   not null default false,
    paymentId        int references payment (paymentId)
);
