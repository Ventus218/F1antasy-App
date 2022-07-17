-- *********************************************
-- * SQL MySQL generation                      
-- *--------------------------------------------
-- * DB-MAIN version: 11.0.2              
-- * Generator date: Sep 14 2021              
-- * Generation date: Sun Jul 17 10:48:22 2022 
-- * LUN file: C:\Users\fraca\OneDrive - Alma Mater Studiorum Università di Bologna\F1antasy\FANTASY_SCHEMA_CONCETTUALE_UPDATE.lun 
-- * Schema: F1ANTASY/2 
-- ********************************************* 


-- Database Section
-- ________________ 

create database F1ANTASY;
use F1ANTASY;


-- Tables Section
-- _____________ 

create table CAMPIONATO (
     Anno int not null,
     constraint IDCAMPIONATO_ID primary key (Anno));

create table CLASSIFICA_PRIVATA (
     Nome varchar(255) not null,
     NumeroPartecipanti int not null,
     constraint IDCLASSIFICA_PRIVATA_ID primary key (Nome));

create table GRAN_PREMIO (
     Stato varchar(255) not null,
     Nome varchar(255) not null,
     NumeroGiri int not null,
     LunghezzaCircuito int not null,
     constraint IDGRAN_PREMIO primary key (Stato, Nome));

create table GRAN_PREMIO_PROGRAMMATO (
     AnnoCampionato int not null,
     Data date not null,
     Stato varchar(255) not null,
     Nome varchar(255) not null,
     Concluso BOOL default false not null,
     constraint IDGRAN_PREMIO primary key (AnnoCampionato, Data),
     constraint IDISTANZA_GRAN_PREMIO unique (Stato, Nome, Data));

create table INGAGGIO_PILOTA (
     AnnoCampionato int not null,
     NomeScuderia varchar(255) not null,
     CodicePilota int not null,
     constraint IDINGAGGIO_PILOTA primary key (CodicePilota, AnnoCampionato, NomeScuderia));

create table MOTORIZZAZIONE (
     Nome varchar(255) not null,
     constraint IDMOTORIZZAZIONE primary key (Nome));

create table MOTORIZZAZIONE_IN_GRAN_PREMIO (
     AnnoCampionato int not null,
     DataGranPremio date not null,
     NomeMotorizzazione varchar(255) not null,
     PunteggioOttenuto int,
     Prezzo int not null,
     constraint IDRISULTATO_MOTORIZZAZIONE primary key (NomeMotorizzazione, AnnoCampionato, DataGranPremio));

create table PILOTA (
     Codice int not null auto_increment,
     Nome varchar(255) not null,
     Cognome varchar(255) not null,
     constraint IDPILOTA primary key (Codice));

create table PILOTA_IN_GRAN_PREMIO (
     CodicePilota int not null,
     AnnoCampionato int not null,
     DataGranPremio date not null,
     Posizione int,
     Prezzo int not null,
     constraint IDRISULTATO_PILOTA primary key (AnnoCampionato, DataGranPremio, CodicePilota),
     constraint IDRISULTATO_PILOTA_1 unique (AnnoCampionato, DataGranPremio, Posizione));

create table POSIZIONE_PUNTEGGIO (
     Posizione int not null,
     Punteggio int not null,
     constraint IDPOSIZIONE_PUNTEGGIO primary key (Posizione));

create table REGISTRAZIONE (
     UsernameUtente varchar(255) not null,
     NomeClassificaPrivata varchar(255) not null,
     constraint IDREGISTRAZIONE primary key (NomeClassificaPrivata, UsernameUtente));

create table SCAMBIO_MOTORIZZAZIONE (
     AnnoCampionato int not null,
     DataGranPremio date not null,
     UsernameUtente varchar(255) not null,
     Codice int not null auto_increment,
     DataOra date not null,
     MotorizzazioneCeduta varchar(255) not null,
     MotorizzazioneAcquisita varchar(255) not null,
     constraint IDSCAMBIO_MOTORIZZAZIONE primary key (Codice));

create table SCAMBIO_PILOTA (
     AnnoCampionato int not null,
     DataGranPremio date not null,
     UsernameUtente varchar(255) not null,
     Codice int not null auto_increment,
     DataOra date not null,
     PilotaCeduto int not null,
     PilotaAcquisito int not null,
     constraint IDSCAMBIO_PILOTA primary key (Codice));

