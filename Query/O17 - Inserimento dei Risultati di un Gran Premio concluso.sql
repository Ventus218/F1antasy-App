# O17 - Inserimento dei Risultati di un Gran Premio concluso
INSERT INTO RISULTATO_PILOTA(CodicePilota, Posizione, AnnoCampionato, DataGranPremio)
    VALUES
        (1, 1, 2021, '2021-05-22'),
        (3, 2, 2021, '2021-05-22'),
        (2, 3, 2021, '2021-05-22'),
        (5, 4, 2021, '2021-05-22'),
        (6, 5, 2021, '2021-05-22'),
        (8, 6, 2021, '2021-05-22'),
        (7, 7, 2021, '2021-05-22'),
        (11, 8, 2021, '2021-05-22'),
        (4, 9, 2021, '2021-05-22'),
        (9, 10, 2021, '2021-05-22'),
        (10, 11, 2021, '2021-05-22'),
        (15, 12, 2021, '2021-05-22'),
        (12, 13, 2021, '2021-05-22'),
        (16, 14, 2021, '2021-05-22'),
        (13, 15, 2021, '2021-05-22'),
        (14, 16, 2021, '2021-05-22'),
        (19, 17, 2021, '2021-05-22'),
        (17, 18, 2021, '2021-05-22'),
        (18, NULL, 2021, '2021-05-22'),
        (20, NULL, 2021, '2021-05-22');

INSERT INTO RISULTATO_MOTORIZZAZIONE(AnnoCampionato, DataGranPremio, NomeMotorizzazione, PunteggioOttenuto)
    SELECT 2021 AS AnnoCampionato, '2021-05-22' AS DataGranPremio, T.NomeMotorizzazione, CAST(AVG(T.Punteggio) AS DECIMAL(0)) AS PunteggioMotorizzazione
    FROM (
            SELECT SP.NomeMotorizzazione, PP.Punteggio
            FROM SCUDERIA_PARTECIPANTE SP JOIN INGAGGIO_PILOTA IP JOIN RISULTATO_PILOTA RP JOIN POSIZIONE_PUNTEGGIO PP
                ON SP.AnnoCampionato = IP.AnnoCampionato AND SP.NomeScuderia = IP.NomeScuderia
                AND RP.AnnoCampionato = SP.AnnoCampionato
                AND IP.CodicePilota = RP.CodicePilota
                AND RP.Posizione = PP.Posizione
            WHERE RP.AnnoCampionato = 2021
            AND RP.DataGranPremio = '2021-05-22'
            UNION ALL
            SELECT SP.NomeMotorizzazione, 0  AS PunteggioMotorizzazione
            FROM SCUDERIA_PARTECIPANTE SP JOIN INGAGGIO_PILOTA IP JOIN RISULTATO_PILOTA RP
                ON SP.AnnoCampionato = IP.AnnoCampionato AND SP.NomeScuderia = IP.NomeScuderia
                AND RP.AnnoCampionato = SP.AnnoCampionato
                AND IP.CodicePilota = RP.CodicePilota
            WHERE RP.AnnoCampionato = 2021
            AND RP.DataGranPremio = '2021-05-22'
            AND RP.Posizione IS NULL
             ) AS T
    GROUP BY T.NomeMotorizzazione;


UPDATE GRAN_PREMIO_PROGRAMMATO
SET Concluso = TRUE
WHERE AnnoCampionato = 2021
AND Data = '2021-05-22';