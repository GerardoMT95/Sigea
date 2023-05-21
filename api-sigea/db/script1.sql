--
-- PostgreSQL database dump
--

-- Dumped from database version 11.4
-- Dumped by pg_dump version 11.4

-- Started on 2020-02-21 10:15:40

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 10 (class 2615 OID 56419)
-- Name: sigec; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA sigec;


--
-- TOC entry 228 (class 1259 OID 56579)
-- Name: lock_seq; Type: SEQUENCE; Schema: sigec; Owner: -
--

CREATE SEQUENCE sigec.lock_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 229 (class 1259 OID 56581)
-- Name: sigea_contatti_seq; Type: SEQUENCE; Schema: sigec; Owner: -
--

CREATE SEQUENCE sigec.sigea_contatti_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 229 (class 1259 OID 56581)
-- Name: sigea_media_seq; Type: SEQUENCE; Schema: sigec; Owner: -
--

CREATE SEQUENCE sigec.sigea_media_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 231 (class 1259 OID 56585)
-- Name: sigea_eventi_seq; Type: SEQUENCE; Schema: sigec; Owner: -
--

CREATE SEQUENCE sigec.sigea_eventi_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 233 (class 1259 OID 56589)
-- Name: sigea_link_seq; Type: SEQUENCE; Schema: sigec; Owner: -
--

CREATE SEQUENCE sigec.sigea_link_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 234 (class 1259 OID 56591)
-- Name: sigea_location_seq; Type: SEQUENCE; Schema: sigec; Owner: -
--

CREATE SEQUENCE sigec.sigea_location_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 235 (class 1259 OID 56593)
-- Name: sigea_logeventi_seq; Type: SEQUENCE; Schema: sigec; Owner: -
--

CREATE SEQUENCE sigec.sigea_logeventi_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 236 (class 1259 OID 56595)
-- Name: sigea_periodi_seq; Type: SEQUENCE; Schema: sigec; Owner: -
--

CREATE SEQUENCE sigec.sigea_periodi_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 237 (class 1259 OID 56597)
-- Name: sigea_pubblicazioni_seq; Type: SEQUENCE; Schema: sigec; Owner: -
--

CREATE SEQUENCE sigec.sigea_pubblicazioni_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 238 (class 1259 OID 56599)
-- Name: sigea_relazioni_seq; Type: SEQUENCE; Schema: sigec; Owner: -
--

CREATE SEQUENCE sigec.sigea_relazioni_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 239 (class 1259 OID 56601)
-- Name: sigea_segnalazioni_seq; Type: SEQUENCE; Schema: sigec; Owner: -
--

CREATE SEQUENCE sigec.sigea_segnalazioni_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 205 (class 1259 OID 56420)
-- Name: t_lock; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_lock (
    lock_id bigint NOT NULL,
    data_lock timestamp without time zone,
    item_id bigint,
    owner_id bigint,
    nome_tabella character varying(255)
);


--
-- TOC entry 206 (class 1259 OID 56425)
-- Name: t_sigea_accessibilita; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_accessibilita (
    evento_id bigint NOT NULL,
    flag_anziani boolean,
    flag_audioguide boolean,
    flag_audioguide_percorsi boolean,
    flag_biblioteca boolean,
    flag_cani boolean,
    flag_cani_medi boolean,
    flag_cani_piccoli boolean,
    flag_computer boolean,
    flag_disabilita_fisica boolean,
    flag_disabilita_uditiva boolean,
    flag_disabilita_visiva boolean,
    flag_famiglie_bambini boolean,
    flag_giardini boolean,
    flag_gravidanza boolean,
    flag_guide_braille boolean,
    flag_infopoint boolean,
    flag_ingressi boolean,
    flag_lis boolean,
    flag_ludoteca boolean,
    flag_materiale_leggibile boolean,
    flag_materiale_sottotitolato boolean,
    flag_nursey boolean,
    flag_parcheggio_disabili boolean,
    flag_parcogiochi boolean,
    flag_percorsi boolean,
    flag_percorsi_tattili boolean,
    flag_persone_animali boolean,
    flag_postazioni_audio boolean,
    flag_riproduzione_tattili boolean,
    flag_segnaletica_braille boolean,
    flag_segnaletica_leggibile boolean,
    flag_servizi_igienici boolean,
    flag_sito boolean
);


--
-- TOC entry 207 (class 1259 OID 56430)
-- Name: t_sigea_progetti; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_progetti (
    progetto_id character varying(255) NOT NULL,
    titolo_progetto character varying(255),
    partita_iva character varying(255)
);

--
-- TOC entry 207 (class 1259 OID 56430)
-- Name: t_sigea_attivita; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_attivita (
    attivita_id bigint NOT NULL,
    denominazione character varying(255)
);


--
-- TOC entry 208 (class 1259 OID 56435)
-- Name: t_sigea_attrattori; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_attrattori (
    attrattore_id bigint NOT NULL,
    etichetta character varying(300) NOT NULL
);


--
-- TOC entry 209 (class 1259 OID 56440)
-- Name: t_sigea_contatti; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_contatti (
    contatto_id bigint NOT NULL,
    contatto character varying(300),
    tipo character varying(20),
    evento_id bigint NOT NULL
);