create table SCELTA_PILOTA (
     CodicePilota int not null,
     AnnoCampionato int not null,
     DataGranPremio date not null,
     UsernameUtente varchar(255) not null,
     constraint IDSCELTA_PILOTA primary key (CodicePilota, AnnoCampionato, DataGranPremio, UsernameUtente));

create table SCUDERIA (
     Nome varchar(255) not null,
     constraint IDSCUDERIA primary key (Nome));

create table SCUDERIA_PARTECIPANTE (
     AnnoCampionato int not null,
     NomeScuderia varchar(255) not null,
     NomeMotorizzazione varchar(255) not null,
     constraint IDSCUDERIA_IN_CAMPIONATO_ID primary key (AnnoCampionato, NomeScuderia));

create table SQUADRA (
     AnnoCampionato int not null,
     DataGranPremio date not null,
     UsernameUtente varchar(255) not null,
     ScambiEffettuati int default 0 not null,
     BudgetRimanente int default 100000000 not null,
     NomeMotorizzazione varchar(255) not null,
     constraint IDSQUADRA_ID primary key (AnnoCampionato, DataGranPremio, UsernameUtente));

create table UTENTE (
     Username varchar(255) not null,
     Password varchar(255) not null,
     PunteggioCorrente int default 0 not null,
     constraint IDUTENTE primary key (Username));


-- Constraints Section
-- ___________________ 

-- Not implemented
-- alter table CAMPIONATO add constraint IDCAMPIONATO_CHK
--     check(exists(select * from GRAN_PREMIO_PROGRAMMATO
--                  where GRAN_PREMIO_PROGRAMMATO.AnnoCampionato = Anno)); 

-- Not implemented
-- alter table CLASSIFICA_PRIVATA add constraint IDCLASSIFICA_PRIVATA_CHK
--     check(exists(select * from REGISTRAZIONE
--                  where REGISTRAZIONE.NomeClassificaPrivata = Nome)); 

alter table GRAN_PREMIO_PROGRAMMATO add constraint FKSVOLGIMENTO
     foreign key (Stato, Nome)
     references GRAN_PREMIO (Stato, Nome)
     on delete cascade
     on update cascade;

alter table GRAN_PREMIO_PROGRAMMATO add constraint FKCOMPOSIZIONE
     foreign key (AnnoCampionato)
     references CAMPIONATO (Anno);

alter table INGAGGIO_PILOTA add constraint FKING_PIL
     foreign key (CodicePilota)
     references PILOTA (Codice)
     on delete cascade
     on update cascade;

alter table INGAGGIO_PILOTA add constraint FKING_SCU
     foreign key (AnnoCampionato, NomeScuderia)
     references SCUDERIA_PARTECIPANTE (AnnoCampionato, NomeScuderia)
     on delete cascade
     on update cascade;

alter table MOTORIZZAZIONE_IN_GRAN_PREMIO add constraint FKRIS_MOT
     foreign key (NomeMotorizzazione)
     references MOTORIZZAZIONE (Nome)
     on delete cascade
     on update cascade;

alter table MOTORIZZAZIONE_IN_GRAN_PREMIO add constraint FKRIS_GRA_MOT
     foreign key (AnnoCampionato, DataGranPremio)
     references GRAN_PREMIO_PROGRAMMATO (AnnoCampionato, Data)
     on delete cascade
     on update cascade;

alter table PILOTA_IN_GRAN_PREMIO add constraint FKRIS_PIL
     foreign key (CodicePilota)
     references PILOTA (Codice)
     on delete cascade
     on update cascade;

alter table PILOTA_IN_GRAN_PREMIO add constraint FKPOSIZIONAMENTO
     foreign key (Posizione)
     references POSIZIONE_PUNTEGGIO (Posizione)
     on delete cascade
     on update cascade;

alter table PILOTA_IN_GRAN_PREMIO add constraint FKRISULTATO
     foreign key (AnnoCampionato, DataGranPremio)
     references GRAN_PREMIO_PROGRAMMATO (AnnoCampionato, Data)
     on delete cascade
     on update cascade;

alter table REGISTRAZIONE add constraint FKREG_CLA
     foreign key (NomeClassificaPrivata)
     references CLASSIFICA_PRIVATA (Nome)
     on delete cascade
     on update cascade;

alter table REGISTRAZIONE add constraint FKREG_UTE
     foreign key (UsernameUtente)
     references UTENTE (Username)
     on delete cascade
     on update cascade;

