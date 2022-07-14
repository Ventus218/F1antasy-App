package main.model;

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

    private String usernameUtente;

    private List<PilotaConPrezzo> selectedPiloti = new ArrayList();
    private Optional<MotorizzazioneConPrezzo> selectedMotorizzazione = Optional.empty();
    private String nomeSquadra = "";
    private Integer budgetRimanente = BUDGET;
    private List<PilotaConPrezzo> availablePiloti;
    private List<MotorizzazioneConPrezzo> availableMotorizzazioni;

    public PaneInizializzazioneSquadraModel(String usernameUtente) {
        this.usernameUtente = usernameUtente;

        setupConnection();

        availablePiloti = getPilotiConPrezzoFromDB();
        availableMotorizzazioni = getMotorizzazioniConPrezzoFromDB();
    }

    private void setupConnection() {
        // TODO
    }

    public Boolean createSquadra() {
        if (getSelectedPiloti().size() != 4) { return false; }
        if (getSelectedMotorizzazione().isEmpty()) { return false; }
        if (getBudgetRimanente() < 0) { return false; }
        if (getNomeSquadra().isEmpty()) { return false; }

        createSquadraInDB(usernameUtente,
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

    private void createSquadraInDB(String usernameUtente, String nome, List<Pilota> piloti, Motorizzazione motorizzazione ) {
        // TODO GET MOST RECENT NON-COMPLETED GRAND PRIX

        // TODO O2
    }

    private List<PilotaConPrezzo> getPilotiConPrezzoFromDB() {
        // TODO O4

        // MOCKUP
        return PilotaConPrezzo.getSample();
    }

    private List<MotorizzazioneConPrezzo> getMotorizzazioniConPrezzoFromDB() {
        // TODO O5

        // MOCKUP
        return MotorizzazioneConPrezzo.getSample();
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
}