--
-- TOC entry 210 (class 1259 OID 56445)
-- Name: t_sigea_datigenerali; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_datigenerali (
    evento_id bigint NOT NULL,
    abstract character varying(300),
    abstract_multi jsonb,
    descrizione character varying(4000),
    descrizione_multi jsonb,
    snippet character varying(300),
    snippet_multi jsonb,
    titolo character varying(300),
    titolo_multi jsonb
);


--
-- TOC entry 211 (class 1259 OID 56453)
-- Name: t_sigea_decodifica; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_decodifica (
    key character varying(255) NOT NULL,
    value character varying(255)
);


--
-- TOC entry 212 (class 1259 OID 56461)
-- Name: t_sigea_dettaglioutente; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_dettaglioutente (
    utente_id bigint NOT NULL,
    cod_fiscale character varying(20),
    cognome character varying(100),
    email character varying(100),
    tel character varying(255),
    cel character varying(255),
    nome character varying(100),
    username character varying(100),
    tipologia_id character varying(255)
);


--
-- TOC entry 213 (class 1259 OID 56469)
-- Name: t_sigea_documenti; Type: TABLE; Schema: sigec; Owner: -
--


CREATE TABLE sigec.t_sigea_documenti (
    documento_id bigint NOT NULL,
    dimensione bigint,
    estensione character varying(20),
    nome_originale character varying(300),
    path_to_copy character varying(255),
    titolo character varying(300),
    titolo_multi jsonb,
    evento_id bigint NOT NULL
);


--
-- TOC entry 214 (class 1259 OID 56477)
-- Name: t_sigea_eventi; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_eventi (
    evento_id bigint NOT NULL,
    progetto_id character varying(255),
    data_a_max date,
    data_da_min date,
    data_inserimento timestamp without time zone,
    data_inserimento_notime timestamp without time zone,
    fg_finanziato boolean,
    fg_pubblicato boolean,
    fg_validazione_pregressa boolean,
    fg_validazione_ultimo_giorno boolean,
    tipo character varying(100),
    id_mibact character varying(255),
    tipologia_mibact character varying(255),
    titolo_progetto character varying(255),
    grande_evento boolean,
    email_riconciliazione character varying(255),
    da_riconciliare boolean,
    ultime_note character varying(4000),
    attivita_id bigint,
    owner_id bigint NOT NULL,
    utente_ultima_modifica_id bigint,
    richiesta_id bigint,
    stato_id character varying(20)
);


--
-- TOC entry 215 (class 1259 OID 56485)
-- Name: t_sigea_immagini; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_immagini (
    immagine_id bigint NOT NULL,
    credits character varying(300),
    didascalia character varying(300),
    didascalia_multi jsonb,
    dimensione bigint,
    estensione character varying(20),
    nome_originale character varying(300),
    path_to_copy character varying(255),
    evento_id bigint NOT NULL
);


--
-- TOC entry 216 (class 1259 OID 56493)
-- Name: t_sigea_link; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_link (
    link_id bigint NOT NULL,
    credits character varying(300),
    didascalia character varying(300),
    didascalia_multi jsonb,
    link character varying(300),
    titolo character varying(300),
    titolo_multi jsonb,
    evento_id bigint NOT NULL
);


--
-- TOC entry 217 (class 1259 OID 56501)
-- Name: t_sigea_location; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_location (
    location_id bigint NOT NULL,
    area_territoriale character varying(300),
    cap character varying(5),
    cod_area character varying(255),
    cod_comune character varying(255),
    cod_nazione character varying(255),
    cod_provincia character varying(255),
    cod_regione character varying(255),
    comune character varying(300),
    comune_estero character varying(300),
    fg_principale boolean,
    indirizzo character varying(300),
    latitudine numeric(19,6),
    link character varying(300),
    longitudine numeric(19,6),
    nazione character varying(300),
    nome_location character varying(300),
    provincia character varying(300),
    fg_puglia boolean,
    regione character varying(300),
    evento_id bigint NOT NULL
);


--
-- TOC entry 218 (class 1259 OID 56509)
-- Name: t_sigea_location_attrattore; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_location_attrattore (
    location_id bigint NOT NULL,
    attrattore_id bigint NOT NULL
);


--
-- TOC entry 219 (class 1259 OID 56514)
-- Name: t_sigea_logeventi; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_logeventi (
    log_id bigint NOT NULL,
    data_modifica timestamp without time zone,
    descrizione_stato character varying(50),
    nome_utente character varying(100),
    note character varying(4000),
    operazioni character varying(4000),
    tipo_operazione character varying(100),
    evento_id bigint NOT NULL
);


--
-- TOC entry 220 (class 1259 OID 56522)
-- Name: t_sigea_periodi; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_periodi (
    periodo_id bigint NOT NULL,
    cadenza character varying(255),
    cadenza_domenica boolean,
    cadenza_giovedi boolean,
    cadenza_lunedi boolean,
    cadenza_martedi boolean,
    cadenza_mensile character varying(255),
    cadenza_mercoledi boolean,
    cadenza_sabato boolean,
    cadenza_venerdi boolean,
    chiusura_domenica boolean,
    chiusura_giovedi boolean,
    chiusura_lunedi boolean,
    chiusura_martedi boolean,
    chiusura_mercoledi boolean,
    chiusura_sabato boolean,
    chiusura_venerdi boolean,
    data_a date,
    data_da date,
    data_secca boolean,
    continuato boolean,
    orario_apertura character varying(10),
    orario_apertura_mattina character varying(10),
    orario_apertura_pomeriggio character varying(10),
    orario_chiusura character varying(10),
    orario_chiusura_mattina character varying(10),
    orario_chiusura_pomeriggio character varying(10),
    evento_id bigint NOT NULL
);


