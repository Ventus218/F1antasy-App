-- Run immediately after F1ANTASY.ddl

-- O1 - RegistrazioneUtente

CREATE PROCEDURE registrazioneUtente (IN user VARCHAR(255), IN pass VARCHAR(255))
BEGIN

    INSERT INTO UTENTE(Username, Password, PunteggioCorrente)
        VALUES(user, pass, 0); -- mettere default

END;

-- O2 - Inizializzazione della Squadra (scelta Piloti e Motorizzazione)

CREATE PROCEDURE inizializzazioneSquadra (  IN user VARCHAR(255),
                                                                        IN nomeMot VARCHAR(255),
                                                                        IN codicePilota1 INT,
                                                                        IN codicePilota2 INT,
                                                                        IN codicePilota3 INT,
                                                                        IN codicePilota4 INT    )
BEGIN

    DECLARE annoC INT;
    DECLARE dataGP DATE;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  -- rollback any changes made in the transaction
        RESIGNAL;  -- raise again the sql exception to the caller
    END;

    START TRANSACTION;

    CALL visualizzaGranPremioCorrente(annoC, dataGP, @useless1, @useless2, @useless3);

    INSERT INTO SQUADRA(AnnoCampionato, DataGranPremio, UsernameUtente, ScambiEffettuati, BudgetRimanente, NomeMotorizzazione)
    VALUE
        (annoC,
         dataGP,
         user,
         0, -- mettere default
         100000000 - (SELECT Prezzo FROM MOTORIZZAZIONE_IN_GRAN_PREMIO -- mettere default al posto di 100000000 ma controllare se funziona
                                WHERE AnnoCampionato = annoC
                                AND DataGranPremio = dataGP
                                AND NomeMotorizzazione = nomeMot),
        nomeMot);

    INSERT INTO SCELTA_PILOTA(CodicePilota, AnnoCampionato, DataGranPremio, UsernameUtente)
    VALUES
        (codicePilota1, annoC, dataGP, user),
        (codicePilota2, annoC, dataGP, user),
        (codicePilota3, annoC, dataGP, user),
        (codicePilota4, annoC, dataGP, user);

    UPDATE SQUADRA
    SET  BudgetRimanente = BudgetRimanente - (  SELECT sum(Prezzo) AS PrezzoTotale FROM PILOTA_IN_GRAN_PREMIO
                                                                            WHERE AnnoCampionato = annoC
                                                                            AND DataGranPremio = dataGP
                                                                            AND (   CodicePilota = codicePilota1
                                                                                        OR CodicePilota = codicePilota2
                                                                                        OR CodicePilota = codicePilota3
                                                                                        OR CodicePilota = codicePilota4)    )
    WHERE AnnoCampionato = annoC
    AND DataGranPremio = dataGP
    AND UsernameUtente = user;
    COMMIT;

END;

-- O3a - Visualizzazione della Squadra per un certo Gran Premio (Piloti)

CREATE PROCEDURE visualizzazionePilotiSquadra (IN user VARCHAR(255), IN annoC INT, IN dataGP DATE)
BEGIN

    SELECT P.*, PGP.Prezzo
    FROM UTENTE U JOIN SQUADRA S JOIN SCELTA_PILOTA SP JOIN PILOTA_IN_GRAN_PREMIO PGP JOIN PILOTA P
        ON U.Username = S.UsernameUtente
        AND SP.UsernameUtente = U.Username AND SP.AnnoCampionato = S.AnnoCampionato AND SP.DataGranPremio = S.DataGranPremio
        AND PGP.CodicePilota = SP.CodicePilota AND PGP.AnnoCampionato = SP.AnnoCampionato AND PGP.DataGranPremio = S.DataGranPremio
        AND P.Codice = PGP.CodicePilota
    WHERE U.Username = user
    AND S.AnnoCampionato = annoC
    AND S.DataGranPremio = dataGP;

END;

-- O3b - Visualizzazione della Squadra per un certo Gran Premio (Motorizzazione)
CREATE PROCEDURE visualizzazioneSquadra (IN user VARCHAR(255), IN annoC INT, IN dataGP DATE)
BEGIN

    SELECT S.*
    FROM SQUADRA S
    WHERE S.UsernameUtente = user
    AND S.AnnoCampionato = annoC
    AND S.DataGranPremio = dataGP;

END;

