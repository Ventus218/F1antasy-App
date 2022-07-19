-- Inserimento Motorizzazioni
INSERT INTO MOTORIZZAZIONE(Nome)
    VALUES
        ('Ferrari'),
        ('Mercedes'),
        ('Honda'),
        ('Renault'),
        ('Red Bull');

-- Inserimento Scuderie
INSERT INTO SCUDERIA(Nome)
    VALUES
        ('Ferrari'),
        ('Mercedes'),
        ('Red Bull'),
        ('Mclaren'),
        ('Alpine'),
        ('Haas'),
        ('AlphaTauri'),
        ('Alfa Romeo'),
        ('Williams'),
        ('Aston Martin');

-- Inserimento Gran Premi
INSERT INTO GRAN_PREMIO(Stato, Nome, NumeroGiri, LunghezzaCircuito)
    VALUES
        ('Arabia Saudita', 'Jeddah', 50, 6000),
        ('Australia', 'Melbourne', 58, 5000),
        ('Florida', 'Miami Autodrome', 57, 5000),
        ('Gran Bretagna', 'Silverstone', 52, 6000),
        ('Giappone', 'Suzuka', 53, 6000),
        ('Belgio', 'Spa-Francorchamps', 40, 7000),
        ('Singapore', 'Marina Bay', 61, 5000),
        ('Brasile', 'Interlagos', 71, 4000),
        ('Italia', 'Monza', 70, 3000),
        ('Messico', 'Città del Messico', 71, 4000),
        ('Spagna', 'Catalogna', 69, 3000),
        ('Principato di Monaco', 'Monte Carlo', 70, 3000);

-- Inserimento Piloti
INSERT INTO PILOTA(Codice, Nome, Cognome)
VALUES
    (1, 'Lewis', 'Hamilton'),
    (2, 'Valtteri', 'Bottas'),
    (3, 'Max', 'Verstappen'),
    (4, 'Sergio', 'Perez'),
    (5, 'Daniel', 'Ricciardo'),
    (6, 'Lando', 'Norris'),
    (7, 'Sebastian', 'Vettel'),
    (8, 'Lance', 'Stroll'),
    (9, 'Esteban', 'Ocon'),
    (10, 'Fernando', 'Alonso'),
    (11, 'Charles', 'Leclerc'),
    (12, 'Carlos', 'Sainz'),
    (13, 'Pierre', 'Gasly'),
    (14, 'Yuki', 'Tsunoda'),
    (15, 'Kimi', 'Raikkonen'),
    (16, 'Antonio', 'Giovinazzi'),
    (17, 'Mick', 'Schumacher'),
    (18, 'Nikita', 'Mazepin'),
    (19, 'George', 'Russell'),
    (20, 'Nicholas', 'Latifi'),
    (21, 'Alexander', 'Albon'),
    (22, 'Guanyu', 'Zhou'),
    (23, 'Kevin', 'Magnussen');

-- Inserimento dei Punteggi relativi a ciascuna Posizione
INSERT INTO POSIZIONE_PUNTEGGIO(Posizione, Punteggio)
    VALUES
    (1, 38),
    (2, 32),
    (3, 27),
    (4, 23),
    (5, 20),
    (6, 18),
    (7, 16),
    (8, 14),
    (9, 12),
    (10, 10),
    (11, 9),
    (12, 8),
    (13, 7),
    (14, 6),
    (15, 5),
    (16, 4),
    (17, 3),
    (18, 2),
    (19, 1),
    (20, 0);

-- Inserimento Campionato
INSERT INTO CAMPIONATO(Anno)
    VALUES
        (2022);