--
-- TOC entry 240 (class 1259 OID 57623)
-- Name: t_sigea_pubb_allegati; Type: TABLE; Schema: sigec; Owner: postgres
--

CREATE TABLE sigec.t_sigea_pubb_allegati (
    allegato_id bigint NOT NULL,
    dimensione bigint,
    estensione character varying(20),
    nome_originale character varying(300),
    pubblicazione_id bigint NOT NULL
);

--
-- TOC entry 221 (class 1259 OID 56530)
-- Name: t_sigea_pubblicazioni; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_pubblicazioni (
    pubblicazione_id bigint NOT NULL,
    data_pubblicazione timestamp without time zone,
    note_aggiuntive character varying(255),
    evento_pubblicato jsonb,
    generic_metadata_wip jsonb,
    generic_metadata jsonb,
    stato_pubblicazione character varying(255),
    evento_id bigint NOT NULL,
    redazione_id character varying(255) NOT NULL,
    redattore_id bigint NOT NULL
);

--
-- TOC entry 242 (class 1259 OID 57638)
-- Name: t_sigea_ruoli; Type: TABLE; Schema: sigec; Owner: postgres
--

CREATE TABLE sigec.t_sigea_ruoli (
    ruolo_id character varying(255) NOT NULL,
    permessi jsonb
);

--
-- TOC entry 243 (class 1259 OID 57651)
-- Name: t_sigea_tipologia_ruolo; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_tipologia_ruolo (
    tipologia_id character varying(255) NOT NULL,
    ruolo_id character varying(255) NOT NULL
);

--
-- TOC entry 242 (class 1259 OID 57638)
-- Name: t_sigea_redazioni; Type: TABLE; Schema: sigec; Owner: postgres
--

CREATE TABLE sigec.t_sigea_redazioni (
    redazione_id character varying(255) NOT NULL,
    nome_redazione character varying(255),
    link_redazione character varying(255),
    nome_jsp character varying(255),
    link_scheda character varying(255),
    auto_spubblicazione boolean,
    attivazione_jms boolean
);

--
-- TOC entry 243 (class 1259 OID 57651)
-- Name: t_sigea_tipologia_redazione; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_tipologia_redazione (
    tipologia_id character varying(255) NOT NULL,
    redazione_id character varying(255) NOT NULL
);

--
-- TOC entry 222 (class 1259 OID 56538)
-- Name: t_sigea_relazioni; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_relazioni (
    relazione_id bigint NOT NULL,
    evento_relazionato_id bigint,
    tipo_evento_relazionato character varying(255),
    tipo_relazione character varying(20),
    evento_id bigint NOT NULL
);


--
-- TOC entry 223 (class 1259 OID 56543)
-- Name: t_sigea_richiestaattivita; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_richiestaattivita (
    richiesta_id bigint NOT NULL,
    denominazione character varying(255)
);


--
-- TOC entry 224 (class 1259 OID 56548)
-- Name: t_sigea_segnalazioni; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_segnalazioni (
    segnalazione_id bigint NOT NULL,
    codistat character varying(6),
    comune character varying(300),
    data_a date NOT NULL,
    data_da date NOT NULL,
    data_inserimento timestamp without time zone,
    data_modifica timestamp without time zone,
    descrizione character varying(4000),
    indirizzo character varying(500),
    location character varying(500),
    nome character varying(100) NOT NULL,
    riferimento character varying(500) NOT NULL,
    status character varying(50) NOT NULL,
    idutente_inserimento bigint NOT NULL,
    idutente_modifica bigint
);


--
-- TOC entry 225 (class 1259 OID 56556)
-- Name: t_sigea_stati; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_stati (
    stato_id character varying(20) NOT NULL,
    descrizione character varying(50),
    stato_inizio boolean,
    stato_fine boolean,
    accesso_condizionato boolean,
    nome character varying(255),
    configurazione jsonb
);


--
-- TOC entry 226 (class 1259 OID 56561)
-- Name: t_sigea_tickets; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_tickets (
    evento_id bigint NOT NULL,
    convezioni_gratuite character varying(300),
    convenzioni_ridotte character varying(300),
    flag_gratis_accompagnatori boolean,
    flag_gratis_anziani boolean,
    flag_gratis_bambini boolean,
    flag_gratis_convenzioni boolean,
    flag_gratis_disabili boolean,
    flag_ridotto_accompagnatori boolean,
    flag_ridotto_anziani boolean,
    flag_ridotto_bambini boolean,
    flag_ridotto_convenzioni boolean,
    flag_ridotto_disabili boolean,
    link_prenotazioni character varying(300),
    nota character varying(300),
    nota_multi jsonb,
    ticket_gruppinumero bigint,
    ticket_gruppiridotto numeric(19,2),
    ticket_intero numeric(19,2),
    ticket_ridotto numeric(19,2),
    tipo_ticket character varying(50)
);


