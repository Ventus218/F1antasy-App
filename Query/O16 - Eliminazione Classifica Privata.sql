# O16 - Eliminazione Classifica Privata

CREATE PROCEDURE eliminaClassificaPrivata(IN nome VARCHAR(255))
BEGIN

    DELETE FROM CLASSIFICA_PRIVATA C
    WHERE C.Nome = nome;

END;