-- O4 - Visualizzazione di tutti i Piloti per un certo Gran Premio in ordine decrescente di prezzo

CREATE PROCEDURE visualizzazionePilotiPrezzoPerGranPremio (IN annoC INT, IN dataGP DATE)
BEGIN

    SELECT P.*, PGP.Prezzo
    FROM SCUDERIA_PARTECIPANTE SP JOIN INGAGGIO_PILOTA IP JOIN PILOTA P JOIN PILOTA_IN_GRAN_PREMIO PGP
        ON SP.AnnoCampionato = IP.AnnoCampionato AND SP.NomeScuderia = IP.NomeScuderia
        AND P.Codice = IP.CodicePilota
        AND PGP.CodicePilota = P.Codice AND PGP.AnnoCampionato = SP.AnnoCampionato
    WHERE SP.AnnoCampionato = annoC
    AND PGP.DataGranPremio = dataGP
    ORDER BY PGP.Prezzo DESC ;

END;

-- O5 - Visualizzazione di tutte le Motorizzazioni per un certo Gran Premio in ordine decrescente di prezzo

CREATE PROCEDURE visualizzazioneMotorizzazioniPrezzoPerGranPremio (IN annoC INT, IN dataGP DATE)
BEGIN

    SELECT MGP.NomeMotorizzazione, MGP.Prezzo
    FROM MOTORIZZAZIONE_IN_GRAN_PREMIO MGP
    WHERE MGP.AnnoCampionato = annoC
    AND MGP.DataGranPremio = dataGP
    ORDER BY MGP.Prezzo DESC;

END;

-- O6 - Visualizzazione Classifica Globale

CREATE PROCEDURE visualizzazioneClassificaGlobale ()
BEGIN

    SELECT Username, PunteggioCorrente FROM UTENTE
    ORDER BY PunteggioCorrente DESC;

END;

-- O7 - Visualizzazione Classifica Privata

CREATE PROCEDURE visualizzazioneClassificaPrivata (IN nome VARCHAR(255))
BEGIN

    SELECT U.Username, U.PunteggioCorrente
    FROM REGISTRAZIONE R JOIN UTENTE U
        ON R.UsernameUtente = U.Username
    WHERE R.NomeClassificaPrivata = nome
    ORDER BY U.PunteggioCorrente DESC;

END;

-- O9 - Visualizzazione del Punteggio ottenuto in un Gran Premio concluso

CREATE PROCEDURE punteggioUtentePerGranPremio (IN user VARCHAR(255), IN annoC INT, IN dataGP DATE, OUT punteggio INT)
BEGIN

    SELECT SUM(t.Punteggio) as punteggio_tot INTO punteggio
    FROM (SELECT pp.Punteggio
        FROM utente u JOIN gran_premio_programmato gpp JOIN squadra s
        JOIN scelta_pilota sp JOIN pilota_in_gran_premio rp JOIN posizione_punteggio pp
            ON (gpp.AnnoCampionato = s.AnnoCampionato
            AND gpp.Data = s.DataGranPremio
            AND u.Username = s.UsernameUtente
            AND sp.AnnoCampionato = s.AnnoCampionato
            AND sp.UsernameUtente = u.Username
            AND sp.DataGranPremio = s.DataGranPremio
            AND rp.DataGranPremio = s.DataGranPremio
            AND rp.AnnoCampionato = s.AnnoCampionato
            AND rp.CodicePilota = sp.CodicePilota
            AND rp.Posizione = pp.Posizione)
        WHERE gpp.AnnoCampionato = annoC
        AND gpp.Data = dataGP
        AND u.Username = user
        UNION ALL
        SELECT mgp.PunteggioOttenuto
        FROM utente u JOIN gran_premio_programmato gpp JOIN squadra s JOIN motorizzazione_in_gran_premio mgp
            ON (gpp.AnnoCampionato = s.AnnoCampionato
            AND gpp.Data = s.DataGranPremio
            AND u.Username = s.UsernameUtente
            AND mgp.AnnoCampionato = s.AnnoCampionato
            AND mgp.DataGranPremio = s.DataGranPremio
            AND mgp.NomeMotorizzazione = s.NomeMotorizzazione)
        WHERE gpp.AnnoCampionato = annoC
        AND gpp.Data = dataGP
        AND u.Username = user) AS t;

END;

-- O12a - Scambio Pilota