--
-- TOC entry 227 (class 1259 OID 56569)
-- Name: t_sigea_tipologie_utenti; Type: TABLE; Schema: sigec; Owner: -
--

CREATE TABLE sigec.t_sigea_tipologie_utenti (
    tipologia_id character varying(255) NOT NULL,
    descrizione character varying(255),
    is_redattore boolean
);

CREATE TABLE sigec.t_sigea_tipologie_mibact (
    tipologia_id character varying(255) NOT NULL,
    descrizione character varying(255)
);

CREATE TABLE sigec.t_sigea_tipologie_attivita (
    tipologia_id character varying(255) NOT NULL,
    descrizione character varying(255)
);

CREATE TABLE sigec.t_sigea_mezzi (
    mezzo_id character varying(255) NOT NULL,
    descrizione character varying(255)
);

CREATE TABLE sigec.t_sigea_prodotti (
    prodotto_id character varying(255) NOT NULL,
    descrizione character varying(255)
);

CREATE TABLE sigec.t_sigea_valore_attrattivita_turistica (
    valore_id character varying(255) NOT NULL,
    descrizione character varying(255)
);


--
-- TOC entry 3020 (class 0 OID 56420)
-- Dependencies: 205
-- Data for Name: t_lock; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3021 (class 0 OID 56425)
-- Dependencies: 206
-- Data for Name: t_sigea_accessibilita; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3022 (class 0 OID 56430)
-- Dependencies: 207
-- Data for Name: t_sigea_attivita; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3023 (class 0 OID 56435)
-- Dependencies: 208
-- Data for Name: t_sigea_attrattori; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3024 (class 0 OID 56440)
-- Dependencies: 209
-- Data for Name: t_sigea_contatti; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3025 (class 0 OID 56445)
-- Dependencies: 210
-- Data for Name: t_sigea_datigenerali; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3026 (class 0 OID 56453)
-- Dependencies: 211
-- Data for Name: t_sigea_decodifica; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3027 (class 0 OID 56461)
-- Dependencies: 212
-- Data for Name: t_sigea_dettaglioutente; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3028 (class 0 OID 56469)
-- Dependencies: 213
-- Data for Name: t_sigea_documenti; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3029 (class 0 OID 56477)
-- Dependencies: 214
-- Data for Name: t_sigea_eventi; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3030 (class 0 OID 56485)
-- Dependencies: 215
-- Data for Name: t_sigea_immagini; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3031 (class 0 OID 56493)
-- Dependencies: 216
-- Data for Name: t_sigea_link; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3032 (class 0 OID 56501)
-- Dependencies: 217
-- Data for Name: t_sigea_location; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3033 (class 0 OID 56509)
-- Dependencies: 218
-- Data for Name: t_sigea_location_attrattore; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3034 (class 0 OID 56514)
-- Dependencies: 219
-- Data for Name: t_sigea_logeventi; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3035 (class 0 OID 56522)
-- Dependencies: 220
-- Data for Name: t_sigea_periodi; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3036 (class 0 OID 56530)
-- Dependencies: 221
-- Data for Name: t_sigea_pubblicazioni; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3037 (class 0 OID 56538)
-- Dependencies: 222
-- Data for Name: t_sigea_relazioni; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3038 (class 0 OID 56543)
-- Dependencies: 223
-- Data for Name: t_sigea_richiestaattivita; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3039 (class 0 OID 56548)
-- Dependencies: 224
-- Data for Name: t_sigea_segnalazioni; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3040 (class 0 OID 56556)
-- Dependencies: 225
-- Data for Name: t_sigea_stati; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3041 (class 0 OID 56561)
-- Dependencies: 226
-- Data for Name: t_sigea_tickets; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3042 (class 0 OID 56569)
-- Dependencies: 227
-- Data for Name: t_sigea_tipologie_utenti; Type: TABLE DATA; Schema: sigec; Owner: -
--



--
-- TOC entry 3060 (class 0 OID 0)
-- Dependencies: 228
-- Name: lock_seq; Type: SEQUENCE SET; Schema: sigec; Owner: -
--

SELECT pg_catalog.setval('sigec.lock_seq', 1, false);


--
-- TOC entry 3061 (class 0 OID 0)
-- Dependencies: 229
-- Name: sigea_contatti_seq; Type: SEQUENCE SET; Schema: sigec; Owner: -
--

SELECT pg_catalog.setval('sigec.sigea_contatti_seq', 1, false);

--
-- TOC entry 3064 (class 0 OID 0)
-- Dependencies: 232
-- Name: sigea_media_seq; Type: SEQUENCE SET; Schema: sigec; Owner: -
--

SELECT pg_catalog.setval('sigec.sigea_media_seq', 1, false);


--
-- TOC entry 3063 (class 0 OID 0)
-- Dependencies: 231
-- Name: sigea_eventi_seq; Type: SEQUENCE SET; Schema: sigec; Owner: -
--

SELECT pg_catalog.setval('sigec.sigea_eventi_seq', 1, false);


