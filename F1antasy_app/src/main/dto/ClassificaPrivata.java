package main.dto;

import java.util.*;

public class ClassificaPrivata {

    private String nome;
    private List<UtenteInClassifica> utenti;

    public ClassificaPrivata(String nome, List<UtenteInClassifica> utenti) {
        this.nome = nome;
        this.utenti = new ArrayList(utenti);
    }

    public String getNome() {
        return nome;
    }

    public List<UtenteInClassifica> getUtenti() {
        return utenti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassificaPrivata that = (ClassificaPrivata) o;
        return Objects.equals(nome, that.nome) && Objects.equals(utenti, that.utenti);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, utenti);
    }

    public static List<ClassificaPrivata> getSample() {
        List<ClassificaPrivata> l = new ArrayList();

        List<UtenteInClassifica> firstClassificaPrivata = new ArrayList();
        firstClassificaPrivata.add(new UtenteInClassifica("Dario", 75));
        firstClassificaPrivata.add(new UtenteInClassifica("Ale", 50));
        firstClassificaPrivata.add(new UtenteInClassifica("Carlo", 45));


        List<UtenteInClassifica> secondClassificaPrivata = new ArrayList();
        secondClassificaPrivata.add(new UtenteInClassifica("Dario", 75));
        secondClassificaPrivata.add(new UtenteInClassifica("Ale", 50));
        secondClassificaPrivata.add(new UtenteInClassifica("Carlo", 45));
        secondClassificaPrivata.add(new UtenteInClassifica("Lillo", 15));
        secondClassificaPrivata.add(new UtenteInClassifica("Pluto", 10));

        List<UtenteInClassifica> thirdClassificaPrivata = new ArrayList();
        thirdClassificaPrivata.add(new UtenteInClassifica("Ciccio", 100));
        thirdClassificaPrivata.add(new UtenteInClassifica("Dario", 75));
        thirdClassificaPrivata.add(new UtenteInClassifica("Ale", 50));

        l.add(new ClassificaPrivata("Winners", firstClassificaPrivata));
        l.add(new ClassificaPrivata("Squad", secondClassificaPrivata));
        l.add(new ClassificaPrivata("TheTeam", thirdClassificaPrivata));
        return l;
    }
}
