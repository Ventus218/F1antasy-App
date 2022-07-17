# O20 - Aggiornamento prezzo Pilota

INSERT INTO PILOTA_IN_GRAN_PREMIO(AnnoCampionato, DataGranPremio, CodicePilota, Prezzo)
    SELECT 2021, '2021-05-29', PGP.CodicePilota, (PGP.Prezzo + (P.Punteggio-10)*100000) AS NuovoPrezzo
    FROM PILOTA_IN_GRAN_PREMIO PGP JOIN POSIZIONE_PUNTEGGIO P
        ON PGP.Posizione = P.Posizione
    WHERE PGP.AnnoCampionato = 2021
    AND PGP.DataGranPremio = '2021-05-22'
    UNION ALL
    SELECT 2021, '2021-05-29', PGP.CodicePilota, (PGP.Prezzo -1500000) AS NuovoPrezzo
    FROM PILOTA_IN_GRAN_PREMIO PGP
    WHERE PGP.AnnoCampionato = 2021
    AND PGP.DataGranPremio = '2021-05-22'
    AND PGP.Posizione IS NULL;



# UTILI PER CONTROLLARE
SELECT * FROM PILOTA_IN_GRAN_PREMIO
WHERE AnnoCampionato = 2021
AND DataGranPremio = '2021-05-22';

SELECT * FROM PILOTA_IN_GRAN_PREMIO
WHERE AnnoCampionato = 2021
AND DataGranPremio = '2021-05-29';

delete from PILOTA_IN_GRAN_PREMIO where true;
