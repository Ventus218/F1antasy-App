# O5 - Visualizzazione di tutte le Motorizzazioni per un certo Gran Premio in ordine decrescente di prezzo

CREATE PROCEDURE visualizzazioneMotorizzazioniPrezzoPerGranPremio (IN annoC INT, IN dataGP DATE)
BEGIN

    SELECT MGP.NomeMotorizzazione, MGP.Prezzo
    FROM MOTORIZZAZIONE_IN_GRAN_PREMIO MGP
    WHERE MGP.AnnoCampionato = annoC
    AND MGP.DataGranPremio = dataGP
    ORDER BY MGP.Prezzo DESC;

END;
