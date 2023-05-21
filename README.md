# Informazioni su SIGEA
Repository Sistema di Gestione degli Eventi e delle Attività culturali

## Descrizione
Il repository contiene il codice sorgente dei componenti e le librerie utilizzati per realizzare il sistema per la Gestione degli Eventi e delle Attività culturali della Regione Puglia.

Il software è stato realizzato in JAVA sfruttando, principalmente, le seguenti tecnologie/framework:
* SpringMVC per la parte di Front-end
* SpringBoot per il backend e per lo strato di integrazione con altri applicativi, come ad esempio, il gestore dei bandi.

## Struttura del repository
Il repository si compone di un unico branch in cui soon presenti le seguenti cartelle principali:
* **documentazione** - contiene tutta la documentazione progettuale, (manuale di installazione, analisi, progettazione)
* **api-bandi** - contiene lo SpringBoot per recuperare i Bandi da un sistema regionale che li gestisce
* **api-sigea** - contiene lo SpringBoot per le API usate dal Front-end
* **bandicommons** - contiene i DTO utilizzati dai vari componenti
* **fe-sigea** - contiene lo SpringMVC per implementare il front-end
* **persistence-sigea** - contiene un progetto che contiene le classi e i repository utili per popolare il DB di Viaggiare in Puglia
* **sigeacommons** - contiene i model comuni ai vari componenti

## Prerequisiti
I principali prerequisiti sono legati alla presenza del DBMS Postgres, il message broker ActiveMQ e il web server Tomcat (per la parte di front-end).

Per maggiori dettagli si può fare riferimento alla documentazione tecnica che riporta tutte le specifiche


## Istruzioni per l’installazione

Fare riferimento al manuale presente nel folder **documentazione**

## Status del progetto
Stabile

## Amministrazione committente
Regione Puglia

## Soggetti incaricati del mantenimento del progetto open source
Vito Luigi Benedetto

## Indirizzo e-mail a cui inviare segnalazioni di sicurezza
v.benedetto@innova.puglia.it