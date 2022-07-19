-- O7 - Visualizzazione Classifica Privata

CREATE PROCEDURE visualizzazioneClassificaPrivata (IN nome VARCHAR(255))
BEGIN

    SELECT U.Username, U.PunteggioCorrente
    FROM REGISTRAZIONE R JOIN UTENTE U
        ON R.UsernameUtente = U.Username
    WHERE R.NomeClassificaPrivata = nome
    ORDER BY U.PunteggioCorrente DESC;

END;
