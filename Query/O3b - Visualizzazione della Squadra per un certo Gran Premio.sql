# O3b - Visualizzazione della Squadra per un certo Gran Premio (Motorizzazione)
CREATE PROCEDURE visualizzazioneSquadra (IN user VARCHAR(255), IN annoC INT, IN dataGP DATE)
BEGIN

    SELECT S.*
    FROM SQUADRA S
    WHERE S.UsernameUtente = user
    AND S.AnnoCampionato = annoC
    AND S.DataGranPremio = dataGP;

END;
