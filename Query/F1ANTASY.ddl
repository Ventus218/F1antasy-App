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

drop database if exists F1ANTASY;

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

# checkPilotaInGranPremioStessoCampionato
CREATE PROCEDURE checkPilotaInGranPremioStessoCampionato (IN newAnnoC INT, IN newCodPil INT)
BEGIN

    IF NOT EXISTS(  SELECT *
                            FROM INGAGGIO_PILOTA I
                            WHERE I.AnnoCampionato = newAnnoC
                            AND I.CodicePilota = newCodPil)
    THEN
        SIGNAL SQLSTATE '45000';
    END IF;

END;

CREATE TRIGGER checkPilotaInGranPremioStessoCampionato_INSERT AFTER INSERT ON PILOTA_IN_GRAN_PREMIO
FOR EACH ROW
CALL checkPilotaInGranPremioStessoCampionato(NEW.AnnoCampionato, NEW.CodicePilota);

CREATE TRIGGER checkPilotaInGranPremioStessoCampionato_UPDATE AFTER UPDATE ON PILOTA_IN_GRAN_PREMIO
FOR EACH ROW
CALL checkPilotaInGranPremioStessoCampionato(NEW.AnnoCampionato, NEW.CodicePilota);


# checkMotorizzazioneInGranPremioStessoCampionato
CREATE PROCEDURE checkMotorizzazioneInGranPremioStessoCampionato (IN newAnnoC INT, IN newMot VARCHAR(255))
BEGIN

    IF NOT EXISTS(  SELECT *
                            FROM SCUDERIA_PARTECIPANTE S
                            WHERE S.AnnoCampionato = newAnnoC
                            AND S.NomeMotorizzazione = newMot)
    THEN
        SIGNAL SQLSTATE '45000';
    END IF;

END;

CREATE TRIGGER checkMotorizzazioneInGranPremioStessoCampionato_INSERT AFTER INSERT ON MOTORIZZAZIONE_IN_GRAN_PREMIO
FOR EACH ROW
CALL checkMotorizzazioneInGranPremioStessoCampionato(NEW.AnnoCampionato, NEW.NomeMotorizzazione);

CREATE TRIGGER checkMotorizzazioneInGranPremioStessoCampionato_UPDATE AFTER UPDATE ON MOTORIZZAZIONE_IN_GRAN_PREMIO
FOR EACH ROW
CALL checkMotorizzazioneInGranPremioStessoCampionato(NEW.AnnoCampionato, NEW.NomeMotorizzazione);


# checkScambioPilotiInStessoCampionato
CREATE PROCEDURE checkScambioPilotiInStessoCampionato (IN newAnnoC INT, IN newCodPilCed INT, IN newCodPilAcq INT)
BEGIN

    IF NOT EXISTS(  SELECT *
                            FROM INGAGGIO_PILOTA I
                            WHERE I.AnnoCampionato = newAnnoC
                            AND I.CodicePilota = newCodPilCed)
    OR NOT EXISTS(SELECT *
                            FROM INGAGGIO_PILOTA I
                            WHERE I.AnnoCampionato = newAnnoC
                            AND I.CodicePilota = newCodPilAcq)
    THEN
        SIGNAL SQLSTATE '45000';
    END IF;

END;

CREATE TRIGGER checkScambioPilotiInStessoCampionato_INSERT AFTER INSERT ON SCAMBIO_PILOTA
FOR EACH ROW
CALL checkScambioPilotiInStessoCampionato(NEW.AnnoCampionato, NEW.PilotaCeduto, NEW.PilotaAcquisito);

CREATE TRIGGER checkScambioPilotiInStessoCampionato_UPDATE AFTER UPDATE ON SCAMBIO_PILOTA
FOR EACH ROW
CALL checkScambioPilotiInStessoCampionato(NEW.AnnoCampionato, NEW.PilotaCeduto, NEW.PilotaAcquisito);


# checkScambioMotorizzazioniInStessoCampionato
CREATE PROCEDURE checkScambioMotorizzazioniInStessoCampionato (IN newAnnoC INT, IN newMotCed VARCHAR(255), IN newMotAcq VARCHAR(255))
BEGIN

    IF NOT EXISTS(  SELECT *
                            FROM SCUDERIA_PARTECIPANTE S
                            WHERE S.AnnoCampionato = newAnnoC
                            AND S.NomeMotorizzazione = newMotCed)
    OR NOT EXISTS(SELECT *
                            FROM SCUDERIA_PARTECIPANTE S
                            WHERE S.AnnoCampionato = newAnnoC
                            AND S.NomeMotorizzazione = newMotAcq)
    THEN
        SIGNAL SQLSTATE '45000';
    END IF;

END;

CREATE TRIGGER checkScambioMotorizzazioniInStessoCampionato_INSERT AFTER INSERT ON SCAMBIO_MOTORIZZAZIONE
FOR EACH ROW
CALL checkScambioMotorizzazioniInStessoCampionato(NEW.AnnoCampionato, NEW.MotorizzazioneCeduta, NEW.MotorizzazioneCeduta);

