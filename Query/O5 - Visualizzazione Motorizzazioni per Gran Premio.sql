# O5 - Visualizzazione di tutte le Motorizzazioni per un certo Gran Premio in ordine decrescente di prezzo

CREATE PROCEDURE visualizzazioneMotorizzazioniPrezzoPerGranPremio (IN annoC INT, IN dataGP DATE)
BEGIN

    SELECT pm.NomeMotorizzazione, pm.Prezzo
    FROM prezzo_motorizzazione pm
    WHERE pm.AnnoCampionato = annoC
    AND pm.DataGranPremio = dataGP
    ORDER BY pm.Prezzo DESC;

END;