CREATE PROCEDURE scambioPilota (IN user VARCHAR(255), IN annoC INT, IN dataGP DATE, IN codPilCed INT, IN codPilAcq INT)
BEGIN

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  -- rollback any changes made in the transaction
        RESIGNAL;  -- raise again the sql exception to the caller
    END;

    START TRANSACTION;

        INSERT INTO SCAMBIO_PILOTA(AnnoCampionato, DataGranPremio, UsernameUtente, DataOra, PilotaCeduto, PilotaAcquisito)
            VALUE (annoC, dataGP, user, current_date, codPilCed, codPilAcq);

        UPDATE SCELTA_PILOTA
        SET CodicePilota = codPilAcq
        WHERE AnnoCampionato = annoC
        AND DataGranPremio = dataGP
        AND UsernameUtente = user
        AND CodicePilota = codPilCed;

        UPDATE SQUADRA S
        SET ScambiEffettuati = ScambiEffettuati + 1,
        BudgetRimanente = BudgetRimanente + (SELECT PGP.Prezzo FROM PILOTA_IN_GRAN_PREMIO PGP
                                                                        WHERE PGP.AnnoCampionato = S.AnnoCampionato AND PGP.DataGranPremio = S.DataGranPremio
                                                                        AND PGP.CodicePilota = codPilCed),
        BudgetRimanente = BudgetRimanente - (SELECT PGP.Prezzo FROM PILOTA_IN_GRAN_PREMIO PGP
                                                                        WHERE PGP.AnnoCampionato = S.AnnoCampionato AND PGP.DataGranPremio = S.DataGranPremio
                                                                        AND PGP.CodicePilota = codPilAcq)
        WHERE AnnoCampionato = annoC
        AND DataGranPremio = dataGP
        AND UsernameUtente = user;

    COMMIT;

END;

-- O12b - Scambio Motorizzazione

CREATE PROCEDURE scambioMotorizzazione (IN user VARCHAR(255), IN annoC INT, IN dataGP DATE, IN motCed VARCHAR(255), IN motAcq VARCHAR(255))
BEGIN

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  -- rollback any changes made in the transaction
        RESIGNAL;  -- raise again the sql exception to the caller
    END;

    START TRANSACTION;

        INSERT INTO scambio_motorizzazione(AnnoCampionato, DataGranPremio, UsernameUtente, DataOra, MotorizzazioneCeduta, MotorizzazioneAcquisita)
        VALUE (annoC, dataGP, user, current_date(), motCed, motAcq);

        UPDATE SQUADRA S
        SET ScambiEffettuati = ScambiEffettuati + 1,
        BudgetRimanente = BudgetRimanente + (SELECT MGP.Prezzo
                                             FROM MOTORIZZAZIONE_IN_GRAN_PREMIO MGP
                                             WHERE MGP.AnnoCampionato = S.AnnoCampionato AND MGP.DataGranPremio = S.DataGranPremio
                                             AND MGP.NomeMotorizzazione = motCed),
        BudgetRimanente = BudgetRimanente - (SELECT MGP.Prezzo FROM MOTORIZZAZIONE_IN_GRAN_PREMIO MGP
                                             WHERE MGP.AnnoCampionato = S.AnnoCampionato AND MGP.DataGranPremio = S.DataGranPremio
                                             AND MGP.NomeMotorizzazione = motAcq),
        NomeMotorizzazione = motAcq
        WHERE AnnoCampionato = annoC
        AND DataGranPremio = dataGP
        AND UsernameUtente = user;

    COMMIT;

END;

-- O13 - Creazione Classifica Privata

CREATE PROCEDURE creazioneClassificaPrivata (IN user VARCHAR(255), IN nome VARCHAR(255))
BEGIN

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  -- rollback any changes made in the transaction
        RESIGNAL;  -- raise again the sql exception to the caller
    END;

    START TRANSACTION;
        INSERT INTO CLASSIFICA_PRIVATA(Nome, NumeroPartecipanti)
        VALUE (nome, 0);

        CALL iscrizioneClassificaPrivata(user, nome);
    COMMIT;

END;

-- O14 – Iscrizione ad una Classifica Privata

