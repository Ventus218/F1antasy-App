-- O24 - Controllo se Utente ha Squadra per Gran Premio

CREATE PROCEDURE checkUtenteHaSquadraPerGranPremio (IN user VARCHAR(255), IN annoC INT, IN dataGP DATE, OUT result BOOL)
BEGIN
    DECLARE c INT;

    SELECT COUNT(*) INTO c
    FROM SQUADRA S
    WHERE S.UsernameUtente = user
    AND S.AnnoCampionato = annoC
    AND S.DataGranPremio = dataGP;

    IF c = 0 THEN
        SET result = FALSE;
    ELSE
        SET result = TRUE;
    END IF;

END;
