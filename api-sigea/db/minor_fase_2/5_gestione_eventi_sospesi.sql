
 
INSERT INTO sigec.t_sigea_stati_pubblicazioni (codice_stato) VALUES ('SOSPESO');

 
update sigec.t_sigea_pubblicazioni set stato_pubblicazione ='SOSPESO' where evento_id in (select evento_id from sigec.t_sigea_eventi tse  where evento_id in (select distinct(evento_id) from sigec.t_sigea_pubblicazioni tsp  where pubblicazione_id  in (select distinct (pubblicazione_id) from sigec.t_sigea_log_pubblicazioni tslp  where codice_stato ='IN_COMPILAZIONE') and redazione_id !='GENERAL') and stato_id not in ('VALIDATO', 'RIVALIDATO')) and redazione_id !='GENERAL'