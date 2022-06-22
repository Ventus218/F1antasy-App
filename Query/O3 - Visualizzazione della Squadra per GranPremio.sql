# O3 - Visualizzazione della Squadra per un certo Gran Premio
SELECT *
FROM UTENTE U JOIN GRAN_PREMIO_PROGRAMMATO GPP JOIN SCELTA_PILOTA SP
ON (SP.UsernameUtente = U.Username AND SP.AnnoCampionato = GPP.AnnoCampionato AND SP.DataGranPremio = GPP.DATA)
WHERE U.Username = 'CiccioCarluz'
AND GPP.AnnoCampionato = 2021
AND GPP.Data = '2021-05-22';