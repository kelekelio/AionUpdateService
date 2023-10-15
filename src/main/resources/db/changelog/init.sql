create table client_log
(
    id          bigserial
        primary key,
    aion_client bigint,
    log_type    varchar(255)
        constraint client_log_log_type_check
            check ((log_type)::text = ANY
        ((ARRAY ['INFO'::character varying, 'ERROR'::character varying, 'WARNING'::character varying])::text[])),
    message     varchar(255),
    timestamp   timestamp(6)
);

alter table client_log
    owner to postgres;