CREATE PROCEDURE iscrizioneClassificaPrivata (IN user VARCHAR(255), IN nome VARCHAR(255))
BEGIN

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  -- rollback any changes made in the transaction
        RESIGNAL;  -- raise again the sql exception to the caller
    END;

    START TRANSACTION;
        INSERT INTO REGISTRAZIONE(UsernameUtente, NomeClassificaPrivata)
        VALUE (user, nome);

        UPDATE CLASSIFICA_PRIVATA C
        SET C.NumeroPartecipanti = C.NumeroPartecipanti + 1
        WHERE C.Nome = nome;
    COMMIT;

END;

-- O15 – Uscita da una Classifica Privata

CREATE PROCEDURE uscitaClassificaPrivata (IN user VARCHAR(255), IN nome VARCHAR(255))
BEGIN

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  -- rollback any changes made in the transaction
        RESIGNAL;  -- raise again the sql exception to the caller
    END;

    START TRANSACTION;

        DELETE FROM REGISTRAZIONE
        WHERE UsernameUtente = user
        AND NomeClassificaPrivata = nome;

        UPDATE CLASSIFICA_PRIVATA C
        SET C.NumeroPartecipanti = C.NumeroPartecipanti - 1
        WHERE C.Nome = nome;

        IF (SELECT C.NumeroPartecipanti FROM CLASSIFICA_PRIVATA C WHERE C.Nome = nome) = 0 THEN
             CALL eliminaClassificaPrivata(nome);
         END IF;

    COMMIT;

END;

-- O16 - Eliminazione Classifica Privata

CREATE PROCEDURE eliminaClassificaPrivata(IN nome VARCHAR(255))
BEGIN

    DELETE FROM CLASSIFICA_PRIVATA C
    WHERE C.Nome = nome;

END;

-- O17 - Inserimento dei Risultati di un Gran Premio concluso

CREATE PROCEDURE inserimentoRisultatoPilota (IN annoC INT, IN dataGP DATE, IN codPil INT, IN posPil INT)
BEGIN

    UPDATE PILOTA_IN_GRAN_PREMIO
    SET Posizione = posPil
    WHERE CodicePilota = codPil
    AND AnnoCampionato = annoC
    AND DataGranPremio = dataGP;

END;

CREATE PROCEDURE fineInserimentoRisultatiPiloti (IN annoC INT, IN dataGP DATE)
BEGIN

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  -- rollback any changes made in the transaction
        RESIGNAL;  -- raise again the sql exception to the caller
    END;

    START TRANSACTION;

        WITH PUNTEGGI_MOTORIZZAZIONI (NomeMotorizzazione, Punteggio) AS (
            SELECT T.NomeMotorizzazione, CAST(AVG(T.Punteggio) AS DECIMAL(0)) AS PunteggioMotorizzazione
                FROM (
                        SELECT SP.NomeMotorizzazione, PP.Punteggio
                        FROM SCUDERIA_PARTECIPANTE SP JOIN INGAGGIO_PILOTA IP JOIN PILOTA_IN_GRAN_PREMIO PIGP JOIN POSIZIONE_PUNTEGGIO PP
                            ON SP.AnnoCampionato = IP.AnnoCampionato AND SP.NomeScuderia = IP.NomeScuderia
                            AND PIGP.AnnoCampionato = SP.AnnoCampionato
                            AND IP.CodicePilota = PIGP.CodicePilota
                            AND PIGP.Posizione = PP.Posizione
                        WHERE PIGP.AnnoCampionato = annoC
                        AND PIGP.DataGranPremio = dataGP
                        UNION ALL
                        SELECT SP.NomeMotorizzazione, 0  AS PunteggioMotorizzazione
                        FROM SCUDERIA_PARTECIPANTE SP JOIN INGAGGIO_PILOTA IP JOIN PILOTA_IN_GRAN_PREMIO PIGP
                            ON SP.AnnoCampionato = IP.AnnoCampionato AND SP.NomeScuderia = IP.NomeScuderia
                            AND PIGP.AnnoCampionato = SP.AnnoCampionato
                            AND IP.CodicePilota = PIGP.CodicePilota
                        WHERE PIGP.AnnoCampionato = annoC
                        AND PIGP.DataGranPremio = dataGP
                        AND PIGP.Posizione IS NULL
                         ) AS T
                GROUP BY T.NomeMotorizzazione
        )
        UPDATE MOTORIZZAZIONE_IN_GRAN_PREMIO MIGP, PUNTEGGI_MOTORIZZAZIONI PM
        SET MIGP.PunteggioOttenuto = PM.Punteggio
        WHERE MIGP.NomeMotorizzazione = PM.NomeMotorizzazione
        AND AnnoCampionato = annoC
        AND DataGranPremio = dataGP;

        UPDATE GRAN_PREMIO_PROGRAMMATO
        SET Concluso = TRUE
        WHERE AnnoCampionato = annoC
        AND Data = dataGP;

    COMMIT;

