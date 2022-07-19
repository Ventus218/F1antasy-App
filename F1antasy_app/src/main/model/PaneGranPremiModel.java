package main.model;

import main.F1antasyDB;
import main.Utils;
import main.dto.GranPremioProgrammato;
import main.dto.Pilota;
import main.dto.PilotaConPrezzo;
import main.dto.Squadra;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PaneGranPremiModel {

    private String username;

    private List<GranPremioProgrammato> granPremi;
    private Optional<GranPremioProgrammato> selectedGranPremio = Optional.empty();
    private Optional<Squadra> selectedGranPremioSquadra = Optional.empty();
    private Optional<List<PilotaConPrezzo>> selectedGranPremioPilotiConPrezzo = Optional.empty();

    public PaneGranPremiModel(String username) {
        this.username = username;

        try {
            this.granPremi = F1antasyDB.getDB().getGranPremiProgrammati(F1antasyDB.getDB().getCampionatoCorrente().getAnno());
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
        }
    }


    public void selectGranPremio(GranPremioProgrammato gpp) {
        if (! getGranPremi().contains(gpp)) {
            Utils.crashWithMessage("Trying to select a GranPremio which is not present in " + getGranPremi().toString());
        }
        setSelectedGranPremio(Optional.of(gpp));
        try {
            if (F1antasyDB.getDB().utenteHasSquadraForGranPremio(getUsername(), gpp.getCampionato().getAnno(), gpp.getDataGranPremio())) {
                setSelectedGranPremioSquadra(Optional.of(F1antasyDB.getDB().getSquadraUtente(getUsername(), gpp.getCampionato().getAnno(), gpp.getDataGranPremio())));
                List<PilotaConPrezzo> squadraPilotiConPrezzo = F1antasyDB.getDB().getSquadraPilotiUtente(getUsername(), gpp.getCampionato().getAnno(), gpp.getDataGranPremio());
                setSelectedGranPremioPilotiConPrezzo(Optional.of(squadraPilotiConPrezzo));
            } else {
                setSelectedGranPremioSquadra(Optional.empty());
                setSelectedGranPremioPilotiConPrezzo(Optional.empty());
            }
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
        }
    }

    public Optional<Integer> getSelectedGranPremioPrezzoMotorizzazione() {
        if (getSelectedGranPremio().isPresent()) {
            GranPremioProgrammato selectedGPP = getSelectedGranPremio().get();
            try {
                return Optional.of(F1antasyDB.getDB().getPrezzoMotorizzazione(selectedGPP.getCampionato().getAnno(), selectedGPP.getDataGranPremio(), getSelectedGranPremioSquadra().get().getNomeMotorizzazione()));
            } catch (SQLException e) {
                Utils.crashWithMessage(e.toString());
                return null; // will never run
            }
        } else {
            return Optional.empty();
        }
    }

    public Optional<Integer> getSelectedGPPunteggioOttenuto() {
        Optional<GranPremioProgrammato> gp = getSelectedGranPremio();
        if (gp.isPresent()) {
            try {
                return Optional.of(F1antasyDB.getDB().getPunteggioOttenutoGranPremioConcluso(getUsername(), gp.get().getCampionato().getAnno(), gp.get().getDataGranPremio()));
            } catch (SQLException e) {
                Utils.crashWithMessage(e.toString());
                return null; // will never run
            }
        } else {
            return Optional.empty();
        }
    }

    public String getUsername() {
        return username;
    }

    public List<GranPremioProgrammato> getGranPremi() {
        return new ArrayList(granPremi);
    }

    private void setGranPremi(List<GranPremioProgrammato> granPremi) {
        this.granPremi = new ArrayList(granPremi);
    }

    public Optional<GranPremioProgrammato> getSelectedGranPremio() {
        return selectedGranPremio;
    }

    private void setSelectedGranPremio(Optional<GranPremioProgrammato> selectedGranPremio) {
        this.selectedGranPremio = selectedGranPremio;
    }

    public Optional<Squadra> getSelectedGranPremioSquadra() {
        return selectedGranPremioSquadra;
    }

    private void setSelectedGranPremioSquadra(Optional<Squadra> selectedGranPremioSquadra) {
        this.selectedGranPremioSquadra = selectedGranPremioSquadra;
    }

    public Optional<List<PilotaConPrezzo>> getSelectedGranPremioPilotiConPrezzo() {
        if (selectedGranPremioPilotiConPrezzo.isPresent()) {
            return Optional.of(new ArrayList(selectedGranPremioPilotiConPrezzo.get()));
        } else {
            return Optional.empty();
        }
    }

    private void setSelectedGranPremioPilotiConPrezzo(Optional<List<PilotaConPrezzo>> selectedGranPremioPilotiConPrezzo) {
        if (selectedGranPremioPilotiConPrezzo.isPresent()) {
            this.selectedGranPremioPilotiConPrezzo = Optional.of(new ArrayList(selectedGranPremioPilotiConPrezzo.get()));
        } else {
            this.selectedGranPremioPilotiConPrezzo = Optional.empty();
        }
    }
}
