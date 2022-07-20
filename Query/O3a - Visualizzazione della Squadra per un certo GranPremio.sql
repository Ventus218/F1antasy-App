-- O3a - Visualizzazione della Squadra per un certo Gran Premio (Piloti)

CREATE PROCEDURE visualizzazionePilotiSquadra (IN user VARCHAR(255), IN annoC INT, IN dataGP DATE)
BEGIN

    SELECT P.*, PGP.Prezzo
    FROM SQUADRA S JOIN SCELTA_PILOTA SP JOIN PILOTA_IN_GRAN_PREMIO PGP JOIN PILOTA P
        ON SP.UsernameUtente = S.UsernameUtente AND SP.AnnoCampionato = S.AnnoCampionato AND SP.DataGranPremio = S.DataGranPremio
        AND PGP.CodicePilota = SP.CodicePilota AND PGP.AnnoCampionato = SP.AnnoCampionato AND PGP.DataGranPremio = S.DataGranPremio
        AND P.Codice = PGP.CodicePilota
    WHERE S.UsernameUtente = user
    AND S.AnnoCampionato = annoC
    AND S.DataGranPremio = dataGP;

END;
