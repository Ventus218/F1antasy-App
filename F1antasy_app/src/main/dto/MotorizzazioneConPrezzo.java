package main.dto;

import main.components.Acquistabile;

import java.util.Objects;

public class MotorizzazioneConPrezzo implements Acquistabile {

    private Motorizzazione motorizzazione;
    private Integer prezzo;

    public MotorizzazioneConPrezzo(Motorizzazione motorizzazione, Integer prezzo) {
        this.motorizzazione = motorizzazione;
        this.prezzo = prezzo;
    }

    public Motorizzazione getMotorizzazione() {
        return motorizzazione;
    }

    public Integer getPrezzo() {
        return prezzo;
    }

    @Override
    public String getNomeAcquisto() {
        return getMotorizzazione().getNome();
    }

    @Override
    public Integer getPrezzoAcquisto() {
        return getPrezzo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MotorizzazioneConPrezzo that = (MotorizzazioneConPrezzo) o;
        return Objects.equals(motorizzazione, that.motorizzazione) && Objects.equals(prezzo, that.prezzo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(motorizzazione, prezzo);
    }
}
