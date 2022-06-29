# O5 - Visualizzazione di tutte le Motorizzazioni del Campionato corrente in ordine decrescente di prezzo

SELECT pm.NomeMotorizzazione, pm.Prezzo
FROM prezzo_motorizzazione pm
WHERE pm.AnnoCampionato = (SELECT gpp.AnnoCampionato
                         FROM gran_premio_programmato gpp
                         ORDER BY Data DESC
                         LIMIT 1)
AND pm.DataGranPremio = (SELECT gpp.Data
                         FROM gran_premio_programmato gpp
                         ORDER BY Data DESC
                         LIMIT 1)
ORDER BY pm.Prezzo DESC;

/* DA TESTARE */