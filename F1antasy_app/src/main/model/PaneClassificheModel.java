package main.model;

import main.F1antasyDB;
import main.components.UtenteInClassificaConPosizionamento;
import main.dto.Classifica;
import main.dto.ClassificaPrivata;
import main.dto.UtenteInClassifica;

import java.util.*;

public class PaneClassificheModel {

    private String username;

    private List<String> availableClassifichePrivate;

    private Optional<String> selectedClassificaPrivata = Optional.empty();

    private Optional<ClassificaPrivata> classificaPrivata = Optional.empty();

    private Classifica classificaGlobale;


    public PaneClassificheModel(String username) {
        this.username = username;
        this.setClassificaGlobale(this.getClassificaGlobaleFromDB());
        this.setAvailableClassifichePrivate(this.getAvailableClassifichePrivateFromDB());
    }

    private List<String> getAvailableClassifichePrivateFromDB() {
        return F1antasyDB.getNomiClassifichePrivateUtente(getUsername());
    }

    private Optional<ClassificaPrivata> getClassificaPrivataFromDB() {
        if (this.getSelectedClassificaPrivata().isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(F1antasyDB.getClassificaPrivata(getUsername(), getSelectedClassificaPrivata().get()));
        }
    }

    private Classifica getClassificaGlobaleFromDB() {
        return F1antasyDB.getClassificaGlobale();
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

    public Optional<List<UtenteInClassificaConPosizionamento>> getClassificaPrivata() {
        if (classificaPrivata.isPresent()) {
            List<UtenteInClassificaConPosizionamento> l = new ArrayList();
            List<UtenteInClassifica> utenti = classificaPrivata.get().getUtenti();
            for (int i = 0; i < utenti.size(); i++) {
                l.add(new UtenteInClassificaConPosizionamento(utenti.get(i), i+1));
            }
            return Optional.of(l);
        } else {
            return Optional.empty();
        }
    }

    private void setClassificaPrivata(Optional<ClassificaPrivata> classificaPrivata) {
        this.classificaPrivata = classificaPrivata;
    }

    public List<UtenteInClassificaConPosizionamento> getClassificaGlobale() {
        List<UtenteInClassificaConPosizionamento> l = new ArrayList();
        List<UtenteInClassifica> utenti = classificaGlobale.getUtenti();
        for (int i = 0; i < utenti.size(); i++) {
            l.add(new UtenteInClassificaConPosizionamento(utenti.get(i), i+1));
        }
        return l;
    }

    private void setClassificaGlobale(Classifica classificaGlobale) {
        this.classificaGlobale = classificaGlobale;
    }

    private String getUsername() { return this.username; }
}
