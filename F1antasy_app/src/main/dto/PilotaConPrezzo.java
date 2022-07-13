package main.dto;

import main.components.Acquistabile;

import java.util.Objects;

public class PilotaConPrezzo implements Acquistabile {

    private Pilota pilota;
    private Integer prezzo;

    public PilotaConPrezzo(Pilota pilota, Integer prezzo) {
        this.pilota = pilota;
        this.prezzo = prezzo;
    }

    public Pilota getPilota() {
        return pilota;
    }

    public Integer getPrezzo() {
        return prezzo;
    }

    @Override
    public String getNomeAcquisto() {
        return getPilota().getNome() + " " + getPilota().getCognome();
    }

    @Override
    public Integer getPrezzoAcquisto() {
        return getPrezzo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PilotaConPrezzo that = (PilotaConPrezzo) o;
        return Objects.equals(pilota, that.pilota) && Objects.equals(prezzo, that.prezzo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pilota, prezzo);
    }
}
