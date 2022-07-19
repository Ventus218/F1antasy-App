# F1antasy

**F1antasy** è un fanta-gioco siviluppato attorno al mondo della formula 1.

- Forma la tua squadra
- Effettua scambi tra un Gran Premio e l'altro per aumentare il valore della tua squadra
- Competi con i tuoi amici o con il mondo intero 

## Installazione

### Setup del Database

Per prima cosa assicurarsi di avere avviato un'istanza di MySQL sulla porta 3306.
Eseguire in ordine i seguenti script SQL:

1. [F1ANTASY.ddl](Query/F1ANTASY.ddl)
1. [O1 - Registrazione Utente.sql](Query/O1%20-%20Registrazione%20Utente.sql)
1. [O2 - Inizializzazione della Squadra.sql](Query/O2%20-%20Inizializzazione%20della%20Squadra.sql)
1. [O3a - Visualizzazione della Squadra per un certo GranPremio.sql](Query/O3a%20-%20Visualizzazione%20della%20Squadra%20per%20un%20certo%20GranPremio.sql)
1. [O3b - Visualizzazione della Squadra per un certo Gran Premio.sql](Query/O3b%20-%20Visualizzazione%20della%20Squadra%20per%20un%20certo%20Gran%20Premio.sql)
1. [O4 - Visualizzazione Piloti per Gran Premio.sql](Query/O4%20-%20Visualizzazione%20Piloti%20per%20Gran%20Premio.sql)
1. [O5 - Visualizzazione Motorizzazioni per Gran Premio.sql](Query/O5%20-%20Visualizzazione%20Motorizzazioni%20per%20Gran%20Premio.sql)
1. [O6 - Visualizzazione Classifica Globale.sql](Query/O6%20-%20Visualizzazione%20Classifica%20Globale.sql)
1. [O7 - Visualizzazione Classifica Privata.sql](Query/O7%20-%20Visualizzazione%20Classifica%20Privata.sql)
1. [O9 - Visualizzazione del Punteggio ottenuto in un Gran Premio concluso.sql](Query/O9%20-%20Visualizzazione%20del%20Punteggio%20ottenuto%20in%20un%20Gran%20Premio%20concluso.sql)
1. [O10 - Reset del Punteggio dell’Utente.sql](Query/O10%20-%20Reset%20del%20Punteggio%20dell%E2%80%99Utente.sql)
1. [O12a - Scambio Pilota.sql](Query/O12a%20-%20Scambio%20Pilota.sql)
1. [O12b - Scambio Motorizzazione.sql](Query/O12b%20-%20Scambio%20Motorizzazione.sql)
1. [O13 - Creazione Classifica Privata.sql](Query/O13%20-%20Creazione%20Classifica%20Privata.sql3)
1. [O14 – Iscrizione ad una Classifica Privata.sql](Query/O14%20%E2%80%93%20Iscrizione%20ad%20una%20Classifica%20Privata.sql)
1. [O15 – Uscita da una Classifica Privata.sql](Query/O15%20%E2%80%93%20Uscita%20da%20una%20Classifica%20Privata.sql)
1. [O16 - Eliminazione Classifica Privata.sql](Query/O16%20-%20Eliminazione%20Classifica%20Privata.sql)
1. [O17 - Inserimento dei Risultati di un Gran Premio concluso.sql](Query/O17%20-%20Inserimento%20dei%20Risultati%20di%20un%20Gran%20Premio%20concluso.sql)
1. [O18 - Copia della Squadra per il Gran Premio successivo.sql](Query/O18%20-%20Copia%20della%20Squadra%20per%20il%20Gran%20Premio%20successivo.sql)
1. [O19 - Aggiornamento Punteggio dell Utente.sql](Query/O19%20-%20Aggiornamento%20Punteggio%20dell%20Utente.sql)
1. [O20 - Aggiornamento prezzo Pilota.sql](Query/O20%20-%20Aggiornamento%20prezzo%20Pilota.sql)
1. [O21 - Aggiornamento prezzo Motorizzazione.sql](Query/O21%20-%20Aggiornamento%20prezzo%20Motorizzazione.sql)
1. [O22 - Visualizzazione del Valore di una Squadra.sql](Query/O22%20-%20Visualizzazione%20del%20Valore%20di%20una%20Squadra.sql)
1. [O23 - Login Utente.sql](Query/O23%20-%20Login%20Utente.sql)
1. [O24 - Controllo se Utente ha Squadra per Gran Premio.sql](Query/O24%20-%20Controllo%20se%20Utente%20ha%20Squadra%20per%20Gran%20Premio.sql)
1. [O25 - Visualizza Gran Premio Corrente.sql](Query/O25%20-%20Visualizza%20Gran%20Premio%20Corrente.sql)
1. [O26 - Visualizza Nomi Classifiche Private Utente.sql](Query/O26%20-%20Visualizza%20Nomi%20Classifiche%20Private%20Utente.sql)
1. [O27 - Visualizza Gran Premi per Campionato.sql](Query/O27%20-%20Visualizza%20Gran%20Premi%20per%20Campionato.sql)
1. [O28 - Visualizza Punteggio attuale Utente.sql](Query/O28%20-%20Visualizza%20Punteggio%20attuale%20Utente.sql)
1. [Carica DB di Esempio](Query/Inserimenti%20vari.sql)

### Applicazione
Eseguire il jar comprensivo di tutte le librerie necessarie.

Oppure eseguire l'applicazione da un qualsiasi IDE assicurandosi di aver installato JavaFX e MySql-Connector (per JDBC)