# O12a - Scambio Pilota

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
