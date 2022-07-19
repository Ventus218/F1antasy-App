-- O26 - Visualizza Nomi Classifiche Private Utente

CREATE PROCEDURE visualizzaNomiClassifichePrivateUtente (IN user VARCHAR(255))
BEGIN

    SELECT R.NomeClassificaPrivata
    FROM REGISTRAZIONE R
    WHERE R.UsernameUtente = user;

END;
