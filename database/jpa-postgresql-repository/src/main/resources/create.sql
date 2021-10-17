-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION postgres;
-- public.customer definition

-- Drop table

-- DROP TABLE public.customer;

CREATE TABLE public.customer (
	id uuid NOT NULL,
	email varchar(255) NOT NULL,
	"name" varchar(255) NOT NULL,
	status varchar(255) NOT NULL,
	CONSTRAINT customer_pkey PRIMARY KEY (id),
	CONSTRAINT uk_dwk6cx0afu8bs9o4t536v1j5v UNIQUE (email)
);


-- public.product definition

-- Drop table

-- DROP TABLE public.product;

CREATE TABLE public.product (
	customer_id uuid NOT NULL,
	id uuid NOT NULL,
	brand varchar(255) NOT NULL,
	image varchar(255) NOT NULL,
	price numeric(19, 2) NOT NULL,
	review_score float8 NULL,
	title varchar(255) NOT NULL,
	CONSTRAINT product_pkey PRIMARY KEY (customer_id, id),
	CONSTRAINT fkj80n6400wnfqrt86qimf9k6ys FOREIGN KEY (customer_id) REFERENCES public.customer(id)
);
