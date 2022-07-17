# O25 - Visualizza Gran Premio Corrente.sql

CREATE PROCEDURE visualizzaGranPremioCorrente (OUT annoC INT,
                                                                                OUT dataGP DATE,
                                                                                OUT stato VARCHAR(255),
                                                                                OUT nome VARCHAR(255),
                                                                                OUT concluso BOOL)
BEGIN

    SELECT GPP.AnnoCampionato, GPP. Data, GPP.Stato, GPP.Nome, GPP.Concluso INTO annoC, dataGP, stato, nome, concluso
    FROM GRAN_PREMIO_PROGRAMMATO GPP
    WHERE GPP.Concluso = TRUE
    ORDER BY GPP.AnnoCampionato DESC, GPP.Data DESC
    LIMIT 1;

END;