--
-- TOC entry 3065 (class 0 OID 0)
-- Dependencies: 233
-- Name: sigea_link_seq; Type: SEQUENCE SET; Schema: sigec; Owner: -
--

SELECT pg_catalog.setval('sigec.sigea_link_seq', 1, false);


--
-- TOC entry 3066 (class 0 OID 0)
-- Dependencies: 234
-- Name: sigea_location_seq; Type: SEQUENCE SET; Schema: sigec; Owner: -
--

SELECT pg_catalog.setval('sigec.sigea_location_seq', 1, false);


--
-- TOC entry 3067 (class 0 OID 0)
-- Dependencies: 235
-- Name: sigea_logeventi_seq; Type: SEQUENCE SET; Schema: sigec; Owner: -
--

SELECT pg_catalog.setval('sigec.sigea_logeventi_seq', 1, false);


--
-- TOC entry 3068 (class 0 OID 0)
-- Dependencies: 236
-- Name: sigea_periodi_seq; Type: SEQUENCE SET; Schema: sigec; Owner: -
--

SELECT pg_catalog.setval('sigec.sigea_periodi_seq', 1, false);


--
-- TOC entry 3069 (class 0 OID 0)
-- Dependencies: 237
-- Name: sigea_pubblicazioni_seq; Type: SEQUENCE SET; Schema: sigec; Owner: -
--

SELECT pg_catalog.setval('sigec.sigea_pubblicazioni_seq', 1, false);


--
-- TOC entry 3070 (class 0 OID 0)
-- Dependencies: 238
-- Name: sigea_relazioni_seq; Type: SEQUENCE SET; Schema: sigec; Owner: -
--

SELECT pg_catalog.setval('sigec.sigea_relazioni_seq', 1, false);


--
-- TOC entry 3071 (class 0 OID 0)
-- Dependencies: 239
-- Name: sigea_segnalazioni_seq; Type: SEQUENCE SET; Schema: sigec; Owner: -
--

SELECT pg_catalog.setval('sigec.sigea_segnalazioni_seq', 1, false);


--
-- TOC entry 2830 (class 2606 OID 56424)
-- Name: t_lock t_lock_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_lock
    ADD CONSTRAINT t_lock_pkey PRIMARY KEY (lock_id);


--
-- TOC entry 2834 (class 2606 OID 56429)
-- Name: t_sigea_accessibilita t_sigea_accessibilita_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_accessibilita
    ADD CONSTRAINT t_sigea_accessibilita_pkey PRIMARY KEY (evento_id);


--
-- TOC entry 2836 (class 2606 OID 56434)
-- Name: t_sigea_attivita t_sigea_attivita_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_attivita
    ADD CONSTRAINT t_sigea_attivita_pkey PRIMARY KEY (attivita_id);


--
-- TOC entry 2838 (class 2606 OID 56439)
-- Name: t_sigea_attrattori t_sigea_attrattori_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_attrattori
    ADD CONSTRAINT t_sigea_attrattori_pkey PRIMARY KEY (attrattore_id);


--
-- TOC entry 2840 (class 2606 OID 56444)
-- Name: t_sigea_contatti t_sigea_contatti_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_contatti
    ADD CONSTRAINT t_sigea_contatti_pkey PRIMARY KEY (contatto_id);


--
-- TOC entry 2842 (class 2606 OID 56452)
-- Name: t_sigea_datigenerali t_sigea_datigenerali_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_datigenerali
    ADD CONSTRAINT t_sigea_datigenerali_pkey PRIMARY KEY (evento_id);


--
-- TOC entry 2844 (class 2606 OID 56460)
-- Name: t_sigea_decodifica t_sigea_decodifica_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_decodifica
    ADD CONSTRAINT t_sigea_decodifica_pkey PRIMARY KEY (key);


--
-- TOC entry 2846 (class 2606 OID 56468)
-- Name: t_sigea_dettaglioutente t_sigea_dettaglioutente_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_dettaglioutente
    ADD CONSTRAINT t_sigea_dettaglioutente_pkey PRIMARY KEY (utente_id);


--
-- TOC entry 2848 (class 2606 OID 56476)
-- Name: t_sigea_documenti t_sigea_documenti_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_documenti
    ADD CONSTRAINT t_sigea_documenti_pkey PRIMARY KEY (documento_id);


--
-- TOC entry 2850 (class 2606 OID 56484)
-- Name: t_sigea_eventi t_sigea_eventi_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_eventi
    ADD CONSTRAINT t_sigea_eventi_pkey PRIMARY KEY (evento_id);


--
-- TOC entry 2852 (class 2606 OID 56492)
-- Name: t_sigea_immagini t_sigea_immagini_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_immagini
    ADD CONSTRAINT t_sigea_immagini_pkey PRIMARY KEY (immagine_id);


--
-- TOC entry 2854 (class 2606 OID 56500)
-- Name: t_sigea_link t_sigea_link_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_link
    ADD CONSTRAINT t_sigea_link_pkey PRIMARY KEY (link_id);


--
-- TOC entry 2858 (class 2606 OID 56513)
-- Name: t_sigea_location_attrattore t_sigea_location_attrattore_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_location_attrattore
    ADD CONSTRAINT t_sigea_location_attrattore_pkey PRIMARY KEY (location_id, attrattore_id);


