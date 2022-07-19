-- O6 - Visualizzazione Classifica Globale

CREATE PROCEDURE visualizzazioneClassificaGlobale ()
BEGIN

    SELECT Username, PunteggioCorrente FROM UTENTE
    ORDER BY PunteggioCorrente DESC;

END;


