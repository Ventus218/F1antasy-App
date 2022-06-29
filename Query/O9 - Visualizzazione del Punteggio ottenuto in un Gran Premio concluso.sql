# O9 - Visualizzazione del Punteggio ottenuto in un Gran Premio concluso

SELECT SUM(t.Punteggio) as punteggio_tot
FROM (SELECT pp.Punteggio
      FROM utente u JOIN gran_premio_programmato gpp JOIN squadra s
          JOIN scelta_pilota sp JOIN risultato_pilota rp JOIN posizione_punteggio pp
                ON (gpp.AnnoCampionato = s.AnnoCampionato
                    AND gpp.Data = s.DataGranPremio
                    AND u.Username = s.UsernameUtente
                    AND sp.AnnoCampionato = s.AnnoCampionato
                    AND sp.UsernameUtente = u.Username
                    AND sp.DataGranPremio = s.DataGranPremio
                    AND rp.DataGranPremio = s.DataGranPremio
                    AND rp.AnnoCampionato = s.AnnoCampionato
                    AND rp.CodicePilota = sp.CodicePilota
                    AND rp.Posizione = pp.Posizione)
      WHERE gpp.AnnoCampionato = '2021'
        AND gpp.Data = '2021-05-22'
        AND u.Username = 'CiccioCarluz'
      UNION ALL
      SELECT rm.PunteggioOttenuto
      FROM utente u JOIN gran_premio_programmato gpp JOIN squadra s JOIN risultato_motorizzazione rm
            ON (gpp.AnnoCampionato = s.AnnoCampionato
                AND gpp.Data = s.DataGranPremio
                AND u.Username = s.UsernameUtente
                AND rm.AnnoCampionato = s.AnnoCampionato
                AND rm.DataGranPremio = s.DataGranPremio
                AND rm.NomeMotorizzazione = s.NomeMotorizzazione)
      WHERE gpp.AnnoCampionato = '2021'
        AND gpp.Data = '2021-05-22'
        AND u.Username = 'CiccioCarluz') AS t;

# assuming that the drivers who do not finish do not score points