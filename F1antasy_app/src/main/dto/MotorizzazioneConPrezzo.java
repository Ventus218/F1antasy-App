package main.dto;

import main.components.Acquistabile;

import java.util.ArrayList;
import java.util.List;
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

    public static List<MotorizzazioneConPrezzo> getSample() {
        List<MotorizzazioneConPrezzo> list = new ArrayList();
        list.add(new MotorizzazioneConPrezzo(new Motorizzazione("Mercedes"), 15000000));
        list.add(new MotorizzazioneConPrezzo(new Motorizzazione("Honda"), 10000000));
        list.add(new MotorizzazioneConPrezzo(new Motorizzazione("Renault"), 9000000));
        list.add(new MotorizzazioneConPrezzo(new Motorizzazione("Ferrari"), 8000000));
        return list;
    }
}
