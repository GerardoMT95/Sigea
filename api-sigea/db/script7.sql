INSERT INTO sigec.t_sigea_tipologie_mibact (tipologia_id,descrizione) VALUES
('1.2.1','Attività di formazione'),
('1.2.7','Attività didattica'),
('1.2.8','Attività sportiva'),
('1.2.9','Cerimonia di premiazione'),
('1.3.2','Commemorazione'),
('1.3.3','Concerto'),
('1.3.4','Concorso/Competizione'),
('1.3.5','Corsa'),
('1.3.6','Degustazione'),
('1.3.7','Festa/Serata'),
('1.3.8','Giornata aperta'),
('1.3.9','Mercato/Mercatino'),
('2.4.3','Presentazione'),
('5.1.4','Processione'),
('4.1.4','Riunione di affari'),
('4.1.3','Torneo storico/Palio'),
('5.1.1','Tour');

UPDATE sigec.t_sigea_tipologie_mibact SET descrizione='Manifestazione musicale (Concerto di musica)' WHERE tipologia_id='1.1.5';