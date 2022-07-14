package main;

import main.dto.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class F1antasyDB {

    private F1antasyDB() { };

    /**
     * O2 - Inizializzazione della Squadra
     */
    public static Boolean createSquadra(String username, Integer annoCampionato, Date dataGranPremio, String nome, List<Pilota> piloti, Motorizzazione motorizzazione) {
        // MOCKUP
        return true;
    }
    /**
     * O2 - Inizializzazione della Squadra
     */
    public static Boolean createSquadra(String username, String nome, List<Pilota> piloti, Motorizzazione motorizzazione) {
        GranPremioProgrammato gpp = getGranPremioProgrammatoCorrente();
        return createSquadra(username, gpp.getCampionato().getAnno(), gpp.getDataGranPremio(), nome, piloti, motorizzazione);
    }

    /**
     * O3 - Visualizzazione della Squadra per un certo Gran Premio                  (DA DIVIDERE COME OPERAZIONE IN SQUADRA E PILOTI)
     */
    public static Squadra getSquadraUtente(String username, Integer annoCampionato, Date dataGranPremio) {
        // MOCKUP
        return new Squadra("Winners", annoCampionato, dataGranPremio, username, 1, 15000000, new Motorizzazione("Ferrari"));
    }
    /**
     * O3 - Visualizzazione della Squadra per un certo Gran Premio                  (DA DIVIDERE COME OPERAZIONE IN SQUADRA E PILOTI)
     */
    public static Squadra getSquadraUtente(String username) {
        GranPremioProgrammato gpp = getGranPremioProgrammatoCorrente();
        return getSquadraUtente(username, gpp.getCampionato().getAnno(), gpp.getDataGranPremio());
    }

    /**
     * O3 - Visualizzazione della Squadra (PILOTI) per un certo Gran Premio                  (DA DIVIDERE COME OPERAZIONE IN SQUADRA E PILOTI)
     */
    public static List<Pilota> getSquadraPilotiUtente(String username, Integer annoCampionato, Date dataGranPremio) {
        // MOCKUP
        List<Integer> codici = new ArrayList();
        codici.add(9);
        codici.add(13);
        codici.add(16);
        codici.add(18);
        return getPilotiConPrezzo().stream()
                .filter(p -> codici.contains((p.getPilota().getCodice())))
                .map(PilotaConPrezzo::getPilota)
                .collect(Collectors.toList());
    }
    /**
     * O3 - Visualizzazione della Squadra (PILOTI) per un certo Gran Premio                  (DA DIVIDERE COME OPERAZIONE IN SQUADRA E PILOTI)
     */
    public static List<Pilota> getSquadraPilotiUtente(String username) {
        GranPremioProgrammato gpp = getGranPremioProgrammatoCorrente();
        return getSquadraPilotiUtente(username, gpp.getCampionato().getAnno(), gpp.getDataGranPremio());
    }

    /**
     * O4 - Visualizzazione di tutti i Piloti del Campionato corrente in ordine decrescente di prezzo
     */
    public static List<PilotaConPrezzo> getPilotiConPrezzo() {
        // MOCKUP
        return PilotaConPrezzo.getSample();
    }

    /**
     * O5 - Visualizzazione di tutte le Motorizzazioni del Campionato corrente in ordine decrescente di prezzo
     */
    public static List<MotorizzazioneConPrezzo> getMotorizzazioniConPrezzo() {
        // MOCKUP
        return MotorizzazioneConPrezzo.getSample();
    }

    /**
     * O12a - Scambio Pilota
     */
    public static Boolean exchangePiloti(String username, Integer annoCampionato, Date dataGranPremio, Pilota oldPilota, Pilota newPilota) {
        // MOCKUP
        return true;
    }
    /**
     * O12a - Scambio Pilota
     */
    public static Boolean exchangePiloti(String username, Pilota oldPilota, Pilota newPilota) {
        GranPremioProgrammato gpp = getGranPremioProgrammatoCorrente();
        return exchangePiloti(username, gpp.getCampionato().getAnno(), gpp.getDataGranPremio(), oldPilota, newPilota);
    }

    /**
     * O12b - Scambio Motorizzazione
     */
    public static Boolean exchangeMotorizzazione(String username, Integer annoCampionato, Date dataGranPremio, Motorizzazione oldMotorizzazione, Motorizzazione newMotorizzazione) {
        // MOCKUP
        return true;
    }
    /**
     * O12b - Scambio Motorizzazione
     */
    public static Boolean exchangeMotorizzazione(String username, Motorizzazione oldMotorizzazione, Motorizzazione newMotorizzazione) {
        GranPremioProgrammato gpp = getGranPremioProgrammatoCorrente();
        return exchangeMotorizzazione(username, gpp.getCampionato().getAnno(), gpp.getDataGranPremio(), oldMotorizzazione, newMotorizzazione);
    }

    /**
     * O22 - Visualizzazione del Valore di una Squadra
     */
    public static Integer getValoreSquadra(String username, Integer annoCampionato, Date dataGranPremio) {
        // MOCKUP
        return (Integer) getPilotiConPrezzo().stream()
                .filter(p -> getSquadraPilotiUtente(username).contains(p.getPilota()))
                .map(PilotaConPrezzo::getPrezzo)
                .mapToInt(Integer::intValue)
                .sum()
                +
                getMotorizzazioniConPrezzo()
                .stream()
                .filter(m -> m.getMotorizzazione().equals(getSquadraUtente(username, annoCampionato, dataGranPremio).getNomeMotorizzazione()))
                .findFirst()
                .get()
                .getPrezzo();
    }
    /**
     * O22 - Visualizzazione del Valore di una Squadra
     */
    public static Integer getValoreSquadra(String username) {
        // GETTING CURRENT GRAND PRIX (NEED ATTRIBUTES FOR INSERT)
        GranPremioProgrammato gpp = getGranPremioProgrammatoCorrente();
        return getValoreSquadra(username, gpp.getCampionato().getAnno(), gpp.getDataGranPremio());
    }


    public static Integer getPrezzoPilota(Pilota pilota) {
        Optional<PilotaConPrezzo> first = getPilotiConPrezzo().stream().filter(p -> p.getPilota().equals(pilota)).findFirst();
        if (first.isPresent()) {
            return first.get().getPrezzo();
        } else {
            Utils.crashWithMessage("PrezzoPilota was not found as no Pilota like " + pilota.toString() + " was found in " + getPilotiConPrezzo().toString());
            return null; // will never run
        }
    }

    public static Integer getPrezzoMotorizzazione(Motorizzazione motorizzazione) {
        Optional<MotorizzazioneConPrezzo> first = getMotorizzazioniConPrezzo().stream().filter(m -> m.getMotorizzazione().equals(motorizzazione)).findFirst();
        if (first.isPresent()) {
            return first.get().getPrezzo();
        } else {
            Utils.crashWithMessage("PrezzoMotorizzazione was not found as no Motorizzazione like " + motorizzazione.toString() + " was found in " + getMotorizzazioniConPrezzo().toString());
            return null; // will never run
        }
    }


    // ***** MISSING OPERATIONS *****

    public static Integer getPunteggioAttualeUtente(String username) {
        // MOCKUP
        return 100;
    }

    public static List<String> getNomiClassifichePrivateUtente(String username) {
        // MOCKUP
        List<String> list = new ArrayList();
        list.add("Winners");
        list.add("Losers");
        list.add("HappyPeople");
        return list;
    }

    public static GranPremioProgrammato getGranPremioProgrammatoCorrente() {
        // search for first non-finished Gran Prix

        // MOCKUP
        return GranPremioProgrammato.getSample().get(4);
    }
}