CREATE TRIGGER checkScambioMotorizzazioniInStessoCampionato_UPDATE AFTER UPDATE ON SCAMBIO_MOTORIZZAZIONE
FOR EACH ROW
CALL checkScambioMotorizzazioniInStessoCampionato(NEW.AnnoCampionato, NEW.MotorizzazioneCeduta, NEW.MotorizzazioneCeduta);

/*
# checkScuderiaPartecipanteHaDuePiloti
CREATE PROCEDURE checkScuderiaPartecipanteHaDuePiloti (IN newAnnoC INT, IN newNomeScud VARCHAR(255))
BEGIN

    IF 2 != (   SELECT COUNT(*)
                    FROM INGAGGIO_PILOTA I
                    WHERE I.NomeScuderia = newNomeScud
                    AND I.AnnoCampionato = newAnnoC)
    THEN
        SIGNAL SQLSTATE '45000';
    END IF;

END;

CREATE TRIGGER checkScuderiaPartecipanteHaDuePiloti_INSERT_SCUDERIA AFTER INSERT ON SCUDERIA_PARTECIPANTE
FOR EACH ROW
CALL checkScuderiaPartecipanteHaDuePiloti(NEW.AnnoCampionato, NEW.NomeScuderia);

CREATE TRIGGER checkScuderiaPartecipanteHaDuePiloti_UPDATE_SCUDERIA AFTER UPDATE ON SCUDERIA_PARTECIPANTE
FOR EACH ROW
CALL checkScuderiaPartecipanteHaDuePiloti(NEW.AnnoCampionato, NEW.NomeScuderia);

CREATE TRIGGER checkScuderiaPartecipanteHaDuePiloti_INSERT_INGAGGIO AFTER INSERT ON INGAGGIO_PILOTA
FOR EACH ROW
CALL checkScuderiaPartecipanteHaDuePiloti(NEW.AnnoCampionato, NEW.NomeScuderia);

CREATE TRIGGER checkScuderiaPartecipanteHaDuePiloti_UPDATE_INGAGGIO AFTER UPDATE ON INGAGGIO_PILOTA
FOR EACH ROW
CALL checkScuderiaPartecipanteHaDuePiloti(NEW.AnnoCampionato, NEW.NomeScuderia);

CREATE TRIGGER checkScuderiaPartecipanteHaDuePiloti_DELETE_INGAGGIO AFTER DELETE ON INGAGGIO_PILOTA
FOR EACH ROW
CALL checkScuderiaPartecipanteHaDuePiloti(OLD.AnnoCampionato, OLD.NomeScuderia);



# checkSquadraHaQuattroPiloti
CREATE PROCEDURE checkSquadraHaQuattroPiloti (IN newAnnoC INT, IN newDataGP DATE, IN newUser VARCHAR(255))
BEGIN

    IF 4 != (   SELECT COUNT(*)
                    FROM SCELTA_PILOTA S
                    WHERE S.AnnoCampionato = newAnnoC
                    AND S.UsernameUtente = newUser
                    AND S.DataGranPremio = newDataGP)
    THEN
        SIGNAL SQLSTATE '45000';
    END IF;

END;

CREATE TRIGGER checkSquadraHaQuattroPiloti_INSERT_SQUADRA AFTER INSERT ON SQUADRA
FOR EACH ROW
CALL checkSquadraHaQuattroPiloti(NEW.AnnoCampionato, NEW.DataGranPremio, NEW.UsernameUtente);

CREATE TRIGGER checkSquadraHaQuattroPiloti_UPDATE_SQUADRA AFTER UPDATE ON SQUADRA
FOR EACH ROW
CALL checkSquadraHaQuattroPiloti(NEW.AnnoCampionato, NEW.DataGranPremio, NEW.UsernameUtente);

CREATE TRIGGER checkSquadraHaQuattroPiloti_INSERT_SCELTA_PILOTA AFTER INSERT ON SCELTA_PILOTA
FOR EACH ROW
CALL checkSquadraHaQuattroPiloti(NEW.AnnoCampionato, NEW.DataGranPremio, NEW.UsernameUtente);

CREATE TRIGGER checkSquadraHaQuattroPiloti_UPDATE_SCELTA_PILOTA AFTER UPDATE ON SCELTA_PILOTA
FOR EACH ROW
CALL checkSquadraHaQuattroPiloti(NEW.AnnoCampionato, NEW.DataGranPremio, NEW.UsernameUtente);

CREATE TRIGGER checkSquadraHaQuattroPiloti_DELETE_SCELTA_PILOTA AFTER DELETE ON SCELTA_PILOTA
FOR EACH ROW
CALL checkSquadraHaQuattroPiloti(OLD.AnnoCampionato, OLD.DataGranPremio, OLD.UsernameUtente);
*/
