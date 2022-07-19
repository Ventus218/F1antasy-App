package main.model;

import main.F1antasyDB;
import main.Utils;
import main.components.UtenteInClassificaConPosizionamento;
import main.dto.Classifica;
import main.dto.ClassificaPrivata;
import main.dto.UtenteInClassifica;

import java.sql.SQLException;
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
        try {
            setClassificaPrivata(Optional.of(F1antasyDB.getDB().getClassificaPrivata(nome)));
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
        }
    }

    public Boolean createClassificaPrivata(String nome) {
        try {
            if (F1antasyDB.getDB().createClassificaPrivata(getUsername(), nome)) {
                refreshData();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            Utils.showError(e.toString());
            return false;
        }
    }

    public Boolean joinClassificaPrivata(String nome) {
        try {
            if (F1antasyDB.getDB().joinClassificaPrivata(getUsername(), nome)) {
                refreshData();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            Utils.showError(e.toString());
            return false;
        }
    }

    public Boolean leaveClassificaPrivata(String nome) {
        if (! availableClassifichePrivate.contains(nome)) {
            Utils.crashWithMessage("Trying to leave ClassificaPrivata which is not present in availableClassifichePrivata. ");
        }
        try {
            if (F1antasyDB.getDB().leaveClassificaPrivata(getUsername(), nome)) {
                refreshData();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            Utils.showError(e.toString());
            return false;
        }
    }

    private List<String> getAvailableClassifichePrivateFromDB() {
        try {
            return F1antasyDB.getDB().getNomiClassifichePrivateUtente(getUsername());
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
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
