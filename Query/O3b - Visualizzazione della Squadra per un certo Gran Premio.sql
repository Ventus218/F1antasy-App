# O3b - Visualizzazione della Squadra per un certo Gran Premio

SELECT S.NomeMotorizzazione
FROM SQUADRA S
WHERE S.UsernameUtente = 'CiccioCarluz'
AND S.AnnoCampionato = 2021
AND S.DataGranPremio = '2021-05-22';