create table cuisine
(
    id   integer default nextval('cuisine_id_seq'::regclass) not null
        primary key,
    name varchar(100)
);

alter table cuisine
    owner to postgresql;

create table diet
(
    id   integer default nextval('diet_id_seq'::regclass) not null
        primary key,
    name varchar(100)
);

alter table diet
    owner to postgresql;

create table ingredient
(
    id                  integer default nextval('ingredient_id_seq'::regclass) not null
        primary key,
    expensive_index     integer,
    name                varchar(100),
    picture_url         varchar(255),
    ingredient_category smallint
        constraint ingredient_ingredient_category_check
            check ((ingredient_category >= 0) AND (ingredient_category <= 13))
);

alter table ingredient
    owner to postgresql;

create table recipe
(
    id            integer default nextval('recipe_id_seq'::regclass) not null
        primary key,
    cooking_level varchar(255)
        constraint recipe_cooking_level_check
            check ((cooking_level)::text = ANY
        ((ARRAY ['BEGINNER'::character varying, 'INTERMEDIATE'::character varying, 'ADVANCED'::character varying])::text[])),
    favourites    integer,
    instructions  varchar(2000),
    kcals         integer,
    name          varchar(100),
    picture_url   varchar(255),
    prep_time     integer,
    cuisine_id    integer
        constraint fkdnb8kle6xjuko0df4jfb6tc7h
            references cuisine,
    description   varchar(255)
);

alter table recipe
    owner to postgresql;

create table recipe_ingredient
(
    quantity        double precision,
    unit_of_measure varchar(255)
        constraint recipe_ingredient_unit_of_measure_check
            check ((unit_of_measure)::text = ANY
        ((ARRAY ['KG'::character varying, 'G'::character varying, 'L'::character varying, 'ML'::character varying, 'PIECE'::character varying])::text[])),
    ingredient_id   integer not null
        constraint fk9b3oxoskt0chwqxge0cnlkc29
            references ingredient
            on delete cascade,
    recipe_id       integer not null
        constraint fkgu1oxq7mbcgkx5dah6o8geirh
            references recipe
            on delete cascade,
    primary key (ingredient_id, recipe_id)
);

alter table recipe_ingredient
    owner to postgresql;

create table "user"
(
    id                       integer default nextval('user_id_seq'::regclass) not null
        primary key,
    age                      integer,
    bms                      double precision,
    cooking_level            varchar(255)
        constraint user_cooking_level_check
            check ((cooking_level)::text = ANY
        ((ARRAY ['BEGINNER'::character varying, 'INTERMEDIATE'::character varying, 'ADVANCED'::character varying])::text[])),
    email                    varchar(100)                                     not null
        constraint uk_ob8kqyqqgmefl0aco34akdtpe
            unique,
    first_name               varchar(35)                                      not null,
    gender                   varchar(255)
        constraint user_gender_check
            check ((gender)::text = ANY ((ARRAY ['M'::character varying, 'F'::character varying])::text[])),
    height                   integer,
    last_name                varchar(35)                                      not null,
    physical_effort          varchar(255)
        constraint user_physical_effort_check
            check ((physical_effort)::text = ANY
                   ((ARRAY ['SEDENTARY'::character varying, 'SLIGHTLY_ACTIVE'::character varying, 'MODERATELY_ACTIVE'::character varying, 'VERY_ACTIVE'::character varying, 'SUPER_ACTIVE'::character varying])::text[])),
    want_to_learn_new_skills boolean                                          not null,
    want_to_save_money       boolean                                          not null,
    want_to_save_time        boolean                                          not null,
    want_to_try_new_cuisines boolean                                          not null,
    weight                   integer,
    want_to_eat_healthy      boolean
);

alter table "user"
    owner to postgresql;

create table user_recommendation
(
    id                  integer default nextval('user_recommendation_id_seq'::regclass) not null
        primary key,
    date_time           timestamp(6),
    breakfast_recipe_id integer
        constraint fk1joepnfab4ntiu8vtjgsq3fmr
            references recipe
            on delete cascade,
    dinner_recipe_id    integer
        constraint fk98i19b978x4g501smf6wak2gq
            references recipe,
    lunch_recipe_id     integer
        constraint fk2chnl0gxqy1tr4m6oinmavxb7
            references recipe
            on delete cascade,
    user_id             integer
        constraint fkrs534a1t9mqluofcvu5ph3g5o
            references "user"
            on delete cascade
);

alter table user_recommendation
    owner to postgresql;

create table recipe_diet
(
    recipe_id integer not null
        constraint fkaxu8ph3ce8389uv7brfichtuf
            references recipe,
    diet_id   integer not null
        constraint fk5a22ndjfcj9pm4vfe8m2w5tl3
            references diet
);

alter table recipe_diet
    owner to postgresql;

create table user_cuisine
(
    user_id    integer not null
        constraint fk2bwijck82b12pm1trbchmg3m6
            references "user",
    cuisine_id integer not null
        constraint fkryp41nvg2e6ttc6n6bdhjkxbf
            references cuisine
);

alter table user_cuisine
    owner to postgresql;

create table user_diet
(
    user_id integer not null
        constraint fkrtx5tg5uwjdbvmt0ruttd120q
            references "user",
    diet_id integer not null
        constraint fkrdxp6wbdbxbuk1np67iq2fjlo
            references diet
);

alter table user_diet
    owner to postgresql;

create table user_disliked_ingredient
(
    user_id       integer not null
        constraint fkp7xpl2jgwdcighg9ba2hhfl9c
            references "user",
    ingredient_id integer not null
        constraint fkhggdraiybtufoqhc7e70slva2
            references ingredient
);

alter table user_disliked_ingredient
    owner to postgresql;

create table user_ingredient
(
    id              integer default nextval('user_ingredient_id_seq'::regclass) not null
        primary key,
    is_cart         boolean                                                     not null,
    quantity        double precision,
    unit_of_measure varchar(255)
        constraint user_ingredient_unit_of_measure_check
            check ((unit_of_measure)::text = ANY
        ((ARRAY ['KG'::character varying, 'G'::character varying, 'L'::character varying, 'ML'::character varying, 'PIECE'::character varying])::text[])),
    ingredient_id   integer
        constraint fkn11dc3tqlht2bd2mj89l0r9dk
            references ingredient
            on delete cascade,
    user_id         integer
        constraint fkbo1ktc1h5w3bo2geh5fpan9vl
            references "user"
            on delete cascade
);

alter table user_ingredient
    owner to postgresql;

create table ingredient_image
(
    id           varchar(255) default nextval('ingredient_image_id_seq'::regclass) not null
        primary key,
    name         varchar(255),
    content_type varchar(255),
    data         oid,
    size         bigint
);

alter table ingredient_image
    owner to postgresql;

create table recipe_instruction
(
    id        serial
        primary key,
    details   varchar(255),
    step      integer,
    recipe_id integer not null
        constraint fkfnfnvujej5d5udq02ce15m0pl
            references recipe
            on delete cascade
);

alter table recipe_instruction
    owner to postgresql;

