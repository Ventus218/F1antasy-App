# O4 - Visualizzazione di tutti i Piloti del Campionato corrente in ordine decrescente di prezzo
SELECT P.*, PP.Prezzo
FROM SCUDERIA_PARTECIPANTE SP JOIN INGAGGIO_PILOTA IP JOIN PILOTA P JOIN PREZZO_PILOTA PP
    ON SP.AnnoCampionato = IP.AnnoCampionato AND SP.NomeScuderia = IP.NomeScuderia
    AND P.Codice = IP.CodicePilota
    AND PP.CodicePilota = P.Codice AND PP.AnnoCampionato = SP.AnnoCampionato
WHERE SP.AnnoCampionato = (SELECT Anno
                                                FROM CAMPIONATO
                                                ORDER BY Anno DESC
                                                LIMIT 1)
AND PP.DataGranPremio = ( SELECT Data
                                            FROM GRAN_PREMIO_PROGRAMMATO
                                            ORDER BY Data DESC
                                            LIMIT 1)
ORDER BY PP.Prezzo DESC ;

