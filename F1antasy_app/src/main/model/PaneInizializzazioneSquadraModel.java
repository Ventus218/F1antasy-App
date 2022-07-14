package main.model;

import main.F1antasyDB;
import main.dto.Motorizzazione;
import main.dto.MotorizzazioneConPrezzo;
import main.dto.Pilota;
import main.dto.PilotaConPrezzo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PaneInizializzazioneSquadraModel {

    private final Integer BUDGET = 100000000;

    private String username;

    private List<PilotaConPrezzo> selectedPiloti = new ArrayList();
    private Optional<MotorizzazioneConPrezzo> selectedMotorizzazione = Optional.empty();
    private String nomeSquadra = "";
    private Integer budgetRimanente = BUDGET;
    private List<PilotaConPrezzo> availablePiloti;
    private List<MotorizzazioneConPrezzo> availableMotorizzazioni;

    public PaneInizializzazioneSquadraModel(String username) {
        this.username = username;

        availablePiloti = getPilotiConPrezzoFromDB();
        availableMotorizzazioni = getMotorizzazioniConPrezzoFromDB();
    }

    public Boolean createSquadra() {
        if (getSelectedPiloti().size() != 4) { return false; }
        if (getSelectedMotorizzazione().isEmpty()) { return false; }
        if (getBudgetRimanente() < 0) { return false; }
        if (getNomeSquadra().isEmpty()) { return false; }

        createSquadraInDB(getUsername(),
                getNomeSquadra(),
                getSelectedPiloti().stream().map(PilotaConPrezzo::getPilota).collect(Collectors.toList()),
                getSelectedMotorizzazione().get().getMotorizzazione()
        );
        return true;
    }

    public Boolean selectPiloti(List<PilotaConPrezzo> piloti) {
        if (piloti.size() > 4) { return false; }
        for (PilotaConPrezzo p: piloti) {
            if (!getAvailablePiloti().contains(p)) { return false; }
        }

        setSelectedPiloti(piloti);
        updateBudget();
        return true;
    }

    public void selectMotorizzazione(MotorizzazioneConPrezzo m) {
        setSelectedMotorizzazione(Optional.of(m));
        updateBudget();
    }

    public Boolean canCreateSquadra() {
        return !(getSelectedPiloti().size() != 4 || getSelectedMotorizzazione().isEmpty() || getNomeSquadra().isEmpty() || getBudgetRimanente() < 0);
    }

    private void updateBudget() {
        Integer result = BUDGET;
        result = result - getSelectedPiloti().stream().map(PilotaConPrezzo::getPrezzo).collect(Collectors.summingInt(Integer::intValue));
        if (getSelectedMotorizzazione().isPresent()) {
            result = result - getSelectedMotorizzazione().get().getPrezzo();
        }
        setBudgetRimanente(result);
    }

    private void createSquadraInDB(String username, String nome, List<Pilota> piloti, Motorizzazione motorizzazione ) {
        F1antasyDB.createSquadra(username, nome, piloti, motorizzazione);
    }

    private List<PilotaConPrezzo> getPilotiConPrezzoFromDB() {
        return F1antasyDB.getPilotiConPrezzo();
    }

    private List<MotorizzazioneConPrezzo> getMotorizzazioniConPrezzoFromDB() {
        return F1antasyDB.getMotorizzazioniConPrezzo();
    }

    public List<PilotaConPrezzo> getSelectedPiloti() {
        return new ArrayList(selectedPiloti);
    }

    private void setSelectedPiloti(List<PilotaConPrezzo> selectedPiloti) {
        this.selectedPiloti = new ArrayList(selectedPiloti);
    }

    public Optional<MotorizzazioneConPrezzo> getSelectedMotorizzazione() {
        return selectedMotorizzazione;
    }

    private void setSelectedMotorizzazione(Optional<MotorizzazioneConPrezzo> selectedMotorizzazione) {
        this.selectedMotorizzazione = selectedMotorizzazione;
    }

    public String getNomeSquadra() {
        return nomeSquadra;
    }

    public void setNomeSquadra(String nomeSquadra) {
        this.nomeSquadra = nomeSquadra;
    }

    public Integer getBudgetRimanente() {
        return budgetRimanente;
    }

    private void setBudgetRimanente(Integer budgetRimanente) {
        this.budgetRimanente = budgetRimanente;
    }

    public List<PilotaConPrezzo> getAvailablePiloti() {
        return new ArrayList(availablePiloti);
    }

    public List<MotorizzazioneConPrezzo> getAvailableMotorizzazioni() {
        return new ArrayList(availableMotorizzazioni);
    }

    public String getUsername() { return this.username; }
}

