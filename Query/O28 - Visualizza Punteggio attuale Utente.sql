-- O28 - Visualizza Punteggio attuale Utente

CREATE PROCEDURE visualizzaPunteggioAttualeUtente (IN user VARCHAR(255), OUT punteggio INT)
BEGIN

    SELECT U.PunteggioCorrente INTO punteggio
    FROM UTENTE U
    WHERE U.Username = user;

END;