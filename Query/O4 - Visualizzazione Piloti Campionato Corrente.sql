# O4 - Visualizzazione di tutti i Piloti del Campionato corrente in ordine decrescente di prezzo

WITH ULTMO_GRAN_PREMIO_PROGRAMMATO_CONCLUSO (AnnoCampionato, Data) AS (
    SELECT AnnoCampionato, Data
    FROM GRAN_PREMIO_PROGRAMMATO
    WHERE Concluso = TRUE
    ORDER BY AnnoCampionato DESC, Data DESC
    LIMIT 1)
SELECT P.*, PP.Prezzo
FROM SCUDERIA_PARTECIPANTE SP JOIN INGAGGIO_PILOTA IP JOIN PILOTA P JOIN PREZZO_PILOTA PP JOIN ULTMO_GRAN_PREMIO_PROGRAMMATO_CONCLUSO U
    ON SP.AnnoCampionato = IP.AnnoCampionato AND SP.NomeScuderia = IP.NomeScuderia
    AND P.Codice = IP.CodicePilota
    AND PP.CodicePilota = P.Codice AND PP.AnnoCampionato = SP.AnnoCampionato
WHERE SP.AnnoCampionato = U.AnnoCampionato
AND PP.DataGranPremio = U.Data
ORDER BY PP.Prezzo DESC ;