--
-- TOC entry 2856 (class 2606 OID 56508)
-- Name: t_sigea_location t_sigea_location_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_location
    ADD CONSTRAINT t_sigea_location_pkey PRIMARY KEY (location_id);


--
-- TOC entry 2860 (class 2606 OID 56521)
-- Name: t_sigea_logeventi t_sigea_logeventi_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_logeventi
    ADD CONSTRAINT t_sigea_logeventi_pkey PRIMARY KEY (log_id);


--
-- TOC entry 2862 (class 2606 OID 56529)
-- Name: t_sigea_periodi t_sigea_periodi_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_periodi
    ADD CONSTRAINT t_sigea_periodi_pkey PRIMARY KEY (periodo_id);


--
-- TOC entry 2886 (class 2606 OID 57627)
-- Name: t_sigea_pubb_allegati t_sigea_pubb_allegati_pkey; Type: CONSTRAINT; Schema: sigec; Owner: postgres
--

ALTER TABLE ONLY sigec.t_sigea_pubb_allegati
    ADD CONSTRAINT t_sigea_pubb_allegati_pkey PRIMARY KEY (allegato_id);


--
-- TOC entry 2864 (class 2606 OID 56537)
-- Name: t_sigea_pubblicazioni t_sigea_pubblicazioni_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_pubblicazioni
    ADD CONSTRAINT t_sigea_pubblicazioni_pkey PRIMARY KEY (pubblicazione_id);

--
-- TOC entry 2893 (class 2606 OID 57645)
-- Name: t_sigea_redazioni t_sigea_redazioni_pkey; Type: CONSTRAINT; Schema: sigec; Owner: postgres
--

ALTER TABLE ONLY sigec.t_sigea_redazioni
    ADD CONSTRAINT t_sigea_redazioni_pkey PRIMARY KEY (redazione_id);

--
-- TOC entry 2893 (class 2606 OID 57645)
-- Name: t_sigea_redazioni t_sigea_ruoli_pkey; Type: CONSTRAINT; Schema: sigec; Owner: postgres
--

ALTER TABLE ONLY sigec.t_sigea_ruoli
    ADD CONSTRAINT t_sigea_ruoli_pkey PRIMARY KEY (ruolo_id);

--
-- TOC entry 2900 (class 2606 OID 57658)
-- Name: t_sigea_tipologia_redazione t_sigea_tipologia_redazione_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_tipologia_redazione
    ADD CONSTRAINT t_sigea_tipologia_redazione_pkey PRIMARY KEY (tipologia_id, redazione_id);


--
-- TOC entry 2866 (class 2606 OID 56542)
-- Name: t_sigea_relazioni t_sigea_relazioni_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_relazioni
    ADD CONSTRAINT t_sigea_relazioni_pkey PRIMARY KEY (relazione_id);


--
-- TOC entry 2868 (class 2606 OID 56547)
-- Name: t_sigea_richiestaattivita t_sigea_richiestaattivita_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_richiestaattivita
    ADD CONSTRAINT t_sigea_richiestaattivita_pkey PRIMARY KEY (richiesta_id);


--
-- TOC entry 2870 (class 2606 OID 56555)
-- Name: t_sigea_segnalazioni t_sigea_segnalazioni_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_segnalazioni
    ADD CONSTRAINT t_sigea_segnalazioni_pkey PRIMARY KEY (segnalazione_id);


--
-- TOC entry 2872 (class 2606 OID 56560)
-- Name: t_sigea_stati t_sigea_stati_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_stati
    ADD CONSTRAINT t_sigea_stati_pkey PRIMARY KEY (stato_id);


--
-- TOC entry 2874 (class 2606 OID 56568)
-- Name: t_sigea_tickets t_sigea_tickets_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_tickets
    ADD CONSTRAINT t_sigea_tickets_pkey PRIMARY KEY (evento_id);


--
-- TOC entry 2876 (class 2606 OID 56576)
-- Name: t_sigea_tipologie_utenti t_sigea_tipologie_utenti_pkey; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_tipologie_utenti
    ADD CONSTRAINT t_sigea_tipologie_utenti_pkey PRIMARY KEY (tipologia_id);


--
-- TOC entry 2832 (class 2606 OID 56578)
-- Name: t_lock t_lock_unique; Type: CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_lock
    ADD CONSTRAINT t_lock_unique UNIQUE (item_id, nome_tabella);


--
-- TOC entry 2917 (class 2606 OID 57632)
-- Name: t_sigea_pubblicazioni t_sigea_pubb_redaz; Type: FK CONSTRAINT; Schema: sigec; Owner: postgres
--

ALTER TABLE ONLY sigec.t_sigea_pubblicazioni
    ADD CONSTRAINT t_sigea_pubb_redaz_fk FOREIGN KEY (redazione_id) REFERENCES sigec.t_sigea_redazioni(redazione_id);

--
-- TOC entry 2925 (class 2606 OID 57659)
-- Name: t_sigea_tipologia_redazione; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_tipologia_redazione
    ADD CONSTRAINT t_sigea_tipologia_redazione_fk FOREIGN KEY (redazione_id) REFERENCES sigec.t_sigea_redazioni(redazione_id);

    --
