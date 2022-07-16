package main.model;

import main.F1antasyDB;
import main.Utils;
import main.components.UtenteInClassificaConPosizionamento;
import main.dto.Classifica;
import main.dto.ClassificaPrivata;
import main.dto.UtenteInClassifica;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaneClassifichePrivateModel {

    private String username;

    private List<String> availableClassifichePrivate;

    private Optional<String> selectedClassificaPrivata = Optional.empty();

    private Optional<ClassificaPrivata> classificaPrivata = Optional.empty();

    public PaneClassifichePrivateModel(String username) {
        this.username = username;
        refreshData();
    }

    public void refreshData() {
        setAvailableClassifichePrivate(getAvailableClassifichePrivateFromDB());
        setSelectedClassificaPrivata(Optional.empty());
        setClassificaPrivata(Optional.empty());
    }

    public void selectClassificaPrivata(String nome) {
        if (! getAvailableClassifichePrivate().contains(nome)) {
            Utils.crashWithMessage("Trying to select a Classifica Privata which is not in availableClassifichePrivate");
        }

        setSelectedClassificaPrivata(Optional.of(nome));
        setClassificaPrivata(Optional.of(F1antasyDB.getClassificaPrivata(nome)));
    }

    public Boolean createClassificaPrivata(String nome) {
        if (F1antasyDB.createClassificaPrivata(getUsername(), nome)) {
            refreshData();
            return true;
        } else {
            return false;
        }
    }

    public Boolean joinClassificaPrivata(String nome) {
        if (F1antasyDB.joinClassificaPrivata(getUsername(), nome)) {
            refreshData();
            return true;
        } else {
            return false;
        }
    }

    public Boolean leaveClassificaPrivata(String nome) {
        if (! availableClassifichePrivate.contains(nome)) {
            Utils.crashWithMessage("Trying to leave ClassificaPrivata which is not present in availableClassifichePrivata. ");
        }
        if (F1antasyDB.leaveClassificaPrivata(getUsername(), nome)) {
            refreshData();
            return true;
        } else {
            return false;
        }
    }

    private List<String> getAvailableClassifichePrivateFromDB() {
        return F1antasyDB.getNomiClassifichePrivateUtente(getUsername());
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

    private void setSelectedClassificaPrivata(Optional<String> selectedClassificaPrivata) {
        this.selectedClassificaPrivata = selectedClassificaPrivata;
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

    private String getUsername() { return this.username; }
}
