package main.dto;

import main.components.Acquistabile;

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
}
