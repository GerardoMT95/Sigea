do
$$
declare
    f record;
begin
    for f in 
    select pubblicazione_id 
	       from sigec.t_sigea_pubblicazioni tsp
	       where redazione_id <> 'GENERAL' 
    loop
     INSERT INTO sigec.t_sigea_log_pubblicazioni
(id_log_pubblicazioni ,codice_stato, pubblicazione_id, data_modifica)
VALUES(NEXTVAL('SIGEC.SIGEA_LOGPUBBLICAZIONI_SEQ') ,   'IN_COMPILAZIONE', f.pubblicazione_id, current_timestamp);
    end loop;
end;
$$