# O12a - Scambio Pilota

INSERT INTO SCAMBIO_PILOTA(AnnoCampionato, DataGranPremio, UsernameUtente, DataOra, PilotaCeduto, PilotaAcquisito)
VALUE (2021, '2021-05-29', 'CiccioCarluz', current_date, 9, 1);

UPDATE SCELTA_PILOTA
SET CodicePilota = 1
WHERE AnnoCampionato = 2021
AND DataGranPremio = '2021-05-29'
AND UsernameUtente = 'CiccioCarluz'
AND CodicePilota = 9;

UPDATE SQUADRA S
SET ScambiEffettuati = ScambiEffettuati + 1,
BudgetRimanente = BudgetRimanente + (SELECT P.Prezzo FROM PREZZO_PILOTA P
                                                                WHERE P.AnnoCampionato = S.AnnoCampionato AND P.DataGranPremio = S.DataGranPremio
                                                                AND P.CodicePilota = 9),
BudgetRimanente = BudgetRimanente - (SELECT P.Prezzo FROM PREZZO_PILOTA P
                                                                WHERE P.AnnoCampionato = S.AnnoCampionato AND P.DataGranPremio = S.DataGranPremio
                                                                AND P.CodicePilota = 1)
WHERE AnnoCampionato = 2021
AND DataGranPremio = '2021-05-29'
AND UsernameUtente = 'CiccioCarluz';