-- TOC entry 2925 (class 2606 OID 57659)
-- Name: t_sigea_tipologia_ruolo; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_tipologia_ruolo
    ADD CONSTRAINT t_sigea_tipologia_ruolo_fk FOREIGN KEY (ruolo_id) REFERENCES sigec.t_sigea_ruoli(ruolo_id);

--
-- TOC entry 2917 (class 2606 OID 58005)
-- Name: t_sigea_pubb_allegati t_sigea_pubb_allegati_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_pubb_allegati
    ADD CONSTRAINT t_sigea_pubb_allegati_fk FOREIGN KEY (pubblicazione_id) REFERENCES sigec.t_sigea_pubblicazioni(pubblicazione_id);


--
-- TOC entry 2926 (class 2606 OID 57664)
-- Name: t_sigea_tipologia_redazione; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_tipologia_redazione
    ADD CONSTRAINT t_sigea_tipologia_redazione_fk2 FOREIGN KEY (tipologia_id) REFERENCES sigec.t_sigea_tipologie_utenti(tipologia_id);

--
-- TOC entry 2926 (class 2606 OID 57664)
-- Name: t_sigea_tipologia_redazione; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_tipologia_ruolo
    ADD CONSTRAINT t_sigea_tipologia_ruolo_fk2 FOREIGN KEY (tipologia_id) REFERENCES sigec.t_sigea_tipologie_utenti(tipologia_id);

--
-- TOC entry 2878 (class 2606 OID 56608)
-- Name: t_sigea_contatti t_sigea_contatti_eventi_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_contatti
    ADD CONSTRAINT t_sigea_contatti_eventi_fk FOREIGN KEY (evento_id) REFERENCES sigec.t_sigea_eventi(evento_id);


--
-- TOC entry 2887 (class 2606 OID 56653)
-- Name: t_sigea_link t_sigea_link_eventi_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_link
    ADD CONSTRAINT t_sigea_link_eventi_fk FOREIGN KEY (evento_id) REFERENCES sigec.t_sigea_eventi(evento_id);


--
-- TOC entry 2883 (class 2606 OID 56633)
-- Name: t_sigea_eventi t_sigea_eventi_dettutente_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_eventi
    ADD CONSTRAINT t_sigea_eventi_dettutente_fk FOREIGN KEY (owner_id) REFERENCES sigec.t_sigea_dettaglioutente(utente_id);

    --
-- TOC entry 2883 (class 2606 OID 56633)
-- Name: t_sigea_eventi t_sigea_eventi_dettutente_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_eventi
    ADD CONSTRAINT t_sigea_eventi_dettutente_fk2 FOREIGN KEY (utente_ultima_modifica_id) REFERENCES sigec.t_sigea_dettaglioutente(utente_id);


--
-- TOC entry 2892 (class 2606 OID 56678)
-- Name: t_sigea_periodi t_sigea_periodi_eventi_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_periodi
    ADD CONSTRAINT t_sigea_periodi_eventi_fk FOREIGN KEY (evento_id) REFERENCES sigec.t_sigea_eventi(evento_id);


--
-- TOC entry 2894 (class 2606 OID 56688)
-- Name: t_sigea_pubblicazioni t_sigea_pubblicaz_dettutente_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_pubblicazioni
    ADD CONSTRAINT t_sigea_pubblicaz_dettutente_fk FOREIGN KEY (redattore_id) REFERENCES sigec.t_sigea_dettaglioutente(utente_id);


--
-- TOC entry 2877 (class 2606 OID 56603)
-- Name: t_sigea_accessibilita t_sigea_access_eventi_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_accessibilita
    ADD CONSTRAINT t_sigea_access_eventi_fk FOREIGN KEY (evento_id) REFERENCES sigec.t_sigea_eventi(evento_id);


--
-- TOC entry 2893 (class 2606 OID 56683)
-- Name: t_sigea_pubblicazioni t_sigea_pubblicaz_eventi_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_pubblicazioni
    ADD CONSTRAINT t_sigea_pubblicaz_eventi_fk FOREIGN KEY (evento_id) REFERENCES sigec.t_sigea_eventi(evento_id);


--
-- TOC entry 2888 (class 2606 OID 56658)
-- Name: t_sigea_location t_sigea_location_eventi_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_location
    ADD CONSTRAINT t_sigea_location_eventi_fk FOREIGN KEY (evento_id) REFERENCES sigec.t_sigea_eventi(evento_id);


--
-- TOC entry 2886 (class 2606 OID 56648)
-- Name: t_sigea_immagini t_sigea_immagini_eventi_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_immagini
    ADD CONSTRAINT t_sigea_immagini_eventi_fk FOREIGN KEY (evento_id) REFERENCES sigec.t_sigea_eventi(evento_id);


--
-- TOC entry 2898 (class 2606 OID 56708)
-- Name: t_sigea_tickets t_sigea_tickets_eventi_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_tickets
    ADD CONSTRAINT t_sigea_tickets_eventi_fk FOREIGN KEY (evento_id) REFERENCES sigec.t_sigea_eventi(evento_id);


