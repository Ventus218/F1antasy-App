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
BudgetRimanente = BudgetRimanente + (SELECT PGP.Prezzo FROM PILOTA_IN_GRAN_PREMIO PGP
                                                                WHERE PGP.AnnoCampionato = S.AnnoCampionato AND PGP.DataGranPremio = S.DataGranPremio
                                                                AND PGP.CodicePilota = 9),
BudgetRimanente = BudgetRimanente - (SELECT PGP.Prezzo FROM PILOTA_IN_GRAN_PREMIO PGP
                                                                WHERE PGP.AnnoCampionato = S.AnnoCampionato AND PGP.DataGranPremio = S.DataGranPremio
                                                                AND PGP.CodicePilota = 1)
WHERE AnnoCampionato = 2021
AND DataGranPremio = '2021-05-29'
AND UsernameUtente = 'CiccioCarluz';