-- Inserimento Gran Premi Programmati
INSERT INTO GRAN_PREMIO_PROGRAMMATO(AnnoCampionato, Data, Stato, Nome, Concluso)
     VALUES
        (2022, '2022-03-27', 'Arabia Saudita', 'Jeddah', 0),
        (2022, '2022-04-10', 'Australia', 'Melbourne', 0),
        (2022, '2022-05-08', 'Florida', 'Miami Autodrome', 0),
        (2022, '2022-05-22', 'Spagna', 'Catalogna', 0),
        (2022, '2022-05-29', 'Principato di Monaco', 'Monte Carlo', 0),
        (2022, '2022-07-03', 'Gran Bretagna', 'Silverstone', 0),
        (2022, '2022-08-28', 'Belgio', 'Spa-Francorchamps', 0),
        (2022, '2022-09-11', 'Italia', 'Monza', 0),
        (2022, '2022-10-02', 'Singapore', 'Marina Bay', 0),
        (2022, '2022-10-09', 'Giappone', 'Suzuka', 0),
        (2022, '2022-10-30', 'Messico', 'Città del Messico', 0),
        (2022, '2022-11-13', 'Brasile', 'Interlagos', 0);

-- Inserimento Scuderie che partecipano al Campionato
INSERT INTO SCUDERIA_PARTECIPANTE(AnnoCampionato, NomeScuderia, NomeMotorizzazione)
    VALUES
        (2022, 'Ferrari', 'Ferrari'),
        (2022, 'Mercedes', 'Mercedes'),
        (2022, 'Mclaren', 'Mercedes'),
        (2022, 'Red Bull', 'Red Bull'),
        (2022, 'Alpine', 'Renault'),
        (2022, 'AlphaTauri', 'Red Bull'),
        (2022, 'Aston Martin', 'Mercedes'),
        (2022, 'Williams', 'Mercedes'),
        (2022, 'Alfa Romeo', 'Ferrari'),
        (2022, 'Haas', 'Ferrari');

-- Inserimento dei Piloti nella propria Scuderia
INSERT INTO INGAGGIO_PILOTA(AnnoCampionato, NomeScuderia, CodicePilota)
    VALUES
        (2022, 'Mercedes', 1),
        (2022, 'Mercedes', 19),
        (2022, 'Red Bull', 3),
        (2022, 'Red Bull', 4),
        (2022, 'Mclaren', 5),
        (2022, 'Mclaren', 6),
        (2022, 'Aston Martin', 7),
        (2022, 'Aston Martin', 8),
        (2022, 'Alpine', 9),
        (2022, 'Alpine', 10),
        (2022, 'Ferrari', 11),
        (2022, 'Ferrari', 12),
        (2022, 'AlphaTauri', 13),
        (2022, 'AlphaTauri', 14),
        (2022, 'Alfa Romeo', 2),
        (2022, 'Alfa Romeo', 22),
        (2022, 'Haas', 17),
        (2022, 'Haas', 23),
        (2022, 'Williams', 20),
        (2022, 'Williams', 21);

-- Inserimento dei Piloti nel primo Gran Premio del Campionato
INSERT INTO PILOTA_IN_GRAN_PREMIO(AnnoCampionato, DataGranPremio, CodicePilota, Prezzo, Posizione)
    VALUES
        (2022, '2022-03-27', 3,     40000000, NULL),
        (2022, '2022-03-27', 1,     36000000, NULL),
        (2022, '2022-03-27', 2,     33000000, NULL),
        (2022, '2022-03-27', 4,     32000000, NULL),
        (2022, '2022-03-27', 12,   30000000, NULL),
        (2022, '2022-03-27', 6,     27000000, NULL),
        (2022, '2022-03-27', 11,   25000000, NULL),
        (2022, '2022-03-27', 5,     23000000, NULL),
        (2022, '2022-03-27', 13,   21000000, NULL),
        (2022, '2022-03-27', 10,   19000000, NULL),
        (2022, '2022-03-27', 9,     17000000, NULL),
        (2022, '2022-03-27', 7,     15000000, NULL),
        (2022, '2022-03-27', 8,     13000000, NULL),
        (2022, '2022-03-27', 14,   11000000, NULL),
        (2022, '2022-03-27', 19,   9000000, NULL),
        (2022, '2022-03-27', 20,   7000000, NULL),
        (2022, '2022-03-27', 17,   6000000, NULL),
        (2022, '2022-03-27', 21,   10000000, NULL),
        (2022, '2022-03-27', 22,   10000000, NULL),
        (2022, '2022-03-27', 23,   10000000, NULL);

