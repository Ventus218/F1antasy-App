# O14 – Iscrizione ad una Classifica Privata

INSERT INTO REGISTRAZIONE(UsernameUtente, NomeClassificaPrivata)
VALUE ('CiccioCarluz', 'Winners');

UPDATE CLASSIFICA_PRIVATA
SET NumeroPartecipanti = NumeroPartecipanti + 1
WHERE Nome = 'Winners';
