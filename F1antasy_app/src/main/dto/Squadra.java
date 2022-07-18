package main.dto;

import java.time.LocalDate;
import java.util.Objects;

public class Squadra {

    public static final Integer MASSIMO_SCAMBI = 2;

    private Integer annoCampionato;
    private LocalDate dataGranPremio;
    private String usernameUtente;
    private Integer scambiEffettuati;
    private Integer budgetRimanente;
    private Motorizzazione nomeMotorizzazione;

    public Squadra(Integer annoCampionato, LocalDate dataGranPremio, String usernameUtente, Integer scambiEffettuati, Integer budgetRimanente, Motorizzazione nomeMotorizzazione) {
        this.annoCampionato = annoCampionato;
        this.dataGranPremio = dataGranPremio;
        this.usernameUtente = usernameUtente;
        this.scambiEffettuati = scambiEffettuati;
        this.budgetRimanente = budgetRimanente;
        this.nomeMotorizzazione = nomeMotorizzazione;
    }

    public Integer getAnnoCampionato() {
        return annoCampionato;
    }

    public LocalDate getDataGranPremio() {
        return dataGranPremio;
    }

    public String getUsernameUtente() {
        return usernameUtente;
    }

    public Integer getScambiEffettuati() {
        return scambiEffettuati;
    }

    public Integer getBudgetRimanente() {
        return budgetRimanente;
    }

    public Motorizzazione getNomeMotorizzazione() {
        return nomeMotorizzazione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Squadra squadra = (Squadra) o;
        return Objects.equals(annoCampionato, squadra.annoCampionato) && Objects.equals(dataGranPremio, squadra.dataGranPremio) && Objects.equals(usernameUtente, squadra.usernameUtente) && Objects.equals(scambiEffettuati, squadra.scambiEffettuati) && Objects.equals(budgetRimanente, squadra.budgetRimanente) && Objects.equals(nomeMotorizzazione, squadra.nomeMotorizzazione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(annoCampionato, dataGranPremio, usernameUtente, scambiEffettuati, budgetRimanente, nomeMotorizzazione);
    }
}