END;

CREATE TRIGGER granPremioConclusoEvent AFTER UPDATE  ON GRAN_PREMIO_PROGRAMMATO
FOR EACH ROW
BEGIN

    IF NEW.Concluso = TRUE AND OLD.Concluso = FALSE THEN

        CALL aggiornamentoPunteggiUtentiGranPremioConcluso(NEW.AnnoCampionato, NEW.Data);

        -- QUESTO CONTROLLO SERVE PERCHE' SE QUESTO FOSSE L'ULTIMO CAMPIONATO DELL'ANNO
        -- SAREBBE SBAGLIATO INSERIRE I PILOTI/MOTORIZZAZIONI_IN_GRAN_PREMIO PER L'ANNO SUCCESSIVO.
        -- SIA PERCHE' NON SAREBBERO PRESENTI GLI INGAGGI PILOTI/MOTORIZZAZIONI SIA PERCHE' NON
        -- E' DETTO CHE TUTTI I PILOTI DI QUESTO ANNO PARTECIPINO ANCHE A QUELLO PROSSIMO.
        IF  OLD.AnnoCampionato = NEW.AnnoCampionato THEN
            CALL aggiornamentoPrezziPilotiGPConcluso(NEW.AnnoCampionato, NEW.Data);
            CALL aggiornamentoPrezziMotorizzazioniGPConcluso();
            CALL copiaSquadrePilotiGPConcluso(NEW.AnnoCampionato, NEW.Data);
        END IF;

    END IF;

END;

-- O18 - Copia della Squadra per il Gran Premio successivo

CREATE PROCEDURE copiaSquadrePilotiGPConcluso (IN annoC INT, IN dataGP DATE)
BEGIN

    DECLARE nextAnnoC INT;
    DECLARE nextDataGP DATE;

    -- GETTING NEXT GRAND PRIX
    CALL visualizzaGranPremioCorrente(nextAnnoC, nextDataGP, @useless, @useless, @useless);

    INSERT INTO SQUADRA(AnnoCampionato, DataGranPremio, UsernameUtente, ScambiEffettuati, BudgetRimanente, NomeMotorizzazione)
    SELECT nextAnnoC AS AnnoCampionato, nextDataGP AS DataGranPremio, S.UsernameUtente, 0 AS ScambiEffettuati, S.BudgetRimanente, S.NomeMotorizzazione -- metteere default su scambi effettuati
    FROM SQUADRA S
    WHERE S.AnnoCampionato = annoC
    AND S.DataGranPremio = dataGP;

    INSERT INTO SCELTA_PILOTA(CodicePilota, AnnoCampionato, DataGranPremio, UsernameUtente)
    SELECT S.CodicePilota, nextAnnoC AS AnnoCampionato, nextDataGP AS DataGranPremio, S.UsernameUtente
    FROM SCELTA_PILOTA S
    WHERE S.AnnoCampionato = annoC
    AND S.DataGranPremio = dataGP;

END;

-- O19 - Aggiornamento Punteggi degli Utenti

