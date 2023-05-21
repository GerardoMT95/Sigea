INSERT INTO sigec.t_sigea_tipologie_utenti
(tipologia_id, descrizione, is_redattore)
VALUES('REDVALCAP-B2B', 'Caporedattore e Validatore di BuyPuglia', true);

INSERT INTO sigec.t_sigea_tipologia_ruolo
(tipologia_id, ruolo_id)
VALUES('REDVALCAP-B2B', 'VALIDATORE');

INSERT INTO sigec.t_sigea_tipologia_ruolo
(tipologia_id, ruolo_id)
VALUES('REDVALCAP-B2B', 'CAPOREDATTORE');

INSERT INTO sigec.t_sigea_redazioni
(redazione_id, nome_redazione, link_redazione, nome_jsp, link_scheda, auto_spubblicazione, attivazione_jms)
VALUES('B2B', null, 'www.dms.puglia.it', 'b2bScheda', null, true, false);

INSERT INTO sigec.t_sigea_redazioni
(redazione_id, nome_redazione, link_redazione, nome_jsp, link_scheda, auto_spubblicazione, attivazione_jms)
VALUES('B2BDMS', null, 'www.dms.puglia.it', 'b2bdmsScheda', null, true, false);

INSERT INTO sigec.t_sigea_tipologia_redazione
(tipologia_id, redazione_id)
VALUES('REDVALCAP-B2B', 'B2B');

INSERT INTO sigec.t_sigea_tipologia_redazione
(tipologia_id, redazione_id)
VALUES('REDVALCAP-B2B', 'B2BDMS');

UPDATE sigec.t_sigea_ruoli
SET permessi='{"creaEvento": true, "filtroGestisci": true, "filtroRedattore": true, "pubblicazioneB2B": true, "pubblicazioneVIP": true, "pubblicazioneB2BDMS": true, "filtroEventoSeeAll": true, "filtroSegnalazioneSeeAll": true}'
WHERE ruolo_id='CAPOREDATTORE';