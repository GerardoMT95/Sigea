ALTER TABLE sigec.t_sigea_periodi ALTER COLUMN orario_apertura TYPE varchar(20) USING orario_apertura::varchar;
ALTER TABLE sigec.t_sigea_periodi ALTER COLUMN orario_apertura_mattina TYPE varchar(20) USING orario_apertura_mattina::varchar;
ALTER TABLE sigec.t_sigea_periodi ALTER COLUMN orario_apertura_pomeriggio TYPE varchar(20) USING orario_apertura_pomeriggio::varchar;
ALTER TABLE sigec.t_sigea_periodi ALTER COLUMN orario_chiusura TYPE varchar(20) USING orario_chiusura::varchar;
ALTER TABLE sigec.t_sigea_periodi ALTER COLUMN orario_chiusura_mattina TYPE varchar(20) USING orario_chiusura_mattina::varchar;
ALTER TABLE sigec.t_sigea_periodi ALTER COLUMN orario_chiusura_pomeriggio TYPE varchar(20) USING orario_chiusura_pomeriggio::varchar;
