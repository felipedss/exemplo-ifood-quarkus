CREATE TABLE public.prato (
	id int8 NOT NULL,
	nome varchar(255) NULL,
	preco numeric(19,2) NULL,
	restaurante_id int8 NULL,
	CONSTRAINT prato_pkey PRIMARY KEY (id)
);

ALTER TABLE public.prato ADD CONSTRAINT fk_prato_restaurante_id FOREIGN KEY (restaurante_id) REFERENCES public.restaurante(id);
