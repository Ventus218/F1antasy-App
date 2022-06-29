SELECT SUM(t.Prezzo) as ValoreSquadraTotale
FROM(
SELECT pp.Prezzo
    FROM prezzo_motorizzazione pm JOIN squadra s JOIN utente u JOIN gran_premio_programmato gpp JOIN scelta_pilota sp JOIN prezzo_pilota pp
        ON(s.AnnoCampionato=gpp.AnnoCampionato
            AND s.DataGranPremio=gpp.Data
            AND S.UsernameUtente=U.Username
            AND s.AnnoCampionato=sp.AnnoCampionato
            AND S.DataGranPremio=sp.DataGranPremio
            AND S.UsernameUtente=sp.UsernameUtente
            AND sp.CodicePilota=pp.CodicePilota
            AND pp.AnnoCampionato=s.AnnoCampionato
            AND pp.DataGranPremio=s.DataGranPremio)
WHERE u.username='CiccioCarluz'
AND gpp.AnnoCampionato='2021'
AND gpp.Data='2021-05-22'
UNION
SELECT pm.Prezzo
FROM prezzo_motorizzazione pm JOIN squadra s JOIN utente u join gran_premio_programmato gpp
            ON( s.NomeMotorizzazione=pm.NomeMotorizzazione
            AND pm.DataGranPremio=s.DataGranPremio
            AND pm.AnnoCampionato=S.AnnoCampionato)
WHERE u.username='CiccioCarluz'
AND gpp.AnnoCampionato='2021'
AND gpp.Data='2021-05-22') as t;