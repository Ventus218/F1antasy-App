# O2 - Inizializzazione della Squadra (scelta Piloti e Motorizzazione)
DROP PROCEDURE inizializzazioneSquadra
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
         100000000 - (SELECT Prezzo FROM PREZZO_MOTORIZZAZIONE -- mettere default al posto di 100000000 ma controllare se funziona
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
    SET  BudgetRimanente = BudgetRimanente - (  SELECT sum(Prezzo) AS PrezzoTotale FROM PREZZO_PILOTA
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

# Need to add in DDL
/*
    ALTER TABLE SQUADRA
    ADD CONSTRAINT CHK_BudgetRimanente_Positive CHECK (SQUADRA.BudgetRimanente >= 0);
*/
