package main.model;

import main.F1antasyDB;
import main.dto.UtenteInClassifica;

import java.util.*;

public class PaneClassificheModel {

    private String username;

    private List<String> availableClassifichePrivate;

    private Optional<String> selectedClassificaPrivata = Optional.empty();

    private Optional<List<UtenteInClassifica>> classificaPrivata = Optional.empty();

    private List<UtenteInClassifica> classificaGlobale;


    public PaneClassificheModel(String username) {
        this.username = username;
        setupConnection();
        this.setClassificaGlobale(this.getClassificaGlobaleFromDB());
        this.setAvailableClassifichePrivate(this.getAvailableClassifichePrivateFromDB());
    }

    private void setupConnection() {
        // TODO
    }

    private List<String> getAvailableClassifichePrivateFromDB() {
        // TODO

        // MOCKUP
        List<String> list = new ArrayList();
        list.add("Winners");
        list.add("Losers");
        list.add("HappyPeople");
        return list;
    }

    private Optional<List<UtenteInClassifica>> getClassificaPrivataFromDB() {
        if (this.getSelectedClassificaPrivata().isEmpty()) {
            return Optional.empty();
        } else {
            // TODO

            // MOCKUP
            Map<String, List<UtenteInClassifica>> map = new HashMap();

            List<UtenteInClassifica> firstClassificaPrivata = new ArrayList();
            firstClassificaPrivata.add(new UtenteInClassifica("Dario", 1, 75));
            firstClassificaPrivata.add(new UtenteInClassifica("Ale", 2, 50));
            firstClassificaPrivata.add(new UtenteInClassifica("Carlo", 3, 45));
            map.put(getAvailableClassifichePrivate().get(0), firstClassificaPrivata);

            List<UtenteInClassifica> secondClassificaPrivata = new ArrayList();
            secondClassificaPrivata.add(new UtenteInClassifica("Dario", 1, 75));
            secondClassificaPrivata.add(new UtenteInClassifica("Ale", 2, 50));
            secondClassificaPrivata.add(new UtenteInClassifica("Carlo", 3, 45));
            secondClassificaPrivata.add(new UtenteInClassifica("Lillo", 4, 15));
            secondClassificaPrivata.add(new UtenteInClassifica("Pluto", 5, 10));
            map.put(getAvailableClassifichePrivate().get(1), secondClassificaPrivata);

            List<UtenteInClassifica> thirdClassificaPrivata = new ArrayList();
            thirdClassificaPrivata.add(new UtenteInClassifica("Ciccio", 1, 100));
            thirdClassificaPrivata.add(new UtenteInClassifica("Dario", 2, 75));
            thirdClassificaPrivata.add(new UtenteInClassifica("Ale", 3, 50));
            map.put(getAvailableClassifichePrivate().get(2), thirdClassificaPrivata);

            return Optional.of(map.get(this.getSelectedClassificaPrivata().get()));
        }
    }

    private List<UtenteInClassifica> getClassificaGlobaleFromDB() {
        // TODO

        // MOCKUP
        List<UtenteInClassifica> list = new ArrayList();
        list.add(new UtenteInClassifica("Ciccio", 1, 100));
        list.add(new UtenteInClassifica("Dario", 2, 75));
        list.add(new UtenteInClassifica("Ale", 3, 50));
        list.add(new UtenteInClassifica("Carlo", 4, 45));
        list.add(new UtenteInClassifica("Franky", 5, 35));
        list.add(new UtenteInClassifica("Jack", 6, 20));
        list.add(new UtenteInClassifica("Lillo", 7, 15));
        list.add(new UtenteInClassifica("Pluto", 8, 10));
        list.add(new UtenteInClassifica("Suus", 9, 0));

        return list;
    }

    public List<String> getAvailableClassifichePrivate() {
        return new ArrayList(availableClassifichePrivate);
    }

    private void setAvailableClassifichePrivate(List<String> availableClassifichePrivate) {
        this.availableClassifichePrivate = new ArrayList(availableClassifichePrivate);
    }

    public Optional<String> getSelectedClassificaPrivata() {
        return selectedClassificaPrivata;
    }

    public void setSelectedClassificaPrivata(Optional<String> selectedClassificaPrivata) {
        if (selectedClassificaPrivata.isPresent() && ! this.getAvailableClassifichePrivate().contains(selectedClassificaPrivata.get())) {
            return;
        }

        this.selectedClassificaPrivata = selectedClassificaPrivata;
        if (this.selectedClassificaPrivata.isEmpty()) {
            this.setClassificaPrivata(Optional.empty());
        } else {
            this.setClassificaPrivata(Optional.of(this.getClassificaPrivataFromDB().get()));
        }
    }

    public Optional<List<UtenteInClassifica>> getClassificaPrivata() {
        if (classificaPrivata.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(new ArrayList(classificaPrivata.get()));
        }
    }

    private void setClassificaPrivata(Optional<List<UtenteInClassifica>> classificaPrivata) {
        if (classificaPrivata.isEmpty()) {
            this.classificaPrivata = Optional.empty();
        } else {
            this.classificaPrivata = Optional.of(new ArrayList(classificaPrivata.get()));
        }
    }

    public List<UtenteInClassifica> getClassificaGlobale() {
        return new ArrayList(this.classificaGlobale);
    }

    private void setClassificaGlobale(List<UtenteInClassifica> classificaGlobale) {
        this.classificaGlobale = new ArrayList(classificaGlobale);
    }

    private String getUsername() { return this.username; }
}
