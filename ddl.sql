CREATE TABLE cardapio
(
  id_cardapio serial NOT NULL,
  dt_cardapio date NOT NULL,
  CONSTRAINT pk_cardapio PRIMARY KEY (id_cardapio)
) 
WITHOUT OIDS;
ALTER TABLE cardapio OWNER TO postgres;

CREATE UNIQUE INDEX ix_cardapio1
  ON cardapio
  USING btree
  (dt_cardapio);

CREATE TABLE cardapio_item
(
  id_cardapio_item serial NOT NULL,
  id_cardapio integer NOT NULL,
  tx_cardapio_item character varying(64) NOT NULL,
  CONSTRAINT pk_cardapio_item PRIMARY KEY (id_cardapio_item),
  CONSTRAINT fk_cardapio_item_cardapio FOREIGN KEY (id_cardapio)
      REFERENCES cardapio (id_cardapio) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITHOUT OIDS;
ALTER TABLE cardapio_item OWNER TO postgres;


CREATE TABLE pedido
(
  id_pedido serial NOT NULL,
  dt_pedido date NOT NULL,
  tx_observacao character varying(256),
  CONSTRAINT pk_pedido PRIMARY KEY (id_pedido)
) 
WITHOUT OIDS;
ALTER TABLE pedido OWNER TO postgres;


CREATE TABLE pedido_item
(
  id_pedido serial NOT NULL,
  id_cardapio_item integer NOT NULL,
  CONSTRAINT pk_pedido_item PRIMARY KEY (id_pedido, id_cardapio_item),
  CONSTRAINT fk_pedido_item_cardapio_item FOREIGN KEY (id_cardapio_item)
      REFERENCES cardapio_item (id_cardapio_item) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_pedido_item_pedido FOREIGN KEY (id_pedido)
      REFERENCES pedido (id_pedido) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITHOUT OIDS;
ALTER TABLE pedido_item OWNER TO postgres;