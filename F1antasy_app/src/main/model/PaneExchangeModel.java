package main.model;

import main.F1antasyDB;
import main.Utils;
import main.components.Acquistabile;
import main.dto.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PaneExchangeModel<T extends Acquistabile> {

    private String username;

    private T oldAcquistabile;
    private List<T> availableAquistabili;
    private Optional<T> newAcquistabile = Optional.empty();
    private Integer _budgetRimanente;

    public PaneExchangeModel(String username, T oldAcquistabile) {
        setUsername(username);
        this.oldAcquistabile = oldAcquistabile;
        this.availableAquistabili = getAvailableAcquistabiliFromDB();

        try {
            GranPremioProgrammato currentGP = F1antasyDB.getGranPremioProgrammatoCorrente();
            Squadra s = F1antasyDB.getSquadraUtente(getUsername(), currentGP.getCampionato().getAnno(), currentGP.getDataGranPremio());
            setBudgetRimanente(s.getBudgetRimanente());
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
        }
    }

    public Boolean makeExchange() {
        if (getBudgetRimanente() < 0 || newAcquistabile.isEmpty()) { return false; }
        if (oldAcquistabileIsPilota()) {
            PilotaConPrezzo oldPCP = (PilotaConPrezzo) oldAcquistabile;
            PilotaConPrezzo newPCP = (PilotaConPrezzo) newAcquistabile.get();
            try {
                return F1antasyDB.exchangePiloti(getUsername(), oldPCP.getPilota(), newPCP.getPilota());
            } catch (SQLException e) {
                Utils.crashWithMessage(e.toString());
                return null; // will never run
            }
        } else {
            MotorizzazioneConPrezzo oldM = (MotorizzazioneConPrezzo) oldAcquistabile;
            MotorizzazioneConPrezzo newM = (MotorizzazioneConPrezzo) newAcquistabile.get();
            try {
                return F1antasyDB.exchangeMotorizzazione(getUsername(), oldM.getMotorizzazione(), newM.getMotorizzazione());
            } catch (SQLException e) {
                Utils.crashWithMessage(e.toString());
                return null; // will never run
            }
        }
    }

    public void setNewAcquistabile(T newAcquistabile) {
        if (! availableAquistabili.contains(newAcquistabile)) {
            Utils.crashWithMessage("newAcquistabile was not in list of availableAcquistabili");
        } else {
            setNewAcquistabile(Optional.of(newAcquistabile));
        }
    }

    private Boolean oldAcquistabileIsPilota() {
        return oldAcquistabile instanceof PilotaConPrezzo;
    }

    private Boolean oldAcquistabileIsMotorizzazione() {
        return oldAcquistabile instanceof MotorizzazioneConPrezzo;
    }

    private List<T> getAvailableAcquistabiliFromDB() {
        if (oldAcquistabileIsPilota()) {
            try {
                List<Integer> codiciPilotiGiaInSquadra = F1antasyDB.getSquadraPilotiUtente(getUsername())
                        .stream()
                        .map(PilotaConPrezzo::getPilota)
                        .map(Pilota::getCodice)
                        .collect(Collectors.toList());
                List<PilotaConPrezzo> filtered = getPilotiConPrezzoFromDB()
                        .stream()
                        .filter(p -> ! codiciPilotiGiaInSquadra.contains(p.getPilota().getCodice()))
                        .collect(Collectors.toList());
                return (List<T>) filtered;
            } catch (SQLException e) {
                Utils.crashWithMessage("Failed to retrieve squadra to filter out on availableForExchange with error\n" + e.toString());
                return null; // will never run
            }
        } else { // is MotorizzazioneConPrezzo
            Motorizzazione oldMotorizzazione = ((MotorizzazioneConPrezzo) oldAcquistabile).getMotorizzazione();
            List<MotorizzazioneConPrezzo> filtered = getMotorizzazioniConPrezzoFromDB().stream().filter(m -> ! m.getMotorizzazione().equals(oldMotorizzazione)).collect(Collectors.toList());
            return (List<T>) filtered;
        }
    }

    private List<PilotaConPrezzo> getPilotiConPrezzoFromDB() {
        try {
            return F1antasyDB.getPilotiConPrezzo();
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
    }

    private List<MotorizzazioneConPrezzo> getMotorizzazioniConPrezzoFromDB() {
        try {
            return F1antasyDB.getMotorizzazioniConPrezzo();
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
    }

    public T getOldAcquistabile() {
        return oldAcquistabile;
    }

    public List<T> getAvailableAcquistabili() {
        return new ArrayList<>(this.availableAquistabili);
    }

    public Optional<T> getNewAcquistabile() {
        return newAcquistabile;
    }

    private void setNewAcquistabile(Optional<T> newAcquistabile) {
        this.newAcquistabile = newAcquistabile;
    }

    public Integer getBudgetRimanente() {
        if (newAcquistabile.isPresent()) {
            return this._budgetRimanente + oldAcquistabile.getPrezzoAcquisto() - newAcquistabile.get().getPrezzoAcquisto();
        } else {
            return this._budgetRimanente;
        }
    }

    private void setBudgetRimanente(Integer budgetRimanente) {
        this._budgetRimanente = budgetRimanente;
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }
}
