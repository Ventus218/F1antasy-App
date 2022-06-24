# O21 - Aggiornamento prezzo Motorizzazione

INSERT into PREZZO_MOTORIZZAZIONE(AnnoCampionato, DataGranPremio, NomeMotorizzazione, Prezzo)
SELECT 2021, '2021-05-29', SP.NomeMotorizzazione, AVG(PP.Prezzo) AS PrezzoMotorizzazione
FROM SCUDERIA_PARTECIPANTE SP JOIN INGAGGIO_PILOTA IP JOIN PREZZO_PILOTA PP
    ON SP.AnnoCampionato = IP.AnnoCampionato AND SP.NomeScuderia = IP.NomeScuderia
    AND PP.AnnoCampionato = SP.AnnoCampionato
    AND IP.CodicePilota = PP.CodicePilota
WHERE PP.AnnoCampionato = 2021
AND PP.DataGranPremio = '2021-05-22'
GROUP BY SP.NomeMotorizzazione;

SELECT * FROM PREZZO_MOTORIZZAZIONE;