-- Inserimento delle Motorizzazioni nel primo Gran Premio del Campionato
INSERT INTO MOTORIZZAZIONE_IN_GRAN_PREMIO(AnnoCampionato, DataGranPremio, NomeMotorizzazione, Prezzo, PunteggioOttenuto)
VALUES
    (2022, '2022-03-27', 'Mercedes',    15000000, NULL),
    (2022, '2022-03-27', 'Ferrari',         9000000, NULL),
    (2022, '2022-03-27', 'Renault',        8000000, NULL),
    (2022, '2022-03-27', 'Red Bull',        7000000, NULL);

-- Registrazione Utenti
CALL registrazioneUtente('CiccioCarluz', 'Password');
CALL registrazioneUtente('Mario', 'Password');
CALL registrazioneUtente('Dario', 'Password');
CALL registrazioneUtente('Ventu', 'Password');
CALL registrazioneUtente('Franky', 'Password');
CALL registrazioneUtente('Roman', 'Password');

-- Creazione Classifiche Private
CALL creazioneClassificaPrivata('CiccioCarluz', 'UniboLeague');
CALL iscrizioneClassificaPrivata('Dario', 'UniboLeague');
CALL iscrizioneClassificaPrivata('Roman', 'UniboLeague');
CALL iscrizioneClassificaPrivata('Ventu', 'UniboLeague');

CALL creazioneClassificaPrivata('Ventu', 'Bolognesi');
CALL iscrizioneClassificaPrivata('Dario', 'Bolognesi');

-- Inizializzazione Squadre per il primo Gran Premio
CALL inizializzazioneSquadra('CiccioCarluz', 'Ferrari', 5, 6, 12, 19);
CALL inizializzazioneSquadra('Dario', 'Mercedes', 5, 8, 12, 10);
CALL inizializzazioneSquadra('Ventu', 'Ferrari', 5, 8, 12, 11);
CALL inizializzazioneSquadra('Mario', 'Mercedes', 22, 21, 3, 11);
CALL inizializzazioneSquadra('Franky', 'Red Bull', 22, 17, 3, 13);
CALL inizializzazioneSquadra('Roman', 'Red Bull', 17, 21, 3, 11);

-- Inserimento risultati Gran Premi

-- 2022-03-27, Arabia Saudita, Jeddah
CALL inserimentoRisultatoPilota(2022, '2022-03-27', 1, 1);
CALL inserimentoRisultatoPilota(2022, '2022-03-27', 3, 2);
CALL inserimentoRisultatoPilota(2022, '2022-03-27', 2, 3);
CALL inserimentoRisultatoPilota(2022, '2022-03-27', 5, 4);
CALL inserimentoRisultatoPilota(2022, '2022-03-27', 6, 5);
CALL inserimentoRisultatoPilota(2022, '2022-03-27', 8, 6);
CALL inserimentoRisultatoPilota(2022, '2022-03-27', 7, 7);
CALL inserimentoRisultatoPilota(2022, '2022-03-27', 11, 8);
CALL inserimentoRisultatoPilota(2022, '2022-03-27', 4, 9);
CALL inserimentoRisultatoPilota(2022, '2022-03-27', 9, 10);
CALL inserimentoRisultatoPilota(2022, '2022-03-27', 10, 11);
CALL inserimentoRisultatoPilota(2022, '2022-03-27', 21, 12);
CALL inserimentoRisultatoPilota(2022, '2022-03-27', 12, 13);
CALL inserimentoRisultatoPilota(2022, '2022-03-27', 22, 14);
CALL inserimentoRisultatoPilota(2022, '2022-03-27', 13, 15);
CALL inserimentoRisultatoPilota(2022, '2022-03-27', 14, 16);
CALL inserimentoRisultatoPilota(2022, '2022-03-27', 19, 17);
CALL inserimentoRisultatoPilota(2022, '2022-03-27', 17, 18);

