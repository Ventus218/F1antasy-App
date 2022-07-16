package main.model;

import main.F1antasyDB;
import main.components.UtenteInClassificaConPosizionamento;
import main.dto.Classifica;
import main.dto.UtenteInClassifica;

import java.util.*;

public class PaneClassificaGlobaleModel {

    private String username;

    private Classifica classificaGlobale;


    public PaneClassificaGlobaleModel(String username) {
        this.username = username;
        this.setClassificaGlobale(this.getClassificaGlobaleFromDB());
    }


    private Classifica getClassificaGlobaleFromDB() {
        return F1antasyDB.getClassificaGlobale();
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
