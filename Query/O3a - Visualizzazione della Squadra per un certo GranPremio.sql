# O3a - Visualizzazione della Squadra per un certo Gran Premio (Piloti)

CREATE PROCEDURE visualizzazionePilotiSquadra (IN user VARCHAR(255), IN annoC INT, IN dataGP DATE)
BEGIN

    SELECT P.*, PP.Prezzo
    FROM UTENTE U JOIN SQUADRA S JOIN SCELTA_PILOTA SP JOIN PREZZO_PILOTA PP JOIN PILOTA P
        ON U.Username = S.UsernameUtente
        AND SP.UsernameUtente = U.Username AND SP.AnnoCampionato = S.AnnoCampionato AND SP.DataGranPremio = S.DataGranPremio
        AND PP.CodicePilota = SP.CodicePilota AND PP.AnnoCampionato = SP.AnnoCampionato AND PP.DataGranPremio = S.DataGranPremio
        AND P.Codice = PP.CodicePilota
    WHERE U.Username = user
    AND S.AnnoCampionato = annoC
    AND S.DataGranPremio = dataGP;

END;
