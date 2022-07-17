#O8
SELECT P.Nome, P.Cognome, PP.Punteggio
FROM PILOTA P JOIN POSIZIONE_PUNTEGGIO PP JOIN PILOTA_IN_GRAN_PREMIO RP
    ON PP.Posizione = RP.Posizione
    AND P.Codice = RP.CodicePilota

WHERE RP.AnnoCampionato='2021'
AND RP.DataGranPremio='2021-05-22'
#ORDER BY PP.Punteggio DESC
UNION ALL
SELECT P.Nome, P.Cognome, 0 AS Punteggio
FROM PILOTA P JOIN PILOTA_IN_GRAN_PREMIO PGP
    ON P.Codice = PGP.CodicePilota

WHERE PGP.AnnoCampionato='2021'
AND PGP.DataGranPremio='2021-05-22'
AND PGP.Posizione IS NULL;

#union con altra query SOLO QUELLI CON POSIZIONE NULL PERCHè NON QUALIFICATI