CREATE PROCEDURE aggiornamentoPunteggiUtentiGranPremioConcluso (IN annoC INT, IN dataGP DATE)
BEGIN

    WITH PUNTEGGI_OTTENUTI_UTENTE (UsernameUtente, PunteggioOttenuto) AS (
        SELECT t.UsernameUtente, SUM(t.Punteggio) as PunteggioOttenuto
            FROM (
                SELECT S.UsernameUtente, PP.Punteggio
                FROM GRAN_PREMIO_PROGRAMMATO GPP JOIN SQUADRA S JOIN SCELTA_PILOTA SP JOIN PILOTA_IN_GRAN_PREMIO PGP JOIN POSIZIONE_PUNTEGGIO PP
                    ON GPP.AnnoCampionato = S.AnnoCampionato
                    AND GPP.Data = S.DataGranPremio
                    AND S.AnnoCampionato = SP.AnnoCampionato
                    AND S.UsernameUtente = SP.UsernameUtente
                    AND S.DataGranPremio = SP.DataGranPremio
                    AND SP.DataGranPremio = PGP.DataGranPremio
                    AND SP.AnnoCampionato = PGP.AnnoCampionato
                    AND SP.CodicePilota = PGP.CodicePilota
                    AND PGP.Posizione = PP.Posizione
                WHERE GPP.AnnoCampionato = annoC
                AND GPP.Data = dataGP
                UNION ALL
                SELECT S.UsernameUtente, MGP.PunteggioOttenuto
                FROM GRAN_PREMIO_PROGRAMMATO GPP JOIN SQUADRA S JOIN motorizzazione_in_gran_premio MGP
                    ON GPP.AnnoCampionato = S.AnnoCampionato
                    AND GPP.Data = S.DataGranPremio
                    AND S.AnnoCampionato = MGP.AnnoCampionato
                    AND S.DataGranPremio = MGP.DataGranPremio
                    AND S.NomeMotorizzazione = MGP.NomeMotorizzazione
                WHERE GPP.AnnoCampionato = annoC
                AND GPP.Data = dataGP) AS T
            GROUP BY T.UsernameUtente
        )
    UPDATE UTENTE U JOIN PUNTEGGI_OTTENUTI_UTENTE P
        ON U.Username = P.UsernameUtente
    SET U.PunteggioCorrente = U.PunteggioCorrente + P.PunteggioOttenuto;

END;

-- O20 - Aggiornamento prezzi Piloti

CREATE PROCEDURE aggiornamentoPrezziPilotiGPConcluso (IN annoC INT, IN dataGP DATE)
BEGIN

    DECLARE nextAnnoC INT;
    DECLARE nextDataGP DATE;

    -- GETTING NEXT GRAND PRIX
    CALL visualizzaGranPremioCorrente(nextAnnoC, nextDataGP, @useless, @useless, @useless);

    INSERT INTO PILOTA_IN_GRAN_PREMIO(AnnoCampionato, DataGranPremio, CodicePilota, Prezzo)
        SELECT nextAnnoC, nextDataGP, PGP.CodicePilota, (PGP.Prezzo + (P.Punteggio-10)*100000) AS NuovoPrezzo
        FROM PILOTA_IN_GRAN_PREMIO PGP JOIN POSIZIONE_PUNTEGGIO P
            ON PGP.Posizione = P.Posizione
        WHERE PGP.AnnoCampionato = annoC
        AND PGP.DataGranPremio = dataGP
        UNION ALL
        SELECT nextAnnoC, nextDataGP, PGP.CodicePilota, (PGP.Prezzo -1500000) AS NuovoPrezzo
        FROM PILOTA_IN_GRAN_PREMIO PGP
        WHERE PGP.AnnoCampionato = annoC
        AND PGP.DataGranPremio = dataGP
        AND PGP.Posizione IS NULL;

END;

-- O21 - Aggiornamento prezzi Motorizzazioni

CREATE PROCEDURE aggiornamentoPrezziMotorizzazioniGPConcluso ()
BEGIN

    DECLARE nextAnnoC INT;
    DECLARE nextDataGP DATE;

    -- GETTING NEXT GRAND PRIX
    CALL visualizzaGranPremioCorrente(nextAnnoC, nextDataGP, @useless, @useless, @useless);

    INSERT into MOTORIZZAZIONE_IN_GRAN_PREMIO(AnnoCampionato, DataGranPremio, NomeMotorizzazione, Prezzo)
        SELECT nextAnnoC, nextDataGP, SP.NomeMotorizzazione, AVG(PGP.Prezzo) AS PrezzoMotorizzazione
        FROM SCUDERIA_PARTECIPANTE SP JOIN INGAGGIO_PILOTA IP JOIN PILOTA_IN_GRAN_PREMIO PGP
            ON SP.AnnoCampionato = IP.AnnoCampionato AND SP.NomeScuderia = IP.NomeScuderia
            AND PGP.AnnoCampionato = SP.AnnoCampionato
            AND IP.CodicePilota = PGP.CodicePilota
        WHERE PGP.AnnoCampionato = nextAnnoC
        AND PGP.DataGranPremio = nextDataGP
        GROUP BY SP.NomeMotorizzazione;

END;

-- O22 - Visualizzazione del Valore di una Squadra.sql