CALL fineInserimentoRisultatiPiloti(2022, '2022-03-27');

-- Scambi Piloti e Motorizzazioni
CALL scambioPilota('Dario', 2022, '2022-04-10',10, 23);
CALL scambioMotorizzazione('Dario', 2022, '2022-04-10','Mercedes', 'Renault');
CALL scambioPilota('Ventu', 2022, '2022-04-10',12, 22);
CALL scambioPilota('Ventu', 2022, '2022-04-10',8, 14);

-- 2022-04-10, Australia, Melbourne,
CALL inserimentoRisultatoPilota(2022, '2022-04-10', 11, 1);
CALL inserimentoRisultatoPilota(2022, '2022-04-10', 4, 2);
CALL inserimentoRisultatoPilota(2022, '2022-04-10', 19, 3);
CALL inserimentoRisultatoPilota(2022, '2022-04-10', 1, 4);
CALL inserimentoRisultatoPilota(2022, '2022-04-10', 6, 5);
CALL inserimentoRisultatoPilota(2022, '2022-04-10', 5, 6);
CALL inserimentoRisultatoPilota(2022, '2022-04-10', 9, 7);
CALL inserimentoRisultatoPilota(2022, '2022-04-10', 2, 8);
CALL inserimentoRisultatoPilota(2022, '2022-04-10', 13, 9);
CALL inserimentoRisultatoPilota(2022, '2022-04-10', 21, 10);
CALL inserimentoRisultatoPilota(2022, '2022-04-10', 22, 11);
CALL inserimentoRisultatoPilota(2022, '2022-04-10', 8, 12);
CALL inserimentoRisultatoPilota(2022, '2022-04-10', 17, 13);
CALL inserimentoRisultatoPilota(2022, '2022-04-10', 23, 14);
CALL inserimentoRisultatoPilota(2022, '2022-04-10', 14, 15);
CALL inserimentoRisultatoPilota(2022, '2022-04-10', 20, 16);
CALL inserimentoRisultatoPilota(2022, '2022-04-10', 10, 17);

CALL fineInserimentoRisultatiPiloti(2022, '2022-04-10');

-- Scambi Piloti e Motorizzazioni
CALL scambioPilota('Dario', 2022, '2022-05-08',12, 11);
CALL scambioPilota('Dario', 2022, '2022-05-08',5, 17);

-- 2022-05-08, Florida, Miami Autodrome
CALL inserimentoRisultatoPilota(2022, '2022-05-08', 3, 1);
CALL inserimentoRisultatoPilota(2022, '2022-05-08', 11, 2);
CALL inserimentoRisultatoPilota(2022, '2022-05-08', 12, 3);
CALL inserimentoRisultatoPilota(2022, '2022-05-08', 4, 4);
CALL inserimentoRisultatoPilota(2022, '2022-05-08', 19, 5);
CALL inserimentoRisultatoPilota(2022, '2022-05-08', 1, 6);
CALL inserimentoRisultatoPilota(2022, '2022-05-08', 2, 7);
CALL inserimentoRisultatoPilota(2022, '2022-05-08', 9, 8);
CALL inserimentoRisultatoPilota(2022, '2022-05-08', 21, 9);
CALL inserimentoRisultatoPilota(2022, '2022-05-08', 8, 10);
CALL inserimentoRisultatoPilota(2022, '2022-05-08', 10, 11);
CALL inserimentoRisultatoPilota(2022, '2022-05-08', 14, 12);
CALL inserimentoRisultatoPilota(2022, '2022-05-08', 5, 13);
CALL inserimentoRisultatoPilota(2022, '2022-05-08', 20, 15);
CALL inserimentoRisultatoPilota(2022, '2022-05-08', 17, 16);

CALL fineInserimentoRisultatiPiloti(2022, '2022-05-08');

