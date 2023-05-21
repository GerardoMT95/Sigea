-- Solo Caporedattore BuyPuglia - REDCAP-B2B
INSERT INTO sigec.t_sigea_tipologie_utenti
(tipologia_id, descrizione, is_redattore)
VALUES('REDCAP-B2B', 'Caporedattore di BuyPuglia', true);

INSERT INTO sigec.t_sigea_tipologia_ruolo
(tipologia_id, ruolo_id)
VALUES('REDCAP-B2B', 'CAPOREDATTORE');

INSERT INTO sigec.t_sigea_tipologia_redazione
(tipologia_id, redazione_id)
VALUES('REDCAP-B2B', 'B2B');

-- Solo Caporedattore DMS - REDCAP-DMS
INSERT INTO sigec.t_sigea_tipologie_utenti
(tipologia_id, descrizione, is_redattore)
VALUES('REDCAP-DMS', 'Caporedattore del DMS', true);

INSERT INTO sigec.t_sigea_tipologia_ruolo
(tipologia_id, ruolo_id)
VALUES('REDCAP-DMS', 'CAPOREDATTORE');

INSERT INTO sigec.t_sigea_tipologia_redazione
(tipologia_id, redazione_id)
VALUES('REDCAP-DMS', 'B2BDMS');

-- Caporedattore VIP + DMS - REDCAP-VIPDMS
INSERT INTO sigec.t_sigea_tipologie_utenti
(tipologia_id, descrizione, is_redattore)
VALUES('REDCAP-VIPDMS', 'Caporedattore di Viaggare in Puglia e DMS', true);

INSERT INTO sigec.t_sigea_tipologia_ruolo
(tipologia_id, ruolo_id)
VALUES('REDCAP-VIPDMS', 'CAPOREDATTORE');

INSERT INTO sigec.t_sigea_tipologia_redazione
(tipologia_id, redazione_id)
VALUES('REDCAP-VIPDMS', 'VIP');

INSERT INTO sigec.t_sigea_tipologia_redazione
(tipologia_id, redazione_id)
VALUES('REDCAP-VIPDMS', 'B2BDMS');

-- Caporedattore DMS + B2B - REDCAP-B2BDMS
INSERT INTO sigec.t_sigea_tipologie_utenti
(tipologia_id, descrizione, is_redattore)
VALUES('REDCAP-B2BDMS', 'Caporedattore di BuyPuglia e DMS', true);

INSERT INTO sigec.t_sigea_tipologia_ruolo
(tipologia_id, ruolo_id)
VALUES('REDCAP-B2BDMS', 'CAPOREDATTORE');

INSERT INTO sigec.t_sigea_tipologia_redazione
(tipologia_id, redazione_id)
VALUES('REDCAP-B2BDMS', 'B2B');

INSERT INTO sigec.t_sigea_tipologia_redazione
(tipologia_id, redazione_id)
VALUES('REDCAP-B2BDMS', 'B2BDMS');

-- Caporedattore VIP + DMS + B2B - REDCAP-VIPB2BDMS
INSERT INTO sigec.t_sigea_tipologie_utenti
(tipologia_id, descrizione, is_redattore)
VALUES('REDCAP-VIPB2BDMS', 'Caporedattore di Viaggiare in Puglia, BuyPuglia e DMS', true);

INSERT INTO sigec.t_sigea_tipologia_ruolo
(tipologia_id, ruolo_id)
VALUES('REDCAP-VIPB2BDMS', 'CAPOREDATTORE');

INSERT INTO sigec.t_sigea_tipologia_redazione
(tipologia_id, redazione_id)
VALUES('REDCAP-VIPB2BDMS', 'VIP');

INSERT INTO sigec.t_sigea_tipologia_redazione
(tipologia_id, redazione_id)
VALUES('REDCAP-VIPB2BDMS', 'B2B');

INSERT INTO sigec.t_sigea_tipologia_redazione
(tipologia_id, redazione_id)
VALUES('REDCAP-VIPB2BDMS', 'B2BDMS');

-- Validatore + Redattore VIP - REDVAL-VIP
INSERT INTO sigec.t_sigea_tipologie_utenti
(tipologia_id, descrizione, is_redattore)
VALUES('REDVAL-VIP', 'Redattore e Validatore di Viaggare in Puglia', true);

INSERT INTO sigec.t_sigea_tipologia_ruolo
(tipologia_id, ruolo_id)
VALUES('REDVAL-VIP', 'VALIDATORE');

INSERT INTO sigec.t_sigea_tipologia_ruolo
(tipologia_id, ruolo_id)
VALUES('REDVAL-VIP', 'REDATTORE');

INSERT INTO sigec.t_sigea_tipologia_redazione
(tipologia_id, redazione_id)
VALUES('REDVAL-VIP', 'VIP');

-- Validatore + Caporedattore BuyPuglia - REDVALCAP-B2B
DELETE FROM sigec.t_sigea_tipologia_redazione
WHERE tipologia_id='REDVALCAP-B2B' AND redazione_id='B2BDMS';

-- Validatore + Caporedattore DMS - REDVALCAP-DMS
INSERT INTO sigec.t_sigea_tipologie_utenti
(tipologia_id, descrizione, is_redattore)
VALUES('REDVALCAP-DMS', 'Caporedattore e Validatore del DMS', true);

INSERT INTO sigec.t_sigea_tipologia_ruolo
(tipologia_id, ruolo_id)
VALUES('REDVALCAP-DMS', 'VALIDATORE');

INSERT INTO sigec.t_sigea_tipologia_ruolo
(tipologia_id, ruolo_id)
VALUES('REDVALCAP-DMS', 'CAPOREDATTORE');

INSERT INTO sigec.t_sigea_tipologia_redazione
(tipologia_id, redazione_id)
VALUES('REDVALCAP-DMS', 'B2BDMS');

-- Validatore + Caporedattore VIP + Caporedattore DMS + Caporedattore BuyPuglia - REDVALCAP-VIPB2BDMS
INSERT INTO sigec.t_sigea_tipologie_utenti
(tipologia_id, descrizione, is_redattore)
VALUES('REDVALCAP-VIPB2BDMS', 'Caporedattore e Validatore di Viaggiare in Puglia, BuyPuglia e DMS', true);

INSERT INTO sigec.t_sigea_tipologia_ruolo
(tipologia_id, ruolo_id)
VALUES('REDVALCAP-VIPB2BDMS', 'VALIDATORE');

INSERT INTO sigec.t_sigea_tipologia_ruolo
(tipologia_id, ruolo_id)
VALUES('REDVALCAP-VIPB2BDMS', 'CAPOREDATTORE');

INSERT INTO sigec.t_sigea_tipologia_redazione
(tipologia_id, redazione_id)
VALUES('REDVALCAP-VIPB2BDMS', 'VIP');

INSERT INTO sigec.t_sigea_tipologia_redazione
(tipologia_id, redazione_id)
VALUES('REDVALCAP-VIPB2BDMS', 'B2B');

INSERT INTO sigec.t_sigea_tipologia_redazione
(tipologia_id, redazione_id)
VALUES('REDVALCAP-VIPB2BDMS', 'B2BDMS');

-- update stato in lavorazione per cambio lable pulsante
UPDATE sigec.t_sigea_stati SET nome='IN LAVORAZIONE' WHERE stato_id='LAVORAZIONE';