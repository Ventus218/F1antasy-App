# O21 - Aggiornamento prezzo Motorizzazione

INSERT into MOTORIZZAZIONE_IN_GRAN_PREMIO(AnnoCampionato, DataGranPremio, NomeMotorizzazione, Prezzo)
SELECT 2021, '2021-05-29', SP.NomeMotorizzazione, AVG(PGP.Prezzo) AS PrezzoMotorizzazione
FROM SCUDERIA_PARTECIPANTE SP JOIN INGAGGIO_PILOTA IP JOIN PILOTA_IN_GRAN_PREMIO PGP
    ON SP.AnnoCampionato = IP.AnnoCampionato AND SP.NomeScuderia = IP.NomeScuderia
    AND PGP.AnnoCampionato = SP.AnnoCampionato
    AND IP.CodicePilota = PGP.CodicePilota
WHERE PGP.AnnoCampionato = 2021
AND PGP.DataGranPremio = '2021-05-22'
GROUP BY SP.NomeMotorizzazione;

SELECT * FROM MOTORIZZAZIONE_IN_GRAN_PREMIO;
