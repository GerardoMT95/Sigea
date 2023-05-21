update sigec.t_sigea_periodi set orario_chiusura = concat(data_a, ' 00:00') where orario_chiusura is not null and (position ( '00:00' in orario_chiusura)) > 0;
update sigec.t_sigea_periodi set orario_chiusura_pomeriggio =concat(data_a, ' 00:00') where orario_chiusura_pomeriggio is not null and (position ( '00:00' in orario_chiusura_pomeriggio)) > 0;
update sigec.t_sigea_periodi set orario_chiusura_mattina  = concat(data_a, ' 00:00') where orario_chiusura_mattina is not null and (position ( '00:00' in orario_chiusura_mattina)) > 0;
