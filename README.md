# F1antasy

**F1antasy** è un fanta-gioco siviluppato attorno al mondo della Formula 1. E' stato realizzato come progetto per l'esame di Basi di Dati.

- Forma la tua squadra
- Effettua scambi tra un Gran Premio e l'altro per aumentare il valore della tua squadra
- Competi con i tuoi amici o con il mondo intero 

## Installazione

### Setup del Database

Per prima cosa assicurarsi di avere avviato un'istanza di MySQL sulla porta 3306.
Eseguire in ordine i seguenti script SQL:

1. [F1ANTASY.ddl](Query/F1ANTASY.ddl)
1. [Stored Procedures.sql](Query/Stored%20Procedures.sql)
1. [Create sample DB.sql](Query/Create%20sample%20DB.sql)

### Applicazione
Eseguire il jar (solo per Windows) comprensivo di tutte le librerie necessarie.
Oppure eseguire l'applicazione da un qualsiasi IDE assicurandosi di aver installato JavaFX e MySql-Connector (per JDBC)

Appena avviata l'applicazione verranno richieste le credenziali di accesso all'istanza di MySQL, inserirle e a quel punto si può procedere con la registrazione come nuovo utente oppure, maggiormente consigliato, accedere con uno dei seguenti account di esempio:

|*Username*   |*Password*|
|-------------|----------|
|Dario        |Password  |
|Ventu        |Password  |
|CiccioCarluz |Password  |

# Creators

[Alessandro Venturini](https://github.com/Ventus218)

[Francesco Carlucci](https://github.com/Fracarlucci)

[Dario Berti](https://github.com/DarioBerti)
