package main.dto;

import main.components.Acquistabile;

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
}
