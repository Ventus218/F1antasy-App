# O1 - RegistrazioneUtente

CREATE PROCEDURE registrazioneUtente (IN user VARCHAR(255), IN pass VARCHAR(255))
BEGIN

    INSERT INTO UTENTE(Username, Password, PunteggioCorrente)
        VALUES(user, pass, 0); -- mettere default

END;
