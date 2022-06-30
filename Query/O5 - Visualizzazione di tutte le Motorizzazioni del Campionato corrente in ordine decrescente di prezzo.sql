# O5 - Visualizzazione di tutte le Motorizzazioni del Campionato corrente in ordine decrescente di prezzo

WITH ULTMO_GRAN_PREMIO_PROGRAMMATO_CONCLUSO (AnnoCampionato, Data) AS (
    SELECT AnnoCampionato, Data
    FROM GRAN_PREMIO_PROGRAMMATO
    WHERE Concluso = TRUE
    ORDER BY AnnoCampionato DESC, Data DESC
    LIMIT 1)
SELECT pm.NomeMotorizzazione, pm.Prezzo
FROM prezzo_motorizzazione pm JOIN ULTMO_GRAN_PREMIO_PROGRAMMATO_CONCLUSO U
WHERE pm.AnnoCampionato = U.AnnoCampionato
AND pm.DataGranPremio = U.Data
ORDER BY pm.Prezzo DESC;
