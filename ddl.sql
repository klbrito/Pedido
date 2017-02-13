CREATE TABLE almoco.cardapio
(
  id_cardapio integer NOT NULL,
  dt_cardapio date NOT NULL,
  CONSTRAINT pk_cardapio PRIMARY KEY (id_cardapio)
) 
WITHOUT OIDS;
ALTER TABLE almoco.cardapio OWNER TO postgres;

CREATE UNIQUE INDEX ix_cardapio1
  ON almoco.cardapio
  USING btree
  (dt_cardapio);

CREATE TABLE almoco.cardapio_item
(
  id_cardapio_item integer NOT NULL DEFAULT nextval('cardapio_item_id_cardapio_item_seq'::regclass),
  id_cardapio integer NOT NULL,
  tx_cardapio_item character varying(64) NOT NULL,
  CONSTRAINT pk_cardapio_item PRIMARY KEY (id_cardapio_item),
  CONSTRAINT fk_cardapio_item_cardapio FOREIGN KEY (id_cardapio)
      REFERENCES almoco.cardapio (id_cardapio) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITHOUT OIDS;
ALTER TABLE almoco.cardapio_item OWNER TO postgres;


CREATE TABLE almoco.pedido
(
  id_pedido integer NOT NULL,
  dt_pedido date NOT NULL,
  tx_observacao character varying(256),
  CONSTRAINT pk_pedido PRIMARY KEY (id_pedido)
) 
WITHOUT OIDS;
ALTER TABLE almoco.pedido OWNER TO postgres;


CREATE TABLE almoco.pedido_item
(
  id_pedido integer NOT NULL,
  id_cardapio_item integer NOT NULL,
  CONSTRAINT pk_pedido_item PRIMARY KEY (id_pedido, id_cardapio_item),
  CONSTRAINT fk_pedido_item_cardapio_item FOREIGN KEY (id_cardapio_item)
      REFERENCES almoco.cardapio_item (id_cardapio_item) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_pedido_item_pedido FOREIGN KEY (id_pedido)
      REFERENCES almoco.pedido (id_pedido) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITHOUT OIDS;
ALTER TABLE almoco.pedido_item OWNER TO postgres;