CREATE PROCEDURE valoreSquadra (IN user VARCHAR(255), IN annoC INT, IN dataGP DATE, OUT result INT)
BEGIN

    SELECT SUM(t.Prezzo) as ValoreSquadraTotale INTO result
    FROM(
        SELECT pgp.Prezzo
            FROM squadra s JOIN gran_premio_programmato gpp JOIN scelta_pilota sp JOIN pilota_in_gran_premio pgp
                ON(s.AnnoCampionato=gpp.AnnoCampionato
                    AND s.DataGranPremio=gpp.Data
                    AND s.AnnoCampionato=sp.AnnoCampionato
                    AND S.DataGranPremio=sp.DataGranPremio
                    AND S.UsernameUtente=sp.UsernameUtente
                    AND sp.CodicePilota=pgp.CodicePilota
                    AND pgp.AnnoCampionato=s.AnnoCampionato
                    AND pgp.DataGranPremio=s.DataGranPremio)
        WHERE s.UsernameUtente=user
        AND gpp.AnnoCampionato=annoC
        AND gpp.Data=dataGP
        UNION
        SELECT mgp.Prezzo
        FROM motorizzazione_in_gran_premio mgp JOIN squadra s join gran_premio_programmato gpp
                    ON( s.NomeMotorizzazione=mgp.NomeMotorizzazione
                    AND mgp.DataGranPremio=s.DataGranPremio
                    AND mgp.AnnoCampionato=S.AnnoCampionato)
        WHERE s.UsernameUtente=user
        AND gpp.AnnoCampionato=annoC
        AND gpp.Data=dataGP
        ) as t;

END;

-- O23 - Login Utente

CREATE PROCEDURE loginUtente (IN user VARCHAR(255), IN pass VARCHAR(255), OUT success BOOL)
BEGIN

    DECLARE c INT;

    SELECT count(*) INTO c FROM UTENTE
    WHERE Username = user
    AND Password = pass;

    IF c = 0 THEN
        SET success = FALSE;
    ELSE
        SET success = TRUE;
    END IF;

END;

-- O24 - Controllo se Utente ha Squadra per Gran Premio

CREATE PROCEDURE checkUtenteHaSquadraPerGranPremio (IN user VARCHAR(255), IN annoC INT, IN dataGP DATE, OUT result BOOL)
BEGIN
    DECLARE c INT;

    SELECT COUNT(*) INTO c
    FROM SQUADRA S
    WHERE S.UsernameUtente = user
    AND S.AnnoCampionato = annoC
    AND S.DataGranPremio = dataGP;

    IF c = 0 THEN
        SET result = FALSE;
    ELSE
        SET result = TRUE;
    END IF;

END;

-- O25 - Visualizza Gran Premio Corrente.sql

CREATE PROCEDURE visualizzaGranPremioCorrente (OUT annoC INT,
                                                                                OUT dataGP DATE,
                                                                                OUT stato VARCHAR(255),
                                                                                OUT nome VARCHAR(255),
                                                                                OUT concluso BOOL)
BEGIN

    SELECT GPP.AnnoCampionato, GPP. Data, GPP.Stato, GPP.Nome, GPP.Concluso INTO annoC, dataGP, stato, nome, concluso
    FROM GRAN_PREMIO_PROGRAMMATO GPP
    WHERE GPP.Concluso = FALSE
    ORDER BY GPP.AnnoCampionato ASC, GPP.Data ASC
    LIMIT 1;

END;

-- O26 - Visualizza Nomi Classifiche Private Utente

CREATE PROCEDURE visualizzaNomiClassifichePrivateUtente (IN user VARCHAR(255))
BEGIN

    SELECT R.NomeClassificaPrivata
    FROM REGISTRAZIONE R
    WHERE R.UsernameUtente = user;

END;

-- O27 - Visualizzazione dei Gran Premi di un Campionato

CREATE PROCEDURE visualizzaGranPremiPerCampionato (IN annoC INT)
BEGIN

    SELECT *
    FROM GRAN_PREMIO_PROGRAMMATO GPP
    WHERE GPP.AnnoCampionato = annoC;

END;

-- O28 - Visualizza Punteggio attuale Utente

CREATE PROCEDURE visualizzaPunteggioAttualeUtente (IN user VARCHAR(255), OUT punteggio INT)
BEGIN

    SELECT U.PunteggioCorrente INTO punteggio
    FROM UTENTE U
    WHERE U.Username = user;

END;