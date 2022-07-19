-- O23 - Login Utente

CREATE PROCEDURE loginUtente (IN user VARCHAR(255), IN pass VARCHAR(255), OUT success BOOL)
BEGIN

    DECLARE c INT;

    SELECT count(*) INTO c FROM UTENTE
    WHERE Username = user
    AND Password = pass;

    IF c = 0 THEN
        SET success = FALSE;
    ELSE
        SET success = TRUE;
    END IF;

END;
