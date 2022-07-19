package main.model;

import main.F1antasyDB;
import main.User;
import main.Utils;
import main.dto.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PaneSquadraModel {

    private String username;
    private Squadra squadra;
    private Integer punteggioAttuale;
    private Integer valoreSquadra;
    private List<PilotaConPrezzo> pilotiConPrezzo;

    public PaneSquadraModel(String username) {
        this.username = username;

        refreshData();
    }

    public void refreshData() {
        setSquadra(getSquadraFromDB());
        setPunteggioAttuale(getPunteggioAttualeFromDB());
        setValoreSquadra(getValoreSquadraFromDB());
        setPilotiConPrezzo(getPilotiConPrezzoFromDB());
    }

    public Integer getPrezzoPilota(Pilota p) {
        return getPilotiConPrezzo().stream()
                .filter(pilota -> pilota.getPilota().getCodice().equals(p.getCodice()))
                .findFirst()
                .get()
                .getPrezzo();
    }

    public Integer getPrezzoMotorizzazione(Motorizzazione m) {
        return getPrezzoMotorizzazioneFromDB(m);
    }

    private Squadra getSquadraFromDB() {
        try {
            return F1antasyDB.getDB().getSquadraUtente(getUsername());
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
    }

    private Integer getPunteggioAttualeFromDB() {
        try {
            return F1antasyDB.getDB().getPunteggioAttualeUtente(getUsername());
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
    }

    private Integer getValoreSquadraFromDB() {
        try {
            return F1antasyDB.getDB().getValoreSquadra(getUsername());
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
    }

    private List<PilotaConPrezzo> getPilotiConPrezzoFromDB() {
        try {
            return F1antasyDB.getDB().getSquadraPilotiUtente(getUsername());
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
    }

    private Integer getPrezzoPilotaFromDB(Pilota pilota) {
        try {
            return F1antasyDB.getDB().getPrezzoPilota(pilota);
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
    }

    private Integer getPrezzoMotorizzazioneFromDB(Motorizzazione motorizzazione) {
        try {
            return F1antasyDB.getDB().getPrezzoMotorizzazione(motorizzazione);
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
    }


    public String getUsername() {
        return username;
    }

    public Squadra getSquadra() {
        return squadra;
    }

    private void setSquadra(Squadra squadra) {
        this.squadra = squadra;
    }

    public Integer getPunteggioAttuale() {
        return punteggioAttuale;
    }

    private void setPunteggioAttuale(Integer punteggioAttuale) {
        this.punteggioAttuale = punteggioAttuale;
    }

    public Integer getValoreSquadra() {
        return valoreSquadra;
    }

    private void setValoreSquadra(Integer valoreSquadra) {
        this.valoreSquadra = valoreSquadra;
    }

    public List<Pilota> getPiloti() {
        return new ArrayList(pilotiConPrezzo.stream().map(PilotaConPrezzo::getPilota).collect(Collectors.toList()));
    }

    private void setPilotiConPrezzo(List<PilotaConPrezzo> piloti) {
        this.pilotiConPrezzo = new ArrayList(piloti);
    }

    private List<PilotaConPrezzo> getPilotiConPrezzo() {
        return new ArrayList(pilotiConPrezzo);
    }
}
