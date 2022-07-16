# O3a - Visualizzazione della Squadra per un certo Gran Premio
SELECT P.*
FROM UTENTE U JOIN SQUADRA S JOIN SCELTA_PILOTA SP JOIN PILOTA P
    ON U.Username = S.UsernameUtente
    AND SP.UsernameUtente = U.Username AND SP.AnnoCampionato = S.AnnoCampionato AND SP.DataGranPremio = S.DataGranPremio
    AND P.Codice = SP.CodicePilota
WHERE U.Username = 'CiccioCarluz'
AND S.AnnoCampionato = 2021
AND S.DataGranPremio = '2021-05-22';