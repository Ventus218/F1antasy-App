package main.model;

import main.F1antasyDB;
import main.Utils;
import main.components.UtenteInClassificaConPosizionamento;
import main.dto.Classifica;
import main.dto.UtenteInClassifica;

import java.sql.SQLException;
import java.util.*;

public class PaneClassificaGlobaleModel {

    private String username;

    private Classifica classificaGlobale;


    public PaneClassificaGlobaleModel(String username) {
        this.username = username;
        this.setClassificaGlobale(this.getClassificaGlobaleFromDB());
    }


    private Classifica getClassificaGlobaleFromDB() {
        try {
            return F1antasyDB.getDB().getClassificaGlobale();
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
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
