-- Auto-generated SQL script #202104261048
UPDATE sigec.t_sigea_stati
	SET nome='MODIFICA'
	WHERE stato_id='MODIFICA';
UPDATE sigec.t_sigea_stati
	SET nome='ANNULLA EVENTO'
	WHERE stato_id='ANNULLATO';
UPDATE sigec.t_sigea_stati
	SET nome='COMPILA'
	WHERE stato_id='LAVORAZIONE';
UPDATE sigec.t_sigea_stati
	SET nome='RIFIUTA'
	WHERE stato_id='RESPINTO';
-- Auto-generated SQL script #202104261239
UPDATE sigec.t_sigea_stati
	SET descrizione='In attesa di valutazione'
	WHERE stato_id='VALUTAZIONE';
UPDATE sigec.t_sigea_stati
	SET descrizione='In valutazione'
	WHERE stato_id='LAVORAZIONE';
-- Auto-generated SQL script #202104301707
UPDATE sigec.t_sigea_stati
	SET descrizione='Rifiutato'
	WHERE stato_id='RESPINTO';


