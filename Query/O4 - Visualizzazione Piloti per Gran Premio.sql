# O4 - Visualizzazione di tutti i Piloti per un certo Gran Premio in ordine decrescente di prezzo

CREATE PROCEDURE visualizzazionePilotiPrezzoPerGranPremio (IN annoC INT, IN dataGP DATE)
BEGIN

    SELECT P.*, PP.Prezzo
    FROM SCUDERIA_PARTECIPANTE SP JOIN INGAGGIO_PILOTA IP JOIN PILOTA P JOIN PREZZO_PILOTA PP
        ON SP.AnnoCampionato = IP.AnnoCampionato AND SP.NomeScuderia = IP.NomeScuderia
        AND P.Codice = IP.CodicePilota
        AND PP.CodicePilota = P.Codice AND PP.AnnoCampionato = SP.AnnoCampionato
    WHERE SP.AnnoCampionato = annoC
    AND PP.DataGranPremio = dataGP
    ORDER BY PP.Prezzo DESC ;

END;
