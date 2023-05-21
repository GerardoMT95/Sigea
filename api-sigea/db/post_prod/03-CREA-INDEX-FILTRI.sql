CREATE INDEX pubbl_evento_id_idx ON sigec.t_SIGEA_PUBBLICAZIONI (EVENTO_ID);
CREATE INDEX datigenerali_titolo_idx ON sigec.t_sigea_datiGenerali (titolo);
CREATE INDEX dettaglioutente_nome_idx ON sigec.t_sigea_dettaglioutente (nome);
CREATE INDEX location_evento_id_idx ON sigec.t_sigea_location (EVENTO_ID);

CREATE INDEX evento_evento_id_idx ON sigec.t_sigea_eventi (EVENTO_ID);
CREATE INDEX evento_tipo_idx ON sigec.t_sigea_eventi (tipo);
CREATE INDEX evento_fg_pubb_idx ON sigec.t_sigea_eventi (fg_pubblicato);
CREATE INDEX stato_descrizione_idx ON sigec.t_sigea_stati (descrizione);