# O12b - Scambio Motorizzazione

INSERT INTO scambio_motorizzazione(AnnoCampionato, DataGranPremio, UsernameUtente, DataOra, MotorizzazioneCeduta, MotorizzazioneAcquisita)
VALUE (2021, '2021-05-29', 'CiccioCarluz', current_date(), 'Ferrari', 'Renault');

UPDATE squadra s
SET ScambiEffettuati = ScambiEffettuati + 1,
BudgetRimanente = BudgetRimanente + (SELECT p.Prezzo
                                     FROM prezzo_motorizzazione p
                                     WHERE p.AnnoCampionato = s.AnnoCampionato AND p.DataGranPremio = s.DataGranPremio
                                     AND p.NomeMotorizzazione = 'Ferrari'),
BudgetRimanente = BudgetRimanente - (SELECT P.Prezzo FROM prezzo_motorizzazione p
                                     WHERE p.AnnoCampionato = s.AnnoCampionato AND p.DataGranPremio = s.DataGranPremio
                                     AND p.NomeMotorizzazione = 'Renault'),
NomeMotorizzazione = 'Renault'
WHERE AnnoCampionato = 2021
AND DataGranPremio = '2021-05-29'
AND UsernameUtente = 'CiccioCarluz';

SELECT *
FROM scambio_motorizzazione s
WHERE s.AnnoCampionato = '2021'
    AND s.DataGranPremio = '2021-05-29'
    AND s.UsernameUtente = 'CiccioCarluz';