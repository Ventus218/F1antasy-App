-- O21 - Aggiornamento prezzi Motorizzazioni

CREATE PROCEDURE aggiornamentoPrezziMotorizzazioniGPConcluso ()
BEGIN

    DECLARE nextAnnoC INT;
    DECLARE nextDataGP DATE;

    -- GETTING NEXT GRAND PRIX
    CALL visualizzaGranPremioCorrente(nextAnnoC, nextDataGP, @useless, @useless, @useless);

    INSERT into MOTORIZZAZIONE_IN_GRAN_PREMIO(AnnoCampionato, DataGranPremio, NomeMotorizzazione, Prezzo)
        SELECT nextAnnoC, nextDataGP, SP.NomeMotorizzazione, ROUND(AVG(PGP.Prezzo)/100000)*100000 AS PrezzoMotorizzazione
        FROM SCUDERIA_PARTECIPANTE SP JOIN INGAGGIO_PILOTA IP JOIN PILOTA_IN_GRAN_PREMIO PGP
            ON SP.AnnoCampionato = IP.AnnoCampionato AND SP.NomeScuderia = IP.NomeScuderia
            AND PGP.AnnoCampionato = SP.AnnoCampionato
            AND IP.CodicePilota = PGP.CodicePilota
        WHERE PGP.AnnoCampionato = nextAnnoC
        AND PGP.DataGranPremio = nextDataGP
        GROUP BY SP.NomeMotorizzazione;

END;
