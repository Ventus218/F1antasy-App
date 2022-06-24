# O20 - Aggiornamento prezzo Pilota

INSERT INTO PREZZO_PILOTA(AnnoCampionato, DataGranPremio, CodicePilota, Prezzo)
    SELECT 2021, '2021-05-29', PP.CodicePilota, (PP.Prezzo + (P.Punteggio-10)*100000) AS NuovoPrezzo
    FROM PREZZO_PILOTA PP JOIN RISULTATO_PILOTA RP JOIN POSIZIONE_PUNTEGGIO P
        ON PP.AnnoCampionato = RP.AnnoCampionato
        AND PP.DataGranPremio = RP.DataGranPremio
        AND PP.CodicePilota = RP.CodicePilota
        AND RP.Posizione = P.Posizione
    WHERE PP.AnnoCampionato = 2021
    AND PP.DataGranPremio = '2021-05-22'
    UNION
    SELECT 2021, '2021-05-29', PP.CodicePilota, (PP.Prezzo -1500000) AS NuovoPrezzo
    FROM PREZZO_PILOTA PP JOIN RISULTATO_PILOTA RP
        ON PP.AnnoCampionato = RP.AnnoCampionato
        AND PP.DataGranPremio = RP.DataGranPremio
        AND PP.CodicePilota = RP.CodicePilota
    WHERE PP.AnnoCampionato = 2021
    AND PP.DataGranPremio = '2021-05-22'
    AND RP.Posizione IS NULL;



# UTILI PER CONTROLLARE
SELECT * FROM PREZZO_PILOTA
WHERE AnnoCampionato = 2021
AND DataGranPremio = '2021-05-22';

SELECT * FROM PREZZO_PILOTA
WHERE AnnoCampionato = 2021
AND DataGranPremio = '2021-05-29';

delete from RISULTATO_PILOTA where true;




