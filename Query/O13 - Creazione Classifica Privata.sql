# O13 - Creazione Classifica Privata

CREATE PROCEDURE creazioneClassificaPrivata (IN user VARCHAR(255), IN nome VARCHAR(255))
BEGIN

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  -- rollback any changes made in the transaction
        RESIGNAL;  -- raise again the sql exception to the caller
    END;

    START TRANSACTION;
        INSERT INTO CLASSIFICA_PRIVATA(Nome, NumeroPartecipanti)
        VALUE (nome, 0);

        CALL iscrizioneClassificaPrivata(user, nome);
    COMMIT;

END;
