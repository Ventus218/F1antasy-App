package main;

import main.dto.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class F1antasyDB {

    private static final Connection connection = (new ConnectionProvider("root", "Password123!", "F1ANTASY")).getMySQLConnection();

    private F1antasyDB() { };

    /**
     * O1 - RegistrazioneUtente
     */
    public static void signInUtente(String username, String password) throws SQLException {
        CallableStatement s;
        try {
            s = connection.prepareCall("{call registrazioneUtente(?, ?)}");
            s.setString(1, username);
            s.setString(2, password);
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return;
        }
        s.execute();
        s.close();
    }

    /**
     * O2 - Inizializzazione della Squadra
     */
    public static void createSquadra(String username, String nome, List<Pilota> piloti, Motorizzazione motorizzazione) throws SQLException {
        CallableStatement s;
        try {
            s = connection.prepareCall("{call inizializzazioneSquadra(?, ?, ?, ?, ?, ?)}");
            s.setString(1, username);
            s.setString(2, motorizzazione.getNome());
            s.setInt(3, piloti.get(0).getCodice());
            s.setInt(4, piloti.get(1).getCodice());
            s.setInt(5, piloti.get(2).getCodice());
            s.setInt(6, piloti.get(3).getCodice());
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return;
        }
        s.execute();
        s.close();
    }

    /**
     * O3a - Visualizzazione della Squadra (PILOTI) per un certo Gran Premio
     */
    public static List<PilotaConPrezzo> getSquadraPilotiUtente(String username, Integer annoCampionato, LocalDate dataGranPremio) throws SQLException {
        // if (!utenteHasInitializedSquadra(username, annoCampionato, dataGranPremio)) {
        //     Utils.crashWithMessage("Trying to get SquadraPiloti of " + username + " while it was not even initialized.");
        // }

        CallableStatement s;
        try {
            s = connection.prepareCall("{call visualizzazionePilotiSquadra(?, ?, ?)}");
            s.setString(1, username);
            s.setInt(2, annoCampionato);
            s.setDate(3, Date.valueOf(dataGranPremio));
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
        Boolean result = s.execute();

        List<PilotaConPrezzo> l = new ArrayList();
        while (result) {
            ResultSet rs = s.getResultSet();

            while (rs.next()) {
                Integer codice = rs.getInt(1);
                String nome = rs.getString(2);
                String cognome = rs.getString(3);
                Integer prezzo = rs.getInt(4);

                l.add(new PilotaConPrezzo(new Pilota(codice, nome, cognome), prezzo));
            }

            result = s.getMoreResults();
        }
        s.close();

        return l;
    }
    /**
     * O3a - Visualizzazione della Squadra (PILOTI) per un certo Gran Premio
     */
    public static List<PilotaConPrezzo> getSquadraPilotiUtente(String username) throws SQLException {
        GranPremioProgrammato gpp = getGranPremioProgrammatoCorrente();
        return getSquadraPilotiUtente(username, gpp.getCampionato().getAnno(), gpp.getDataGranPremio());
    }

    /**
     * O3b - Visualizzazione della Squadra per un certo Gran Premio
     */
    public static Squadra getSquadraUtente(String username, Integer annoCampionato, LocalDate dataGranPremio) throws SQLException {
        if (!utenteHasSquadraForGranPremio(username, annoCampionato, dataGranPremio)) {
            Utils.crashWithMessage("Utente " + username + " doesn't have a squadra for GranPremio " + annoCampionato + " " + dataGranPremio.toString());
        }

        CallableStatement s;
        try {
            s = connection.prepareCall("{call visualizzazioneSquadra(?, ?, ?)}");
            s.setString(1, username);
            s.setInt(2, annoCampionato);
            s.setDate(3, Date.valueOf(dataGranPremio));
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
        s.execute();
        ResultSet rs = s.getResultSet();
        rs.next();
        Integer annoC = rs.getInt(1);
        LocalDate dataGP = rs.getDate(2).toLocalDate();
        String user = rs.getString(3);
        Integer scambi = rs.getInt(4);
        Integer budget = rs.getInt(5);
        String nomeMot = rs.getString(6);

        return new Squadra("NOME", annoC, dataGP, user, scambi, budget, new Motorizzazione(nomeMot));
    }
    /**
     * O3b - Visualizzazione della Squadra per un certo Gran Premio
     */
    public static Squadra getSquadraUtente(String username) throws SQLException {
        GranPremioProgrammato gpp = getGranPremioProgrammatoCorrente();
        return getSquadraUtente(username, gpp.getCampionato().getAnno(), gpp.getDataGranPremio());
    }

    /**
     * O4 - Visualizzazione di tutti i Piloti per un certo Gran Premio in ordine decrescente di prezzo
     */
    public static List<PilotaConPrezzo> getPilotiConPrezzo(Integer annoCampionato, LocalDate dataGranPremio) throws SQLException {
        CallableStatement s;
        try {
            s = connection.prepareCall("{call visualizzazionePilotiPrezzoPerGranPremio(?, ?)}");
            s.setInt(1, annoCampionato);
            s.setDate(2, Date.valueOf(dataGranPremio));
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
        Boolean result = s.execute();

        List<PilotaConPrezzo> l = new ArrayList();
        while (result) {
            ResultSet rs = s.getResultSet();

            while (rs.next()) {
                Integer codice = rs.getInt(1);
                String nome = rs.getString(2);
                String cognome = rs.getString(3);
                Integer prezzo = rs.getInt(4);

                l.add(new PilotaConPrezzo(new Pilota(codice, nome, cognome), prezzo));
            }

            result = s.getMoreResults();
        }
        s.close();

        return l;
    }
    public static List<PilotaConPrezzo> getPilotiConPrezzo() throws SQLException {
        GranPremioProgrammato gpp = getGranPremioProgrammatoCorrente();
        return getPilotiConPrezzo(gpp.getCampionato().getAnno(), gpp.getDataGranPremio());
    }

    /**
     * O5 - Visualizzazione di tutte le Motorizzazioni per un certo Gran Premio in ordine decrescente di prezzo
     */
    public static List<MotorizzazioneConPrezzo> getMotorizzazioniConPrezzo(Integer annoCampionato, LocalDate dataGranPremio) throws SQLException {
        CallableStatement s;
        try {
            s = connection.prepareCall("{call visualizzazioneMotorizzazioniPrezzoPerGranPremio(?, ?)}");
            s.setInt(1, annoCampionato);
            s.setDate(2, Date.valueOf(dataGranPremio));
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
        Boolean result = s.execute();

        List<MotorizzazioneConPrezzo> l = new ArrayList();
        while (result) {
            ResultSet rs = s.getResultSet();

            while (rs.next()) {
                String nome = rs.getString(1);
                Integer prezzo = rs.getInt(2);

                l.add(new MotorizzazioneConPrezzo(new Motorizzazione(nome), prezzo));
            }

            result = s.getMoreResults();
        }
        s.close();

        return l;
    }
    public static List<MotorizzazioneConPrezzo> getMotorizzazioniConPrezzo() throws SQLException {
        GranPremioProgrammato gpp = getGranPremioProgrammatoCorrente();
        return getMotorizzazioniConPrezzo(gpp.getCampionato().getAnno(), gpp.getDataGranPremio());
    }

    /**
     * O6 - Visualizzazione Classifica Globale
     */
    public static Classifica getClassificaGlobale() throws SQLException {
        CallableStatement s;
        try {
            s = connection.prepareCall("{call visualizzazioneClassificaGlobale()}");
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
        Boolean result = s.execute();

        List<UtenteInClassifica> l = new ArrayList();
        while (result) {
            ResultSet rs = s.getResultSet();

            while (rs.next()) {
                String username = rs.getString(1);
                Integer punteggio = rs.getInt(2);

                l.add(new UtenteInClassifica(username, punteggio));
            }

            result = s.getMoreResults();
        }
        s.close();

        return new Classifica(l);
    }

    /**
     * O7 - Visualizzazione Classifica Privata
     */
    public static ClassificaPrivata getClassificaPrivata(String nome) throws SQLException {
        CallableStatement s;
        try {
            s = connection.prepareCall("{call visualizzazioneClassificaPrivata(?)}");
            s.setString(1, nome);
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
        Boolean result = s.execute();

        List<UtenteInClassifica> l = new ArrayList();
        while (result) {
            ResultSet rs = s.getResultSet();

            while (rs.next()) {
                String username = rs.getString(1);
                Integer punteggio = rs.getInt(2);

                l.add(new UtenteInClassifica(username, punteggio));
            }

            result = s.getMoreResults();
        }
        s.close();

        return new ClassificaPrivata(nome, l);
    }

    /**
     * O9 - Visualizzazione del Punteggio ottenuto in un Gran Premio concluso
     */
    public static Integer getPunteggioOttenutoGranPremioConcluso(String username, Integer annoCampionato, LocalDate dataGranPremio) throws SQLException {
        CallableStatement s;
        try {
            s = connection.prepareCall("{call punteggioUtentePerGranPremio(?, ?, ?, ?)}");
            s.setString(1, username);
            s.setInt(2, annoCampionato);
            s.setDate(3, Date.valueOf(dataGranPremio));
            s.registerOutParameter(4, Types.INTEGER);
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
        s.execute();
        return s.getInt(4);
    }

    /**
     * O12a - Scambio Pilota
     */
    public static Boolean exchangePiloti(String username, Integer annoCampionato, LocalDate dataGranPremio, Pilota oldPilota, Pilota newPilota) throws SQLException {
        if (!utenteHasSquadraForGranPremio(username, annoCampionato, dataGranPremio)) {
            Utils.crashWithMessage("Utente " + username + " doesn't have a squadra for GranPremio " + annoCampionato + " " + dataGranPremio.toString());
        }

        CallableStatement s;
        try {
            s = connection.prepareCall("{call scambioPilota(?, ?, ?, ?, ?)}");
            s.setString(1, username);
            s.setInt(2, annoCampionato);
            s.setDate(3, Date.valueOf(dataGranPremio));
            s.setInt(4, oldPilota.getCodice());
            s.setInt(5, newPilota.getCodice());
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }

        s.execute();
        s.close();
        return true;
    }
    /**
     * O12a - Scambio Pilota
     */
    public static Boolean exchangePiloti(String username, Pilota oldPilota, Pilota newPilota) throws SQLException {
        GranPremioProgrammato gpp = getGranPremioProgrammatoCorrente();
        return exchangePiloti(username, gpp.getCampionato().getAnno(), gpp.getDataGranPremio(), oldPilota, newPilota);
    }

    /**
     * O12b - Scambio Motorizzazione
     */
    public static Boolean exchangeMotorizzazione(String username, Integer annoCampionato, LocalDate dataGranPremio, Motorizzazione oldMotorizzazione, Motorizzazione newMotorizzazione) throws SQLException {
        if (!utenteHasSquadraForGranPremio(username, annoCampionato, dataGranPremio)) {
            Utils.crashWithMessage("Utente " + username + " doesn't have a squadra for GranPremio " + annoCampionato + " " + dataGranPremio.toString());
        }

        CallableStatement s;
        try {
            s = connection.prepareCall("{call scambioMotorizzazione(?, ?, ?, ?, ?)}");
            s.setString(1, username);
            s.setInt(2, annoCampionato);
            s.setDate(3, Date.valueOf(dataGranPremio));
            s.setString(4, oldMotorizzazione.getNome());
            s.setString(5, newMotorizzazione.getNome());
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }

        s.execute();
        s.close();
        return true;
    }
    /**
     * O12b - Scambio Motorizzazione
     */
    public static Boolean exchangeMotorizzazione(String username, Motorizzazione oldMotorizzazione, Motorizzazione newMotorizzazione) throws SQLException {
        GranPremioProgrammato gpp = getGranPremioProgrammatoCorrente();
        return exchangeMotorizzazione(username, gpp.getCampionato().getAnno(), gpp.getDataGranPremio(), oldMotorizzazione, newMotorizzazione);
    }

    /**
     * O13 - Creazione Classifica Privata
     */
    public static Boolean createClassificaPrivata(String username, String nome) throws SQLException {
        CallableStatement s;
        try {
            s = connection.prepareCall("{call creazioneClassificaPrivata(?, ?)}");
            s.setString(1, username);
            s.setString(2, nome);
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }

        s.execute();
        s.close();
        return true;
    }

    /**
     * O14 – Iscrizione ad una Classifica Privata
     */
    public static Boolean joinClassificaPrivata(String username, String nome) throws SQLException {
        if (getNomiClassifichePrivateUtente(username).contains(nome)) {
            Utils.crashWithMessage("User " + username + " already subscribed to ClassificaPrivata: " + nome);
        }

        CallableStatement s;
        try {
            s = connection.prepareCall("{call iscrizioneClassificaPrivata(?, ?)}");
            s.setString(1, username);
            s.setString(2, nome);
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }

        s.execute();
        s.close();
        return true;
    }

    /**
     * O15 – Uscita da una Classifica Privata
     */
    public static Boolean leaveClassificaPrivata(String username, String nome) throws SQLException {
        if (! getNomiClassifichePrivateUtente(username).contains(nome)) {
            Utils.crashWithMessage("User " + username + " is not subscribed to ClassificaPrivata: " + nome);
        }

        CallableStatement s;
        try {
            s = connection.prepareCall("{call uscitaClassificaPrivata(?, ?)}");
            s.setString(1, username);
            s.setString(2, nome);
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }

        s.execute();
        s.close();
        return true;
    }

    /**
     * O22 - Visualizzazione del Valore di una Squadra
     */
    public static Integer getValoreSquadra(String username, Integer annoCampionato, LocalDate dataGranPremio) throws SQLException {
        if (!utenteHasSquadraForGranPremio(username, annoCampionato, dataGranPremio)) {
            Utils.crashWithMessage("Utente " + username + " doesn't have a squadra for GranPremio " + annoCampionato + " " + dataGranPremio.toString());
        }

        CallableStatement s;
        try {
            s = connection.prepareCall("{call valoreSquadra(?, ?, ? ,?)}");
            s.setString(1, username);
            s.setInt(2, annoCampionato);
            s.setDate(3, Date.valueOf(dataGranPremio));
            s.registerOutParameter(4, Types.INTEGER);
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
        s.execute();
        return s.getInt(4);
    }
    /**
     * O22 - Visualizzazione del Valore di una Squadra
     */
    public static Integer getValoreSquadra(String username) throws SQLException {
        // GETTING CURRENT GRAND PRIX (NEED ATTRIBUTES FOR INSERT)
        GranPremioProgrammato gpp = getGranPremioProgrammatoCorrente();
        return getValoreSquadra(username, gpp.getCampionato().getAnno(), gpp.getDataGranPremio());
    }

    /**
     * O23 - Login Utente
     */
    public static Boolean logInUtente(String username, String password) throws SQLException {
        CallableStatement s;
        try {
            s = connection.prepareCall("{call loginUtente(?, ?, ?)}");
            s.setString(1, username);
            s.setString(2, password);
            s.registerOutParameter(3, Types.BOOLEAN);
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
        s.execute();
        return s.getBoolean(3);
    }

    /**
     * O24 - Controllo se Utente ha Squadra per Gran Premio
     */
    public static Boolean utenteHasSquadraForGranPremio(String username, Integer annoCampionato, LocalDate dataGranPremio) throws SQLException {
        CallableStatement s;
        try {
            s = connection.prepareCall("{call checkUtenteHaSquadraPerGranPremio(?, ?, ? ,?)}");
            s.setString(1, username);
            s.setInt(2, annoCampionato);
            s.setDate(3, Date.valueOf(dataGranPremio));
            s.registerOutParameter(4, Types.BOOLEAN);
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
        s.execute();
        return s.getBoolean(4);
    }
    public static Boolean utenteHasInitializedSquadra(String username) throws SQLException {
        GranPremioProgrammato gpp = getGranPremioProgrammatoCorrente();
        return utenteHasSquadraForGranPremio(username, gpp.getCampionato().getAnno(), gpp.getDataGranPremio());
    }

    /**
     * O25 - Visualizza Gran Premio Corrente
     */
    public static GranPremioProgrammato getGranPremioProgrammatoCorrente() throws SQLException {
        CallableStatement s;
        try {
            s = connection.prepareCall("{call visualizzaGranPremioCorrente(?, ?, ?, ?, ?)}");
            s.registerOutParameter(1, Types.INTEGER);
            s.registerOutParameter(2, Types.DATE);
            s.registerOutParameter(3, Types.VARCHAR);
            s.registerOutParameter(4, Types.VARCHAR);
            s.registerOutParameter(5, Types.BOOLEAN);
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
        s.execute();
        return new GranPremioProgrammato(new Campionato(s.getInt(1)), s.getDate(2).toLocalDate(), s.getString(3), s.getString(4), s.getBoolean(5));
    }

    /**
     * O26 - Visualizza Nomi Classifiche Private Utente
     */
    public static List<String> getNomiClassifichePrivateUtente(String username) throws SQLException {
        CallableStatement s;
        try {
            s = connection.prepareCall("{call visualizzaNomiClassifichePrivateUtente(?)}");
            s.setString(1, username);
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
        Boolean result = s.execute();

        List<String> l = new ArrayList();
        while (result) {
            ResultSet rs = s.getResultSet();

            while (rs.next()) {
                String nome = rs.getString(1);

                l.add(nome);
            }

            result = s.getMoreResults();
        }
        s.close();

        return l;
    }

    /**
     * O27 - Visualizza Gran Premi per Campionato
     */
    public static List<GranPremioProgrammato> getGranPremiProgrammati(Integer annoCampionato) throws SQLException {
        CallableStatement s;
        try {
            s = connection.prepareCall("{call visualizzaGranPremiPerCampionato(?)}");
            s.setInt(1, annoCampionato);
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
        Boolean result = s.execute();

        List<GranPremioProgrammato> l = new ArrayList();
        while (result) {
            ResultSet rs = s.getResultSet();

            while (rs.next()) {
                Integer anno = rs.getInt(1);
                LocalDate dataGP = rs.getDate(2).toLocalDate();
                String stato = rs.getString(3);
                String nome = rs.getString(4);
                Boolean concluso = rs.getBoolean(5);

                l.add(new GranPremioProgrammato(new Campionato(anno), dataGP, stato, nome, concluso));
            }

            result = s.getMoreResults();
        }
        s.close();

        return l;
    }

    /**
     * O28 - Visualizza Punteggio attuale Utente
     */
    public static Integer getPunteggioAttualeUtente(String username) throws SQLException {
        CallableStatement s;
        try {
            s = connection.prepareCall("{call visualizzaPunteggioAttualeUtente(?, ?)}");
            s.setString(1, username);
            s.registerOutParameter(2, Types.INTEGER);
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
        s.execute();

        return s.getInt(2);
    }


    // ***** CONVENIENCE METHODS ******

    public static Integer getPrezzoPilota(Integer annoCampionato, LocalDate dataGranPremio, Pilota pilota) throws SQLException {
        Optional<PilotaConPrezzo> first = getPilotiConPrezzo(annoCampionato, dataGranPremio).stream().filter(p -> p.getPilota().equals(pilota)).findFirst();
        if (first.isPresent()) {
            return first.get().getPrezzo();
        } else {
            Utils.crashWithMessage("PrezzoPilota was not found as no Pilota like " + pilota.toString() + " was found in " + getPilotiConPrezzo(annoCampionato, dataGranPremio).toString());
            return null; // will never run
        }
    }
    public static Integer getPrezzoPilota(Pilota pilota) throws SQLException {
        GranPremioProgrammato gpp = getGranPremioProgrammatoCorrente();
        return getPrezzoPilota(gpp.getCampionato().getAnno(), gpp.getDataGranPremio(), pilota);
    }

    public static Integer getPrezzoMotorizzazione(Integer annoCampionato, LocalDate dataGranPremio, Motorizzazione motorizzazione) throws SQLException {
        Optional<MotorizzazioneConPrezzo> first = getMotorizzazioniConPrezzo(annoCampionato, dataGranPremio).stream().filter(m -> m.getMotorizzazione().equals(motorizzazione)).findFirst();
        if (first.isPresent()) {
            return first.get().getPrezzo();
        } else {
            Utils.crashWithMessage("PrezzoMotorizzazione was not found as no Motorizzazione like " + motorizzazione.toString() + " was found in " + getMotorizzazioniConPrezzo().toString());
            return null; // will never run
        }
    }
    public static Integer getPrezzoMotorizzazione(Motorizzazione motorizzazione) throws SQLException {
        GranPremioProgrammato gpp = getGranPremioProgrammatoCorrente();
        return getPrezzoMotorizzazione(gpp.getCampionato().getAnno(), gpp.getDataGranPremio(), motorizzazione);
    }

    public static Campionato getCampionatoCorrente() throws SQLException {
        return getGranPremioProgrammatoCorrente().getCampionato();
    }
}
