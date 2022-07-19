-- O27 - Visualizzazione dei Gran Premi di un Campionato

CREATE PROCEDURE visualizzaGranPremiPerCampionato (IN annoC INT)
BEGIN

    SELECT *
    FROM GRAN_PREMIO_PROGRAMMATO GPP
    WHERE GPP.AnnoCampionato = annoC;

END;
