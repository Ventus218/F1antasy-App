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