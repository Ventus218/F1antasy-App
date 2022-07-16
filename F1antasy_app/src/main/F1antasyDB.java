package main;

import main.dto.*;

import java.time.LocalDate;
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
    public static Boolean createSquadra(String username, Integer annoCampionato, LocalDate dataGranPremio, String nome, List<Pilota> piloti, Motorizzazione motorizzazione) {
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
    public static Squadra getSquadraUtente(String username, Integer annoCampionato, LocalDate dataGranPremio) {
        // if (!utenteHasInitializedSquadra(username, annoCampionato, dataGranPremio)) {
        //     Utils.crashWithMessage("Trying to get Squadra of " + username + " while it was not even initialized.");
        // }
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
    public static List<Pilota> getSquadraPilotiUtente(String username, Integer annoCampionato, LocalDate dataGranPremio) {
        // if (!utenteHasInitializedSquadra(username, annoCampionato, dataGranPremio)) {
        //     Utils.crashWithMessage("Trying to get SquadraPiloti of " + username + " while it was not even initialized.");
        // }

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
    public static List<PilotaConPrezzo> getPilotiConPrezzo(Integer annoCampionato, LocalDate dataGranPremio) {
        // MOCKUP
        return PilotaConPrezzo.getSample();
    }
    /**
     * O4 - Visualizzazione di tutti i Piloti del Campionato corrente in ordine decrescente di prezzo
     */
    public static List<PilotaConPrezzo> getPilotiConPrezzo() {
        GranPremioProgrammato gpp = getGranPremioProgrammatoCorrente();
        return getPilotiConPrezzo(gpp.getCampionato().getAnno(), gpp.getDataGranPremio());
    }

    /**
     * O5 - Visualizzazione di tutte le Motorizzazioni del Campionato corrente in ordine decrescente di prezzo
     */
    public static List<MotorizzazioneConPrezzo> getMotorizzazioniConPrezzo(Integer annoCampionato, LocalDate dataGranPremio) {
        // MOCKUP
        return MotorizzazioneConPrezzo.getSample();
    }
    /**
     * O5 - Visualizzazione di tutte le Motorizzazioni del Campionato corrente in ordine decrescente di prezzo
     */
    public static List<MotorizzazioneConPrezzo> getMotorizzazioniConPrezzo() {
        GranPremioProgrammato gpp = getGranPremioProgrammatoCorrente();
        return getMotorizzazioniConPrezzo(gpp.getCampionato().getAnno(), gpp.getDataGranPremio());
    }

    /**
     * O6 - Visualizzazione Classifica Globale
     */
    public static Classifica getClassificaGlobale() {
        // MOCKUP
        return Classifica.getSample();
    }

    /**
     * O7 - Visualizzazione Classifica Privata
     */
    public static ClassificaPrivata getClassificaPrivata(String nome) {

        // MOCKUP
        Optional<ClassificaPrivata> first = ClassificaPrivata.getSample().stream().filter(c -> c.getNome().equals(nome)).findFirst();
        if (first.isPresent()) {
            return first.get();
        } else {
            Utils.crashWithMessage("No ClassificaPrivata " + nome + " was found.");
            return null; // will never run
        }
    }

    /**
     * O9 - Visualizzazione del Punteggio ottenuto in un Gran Premio concluso
     */
    public static Integer getPunteggioOttenutoGranPremioConcluso(String username, Integer annoCampionato, LocalDate dataGranPremio) {
        Optional<GranPremioProgrammato> gp = getGranPremiProgrammati(annoCampionato).stream().filter(g -> g.getDataGranPremio().equals(dataGranPremio)).findFirst();
        if (gp.isEmpty()) {
            Utils.crashWithMessage("Gran Premio for annoCampionato: " + annoCampionato.toString() + " and dataGranPremio: " + dataGranPremio.toString() + " not found.");
        } else if (!gp.get().getConcluso()) {
            Utils.crashWithMessage("Trying to get PunteggioOttenuto for gp: " + gp.get().toString() + " which is not Concluso");
        }

        // MOCKUP
        return 50;
    }

    /**
     * O12a - Scambio Pilota
     */
    public static Boolean exchangePiloti(String username, Integer annoCampionato, LocalDate dataGranPremio, Pilota oldPilota, Pilota newPilota) {
        // if (!utenteHasInitializedSquadra(username, annoCampionato, dataGranPremio)) {
        //     Utils.crashWithMessage("Trying to exchange Piloti of " + username + " while it's Squadra was not even initialized.");
        // }

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
    public static Boolean exchangeMotorizzazione(String username, Integer annoCampionato, LocalDate dataGranPremio, Motorizzazione oldMotorizzazione, Motorizzazione newMotorizzazione) {
        // if (!utenteHasInitializedSquadra(username, annoCampionato, dataGranPremio)) {
        //     Utils.crashWithMessage("Trying to exchange Motorizzazione of " + username + " while it's Squadra was not even initialized.");
        // }

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
     * O13 - Creazione Classifica Privata
     */
    public static Boolean createClassificaPrivata(String username, String nome) {
        // if (classificaPrivataExists(nome)) {
        //     return false;
        // }

        // MOCKUP
        // if (classificaPrivataExists(nome)) {
        if (false) {
            return false;
        }
        return true;
    }

    /**
     * O14 – Iscrizione ad una Classifica Privata
     */
    public static Boolean joinClassificaPrivata(String username, String nome) {
        if (getNomiClassifichePrivateUtente(username).contains(nome)) {
            Utils.crashWithMessage("User " + username + " already subscribed to ClassificaPrivata: " + nome);
        }
        if (! classificaPrivataExists(nome)) {
            return false;
        }

        // MOCKUP
        return true;
    }

    /**
     * O15 – Uscita da una Classifica Privata
     */
    public static Boolean leaveClassificaPrivata(String username, String nome) {
        if (! getNomiClassifichePrivateUtente(username).contains(nome)) {
            return false;
        }

        // MOCKUP
        return true;
    }

    /**
     * O22 - Visualizzazione del Valore di una Squadra
     */
    public static Integer getValoreSquadra(String username, Integer annoCampionato, LocalDate dataGranPremio) {
        // if (!utenteHasInitializedSquadra(username, annoCampionato, dataGranPremio)) {
        //     Utils.crashWithMessage("Trying to get ValoreSquadra of " + username + " while it's Squadra was not even initialized.");
        // }

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


    public static Integer getPrezzoPilota(Integer annoCampionato, LocalDate dataGranPremio, Pilota pilota) {
        Optional<PilotaConPrezzo> first = getPilotiConPrezzo(annoCampionato, dataGranPremio).stream().filter(p -> p.getPilota().equals(pilota)).findFirst();
        if (first.isPresent()) {
            return first.get().getPrezzo();
        } else {
            Utils.crashWithMessage("PrezzoPilota was not found as no Pilota like " + pilota.toString() + " was found in " + getPilotiConPrezzo(annoCampionato, dataGranPremio).toString());
            return null; // will never run
        }
    }
    public static Integer getPrezzoPilota(Pilota pilota) {
        GranPremioProgrammato gpp = getGranPremioProgrammatoCorrente();
        return getPrezzoPilota(gpp.getCampionato().getAnno(), gpp.getDataGranPremio(), pilota);
    }

    public static Integer getPrezzoMotorizzazione(Integer annoCampionato, LocalDate dataGranPremio, Motorizzazione motorizzazione) {
        Optional<MotorizzazioneConPrezzo> first = getMotorizzazioniConPrezzo(annoCampionato, dataGranPremio).stream().filter(m -> m.getMotorizzazione().equals(motorizzazione)).findFirst();
        if (first.isPresent()) {
            return first.get().getPrezzo();
        } else {
            Utils.crashWithMessage("PrezzoMotorizzazione was not found as no Motorizzazione like " + motorizzazione.toString() + " was found in " + getMotorizzazioniConPrezzo().toString());
            return null; // will never run
        }
    }
    public static Integer getPrezzoMotorizzazione(Motorizzazione motorizzazione) {
        GranPremioProgrammato gpp = getGranPremioProgrammatoCorrente();
        return getPrezzoMotorizzazione(gpp.getCampionato().getAnno(), gpp.getDataGranPremio(), motorizzazione);
    }


    // ***** MISSING OPERATIONS *****

    public static Boolean logInUtente(String username, String password) {
        // MOCKUP
        return username.equals("user") && password.equals("password");
    }

    public static Boolean checkIfUtenteExists(String username) {
        // MOCKUP
        return false;
    }

    public static Boolean utenteHasInitializedSquadra(String username, Integer annoCampionato, LocalDate dataGranPremio) {
        // REMEMBER TO UNCOMMENT USAGE IN O3 O12 O22
        // MOCKUP
        return false;
    }
    public static Boolean utenteHasInitializedSquadra(String username) {
        GranPremioProgrammato gpp = getGranPremioProgrammatoCorrente();
        return utenteHasInitializedSquadra(username, gpp.getCampionato().getAnno(), gpp.getDataGranPremio());
    }

    public static Integer getPunteggioAttualeUtente(String username) {
        // MOCKUP
        return 100;
    }

    public static Boolean classificaPrivataExists(String nome) {
        // MOCKUP
        return ClassificaPrivata.getSample().stream().map(ClassificaPrivata::getNome).collect(Collectors.toList()).contains(nome);
    }

    public static List<String> getNomiClassifichePrivateUtente(String username) {
        // MOCKUP
        return ClassificaPrivata.getSample().stream().map(ClassificaPrivata::getNome).collect(Collectors.toList());
    }

    public static List<GranPremioProgrammato> getGranPremiProgrammati(Integer annoCampionato) {
       // MOCKUP
       return GranPremioProgrammato.getSample().stream().filter(gpp -> gpp.getCampionato().getAnno().equals(annoCampionato)).collect(Collectors.toList());
    }

    public static GranPremioProgrammato getGranPremioProgrammatoCorrente() {
        // search for first non-finished Gran Prix

        // MOCKUP
        return GranPremioProgrammato.getSample().get(4);
    }

    public static Campionato getCampionatoCorrente() {
        return getGranPremioProgrammatoCorrente().getCampionato();
    }
}
