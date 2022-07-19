-- O8 - Visualizzazione Risultati Gran Premio

SELECT P.Nome, P.Cognome, PP.Punteggio
FROM PILOTA P JOIN POSIZIONE_PUNTEGGIO PP JOIN PILOTA_IN_GRAN_PREMIO PGP
    ON PP.Posizione = RP.Posizione
    AND P.Codice = PGP.CodicePilota
WHERE PGP.AnnoCampionato= 2022
AND PGP.DataGranPremio='2022-05-22'
UNION ALL
SELECT P.Nome, P.Cognome, 0 AS Punteggio
FROM PILOTA P JOIN PILOTA_IN_GRAN_PREMIO PGP
    ON P.Codice = PGP.CodicePilota
WHERE PGP.AnnoCampionato=2022
AND PGP.DataGranPremio='2022-05-22'
AND PGP.Posizione IS NULL;

-- union con altra query SOLO QUELLI CON POSIZIONE NULL PERCHè NON QUALIFICATI