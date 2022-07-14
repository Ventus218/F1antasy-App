package main.dto;

import main.components.Acquistabile;

import java.util.ArrayList;
import java.util.List;
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


    public static List<PilotaConPrezzo> getSample() {
        List<PilotaConPrezzo> list = new ArrayList();
        list.add(new PilotaConPrezzo(new Pilota(1, "Lewis", "Hamilton"), 40000000));
        list.add(new PilotaConPrezzo(new Pilota(2, "Bottas", "Valtteri"), 40000000));
        list.add(new PilotaConPrezzo(new Pilota(3, "Max", "Verstappen"), 33000000));
        list.add(new PilotaConPrezzo(new Pilota(4, "Sergio", "Perez"), 32000000));
        list.add(new PilotaConPrezzo(new Pilota(5, "Daniel", "Ricciardo"), 30000000));
        list.add(new PilotaConPrezzo(new Pilota(6, "Carlos", "Sainz"), 25000000));
        list.add(new PilotaConPrezzo(new Pilota(7, "Charles", "Leclerc"), 23000000));
        list.add(new PilotaConPrezzo(new Pilota(8, "Lando", "Norris"), 21000000));
        list.add(new PilotaConPrezzo(new Pilota(9, "Fernando", "Alonso"), 20000000));
        list.add(new PilotaConPrezzo(new Pilota(10, "Pierre", "Gasly"), 19000000));
        list.add(new PilotaConPrezzo(new Pilota(11, "Lance", "Stroll"), 17000000));
        list.add(new PilotaConPrezzo(new Pilota(12, "Esteban", "Ocon"), 15000000));
        list.add(new PilotaConPrezzo(new Pilota(13, "Sebastian", "Vettel"), 14000000));
        list.add(new PilotaConPrezzo(new Pilota(14, "Antonio", "Giovinazzi"), 13000000));
        list.add(new PilotaConPrezzo(new Pilota(15, "Kimi", "Raikkonen"), 12000000));
        list.add(new PilotaConPrezzo(new Pilota(16, "George", "Russel"), 11000000));
        list.add(new PilotaConPrezzo(new Pilota(17, "Nickolas", "Latifi"), 11000000));
        list.add(new PilotaConPrezzo(new Pilota(18, "Yuki", "Tsunoda"), 10000000));
        list.add(new PilotaConPrezzo(new Pilota(19, "Mick", "Shumacher"), 10000000));
        list.add(new PilotaConPrezzo(new Pilota(20, "Nikita", "Mazepin"), 10000000));
        return list;
    }
}