-- Scambi Piloti e Motorizzazioni
CALL scambioPilota('Dario', 2022, '2022-05-22',11, 14);
CALL scambioPilota('Dario', 2022, '2022-05-22',8, 21);

-- 2022-05-22, Spagna, Catalogna
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 3, 1);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 4, 2);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 19, 3);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 12, 4);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 1, 5);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 2, 6);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 9, 7);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 6, 8);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 10, 9);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 14, 10);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 7, 11);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 5, 12);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 13, 13);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 17, 14);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 8, 15);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 20, 16);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 23, 17);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 21, 18);

CALL fineInserimentoRisultatiPiloti(2022, '2022-05-22');

-- Scambi Piloti e Motorizzazioni
CALL scambioPilota('Dario', 2022, '2022-05-29',23, 20);
CALL scambioPilota('Dario', 2022, '2022-05-29',17, 22);

-- 2022-05-29, Principato di Monaco, Monte Carlo
CALL inserimentoRisultatoPilota(2022, '2022-05-29', 3, 1);
CALL inserimentoRisultatoPilota(2022, '2022-05-29', 2, 2);
CALL inserimentoRisultatoPilota(2022, '2022-05-29', 5, 3);
CALL inserimentoRisultatoPilota(2022, '2022-05-29', 4, 4);
CALL inserimentoRisultatoPilota(2022, '2022-05-29', 1, 5);
CALL inserimentoRisultatoPilota(2022, '2022-05-29', 8, 6);
CALL inserimentoRisultatoPilota(2022, '2022-05-29', 6, 7);
CALL inserimentoRisultatoPilota(2022, '2022-05-29', 7, 8);
CALL inserimentoRisultatoPilota(2022, '2022-05-29', 17, 9);
CALL inserimentoRisultatoPilota(2022, '2022-05-29', 13, 10);
CALL inserimentoRisultatoPilota(2022, '2022-05-29', 12, 11);
CALL inserimentoRisultatoPilota(2022, '2022-05-29', 15, 12);
CALL inserimentoRisultatoPilota(2022, '2022-05-29', 11, 13);
CALL inserimentoRisultatoPilota(2022, '2022-05-29', 14, 14);
CALL inserimentoRisultatoPilota(2022, '2022-05-29', 16, 15);
CALL inserimentoRisultatoPilota(2022, '2022-05-29', 21, 16);
CALL inserimentoRisultatoPilota(2022, '2022-05-29', 23, 17);

CALL fineInserimentoRisultatiPiloti(2022, '2022-05-29');

-- Scambi Piloti e Motorizzazioni
CALL scambioPilota('Dario', 2022, '2022-07-03',22, 23);

-- 2022-07-03, Gran Bretagna, Silverstone
CALL inserimentoRisultatoPilota(2022, '2022-07-03', 12, 1);
CALL inserimentoRisultatoPilota(2022, '2022-07-03', 4, 2);
CALL inserimentoRisultatoPilota(2022, '2022-07-03', 1, 3);
CALL inserimentoRisultatoPilota(2022, '2022-07-03', 11, 4);
CALL inserimentoRisultatoPilota(2022, '2022-07-03', 10, 5);
CALL inserimentoRisultatoPilota(2022, '2022-07-03', 6, 6);
CALL inserimentoRisultatoPilota(2022, '2022-07-03', 3, 7);
CALL inserimentoRisultatoPilota(2022, '2022-07-03', 17, 8);
CALL inserimentoRisultatoPilota(2022, '2022-07-03', 7, 9);
CALL inserimentoRisultatoPilota(2022, '2022-07-03', 23, 10);
CALL inserimentoRisultatoPilota(2022, '2022-07-03', 8, 11);
CALL inserimentoRisultatoPilota(2022, '2022-07-03', 20, 12);
CALL inserimentoRisultatoPilota(2022, '2022-07-03', 5, 13);
CALL inserimentoRisultatoPilota(2022, '2022-07-03', 14, 14);

CALL fineInserimentoRisultatiPiloti(2022, '2022-07-03');