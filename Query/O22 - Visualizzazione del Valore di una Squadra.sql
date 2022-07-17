SELECT SUM(t.Prezzo) as ValoreSquadraTotale
FROM(
SELECT pgp.Prezzo
    FROM motorizzazione_in_gran_premio mgp JOIN squadra s JOIN utente u JOIN gran_premio_programmato gpp JOIN scelta_pilota sp JOIN pilota_in_gran_premio pgp
        ON(s.AnnoCampionato=gpp.AnnoCampionato
            AND s.DataGranPremio=gpp.Data
            AND S.UsernameUtente=U.Username
            AND s.AnnoCampionato=sp.AnnoCampionato
            AND S.DataGranPremio=sp.DataGranPremio
            AND S.UsernameUtente=sp.UsernameUtente
            AND sp.CodicePilota=pgp.CodicePilota
            AND pgp.AnnoCampionato=s.AnnoCampionato
            AND pgp.DataGranPremio=s.DataGranPremio)
WHERE u.username='CiccioCarluz'
AND gpp.AnnoCampionato='2021'
AND gpp.Data='2021-05-22'
UNION
SELECT mgp.Prezzo
FROM motorizzazione_in_gran_premio mgp JOIN squadra s JOIN utente u join gran_premio_programmato gpp
            ON( s.NomeMotorizzazione=mgp.NomeMotorizzazione
            AND mgp.DataGranPremio=s.DataGranPremio
            AND mgp.AnnoCampionato=S.AnnoCampionato)
WHERE u.username='CiccioCarluz'
AND gpp.AnnoCampionato='2021'
AND gpp.Data='2021-05-22') as t;