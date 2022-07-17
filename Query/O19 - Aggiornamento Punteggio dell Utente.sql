# O19 - Aggiornamento Punteggio dell’Utente

UPDATE utente
SET PunteggioCorrente = PunteggioCorrente + (
    SELECT SUM(t.Punteggio) as punteggio_tot
    FROM (SELECT pp.Punteggio
          FROM utente u JOIN gran_premio_programmato gpp JOIN squadra s
              JOIN scelta_pilota sp JOIN pilota_in_gran_premio pgp JOIN posizione_punteggio pp
                    ON (gpp.AnnoCampionato = s.AnnoCampionato
                        AND gpp.Data = s.DataGranPremio
                        AND u.Username = s.UsernameUtente
                        AND sp.AnnoCampionato = s.AnnoCampionato
                        AND sp.UsernameUtente = u.Username
                        AND sp.DataGranPremio = s.DataGranPremio
                        AND pgp.DataGranPremio = s.DataGranPremio
                        AND pgp.AnnoCampionato = s.AnnoCampionato
                        AND pgp.CodicePilota = sp.CodicePilota
                        AND pgp.Posizione = pp.Posizione)
          WHERE gpp.AnnoCampionato = '2021'
            AND gpp.Data = '2021-05-22'
            AND u.Username = 'CiccioCarluz'
          UNION ALL
          SELECT mgp.PunteggioOttenuto
          FROM utente u JOIN gran_premio_programmato gpp JOIN squadra s JOIN motorizzazione_in_gran_premio mgp
                ON (gpp.AnnoCampionato = s.AnnoCampionato
                    AND gpp.Data = s.DataGranPremio
                    AND u.Username = s.UsernameUtente
                    AND mgp.AnnoCampionato = s.AnnoCampionato
                    AND mgp.DataGranPremio = s.DataGranPremio
                    AND mgp.NomeMotorizzazione = s.NomeMotorizzazione)
          WHERE gpp.AnnoCampionato = '2021'
            AND gpp.Data = '2021-05-22'
            AND u.Username = 'CiccioCarluz') AS t)
WHERE Username = 'CiccioCarluz';

SELECT PunteggioCorrente
FROM utente
WHERE Username = 'CiccioCarluz';