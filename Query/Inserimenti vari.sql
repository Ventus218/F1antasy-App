INSERT INTO MOTORIZZAZIONE(Nome)
    VALUES
        ('Ferrari'),
        ('Mercedes'),
        ('Honda'),
        ('Renault'),
        ('Red Bull');

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

INSERT INTO GRAN_PREMIO(Stato, Nome, NumeroGiri, LunghezzaCircuito)
    VALUES
        ('Italia', 'Monza', 70, 3000),
        ('Italia', 'Mugello', 80, 2500),
        ('Azerbaijan', 'Baku', 68, 3100),
        ('Spagna', 'Catalogna', 69, 3000),
        ('Principato di Monaco', 'Monte Carlo', 70, 3000);

INSERT INTO PILOTA(Codice, Nome, Cognome)
VALUES
    (1, 'Lewis', 'Hamilton'),
    (2, 'Bottas', 'Valtteri'),
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

CALL registrazioneUtente('CiccioCarluz', 'Password');
CALL registrazioneUtente('Mario', 'Password');
CALL registrazioneUtente('Dario', 'Password');
CALL registrazioneUtente('Ventu', 'Password');
CALL registrazioneUtente('Franky', 'Password');
CALL registrazioneUtente('Roman', 'Password');

CALL creazioneClassificaPrivata('CiccioCarluz', 'UniboLeague');
CALL iscrizioneClassificaPrivata('Dario', 'UniboLeague');
CALL iscrizioneClassificaPrivata('Roman', 'UniboLeague');
CALL iscrizioneClassificaPrivata('Ventu', 'UniboLeague');

CALL creazioneClassificaPrivata('Ventu', 'Bolognesi');
CALL iscrizioneClassificaPrivata('Dario', 'Bolognesi');

INSERT INTO CAMPIONATO(Anno)
    VALUES
        (2022);
        /* (2021); */

INSERT INTO GRAN_PREMIO_PROGRAMMATO(AnnoCampionato, Data, Stato, Nome, Concluso)
     VALUES
       /* (2021, '2021-05-22', 'Spagna', 'Catalogna', 0),
        (2021, '2021-05-29', 'Principato di Monaco', 'Monte Carlo', 0),
        (2021, '2021-06-1', 'Azerbaijan', 'Baku', 0), */
        (2022, '2022-05-22', 'Spagna', 'Catalogna', 0),
        (2022, '2022-05-29', 'Principato di Monaco', 'Monte Carlo', 0),
        (2022, '2022-06-1', 'Azerbaijan', 'Baku', 0);

INSERT INTO SCUDERIA_PARTECIPANTE(AnnoCampionato, NomeScuderia, NomeMotorizzazione)
    VALUES
       /* (2021, 'Ferrari', 'Ferrari'),
        (2021, 'Mercedes', 'Mercedes'),
        (2021, 'Mclaren', 'Mercedes'),
        (2021, 'Red Bull', 'Honda'),
        (2021, 'Alpine', 'Renault'),
        (2021, 'AlphaTauri', 'Honda'),
        (2021, 'Aston Martin', 'Mercedes'),
        (2021, 'Williams', 'Mercedes'),
        (2021, 'Alfa Romeo', 'Ferrari'),
        (2021, 'Haas', 'Ferrari'),*/
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


INSERT INTO INGAGGIO_PILOTA(AnnoCampionato, NomeScuderia, CodicePilota)
    VALUES
        /*(2021, 'Mercedes', 1),
        (2021, 'Mercedes', 2),
        (2021, 'Red Bull', 3),
        (2021, 'Red Bull', 4),
        (2021, 'Mclaren', 5),
        (2021, 'Mclaren', 6),
        (2021, 'Aston Martin', 7),
        (2021, 'Aston Martin', 8),
        (2021, 'Alpine', 9),
        (2021, 'Alpine', 10),
        (2021, 'Ferrari', 11),
        (2021, 'Ferrari', 12),
        (2021, 'AlphaTauri', 13),
        (2021, 'AlphaTauri', 14),
        (2021, 'Alfa Romeo', 15),
        (2021, 'Alfa Romeo', 16),
        (2021, 'Haas', 17),
        (2021, 'Haas', 18),
        (2021, 'Williams', 19),
        (2021, 'Williams', 20),*/
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

