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
