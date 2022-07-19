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
