CREATE TABLE public.prato_carrinho (
	id int8 NOT NULL,
	usuario varchar(255) NULL,
	prato_id int8 NULL,
	CONSTRAINT prato_carrinho_pkey PRIMARY KEY (id)
);

ALTER TABLE public.prato_carrinho ADD CONSTRAINT fk_prato_carrinho_prato_id FOREIGN KEY (prato_id) REFERENCES public.prato(id);