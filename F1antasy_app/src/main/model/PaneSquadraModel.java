package main.model;

import main.F1antasyDB;
import main.User;
import main.dto.*;

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
        GranPremioProgrammato currentGP = F1antasyDB.getGranPremioProgrammatoCorrente();
        return F1antasyDB.getSquadraUtente(getUsername(), currentGP.getCampionato().getAnno(), currentGP.getDataGranPremio());
    }

    private Integer getPunteggioAttualeFromDB() {
        return F1antasyDB.getPunteggioAttualeUtente(getUsername());
    }

    private Integer getValoreSquadraFromDB() {
        return F1antasyDB.getValoreSquadra(getUsername());
    }

    private List<PilotaConPrezzo> getPilotiConPrezzoFromDB() {
        List<Pilota> piloti = F1antasyDB.getSquadraPilotiUtente(getUsername());

        // LEAVE THIS HERE
        List<PilotaConPrezzo> pilotiConPrezzo = new ArrayList();
        for (int i = 0; i < piloti.size(); i++) {
            Pilota p = piloti.get(i);
            pilotiConPrezzo.add(new PilotaConPrezzo(p, getPrezzoPilotaFromDB(p)));
        }
        return pilotiConPrezzo;
    }

    private Integer getPrezzoPilotaFromDB(Pilota pilota) {
        return F1antasyDB.getPrezzoPilota(pilota);
    }

    private Integer getPrezzoMotorizzazioneFromDB(Motorizzazione motorizzazione) {
        return F1antasyDB.getPrezzoMotorizzazione(motorizzazione);
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
