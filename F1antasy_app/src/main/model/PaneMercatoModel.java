package main.model;

import main.F1antasyDB;
import main.Utils;
import main.dto.MotorizzazioneConPrezzo;
import main.dto.PilotaConPrezzo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaneMercatoModel {

    private List<PilotaConPrezzo> availablePiloti;
    private List<MotorizzazioneConPrezzo> availableMotorizzazioni;

    public PaneMercatoModel() {
        availablePiloti = getPilotiConPrezzoFromDB();
        availableMotorizzazioni = getMotorizzazioniConPrezzoFromDB();
    }

    private List<PilotaConPrezzo> getPilotiConPrezzoFromDB() {
        try {
            return F1antasyDB.getDB().getPilotiConPrezzo();
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
    }

    private List<MotorizzazioneConPrezzo> getMotorizzazioniConPrezzoFromDB() {
        try {
            return F1antasyDB.getDB().getMotorizzazioniConPrezzo();
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
    }

    public List<PilotaConPrezzo> getAvailablePiloti() {
        return new ArrayList(availablePiloti);
    }

    public List<MotorizzazioneConPrezzo> getAvailableMotorizzazioni() {
        return new ArrayList(availableMotorizzazioni);
    }

}