--
-- TOC entry 2896 (class 2606 OID 56698)
-- Name: t_sigea_segnalazioni t_sigea_segnalaz_dettutente_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_segnalazioni
    ADD CONSTRAINT t_sigea_segnalaz_dettutente_fk FOREIGN KEY (idutente_inserimento) REFERENCES sigec.t_sigea_dettaglioutente(utente_id);


--
-- TOC entry 2881 (class 2606 OID 56623)
-- Name: t_sigea_documenti t_sigea_documenti_eventi_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_documenti
    ADD CONSTRAINT t_sigea_documenti_eventi_fk FOREIGN KEY (evento_id) REFERENCES sigec.t_sigea_eventi(evento_id);


--
-- TOC entry 2891 (class 2606 OID 56673)
-- Name: t_sigea_logeventi t_sigea_log_eventi_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_logeventi
    ADD CONSTRAINT t_sigea_log_eventi_fk FOREIGN KEY (evento_id) REFERENCES sigec.t_sigea_eventi(evento_id);


--
-- TOC entry 2897 (class 2606 OID 56703)
-- Name: t_sigea_segnalazioni t_sigea_segnalaz_dettutente_fk2; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_segnalazioni
    ADD CONSTRAINT t_sigea_segnalaz_dettutente_fk2 FOREIGN KEY (idutente_modifica) REFERENCES sigec.t_sigea_dettaglioutente(utente_id);


--
-- TOC entry 2889 (class 2606 OID 56663)
-- Name: t_sigea_location_attrattore t_sigea_location_attratt_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_location_attrattore
    ADD CONSTRAINT t_sigea_location_attratt_fk FOREIGN KEY (attrattore_id) REFERENCES sigec.t_sigea_attrattori(attrattore_id);


--
-- TOC entry 2880 (class 2606 OID 56618)
-- Name: t_sigea_dettaglioutente t_sigea_dettutente_utenti_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_dettaglioutente
    ADD CONSTRAINT t_sigea_dettutente_utenti_fk FOREIGN KEY (tipologia_id) REFERENCES sigec.t_sigea_tipologie_utenti(tipologia_id);


--
-- TOC entry 2885 (class 2606 OID 56643)
-- Name: t_sigea_eventi t_sigea_eventi_stati_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_eventi
    ADD CONSTRAINT t_sigea_eventi_stati_fk FOREIGN KEY (stato_id) REFERENCES sigec.t_sigea_stati(stato_id);


--
-- TOC entry 2882 (class 2606 OID 56628)
-- Name: t_sigea_eventi t_sigea_eventi_attivita_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_eventi
    ADD CONSTRAINT t_sigea_eventi_attivita_fk FOREIGN KEY (attivita_id) REFERENCES sigec.t_sigea_attivita(attivita_id);


--
-- TOC entry 2884 (class 2606 OID 56638)
-- Name: t_sigea_eventi t_sigea_eventi_richattivita_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_eventi
    ADD CONSTRAINT t_sigea_eventi_richattivita_fk FOREIGN KEY (richiesta_id) REFERENCES sigec.t_sigea_richiestaattivita(richiesta_id);


--
-- TOC entry 2890 (class 2606 OID 56668)
-- Name: t_sigea_location_attrattore t_sigea_location_attrattore_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_location_attrattore
    ADD CONSTRAINT t_sigea_location_attrattore_fk FOREIGN KEY (location_id) REFERENCES sigec.t_sigea_location(location_id);


--
-- TOC entry 2879 (class 2606 OID 56613)
-- Name: t_sigea_datigenerali t_sigea_eventi_datigenerali_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_datigenerali
    ADD CONSTRAINT t_sigea_eventi_datigenerali_fk FOREIGN KEY (evento_id) REFERENCES sigec.t_sigea_eventi(evento_id);


--
-- TOC entry 2895 (class 2606 OID 56693)
-- Name: t_sigea_relazioni t_sigea_eventi_relazioni_fk; Type: FK CONSTRAINT; Schema: sigec; Owner: -
--

ALTER TABLE ONLY sigec.t_sigea_relazioni
    ADD CONSTRAINT t_sigea_eventi_relazioni_fk FOREIGN KEY (evento_id) REFERENCES sigec.t_sigea_eventi(evento_id);

ALTER TABLE ONLY sigec.t_sigea_tipologie_mibact
    ADD CONSTRAINT t_sigea_tipologie_mibact_pkey PRIMARY KEY (tipologia_id);
	
ALTER TABLE ONLY sigec.t_sigea_tipologie_attivita
    ADD CONSTRAINT t_sigea_tipologie_attivita_pkey PRIMARY KEY (tipologia_id);
	
ALTER TABLE ONLY sigec.t_sigea_mezzi
    ADD CONSTRAINT t_sigea_mezzi_pkey PRIMARY KEY (mezzo_id);
	
ALTER TABLE ONLY sigec.t_sigea_prodotti
    ADD CONSTRAINT t_sigea_prodotti_pkey PRIMARY KEY (prodotto_id);
	
ALTER TABLE ONLY sigec.t_sigea_valore_attrattivita_turistica
    ADD CONSTRAINT t_sigea_valore_attrattivita_turistica_pkey PRIMARY KEY (valore_id);

-- Completed on 2020-02-21 10:15:41

--
-- PostgreSQL database dump complete
--

