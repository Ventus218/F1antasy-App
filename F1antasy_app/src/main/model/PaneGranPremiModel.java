package main.model;

import main.F1antasyDB;
import main.Utils;
import main.dto.GranPremioProgrammato;
import main.dto.Pilota;
import main.dto.PilotaConPrezzo;
import main.dto.Squadra;

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

        this.granPremi = F1antasyDB.getGranPremiProgrammati(F1antasyDB.getCampionatoCorrente().getAnno());
    }


    public void selectGranPremio(GranPremioProgrammato gpp) {
        if (! getGranPremi().contains(gpp)) {
            Utils.crashWithMessage("Trying to select a GranPremio which is not present in " + getGranPremi().toString());
        }
        setSelectedGranPremio(Optional.of(gpp));
        if (gpp.getConcluso()) {
            setSelectedGranPremioSquadra(Optional.of(F1antasyDB.getSquadraUtente(getUsername(), gpp.getCampionato().getAnno(), gpp.getDataGranPremio())));
            List<Pilota> squadraPiloti = F1antasyDB.getSquadraPilotiUtente(getUsername(), gpp.getCampionato().getAnno(), gpp.getDataGranPremio());
            List<PilotaConPrezzo> squadraPilotiConPrezzo = squadraPiloti.stream()
                    .map(p -> new PilotaConPrezzo(p, F1antasyDB.getPrezzoPilota(gpp.getCampionato().getAnno(), gpp.getDataGranPremio(), p)))
                    .collect(Collectors.toList());
            setSelectedGranPremioPilotiConPrezzo(Optional.of(squadraPilotiConPrezzo));
        } else {
            setSelectedGranPremioSquadra(Optional.empty());
            setSelectedGranPremioPilotiConPrezzo(Optional.empty());
        }
    }

    public Optional<Integer> getSelectedGranPremioPrezzoMotorizzazione() {
        if (getSelectedGranPremio().isPresent()) {
            GranPremioProgrammato selectedGPP = getSelectedGranPremio().get();
            return Optional.of(F1antasyDB.getPrezzoMotorizzazione(selectedGPP.getCampionato().getAnno(), selectedGPP.getDataGranPremio(), getSelectedGranPremioSquadra().get().getNomeMotorizzazione()));
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
