# O7 - Visualizzazione Classifica Privata
SELECT R.NomeClassificaPrivata, U.Username, U.PunteggioCorrente FROM REGISTRAZIONE R JOIN UTENTE U ON R.UsernameUtente = U.Username
WHERE R.NomeClassificaPrivata = 'TheTeam';