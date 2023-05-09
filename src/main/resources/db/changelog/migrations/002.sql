DROP table if exists sample;

CREATE TABLE IF NOT EXISTS sample
(
    id uuid NOT NULL primary key,
    date_and_time timestamp(6) without time zone,
    temperature numeric(38,2)
);