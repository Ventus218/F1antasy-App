# O2 - Inizializzazione della Squadra (scelta Piloti e Motorizzazione) ----- NON FUNZIONA!!!!!
START TRANSACTION;
INSERT INTO SQUADRA(AnnoCampionato, DataGranPremio, UsernameUtente, ScambiEffettuati, BudgetRimanente, NomeMotorizzazione)
VALUE
    (2021,
     '2021-05-22',
     'CiccioCarluz',
     0,
     100000000 - (SELECT Prezzo FROM PREZZO_MOTORIZZAZIONE
                            WHERE AnnoCampionato = 2021
                            AND DataGranPremio = '2021-05-22'
                            AND NomeMotorizzazione = 'Ferrari'),
    'Ferrari');

INSERT INTO SCELTA_PILOTA(CodicePilota, AnnoCampionato, DataGranPremio, UsernameUtente)
VALUES
    (1, 2021, '2021-05-22', 'CiccioCarluz'),
    (2, 2021, '2021-05-22', 'CiccioCarluz'),
    (3, 2021, '2021-05-22', 'CiccioCarluz'),
    (4, 2021, '2021-05-22', 'CiccioCarluz');

UPDATE SQUADRA
set  BudgetRimanente = BudgetRimanente - (select sum(Prezzo) as PrezzoTotale from PREZZO_PILOTA
                                                                    where AnnoCampionato = 2021
                                                                    and DataGranPremio = '2021-05-22'
                                                                    and (CodicePilota = 9 or CodicePilota = 13 or CodicePilota = 18 or CodicePilota = 16))
where AnnoCampionato = 2021
and DataGranPremio = '2021-05-22'
and UsernameUtente = 'CiccioCarluz';
COMMIT;

ALTER TABLE SQUADRA
ADD CONSTRAINT CHK_BudgetRimanente_Positive CHECK (SQUADRA.BudgetRimanente >= 0);

# Usefull for changing scelta_pilota
/*
DELETE FROM scelta_pilota
    WHERE UsernameUtente = 'CiccioCarluz'
    AND AnnoCampionato = 2021
    AND DataGranPremio = '2021-05-22';
*/

