ALTER TABLE sigec.t_sigea_relazioni ADD redazione_id varchar NULL;
ALTER TABLE sigec.t_sigea_relazioni ADD CONSTRAINT t_sigea_relazioni_fk FOREIGN KEY (redazione_id) REFERENCES sigec.t_sigea_redazioni(redazione_id);
