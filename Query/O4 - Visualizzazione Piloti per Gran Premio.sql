-- O4 - Visualizzazione di tutti i Piloti per un certo Gran Premio in ordine decrescente di prezzo

CREATE PROCEDURE visualizzazionePilotiPrezzoPerGranPremio (IN annoC INT, IN dataGP DATE)
BEGIN

    SELECT P.*, PGP.Prezzo
    FROM SCUDERIA_PARTECIPANTE SP JOIN INGAGGIO_PILOTA IP JOIN PILOTA P JOIN PILOTA_IN_GRAN_PREMIO PGP
        ON SP.AnnoCampionato = IP.AnnoCampionato AND SP.NomeScuderia = IP.NomeScuderia
        AND P.Codice = IP.CodicePilota
        AND PGP.CodicePilota = P.Codice AND PGP.AnnoCampionato = SP.AnnoCampionato
    WHERE SP.AnnoCampionato = annoC
    AND PGP.DataGranPremio = dataGP
    ORDER BY PGP.Prezzo DESC ;

END;
