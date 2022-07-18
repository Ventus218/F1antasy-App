# O19 - Aggiornamento Punteggi degli Utenti

CREATE PROCEDURE aggiornamentoPunteggiUtentiGranPremioConcluso (IN annoC INT, IN dataGP DATE)
BEGIN

    WITH PUNTEGGI_OTTENUTI_UTENTE (UsernameUtente, PunteggioOttenuto) AS (
        SELECT t.UsernameUtente, SUM(t.Punteggio) as PunteggioOttenuto
            FROM (
                SELECT S.UsernameUtente, PP.Punteggio
                FROM GRAN_PREMIO_PROGRAMMATO GPP JOIN SQUADRA S JOIN SCELTA_PILOTA SP JOIN PILOTA_IN_GRAN_PREMIO PGP JOIN POSIZIONE_PUNTEGGIO PP
                    ON GPP.AnnoCampionato = S.AnnoCampionato
                    AND GPP.Data = S.DataGranPremio
                    AND S.AnnoCampionato = SP.AnnoCampionato
                    AND S.UsernameUtente = SP.UsernameUtente
                    AND S.DataGranPremio = SP.DataGranPremio
                    AND SP.DataGranPremio = PGP.DataGranPremio
                    AND SP.AnnoCampionato = PGP.AnnoCampionato
                    AND SP.CodicePilota = PGP.CodicePilota
                    AND PGP.Posizione = PP.Posizione
                WHERE GPP.AnnoCampionato = annoC
                AND GPP.Data = dataGP
                UNION ALL
                SELECT S.UsernameUtente, MGP.PunteggioOttenuto
                FROM GRAN_PREMIO_PROGRAMMATO GPP JOIN SQUADRA S JOIN motorizzazione_in_gran_premio MGP
                    ON GPP.AnnoCampionato = S.AnnoCampionato
                    AND GPP.Data = S.DataGranPremio
                    AND S.AnnoCampionato = MGP.AnnoCampionato
                    AND S.DataGranPremio = MGP.DataGranPremio
                    AND S.NomeMotorizzazione = MGP.NomeMotorizzazione
                WHERE GPP.AnnoCampionato = annoC
                AND GPP.Data = dataGP) AS T
            GROUP BY T.UsernameUtente
        )
    UPDATE UTENTE U JOIN PUNTEGGI_OTTENUTI_UTENTE P
        ON U.Username = P.UsernameUtente
    SET U.PunteggioCorrente = U.PunteggioCorrente + P.PunteggioOttenuto;

END;
