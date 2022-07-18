CREATE PROCEDURE visualizzaGranPremiPerCampionato (IN annoC INT)
BEGIN

    SELECT *
    FROM GRAN_PREMIO_PROGRAMMATO GPP
    WHERE GPP.AnnoCampionato = annoC;

END;
