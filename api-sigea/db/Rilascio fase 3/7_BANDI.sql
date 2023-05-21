--DROP TABLE sigec.t_sigea_bandi;

CREATE TABLE sigec.t_sigea_bandi (
	bando_id varchar(255) NOT NULL,
	titolo_bando varchar(255) NOT NULL,
	CONSTRAINT t_sigea_bandi_pkey PRIMARY KEY (bando_id)
);



DROP TABLE sigec.t_sigea_progetti;

CREATE TABLE sigec.t_sigea_progetti (
	progetto_id varchar(255) NOT NULL,
	titolo_progetto varchar(255) NOT NULL,
	nome_organizzazione varchar(255) NULL,
	bando_id varchar(255) NOT NULL,
	CONSTRAINT t_sigea_progetti_pkey PRIMARY KEY (progetto_id),
	CONSTRAINT t_sigea_progetti_bandi_fk FOREIGN KEY (bando_id) REFERENCES sigec.t_sigea_bandi(bando_id)

);


ALTER TABLE sigec.t_sigea_eventi 
RENAME COLUMN titolo_progetto TO bando_id;

update sigec.t_sigea_eventi  
set bando_id=NULL,
	progetto_id=NULL 
where 1=1;



/*
ALTER TABLE sigec.t_sigea_eventi 
DROP CONSTRAINT  t_sigea_eventi_progetti_fk;

ALTER TABLE sigec.t_sigea_eventi 
DROP CONSTRAINT  t_sigea_eventi_bandi_fk;

update  sigec.t_sigea_pubblicazioni
set evento_pubblicato = (evento_pubblicato #- '{progettoId}');

update  sigec.t_sigea_pubblicazioni
set evento_pubblicato = (evento_pubblicato #- '{titoloProgetto}');
*/

ALTER TABLE sigec.t_sigea_eventi 
ADD CONSTRAINT t_sigea_eventi_progetti_fk 	FOREIGN KEY (progetto_id) REFERENCES  sigec.t_sigea_progetti(progetto_id);

ALTER TABLE sigec.t_sigea_eventi 
ADD CONSTRAINT t_sigea_eventi_bandi_fk 	FOREIGN KEY (bando_id) REFERENCES  sigec.t_sigea_bandi(bando_id);



