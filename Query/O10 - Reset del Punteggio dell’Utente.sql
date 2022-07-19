-- O10 - Reset del Punteggio degli Utenti

CREATE PROCEDURE resetPunteggioUtenti()
BEGIN

    UPDATE UTENTE
    SET PunteggioCorrente = 0
    WHERE TRUE;

END;

CREATE TRIGGER nuovoCampionato_INSERT AFTER INSERT ON CAMPIONATO
FOR EACH ROW
BEGIN

    IF EXISTS(  SELECT *
                    FROM GRAN_PREMIO_PROGRAMMATO
                    WHERE AnnoCampionato != NEW.Anno
                    AND Concluso = FALSE )
    THEN
        SIGNAL SQLSTATE '45000';
    ELSE
        CALL resetPunteggioUtenti();
    END IF;

END;
