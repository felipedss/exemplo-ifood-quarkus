-- public.restaurante definition

-- Drop table

-- DROP TABLE public.restaurante;

CREATE TABLE public.restaurante (
	id int8 NOT NULL,
	cnpj varchar(255) NULL,
	dataatualizacao date NULL,
	datacriacao date NULL,
	nome varchar(255) NULL,
	proprietario varchar(255) NULL,
	CONSTRAINT restaurante_pkey PRIMARY KEY (id)
);