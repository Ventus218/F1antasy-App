# O9 - Visualizzazione del Punteggio ottenuto in un Gran Premio concluso

CREATE PROCEDURE punteggioUtentePerGranPremio (IN user VARCHAR(255), IN annoC INT, IN dataGP DATE, OUT punteggio INT)
BEGIN

    SELECT SUM(t.Punteggio) as punteggio_tot INTO punteggio
    FROM (SELECT pp.Punteggio
        FROM utente u JOIN gran_premio_programmato gpp JOIN squadra s
        JOIN scelta_pilota sp JOIN pilota_in_gran_premio rp JOIN posizione_punteggio pp
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
        WHERE gpp.AnnoCampionato = annoC
        AND gpp.Data = dataGP
        AND u.Username = user
        UNION ALL
        SELECT mgp.PunteggioOttenuto
        FROM utente u JOIN gran_premio_programmato gpp JOIN squadra s JOIN motorizzazione_in_gran_premio mgp
            ON (gpp.AnnoCampionato = s.AnnoCampionato
            AND gpp.Data = s.DataGranPremio
            AND u.Username = s.UsernameUtente
            AND mgp.AnnoCampionato = s.AnnoCampionato
            AND mgp.DataGranPremio = s.DataGranPremio
            AND mgp.NomeMotorizzazione = s.NomeMotorizzazione)
        WHERE gpp.AnnoCampionato = annoC
        AND gpp.Data = dataGP
        AND u.Username = user) AS t;

END;

# assuming that the drivers who do not finish do not score points