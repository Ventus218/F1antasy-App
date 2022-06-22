# O4 - Visualizzazione della Squadra per un certo Gran Premio
SELECT P.Nome, P.Cognome
FROM INGAGGIO_PILOTA IP JOIN CAMPIONATO C JOIN PILOTA P
ON (IP.Annocampionato=C.Anno AND IP.CodicePilota=P.Codice)
WHERE C.anno="2022"




