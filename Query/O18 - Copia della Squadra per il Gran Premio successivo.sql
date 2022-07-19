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
