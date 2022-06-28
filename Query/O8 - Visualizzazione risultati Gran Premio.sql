#O8
SELECT P.Nome, P.Cognome, PP.Punteggio
FROM Pilota P JOIN POSIZIONE_PUNTEGGIO PP JOIN risultato_pilota RP
    ON PP.Posizione = RP.Posizione
    AND P.Codice = RP.CodicePilota

WHERE RP.AnnoCampionato='2021'
AND RP.DataGranPremio='2021-05-22'
#ORDER BY PP.Punteggio DESC
UNION
SELECT P.Nome, P.Cognome, 0 AS Punteggio
FROM Pilota P JOIN risultato_pilota RP
    ON P.Codice = RP.CodicePilota

WHERE RP.AnnoCampionato='2021'
AND RP.DataGranPremio='2021-05-22'
AND RP.Posizione IS NULL;

#union con altra query SOLO QUELLI CON POSIZIONE NULL PERCHè NON QUALIFICATI