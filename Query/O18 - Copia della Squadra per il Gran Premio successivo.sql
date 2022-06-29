# O18 - Copia della Squadra per il Gran Premio successivo

INSERT INTO SQUADRA(AnnoCampionato, DataGranPremio, UsernameUtente, ScambiEffettuati, BudgetRimanente, NomeMotorizzazione)
WITH ULTMO_GRAN_PREMIO_PROGRAMMATO_CONCLUSO (AnnoCampionato, Data) AS (
    SELECT AnnoCampionato, Data
    FROM GRAN_PREMIO_PROGRAMMATO
    WHERE Concluso = TRUE
    ORDER BY AnnoCampionato DESC, Data DESC
    LIMIT 1)
SELECT 2021 AS AnnoCampionato, '2021-05-29' AS DataGranPremio, S.UsernameUtente, 0 AS ScambiEffettuati, S.BudgetRimanente, S.NomeMotorizzazione
FROM SQUADRA S JOIN ULTMO_GRAN_PREMIO_PROGRAMMATO_CONCLUSO U
WHERE S.AnnoCampionato = U.AnnoCampionato
AND S.DataGranPremio = U.Data;

INSERT INTO SCELTA_PILOTA(CodicePilota, AnnoCampionato, DataGranPremio, UsernameUtente)
WITH ULTMO_GRAN_PREMIO_PROGRAMMATO_CONCLUSO (AnnoCampionato, Data) AS (
    SELECT AnnoCampionato, Data
    FROM GRAN_PREMIO_PROGRAMMATO
    WHERE Concluso = TRUE
    ORDER BY AnnoCampionato DESC, Data DESC
    LIMIT 1)
SELECT S.CodicePilota, 2021 AS AnnoCampionato, '2021-05-29' AS DataGranPremio, S.UsernameUtente
FROM SCELTA_PILOTA S JOIN ULTMO_GRAN_PREMIO_PROGRAMMATO_CONCLUSO U
WHERE S.AnnoCampionato = U.AnnoCampionato
AND S.DataGranPremio = U.Data;
