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
