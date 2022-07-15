package main.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Classifica {

    private List<UtenteInClassifica> utenti;

    public Classifica(List<UtenteInClassifica> utenti) {
        this.utenti = new ArrayList(utenti);
    }

    public List<UtenteInClassifica> getUtenti() {
        return new ArrayList(this.utenti);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classifica that = (Classifica) o;
        return Objects.equals(utenti, that.utenti);
    }

    @Override
    public int hashCode() {
        return Objects.hash(utenti);
    }

    public static Classifica getSample() {
        List<UtenteInClassifica> list = new ArrayList();

        list.add(new UtenteInClassifica("Ciccio", 100));
        list.add(new UtenteInClassifica("Dario", 75));
        list.add(new UtenteInClassifica("Ale", 50));
        list.add(new UtenteInClassifica("Carlo", 45));
        list.add(new UtenteInClassifica("Franky", 35));
        list.add(new UtenteInClassifica("Jack", 20));
        list.add(new UtenteInClassifica("Lillo", 15));
        list.add(new UtenteInClassifica("Pluto", 10));
        list.add(new UtenteInClassifica("Suus", 0));

        return new Classifica(list);
    }
}
