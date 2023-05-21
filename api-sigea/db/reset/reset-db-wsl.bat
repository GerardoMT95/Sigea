#commentare exit per avviare il reset del db
set namespace=dev-dms-storage
set pod=spoteasy-pgsql-7ffb67b865-l8fpw
set container=postgres
set currentDir="%~dp0"

cd %currentDir%

 kubectl -n %namespace% exec -it %pod% -c %container% -- bash -c "rm -r /script-sigea"
 kubectl -n %namespace% exec -it %pod% -c %container% -- bash -c "mkdir /script-sigea"

 kubectl -n %namespace% cp ./00_cleanData.sql %pod%:/script-sigea/00_cleanData.sql -c %container%
 kubectl -n %namespace% cp ./01_resetSequences.sql %pod%:/script-sigea/01_resetSequences.sql -c %container%
 kubectl -n %namespace% cp ../script2.sql %pod%:/script-sigea/script2.sql -c %container%
 kubectl -n %namespace% cp ../script3.sql %pod%:/script-sigea/script3.sql -c %container%
 kubectl -n %namespace% cp ../script4.sql %pod%:/script-sigea/script4.sql -c %container%
 kubectl -n %namespace% cp ../script5.sql %pod%:/script-sigea/script5.sql -c %container%
 kubectl -n %namespace% cp ../script6.sql %pod%:/script-sigea/script6.sql -c %container%
 kubectl -n %namespace% cp ../script7.sql %pod%:/script-sigea/script7.sql -c %container%
 kubectl -n %namespace% cp ../script9_update_t_sigea_stati.sql %pod%:/script-sigea/script9_update_t_sigea_stati.sql -c %container%
 kubectl -n %namespace% cp ../script11_emailrifiutatomodifica.sql %pod%:/script-sigea/script11_emailrifiutatomodifica.sql -c %container%
 kubectl -n %namespace% cp ../script13_insert_statisql.sql %pod%:/script-sigea/script13_insert_statisql.sql -c %container%
 kubectl -n %namespace% cp ../script19_update_t_sigea_stati_anyUser.sql %pod%:/script-sigea/script19_update_t_sigea_stati_anyUser.sql -c %container%
 kubectl -n %namespace% cp ../script20_update_t_sigea_stati_nomePulsanti.sql %pod%:/script-sigea/script20_update_t_sigea_stati_nomePulsanti.sql -c %container%
 kubectl -n %namespace% cp ../script23_validazione_to_lavorazione_for_owner.sql %pod%:/script-sigea/script23_validazione_to_lavorazione_for_owner.sql -c %container%
 kubectl -n %namespace% cp ../migrazione_eventi/sigea_2_insert_sigea_rassegne_evento_mapping_migra2.sql %pod%:/script-sigea/sigea_2_insert_sigea_rassegne_evento_mapping_migra2.sql -c %container%
 kubectl -n %namespace% cp ../migrazione_eventi/sigea_1_insert_commit_dettaglio_utente_attivita.sql %pod%:/script-sigea/sigea_1_insert_commit_dettaglio_utente_attivita.sql -c %container%

 kubectl -n %namespace% exec -it %pod% -c %container% -- bash -c "psql -U admin -f /script-sigea/00_cleanData.sql"
 kubectl -n %namespace% exec -it %pod% -c %container% -- bash -c "psql -U admin -f /script-sigea/01_resetSequences.sql"
 kubectl -n %namespace% exec -it %pod% -c %container% -- bash -c "psql -U admin -f /script-sigea/script2.sql"
 kubectl -n %namespace% exec -it %pod% -c %container% -- bash -c "psql -U admin -f /script-sigea/script3.sql"
 kubectl -n %namespace% exec -it %pod% -c %container% -- bash -c "psql -U admin -f /script-sigea/script4.sql"
 kubectl -n %namespace% exec -it %pod% -c %container% -- bash -c "psql -U admin -f /script-sigea/script5.sql"
 kubectl -n %namespace% exec -it %pod% -c %container% -- bash -c "psql -U admin -f /script-sigea/script6.sql"
 kubectl -n %namespace% exec -it %pod% -c %container% -- bash -c "psql -U admin -f /script-sigea/script7.sql"
 kubectl -n %namespace% exec -it %pod% -c %container% -- bash -c "psql -U admin -f /script-sigea/script9_update_t_sigea_stati.sql"
 kubectl -n %namespace% exec -it %pod% -c %container% -- bash -c "psql -U admin -f /script-sigea/script11_emailrifiutatomodifica.sql"
 kubectl -n %namespace% exec -it %pod% -c %container% -- bash -c "psql -U admin -f /script-sigea/script13_insert_statisql.sql"
 kubectl -n %namespace% exec -it %pod% -c %container% -- bash -c "psql -U admin -f /script-sigea/script19_update_t_sigea_stati_anyUser.sql"
 kubectl -n %namespace% exec -it %pod% -c %container% -- bash -c "psql -U admin -f /script-sigea/script20_update_t_sigea_stati_nomePulsanti.sql"
 kubectl -n %namespace% exec -it %pod% -c %container% -- bash -c "psql -U admin -f /script-sigea/script23_validazione_to_lavorazione_for_owner.sql"
 kubectl -n %namespace% exec -it %pod% -c %container% -- bash -c "psql -U admin -f /script-sigea/sigea_2_insert_sigea_rassegne_evento_mapping_migra2.sql"
 kubectl -n %namespace% exec -it %pod% -c %container% -- bash -c "psql -U admin -f /script-sigea/sigea_1_insert_commit_dettaglio_utente_attivita.sql"

 kubectl -n %namespace% exec -it %pod% -c %container% -- bash -c "rm -r script-sigea"


Pause