INSERT INTO PILOTA_IN_GRAN_PREMIO(AnnoCampionato, DataGranPremio, CodicePilota, Prezzo, Posizione)
    VALUES
        /*(2021, '2021-05-22', 1,     40000000, NULL),
        (2021, '2021-05-22', 2,     40000000, NULL),
        (2021, '2021-05-22', 3,     33000000, NULL),
        (2021, '2021-05-22', 4,     32000000, NULL),
        (2021, '2021-05-22', 5,     30000000, NULL),
        (2021, '2021-05-22', 12,   25000000, NULL),
        (2021, '2021-05-22', 11,   23000000, NULL),
        (2021, '2021-05-22', 6,     21000000, NULL),
        (2021, '2021-05-22', 13,     19000000, NULL),
        (2021, '2021-05-22', 8,   17000000, NULL),
        (2021, '2021-05-22', 9,     15000000, NULL),
        (2021, '2021-05-22', 7,     14000000, NULL),
        (2021, '2021-05-22', 16,   13000000, NULL),
        (2021, '2021-05-22', 15,   12000000, NULL),
        (2021, '2021-05-22', 19,   11000000, NULL),
        (2021, '2021-05-22', 20,   11000000, NULL),
        (2021, '2021-05-22', 10,   20000000, NULL),
        (2021, '2021-05-22', 14,   10000000, NULL),
        (2021, '2021-05-22', 17,   10000000, NULL),
        (2021, '2021-05-22', 18,   10000000, NULL),*/
        (2022, '2022-05-22', 3,     40000000, NULL),
        (2022, '2022-05-22', 1,     36000000, NULL),
        (2022, '2022-05-22', 2,     33000000, NULL),
        (2022, '2022-05-22', 4,     32000000, NULL),
        (2022, '2022-05-22', 12,   30000000, NULL),
        (2022, '2022-05-22', 6,     27000000, NULL),
        (2022, '2022-05-22', 11,   25000000, NULL),
        (2022, '2022-05-22', 5,     23000000, NULL),
        (2022, '2022-05-22', 13,   21000000, NULL),
        (2022, '2022-05-22', 10,   19000000, NULL),
        (2022, '2022-05-22', 9,     17000000, NULL),
        (2022, '2022-05-22', 7,     15000000, NULL),
        (2022, '2022-05-22', 8,     13000000, NULL),
        (2022, '2022-05-22', 14,   11000000, NULL),
        (2022, '2022-05-22', 19,   9000000, NULL),
        (2022, '2022-05-22', 20,   7000000, NULL),
        (2022, '2022-05-22', 17,   6000000, NULL),
        (2022, '2022-05-22', 21,   10000000, NULL),
        (2022, '2022-05-22', 22,   10000000, NULL),
        (2022, '2022-05-22', 23,   10000000, NULL);

INSERT INTO MOTORIZZAZIONE_IN_GRAN_PREMIO(AnnoCampionato, DataGranPremio, NomeMotorizzazione, Prezzo, PunteggioOttenuto)
VALUES
    /*(2021, '2021-05-22', 'Mercedes',    15000000, NULL),
    (2021, '2021-05-22', 'Honda',         10000000, NULL),
    (2021, '2021-05-22', 'Renault',        9000000, NULL),
    (2021, '2021-05-22', 'Ferrari',         8000000, NULL),*/
    (2022, '2022-05-22', 'Mercedes',    15000000, NULL),
    (2022, '2022-05-22', 'Ferrari',         9000000, NULL),
    (2022, '2022-05-22', 'Renault',        8000000, NULL),
    (2022, '2022-05-22', 'Red Bull',        7000000, NULL);



# PRIMO GRAN PREMIO

CALL inizializzazioneSquadra('CiccioCarluz', 'Ferrari', 5, 6, 12, 19);
CALL inizializzazioneSquadra('Dario', 'Mercedes', 5, 8, 12, 10);
CALL inizializzazioneSquadra('Ventu', 'Ferrari', 5, 8, 12, 11);
CALL inizializzazioneSquadra('Mario', 'Mercedes', 22, 21, 3, 11);
CALL inizializzazioneSquadra('Franky', 'Red Bull', 22, 17, 3, 13);
CALL inizializzazioneSquadra('Roman', 'Red Bull', 17, 21, 3, 11);

CALL inserimentoRisultatoPilota(2022, '2022-05-22', 1, 1);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 3, 2);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 2, 3);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 5, 4);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 6, 5);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 8, 6);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 7, 7);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 11, 8);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 4, 9);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 9, 10);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 10, 11);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 21, 12);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 12, 13);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 22, 14);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 13, 15);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 14, 16);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 19, 17);
CALL inserimentoRisultatoPilota(2022, '2022-05-22', 17, 18);

CALL fineInserimentoRisultatiPiloti(2022, '2022-05-22');
