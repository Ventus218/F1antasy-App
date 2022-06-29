# O15 – Uscita da una Classifica Privata

DELETE FROM REGISTRAZIONE
WHERE UsernameUtente = 'CiccioCarluz'
AND NomeClassificaPrivata = 'Winners';

UPDATE CLASSIFICA_PRIVATA
SET NumeroPartecipanti = NumeroPartecipanti - 1
WHERE Nome = 'Winners';
