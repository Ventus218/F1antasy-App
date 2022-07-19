-- O14 – Iscrizione ad una Classifica Privata

CREATE PROCEDURE iscrizioneClassificaPrivata (IN user VARCHAR(255), IN nome VARCHAR(255))
BEGIN

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  -- rollback any changes made in the transaction
        RESIGNAL;  -- raise again the sql exception to the caller
    END;

    START TRANSACTION;
        INSERT INTO REGISTRAZIONE(UsernameUtente, NomeClassificaPrivata)
        VALUE (user, nome);

        UPDATE CLASSIFICA_PRIVATA C
        SET C.NumeroPartecipanti = C.NumeroPartecipanti + 1
        WHERE C.Nome = nome;
    COMMIT;

END;