alter table SCAMBIO_MOTORIZZAZIONE add constraint FKCESSIONE_MOTORIZZAZIONE
     foreign key (MotorizzazioneCeduta)
     references MOTORIZZAZIONE (Nome)
     on delete cascade
     on update cascade;

alter table SCAMBIO_MOTORIZZAZIONE add constraint FKACQUISTO_MOTORIZZAZIONE
     foreign key (MotorizzazioneAcquisita)
     references MOTORIZZAZIONE (Nome)
     on delete cascade
     on update cascade;

alter table SCAMBIO_MOTORIZZAZIONE add constraint FKSCAMBI_MOTORIZZAZIONE
     foreign key (AnnoCampionato, DataGranPremio, UsernameUtente)
     references SQUADRA (AnnoCampionato, DataGranPremio, UsernameUtente)
     on delete cascade
     on update cascade;

alter table SCAMBIO_PILOTA add constraint FKACQUISTO_PILOTA
     foreign key (PilotaCeduto)
     references PILOTA (Codice)
     on delete cascade
     on update cascade;

alter table SCAMBIO_PILOTA add constraint FKCESSIONE_PILOTA
     foreign key (PilotaAcquisito)
     references PILOTA (Codice)
     on delete cascade
     on update cascade;

alter table SCAMBIO_PILOTA add constraint FKSCAMBI_PILOTA
     foreign key (AnnoCampionato, DataGranPremio, UsernameUtente)
     references SQUADRA (AnnoCampionato, DataGranPremio, UsernameUtente)
     on delete cascade
     on update cascade;

alter table SCELTA_PILOTA add constraint FKSCE_SQU
     foreign key (AnnoCampionato, DataGranPremio, UsernameUtente)
     references SQUADRA (AnnoCampionato, DataGranPremio, UsernameUtente)
     on delete cascade
     on update cascade;

alter table SCELTA_PILOTA add constraint FKSCE_PIL
     foreign key (CodicePilota)
     references PILOTA (Codice)
     on delete cascade
     on update cascade;

-- Not implemented
-- alter table SCUDERIA_PARTECIPANTE add constraint IDSCUDERIA_IN_CAMPIONATO_CHK
--     check(exists(select * from INGAGGIO_PILOTA
--                  where INGAGGIO_PILOTA.AnnoCampionato = AnnoCampionato and INGAGGIO_PILOTA.NomeScuderia = NomeScuderia)); 

alter table SCUDERIA_PARTECIPANTE add constraint FKPARTECIPAZIONE
     foreign key (NomeScuderia)
     references SCUDERIA (Nome)
     on delete cascade
     on update cascade;

alter table SCUDERIA_PARTECIPANTE add constraint FKUTILIZZO_MOTORIZZAZIONE
     foreign key (NomeMotorizzazione)
     references MOTORIZZAZIONE (Nome)
     on delete cascade
     on update cascade;

alter table SCUDERIA_PARTECIPANTE add constraint FKISCRIZIONE_FK
     foreign key (AnnoCampionato)
     references CAMPIONATO (Anno)
     on delete cascade
     on update cascade;

-- Not implemented
-- alter table SQUADRA add constraint IDSQUADRA_CHK
--     check(exists(select * from SCELTA_PILOTA
--                  where SCELTA_PILOTA.AnnoCampionato = AnnoCampionato and SCELTA_PILOTA.DataGranPremio = DataGranPremio and SCELTA_PILOTA.UsernameUtente = UsernameUtente)); 

alter table SQUADRA add constraint FKCOSTRUZIONE
     foreign key (UsernameUtente)
     references UTENTE (Username)
     on delete cascade
     on update cascade;

alter table SQUADRA add constraint FKCOMPETIZIONE
     foreign key (AnnoCampionato, DataGranPremio)
     references GRAN_PREMIO_PROGRAMMATO (AnnoCampionato, Data)
     on delete cascade
     on update cascade;

alter table SQUADRA add constraint FKSCELTA_MOTORIZZAZIONE
     foreign key (NomeMotorizzazione)
     references MOTORIZZAZIONE (Nome)
     on delete cascade
     on update cascade;





-- ADDED BY US SECTION --
ALTER TABLE SQUADRA
    ADD CONSTRAINT CHK_BudgetRimanente_Positive CHECK ( SQUADRA.BudgetRimanente >= 0 ),
    ADD CONSTRAINT CHK_ScambiEffettuati_MAX CHECK ( SQUADRA.ScambiEffettuati <= 2 );
