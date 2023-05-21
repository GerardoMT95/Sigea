ALTER TABLE sigec.t_sigea_accessibilita 
DROP COLUMN flag_sito;



update  sigec.t_sigea_pubblicazioni
set evento_pubblicato = (evento_pubblicato #- '{accessibilita,flagSito}');
