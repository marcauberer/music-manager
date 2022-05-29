# Dokumentation zum Programmentwurf

## Music Manager
**Name**: Auberer, Marc <br>
**Matrikelnummer**: 1577789 <br>
**Abgabedatum**: 29.05.2022

## Allgemeine Anmerkungen:

- _es darf nicht auf andere Kapitel als Leistungsnachweis verwiesen werden (z.B. in der Form &quot;XY wurde schon in
  Kapitel 2 behandelt, daher hier keine Ausführung&quot;)_
- _alles muss in UTF-8 codiert sein (Text und Code)_
- _sollten mündliche Aussagen den schriftlichen Aufgaben widersprechen, gelten die schriftlichen Aufgaben (ggf. an
  Anpassung der schriftlichen Aufgaben erinnern!)_
- _alles muss ins Repository (Code, Ausarbeitung und alles was damit zusammenhängt)_
- _die Beispiele sollten, wenn möglich vom aktuellen Stand genommen werden_
    - _finden sich dort keine entsprechenden Beispiele, dürfen auch ältere Commits unter Verweis auf den Commit
      verwendet werden_
    - _Ausnahme: beim Kapitel &quot;Refactoring&quot; darf von vorneherein aus allen Ständen frei gewählt werden (mit
      Verweis auf den entsprechenden Commit)_
- _falls verlangte Negativ-Beispiele nicht vorhanden sind, müssen entsprechend mehr Positiv-Beispiele gebracht werden_
    - _Achtung: werden im Code entsprechende Negativ-Beispiele gefunden, gibt es keine Punkte für die zusätzlichen
      Positiv-Beispiele_
    - _Beispiele_
        - &quot;_Nennen Sie jeweils eine Klasse, die das SRP einhält bzw. verletzt.&quot;_
            - _Antwort: Es gibt keine Klasse, die SRP verletzt, daher hier 2 Klassen, die SRP einhalten: [Klasse 1]
              , [Klasse 2]_
            - _Bewertung: falls im Code tatsächlich keine Klasse das SRP verletzt: volle Punktzahl ODER falls im Code
              mind. eine Klasse SRP verletzt: halbe Punktzahl_
- _verlangte Positiv-Beispiele müssen gebracht werden_
- _Code-Beispiel = Code in das Dokument kopieren_

## Kapitel 1: **Einführung**

### Übersicht über die Applikation

Bei der implementierten Applikation handelt es sich um einen Manager für Musik.
Mithilfe der Anwendung lassen sich Soundtracks mit den zugehörigen Interpreten
und einigen Metadaten anzeigen, erstellen, bearbeiten und wieder löschen.
Zusätzlich bietet die Anwendung einige Hilfsfunktionen wie eine Suche der Titel
auf YouTube an.

### Wie startet man die Applikation?

#### Requirements
- Java 11 oder höher
- Maven

#### Projekt herunterladen

```shell
git clone https://github.com/marcauberer/music-manager.git
```

#### Projekt kompilieren

```shell
mvn package 
```

#### Demo-Daten laden (Optional)

Es existiert ein vorbereiteter Datenbestand, mit dem sich die Applikation testen lässt.
Um diesen zu laden, kann das Skript `reload-demo-data.bat` genutzt werden.

Die Demo-Daten beinhalten auch zwei Nutzeraccounts. Die Zugangsdaten lauten:

1. Username: marc, Passwort: 12345
2. Username: testuser, Password: testpw

#### Ausführbare JAR-Datei finden

```shell
cd 3-mm-plugin
```

Die ausführbare JAR-Datei hat standardmäßig den Namen `plugin-<version>-jar-with-dependencies.jar`.

### Wie testet man die Applikation?

```shell
mvn test
```

## Kapitel 2: Clean Architecture

### Was ist Clean Architecture?

Beim Ansatz der Clean Architecture geht es im Kern darum, ein Softwareprojekt
in mehrere Schichten zu unterteilen und so beispielsweise Applikationslogik von
Domain-Klassen zu trennen. Dies kann mit den Schichten einer Zwiebel verglichen
werden und ermöglicht es, schnell und ohne viel Aufwand Abhängigkeiten wie zum
Beispiel die Datenbank auszutauschen. Zusätzlich soll jede Komponente nur über
die für sie relevanten Sachverhalte Bescheid wissen und es soll eine
Tätigkeitskapselung entstehen.

### Analyse der Dependency Rule

### Analyse der Schichten

In dieser Applikation halten prinzipiell alle Klassen die Dependency-Rule hinsichtlich dem "Fluss" der Abhängigkeiten ein.
Klassen innerer Schichten besitzen <u>keine</u> Abhängigkeiten nach Außen. Dies wird u.a. durch den Aufbau des Maven Projektes
selbst gewährleistet, da nur die äußeren Schichten/Module weiter innen liegende Module als Abhängigkeit definiert haben.
Beispielsweise werden Repositories in der Domain-Schicht als Interface deklariert und erst außen konkret implementiert.

#### Positiv-Beispiel 1: Songs

Ein Positiv-Beispiel ist die Dependency Rule ist die Schichtenunterteilung bei Songs. Hier kommen die Schichten Domain,
Application und Plugin zum Einsatz.

![Layers positive example 1](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/marcauberer/music-manager/main/media/layers-positive1.plantuml&fmt=svg)

#### Positiv-Beispiel 2: Artists

Selbiges gilt für die Schichtenarchitektur bei Artists, wobei hier kein Builder notwendig ist.

![Layers positive example 1](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/marcauberer/music-manager/main/media/layers-positive2.plantuml&fmt=svg)

## Kapitel 3: SOLID

### Analyse Single-Responsibility-Principle (SRP)

#### Positiv-Beispiel
*ToDo*

#### Negativ-Beispiel
*ToDo*

### Analyse Open-Closed-Principle (OCP)

#### Positiv-Beispiel
*ToDo*

#### Negativ-Beispiel
*ToDo*

### Analyse Liskov-Substitution- (LSP), Interface-Segreggation- (ISP), Dependency-Inversion-Principle (DIP)

#### Positiv-Beispiel
*ToDo*

#### Negativ-Beispiel
*ToDo*

## Kapitel 4: Weitere Prinzipien

### Analyse GRASP: Geringe Kopplung

#### Positiv-Beispiel

Ein positives Beispiel für Log Coupling ist die Klasse `SongBuilder`. Diese Klasse wird verwendet um mit dem
Builder-Pattern Song-Objekte zu konstruieren. Sie hat mit der Song-Klasse nur eine einzige Abhängigkeit. Demnach ist das
Testen unproblematisch.

![Low coupling positive example](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/marcauberer/music-manager/main/media/low-coupling-positive.plantuml&fmt=svg)

#### Negativ-Beispiel

Ein Negativ-Beispiel für Low Coupling ist die Klasse `SongRepositoryImpl`. Hier laufen gewissermaßen alle Drähte zusammen,
da die Klasse für die Auflösung von Relationen zwischen Songs und Genres, Songs und Artisten, sowie Songs und BarTypes
zuständig ist. <br>
Das Testen dieser Klasse ist vor allem durch die vielen Mock-Objekte eine Herausforderung.

![Low coupling negative example](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/marcauberer/music-manager/main/media/low-coupling-negative.plantuml&fmt=svg)

### Analyse GRASP: Hohe Kohäsion

#### Positiv-Beispiel

Die Klasse `YTLinkGeneratorService` ist ein Beispiel für hohe Kohäsion. Er vereint sämtliche Funktionalität für ein
spezielles Feature, nämlich das Erstellen von YouTube-Links aus einem gegebenen Song-Objekt. Zugegebenermaßen hat der
Service nicht sonderlich viele Methoden, jedoch sind nicht mehr nötig um diesen Zweck zu erfüllen.

![High cohesion positive example](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/marcauberer/music-manager/main/media/high-cohesion-positive.plantuml&fmt=svg)

### Don't Repeat Yourself (DRY)
Um die Oberflächenkomponenten auf der UI zu platzieren, nutze ich den `GridBagLayoutManager`. Um diesen zu nutzen, bedarf
es `LayoutConstraints`. Da alle JFrame-Klassen diese Funktionalität benötigen, reicht es nicht eine Methoden-Extraktion
innerhalb einer JFrame-Klasse zu machen. Stattdessen habe ich mich dazu entschieden eine neue Klasse `UIHelper` zu erstellen,
der eine statische Methode `placeUIComp` anbietet um eine Komponente zu platzieren. Dieser übergibt man das Root Panel,
die zu platzierende Komponente sowie die Koordinaten und Dimensionen der Komponente auf dem Root Panel.

**Vorher:**
```java
JLabel labelPassword = new JLabel("Password:");
constraints.gridx = 0;
constraints.gridy = 2;
rootPanel.add(labelPassword, constraints);
textFieldPassword.addActionListener(e -> attemptToLogin());
constraints.gridx = 1;
rootPanel.add(textFieldPassword, constraints);
```

Dieser Code wiederholt sich für alle UI-Komponenten.

**Nachher (Commit ID: fae1236a349dcdeea7c5c2dc1532aa7478ce26f3):**

```java
public class UIHelper {

    // Private constructor to hide the public one (Good according to SonarCloud)
    private UIHelper() {}

    public static void placeUIComp(JPanel rootPanel, JComponent component, int gridX, int gridY,
                                   int gridWidth, int gridHeight, int gridWeightX) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = gridX;
        constraints.gridy = gridY;
        constraints.gridwidth = gridWidth;
        constraints.gridheight = gridHeight;
        constraints.weightx = gridWeightX;
        rootPanel.add(component, constraints);
    }
}

```

```java
JLabel labelUsername = new JLabel("Username:");
UIHelper.placeUIComp(rootPanel, labelUsername, 0, 1, 1, 1, 1);
```

Dieser Code wiederholt sich für alle UI-Komponenten.

## Kapitel 5: Unit tests

### 10 Unit tests

Die folgende Tabelle umfasst eine Auswahl von 10 Unit-Tests für die MusicManager-Applikation.
Insgesamt wurden 65 Tests geschrieben.

| Unit Test                         | Beschreibung                                                                                                                                                                                                                        |
|-----------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ArtistServiceTest#getAllArtists() | Es wird für den ArtistService getestet, ob dieser die Anfrage korrekt an die darunterliegende ArtistRepository weitergibt, sowie die Korrekte Liste mit Ergebnissen zurückliefert. Für die ArtistRepository wurde ein Mock genutzt. |
| ArtistServiceTest#createArtist()  | Es wird für den ArtistService getestet, ob ein Artist korrekt erstellt werden kann. Auch wird überprüft, dass der Observer für die Artist-Liste korrekt aufgerufen wird.                                                            |
| ArtistServiceTest#createArtist()  | Es wird für den ArtistService getestet, ob ein Artist korrekt erstellt werden kann. Auch wird überprüft, dass der Observer für die Artist-Liste korrekt aufgerufen wird.                                                            |
| CSVHelper#read1()                 | Es wird für den CSVHelper getestet, ob dieser in der Lage ist eine vorbereitete CSV-Datei zu laden und zu parsen. Das Ergebnis wird mit einem vorgegebenen, korrekten Ergebnis verglichen.                                          |
| CSVHelper#read2()                 | Es wird für den CSVHelper getestet, ob eine Leere CSV-Datei einzulesen, das erwartete leere Ergebnis liefert. Hier wird versucht auf potentiell unerwartetes Verhalten zu testen.                                                   |
| CSVHelper#write1()                | Es wird für den CSVHelper getestet, ob dieser in der Lage ist, eine "Tabelle" korrekt in eine CSV-Datei zu schreiben. Nach Ausführung des Tests wird die Datei eingelesen und geprüft ob der Inhalt korrekt ist.                    |
| GenreTest#getFieldContents()      | Es wird für das Genre getestet, ob der Aufruf an `getFieldContents()` ein String-Array aus den zuvor im Konstruktor überreichten Werte zurückgibt.                                                                                  |
| GenreTest#getCSVHeader()          | Es wird für das Genre getestet, ob der Aufruf an `getCSVHeader()` den korrekten Wert zurückgibt. Hierbei handelt es sich um eine Konstante. Versehentliche Änderungen fallen somit besser auf.                                      |
| SongRepositoryTest#save()         | Es wird für den Song getestet, ob die Methode `save()` das richtige tut. Hierzu werden Aufrufe an Mocks verifiziert und geprüft ob die Länge der Song-Liste auf 4 gewachsen ist.                                                    |
| SongRepositoryTest#delete()       | Es wird für den Song getestet, ob die Methode `delete()` das richtige tut. Hierzu werden Aufrufe an Mocks verifiziert und geprüft ob die Länge der Song-Liste auf 2 geschrumpft ist.                                                |

### ATRIP - Automatic
Automatic wurde über die Java-Testing-Bibliothek JUnit realisiert, die automatisch alle Testklassen sucht und alle darin
befindlichen Tests ausführt.
Über einen Aufruf von `$ mvn test` können die Tests automatisch ausgeführt werden und der Nutzer wird entsprechend
benachrichtigt, sofern Tests fehlgeschlagen sind. Es wurde zudem ein Workflow für GitHub Actions geschrieben, der für
jeden Commit läuft, der an den relevanten Projekt-Dateien Änderungen vorgenommen hat. So ist gewährleistet, dass viele
eventuell unerwünschte Nebeneffekte bei jeder Änderung automatisch gefunden werden.

Zusätzlich zu automatischen Unit-Tests werden neue Code-Smells, Bugs oder Security Probleme vollautomatisch durch
SonarCloud überwacht. Auch warnt das Tool davor, wenn neuer Code ungetestet ist oder andere Probleme aufweist.

### ATRIP - Thorough
Wenn Fehler vermieden werden sollen, ist es zudem wichtig alle Verzweigungen im zu testenden Code zu testen. Selbst
unerwartete Fehler können simuliert werden, um das Verhalten der Applikation im Fehlerfall zu testen.

#### Positiv-Beispiel
Im nachfolgenden Code-Ausschnitt können selbst definierte Exceptions auftreten. Die Tests für diese Methode überprüfen
sowohl den Erfolgsfall, als auch beide Fehlerfälle.

```java
public class UserServiceImpl implements UserService {

    // ...

    @Override
    public User login(String username, String password) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        // Check if the user was found
        User user = optionalUser.orElseThrow(() ->
                new UserNotFoundException(String.format("The user %s was not found.", username))
        );
        // Check if the password is correct
        if (!password.equals(user.getPassword())) {
            String errorMessage = String.format("The user %s was found, but you entered a wrong password.", username);
            throw new WrongPasswordException(errorMessage);
        }
        return user;
    }

    // ...
}
```

```java
@Test
void loginUnknownUsername() {
    // Test data
    UserService userService = new UserServiceImpl(userRepository);

    // Execute
    String actualErrorMessage = assertThrowsExactly(UserNotFoundException.class, () -> {
        userService.login("typo", "12345");
    }).getMessage();

    // Assert
    assertEquals("The user typo was not found.", actualErrorMessage);
}

@Test
void loginWrongPassword() {
    // Test data
    UserService userService = new UserServiceImpl(userRepository);

    // Execute
    String actualErrorMessage = assertThrowsExactly(WrongPasswordException.class, () -> {
        userService.login("dhbw", "dhbwpss");
    }).getMessage();

    // Assert
    assertEquals("The user dhbw was found, but you entered a wrong password.", actualErrorMessage);
}
```

#### Negativ-Beispiel

Im nachfolgenden Code-Ausschnitt können `IOExceptions` auftreten. Diese werden zwar gefangen, aber das Verhalten wird
aktuell durch keinen Unit-Test abgetestet. Es muss gewährleistet sein, dass der Nutzer von dem Problem erfährt und
Maßnahmen einleiten kann.

```java
public class CSVHelper {

    // ...

    public Optional<List<String[]>> read() {
        try {
            // Return empty list if file does not exist
            if (!Files.exists(filePath)) {
                return Optional.of(Collections.emptyList());
            }

            // Read lines of file
            List<String> rows = Files.readAllLines(filePath);

            // Loop through all rows
            List<String[]> result = new ArrayList<>();
            for (int i = 1; i < rows.size(); ++i) {
                // Split row by delimiter to get cells
                String[] cells = rows.get(i).split(delimiter);
                // Trim all cells
                for (int j = 0; j < cells.length; j++) {
                    cells[j] = cells[j].trim();
                }
                // Add to result
                result.add(cells);
            }

            return Optional.of(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // ...
}
```

```java
@Test
void read1() {
    // Test data
    CSVHelper csvHelper = new CSVHelper(TEST_FILE_1, ";");

    // Execute
    Optional<List<String[]>> actualResult = csvHelper.read();

    // Assert
    assertTrue(actualResult.isPresent());
    assertEquals(4, actualResult.get().size());
    List<String[]> expectedResult = List.of(
            new String[]{"0", "Cheese Burger", "1200", "9.5"},
            new String[]{"1", "Spaghetti", "450", "8.2"},
            new String[]{"2", "Schnitzel", "950", "8.9"},
            new String[]{"3", "Caesar Salad", "340", "7.0"}
    );
    assertArrayEquals(expectedResult.get(0), actualResult.get().get(0));
    assertArrayEquals(expectedResult.get(1), actualResult.get().get(1));
    assertArrayEquals(expectedResult.get(2), actualResult.get().get(2));
    assertArrayEquals(expectedResult.get(3), actualResult.get().get(3));
}
```

### ATRIP - Professional

#### Positiv-Beispiel
```java
class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @BeforeEach
    void prepareTests() {
        MockitoAnnotations.openMocks(this);

        // GenreRepository findGenreById
        Genre mockGenre = new Genre(0, "Doom Metal");
        when(genreRepository.findGenreById(0)).thenReturn(Optional.of(mockGenre));

        // GenreRepository findAllGenres
        List<Genre> mockGenres = List.of(
                mockGenre,
                new Genre(1, "Power Metal"),
                new Genre(2, "Death Metal")
        );
        when(genreRepository.findAllGenres()).thenReturn(mockGenres);
    }

    @Test
    @DisplayName("Retrieve all genres - success")
    void getAllGenres() {
        // Test data
        AtomicInteger changeCounter = new AtomicInteger();
        GenreService genreService = new GenreServiceImpl(genreRepository, genreList -> changeCounter.getAndIncrement());

        // Execute
        List<Genre> actualResult = genreService.getAllGenres();

        // Assert
        List<Genre> expectedResult = List.of(
                new Genre(0, "Doom Metal"),
                new Genre(1, "Power Metal"),
                new Genre(2, "Death Metal")
        );
        assertEquals(expectedResult, actualResult);
        assertEquals(1, changeCounter.get());
    }

    @Test
    @DisplayName("Create new genre - success")
    void createGenre() {
        // Test data
        AtomicInteger changeCounter = new AtomicInteger();
        GenreService barTypeService = new GenreServiceImpl(genreRepository, genreList -> changeCounter.getAndIncrement());

        // Execute
        Genre newGenre = new Genre(3, "Melodic Death Metal");
        barTypeService.createGenre(newGenre);

        // Assert
        assertEquals(2, changeCounter.get());
    }
}
```

Bei obiger Testklasse ist vor allem positiv hervorzuheben, dass die Test-Methoden den zu testenden Methoden-Namen entsprechen.
Über die Annotation `DisplayName` wird als Ausgabe immer z.B. `Create new genre - success` angezeigt, was dem Entwickler
den Zweck des Tests vermittelt. Zudem ist der Testcode relativ übersichtlich und lesbar. Sämtliche Initialisierungslogik,
sowie die Erstellung der Mocks findet in der Method `prepareTests` statt, sodass die Testfälle entlastet werden und
Code-Duplikation vermieden wird.

#### Negativ-Beispiel
```java
class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @Test
    void test1() {
        MockitoAnnotations.openMocks(this);

        // GenreRepository findGenreById
        Genre mockGenre = new Genre(0, "Doom Metal");
        when(genreRepository.findGenreById(0)).thenReturn(Optional.of(mockGenre));

        // GenreRepository findAllGenres
        List<Genre> mockGenres = List.of(
                mockGenre,
                new Genre(1, "Power Metal"),
                new Genre(2, "Death Metal")
        );
        when(genreRepository.findAllGenres()).thenReturn(mockGenres);
        
        // Test data
        AtomicInteger changeCounter = new AtomicInteger();
        GenreService genreService = new GenreServiceImpl(genreRepository, genreList -> changeCounter.getAndIncrement());

        // Execute
        List<Genre> actualResult = genreService.getAllGenres();

        // Assert
        List<Genre> expectedResult = List.of(
                new Genre(0, "Doom Metal"),
                new Genre(1, "Power Metal"),
                new Genre(2, "Death Metal")
        );
        assertEquals(expectedResult, actualResult);
        assertEquals(1, changeCounter.get());
    }

    @Test
    void test2() {
        MockitoAnnotations.openMocks(this);

        // GenreRepository findGenreById
        Genre mockGenre = new Genre(0, "Doom Metal");
        when(genreRepository.findGenreById(0)).thenReturn(Optional.of(mockGenre));

        // GenreRepository findAllGenres
        List<Genre> mockGenres = List.of(
                mockGenre,
                new Genre(1, "Power Metal"),
                new Genre(2, "Death Metal")
        );
        when(genreRepository.findAllGenres()).thenReturn(mockGenres);
        
        // Test data
        AtomicInteger changeCounter = new AtomicInteger();
        GenreService barTypeService = new GenreServiceImpl(genreRepository, genreList -> changeCounter.getAndIncrement());

        // Execute
        Genre newGenre = new Genre(3, "Melodic Death Metal");
        barTypeService.createGenre(newGenre);

        // Assert
        assertEquals(2, changeCounter.get());
    }
}
```

Die Kriterien des Positiv-Beispiels werden hier nicht erfüllt.

### Code Coverage
Die Gesamt-Coverage des Projektes beläuft sich auch 52 %. Somit ist das selbst gestellte Ziel von mindestens 50 % erreicht.
Hierbei wurden hauptsächlich die Kern-Komponenten aus den unteren drei Schichten (Util, Domain, Application) intensiv
getestet. Die Plugin-Schicht enthält UI-Code, der von Tests vorerst bewusst ausgenommen ist, da hier Unit-Tests nur bedingt
sinnvoll sind und eigentlich Integrationstests angebracht wären. Die Implementierungen der Repositories, die ebenfalls
in der Plugin-Schicht angesiedelt sind, wurden jedoch gewissenhaft und nahezu vollständig getestet.

| Modul / Layer | Coverage | Getestete Komponenten                                                                                                    |
|---------------|----------|--------------------------------------------------------------------------------------------------------------------------|
| Util          | ca. 83 % | CSVHelper                                                                                                                |
| Domain        | ca. 70 % | Jede Domain-Klasse besitzt zwei Methoden für die Persistenz. Diese wurden abgetestet. Zusätzlich einige andere Methoden. |
| Application   | ca. 89 % | SongBuilder, alle Observer, alle Services, mehrere Exceptions                                                            |
| Plugin / UI   | ca. 39 % | Repository-Implementationen                                                                                              |

### Fakes und Mocks

Mocks wurden beim Testen der Komponenten exzessiv benutzt. Instanziierungen von Klassen aus der Schicht unter der zu
testenden Schicht wurden mit Mocks ersetzt. Die Methodenaufrufe auf solchen Mock-Objekten wurden mittels Mockito umgeleitet
und für bestimmte Eingabewerte, feste Ausgabewerte definiert.

```java
class ArtistRepositoryTest {

    @Mock
    private CSVHelper csvHelper;

    @BeforeEach
    void prepareTest() {
        MockitoAnnotations.openMocks(this);

        // CSVHelper read()
        List<String[]> mockedReadResult = List.of(
                new String[]{"0", "Arch Enemy", "", "0"},
                new String[]{"1", "Amorphis", "", "0"},
                new String[]{"2", "Amaranthe", "", "0"}
        );
        when(csvHelper.read()).thenReturn(Optional.of(mockedReadResult));

        // CSVHelper write()
        doNothing().when(csvHelper).write(any(String[].class), any());
    }

    @Test
    void findArtistById() {
        // Test data
        ArtistRepository artistRepository = new ArtistRepositoryImpl(csvHelper);

        // Execute
        Optional<Artist> optionalArtist = artistRepository.findArtistById(2);

        // Verify
        verify(csvHelper, times(1)).read();

        // Assert
        assertTrue(optionalArtist.isPresent());
        Artist artist = optionalArtist.get();
        assertEquals("Amaranthe", artist.getFirstName());
    }
    
    // ...
}
```

In diesem Beispiel wurde der CSVHelper mit einem Mock ersetzt, sodass während des Tests weder vom Speichermedium gelesen,
noch geschrieben wird.

#### Mock 1
![Mock 1](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/marcauberer/music-manager/main/media/mock1.plantuml&fmt=svg)

#### Mock 2
![Mock 2](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/marcauberer/music-manager/main/media/mock2.plantuml&fmt=svg)

## Kapitel 6: Domain Driven Design

![ER-Diagram](./media/er-diagram.png)

### Ubiquitous Language
Alle in der Domäne üblichen Begriffe wurden ins Domänenmodell im Projekt übernommen. Folgende Tabelle zeigt die
Begrifflichkeiten im Detail:

| Begriff | Beschreibung                                                                                                                                                                                   | Begründung                                                                                                                                                                                                                                             |
|---------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Song    | Ein Song stellt ein Musikstück mit Titel, Bpm-Zahl sowie Relationen zu Artist (n:m), Genre (n:1) und BarType (n:1). Songs können jeweils nur einem Nutzer zugeordnet sein.                     | Songs sind der zentrale Bestandteil der Anwendung. Diese sollen gesammelt werden und weitere Metadaten gespeichert werden (Genre, BarType, Bpm, Artists).                                                                                              |
| Artist  | Ein Artist/Interpret ist ein Künstler, der an einem Song mitgewirkt hat. Es können mehrere Künstler einem Song zugewiesen sein, sowie mehrere Songs einem Künstler.                            | Für gewöhnlich ist nicht nur der Songtitel interessant, sondern auch der Artist. Es kann durchaus vorkommen, dass mehrere Songs mit gleichem Namen abgespeichert sind. Zudem könnte ein Feature eingebaut werden, um alle Songs pro Artist anzuzeigen. |
| Genre   | Ein Genre beschreibt eine Musikrichtung mit einem Namen. Ein Song hat kein bzw. genau ein Genre zugeordnet. Genre können jedoch für mehrere Songs wiederverwendet werden.                      | s. Artist                                                                                                                                                                                                                                              |
| BarType | Ein BarType ist eine Taktart wie beispielsweise beim 3/4-Takt. BarTypes beinhalten zwei Zahlen: Den BeatCount (3) und die BeatValue (4). Auch BarTypes können mehreren Song zugeordnet werden. | s. Artist                                                                                                                                                                                                                                              |
| User    | Ein User repräsentiert einen Benutzer des MusicManager-Systems. Die einem User zugeordneten Songs sind voneinander getrennt, alle anderen Resourcen werden jedoch geteilt.                     | Ohne eine Unterstützung von mehreren, (zumindest teilweise) voneinander getrennten Nutzern, kann ein Mehrbenutzerbetrieb nicht funktionieren.                                                                                                          |

### Entities
Ein Beispiel für eine Entität wäre ein Song. Dieser wird eindeutig über eine ID identifiziert, was automatisch schon
über das eingebaute CSV-Persistenz-Framework gewährleistet wird. Auch ein Lebenszyklus eines Songs ist gegeben.

![Song entity](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/marcauberer/music-manager/main/media/song-entity.plantuml&fmt=svg)

### Value Objects
Da die Applikation MusicManager auf einem relationales Datenbankmodell aufgebaut ist, ist jedes Objekt innerhalb der
Domäne (Artist, BarType, Genre, RelSongArtist, Song, User) ein Entity. Jede dieser Klassen wird in der Persistenzschicht
(CSVHelper + RepositoryImpl-Klassen) mittels einer ID identifiziert, sodass diese automatisch Entities darstellen. In
der Theorie wäre es zwar möglich Datensätze auch anders zu identifizieren, dies wurde jedoch nicht so umgesetzt, sodass
es in der Domäne nur Entities und keine Value Objects gibt.

### Repositories
MusicManager nutzt Repositories mittels Interfaces, um die Herkunft der Daten vor den darüberliegenden Schichten zu
verbergen. So könnten mehrere Implementierungen dieser Interfaces existieren, um mehrere Datenquellen anzusprechen.
Mitgeliefert wird eine Implementierung aller Repositories (Plugin-Schicht) die die Daten mittels CSV-Dateien lesen und
schreiben.

Es gibt beispielsweise eine `SongRepository`. Diese ist für das Vorhalten der gespeicherten Songs zuständig. Zusätzlich
lassen sich über die `SongRepository` alle Songs abfragen, Songs pro Nutzer abfragen, Songs filtern, neue Songs erstellen,
bestehende aktualisieren oder löschen.

Hier die SongRepository als Klassendiagramm:

![Song Repository](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/marcauberer/music-manager/main/media/song-repository.plantuml&fmt=svg)

### Aggregates
Songs werden zwar unabhängig von Genre, BarType und Artisten gespeichert, es soll zur Laufzeit jedoch trotzdem z.B. von
einem Song auf dessen Artisten zugegriffen werden können. Hierfür beinhaltet ein Song immer auch eine Liste an Artisten,
ein/kein Genre, einen/keinen BarType. Demnach ist ein Song-Objekt auch ein Aggregate.

## Kapitel 7: Refactoring

### Code Smells

#### Code Smell 1: Long method
Die Methode `setupUI` der Kasse `MusicManagerUI` war anfangs relativ klein, da die Oberfläche nur wenige Komponenten
beinhaltete. Mit dem Zuwachs an Funktionalität und demnach einem Zuwachs an Layout-Komponenten überstieg die Code-Dichte
innerhalb dieser Methode die Grenze der guten Lesbarkeit. <br>
Um diesen Code Smell zu beheben, wurde die Methode in drei kleinere zerteilt. Eine Oberfunktion die zwei weitere
Unterfunktionen aufruft. Als Call-Argument ist das Root-Panel notwendig, da dieses in der Oberfunktion erstellt wird und
kein Klassen-Attribut ist.

**Vorher:** <br>
```java
private void setupUI() {
    // Setup window
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(0, 0, 900, 520);
    setTitle("Music Manager - Home");
    setResizable(false);
    setLocationRelativeTo(null);
    GridBagConstraints constraints = new GridBagConstraints();

    // Set root layout
    JPanel rootPanel = new JPanel(new GridBagLayout());
    rootPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    setContentPane(rootPanel);

    // Song list
    String[] columnNames = {"Song Title", "Artist", "Genre", "Bpm", "Bar type"};
    songTable = new JTable(new String[][]{}, columnNames);
    songTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    songTable.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent mouseEvent) {
            JTable table = (JTable) mouseEvent.getSource();
            if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                editSong();
            }
        }
    });
    JScrollPane songScrollPane = new JScrollPane(songTable);
    UIHelper.placeUIComp(rootPanel, songScrollPane, 0, 0, 5, 4, 5);

    // New button
    JButton buttonNew = new JButton("New Song");
    buttonNew.addActionListener(e -> newSong());
    UIHelper.placeUIComp(rootPanel, buttonNew, 0, 4, 1, 1, 1);

    // Edit button
    JButton buttonEdit = new JButton("Edit Song");
    buttonEdit.setEnabled(false);
    buttonEdit.addActionListener(e -> editSong());
    UIHelper.placeUIComp(rootPanel, buttonEdit, 1, 4, 1, 1, 1);

    // Delete button
    JButton buttonDelete = new JButton("Delete Song");
    buttonDelete.setEnabled(false);
    buttonDelete.addActionListener(e -> deleteSong());
    UIHelper.placeUIComp(rootPanel, buttonDelete, 2, 4, 1, 1, 1);

    // Play button
    JButton buttonPlay = new JButton("Play Song");
    buttonPlay.setEnabled(false);
    buttonPlay.addActionListener(e -> playSong());
    UIHelper.placeUIComp(rootPanel, buttonPlay, 3, 4, 1, 1, 1);

    // Logout button
    JButton buttonLogout = new JButton("LogOut");
    buttonLogout.addActionListener(e -> triggerLogout());
    UIHelper.placeUIComp(rootPanel, buttonLogout, 4, 4, 1, 1, 1);

    // Add selection listener to song table
    songTable.getSelectionModel().addListSelectionListener(e -> {
        int selectedIndex = songTable.getSelectedRow();
        boolean validIndex = selectedIndex != -1;
        selectedSong = validIndex ? Optional.of(songs.get(selectedIndex)) : Optional.empty();
        buttonEdit.setEnabled(validIndex);
        buttonDelete.setEnabled(validIndex);
        buttonPlay.setEnabled(validIndex);
    });
}
```

**Nachher (Commit-ID: 14951f078c470f3989602c313eea1f48816b43e2):** <br>
```java
private void setupUI() {
    // Setup window
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(0, 0, 900, 520);
    setTitle("Music Manager - Home");
    setResizable(false);
    setLocationRelativeTo(null);

    // Set root layout
    JPanel rootPanel = new JPanel(new GridBagLayout());
    rootPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    setContentPane(rootPanel);

    setupSongTable(rootPanel);

    setupButtons(rootPanel);

    // Add selection listener to song table
    songTable.getSelectionModel().addListSelectionListener(e -> {
        int selectedIndex = songTable.getSelectedRow();
        boolean validIndex = selectedIndex != -1;
        selectedSong = validIndex ? Optional.of(songs.get(selectedIndex)) : Optional.empty();
        buttonEdit.setEnabled(validIndex);
        buttonDelete.setEnabled(validIndex);
        buttonPlay.setEnabled(validIndex);
    });
}

private void setupSongTable(JPanel rootPanel) {
    // Song list
    String[] columnNames = {"Song Title", "Artist", "Genre", "Bpm", "Bar type"};
    songTable = new JTable(new String[][]{}, columnNames);
    songTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    songTable.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent mouseEvent) {
            JTable table = (JTable) mouseEvent.getSource();
            if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                editSong();
            }
        }
    });
    JScrollPane songScrollPane = new JScrollPane(songTable);
    UIHelper.placeUIComp(rootPanel, songScrollPane, 0, 0, 5, 4, 5);
}

private void setupButtons(JPanel rootPanel) {
    // New button
    JButton buttonNew = new JButton("New Song");
    buttonNew.addActionListener(e -> newSong());
    UIHelper.placeUIComp(rootPanel, buttonNew, 0, 4, 1, 1, 1);

    // Edit button
    buttonEdit = new JButton("Edit Song");
    buttonEdit.setEnabled(false);
    buttonEdit.addActionListener(e -> editSong());
    UIHelper.placeUIComp(rootPanel, buttonEdit, 1, 4, 1, 1, 1);

    // Delete button
    buttonDelete = new JButton("Delete Song");
    buttonDelete.setEnabled(false);
    buttonDelete.addActionListener(e -> deleteSong());
    UIHelper.placeUIComp(rootPanel, buttonDelete, 2, 4, 1, 1, 1);

    // Play button
    buttonPlay = new JButton("Play Song");
    buttonPlay.setEnabled(false);
    buttonPlay.addActionListener(e -> playSong());
    UIHelper.placeUIComp(rootPanel, buttonPlay, 3, 4, 1, 1, 1);

    // Logout button
    JButton buttonLogout = new JButton("LogOut");
    buttonLogout.addActionListener(e -> triggerLogout());
    UIHelper.placeUIComp(rootPanel, buttonLogout, 4, 4, 1, 1, 1);
}
```

#### Code Smell 2: Duplicate code
Um die Oberflächenkomponenten auf der UI zu platzieren, nutze ich den `GridBagLayoutManager`. Um diesen zu nutzen, bedarf
es `LayoutConstraints`. Da alle JFrame-Klassen diese Funktionalität benötigen, reicht es nicht eine Methoden-Extraktion
innerhalb einer JFrame-Klasse zu machen. Stattdessen habe ich mich dazu entschieden eine neue Klasse `UIHelper` zu erstellen,
der eine statische Methode `placeUIComp` anbietet um eine Komponente zu platzieren. Dieser übergibt man das Root Panel,
die zu platzierende Komponente sowie die Koordinaten und Dimensionen der Komponente auf dem Root Panel.

**Vorher:**
```java
JLabel labelPassword = new JLabel("Password:");
constraints.gridx = 0;
constraints.gridy = 2;
rootPanel.add(labelPassword, constraints);
textFieldPassword.addActionListener(e -> attemptToLogin());
constraints.gridx = 1;
rootPanel.add(textFieldPassword, constraints);
```

Dieser Code wiederholt sich für alle UI-Komponenten.

**Nachher (Commit ID: fae1236a349dcdeea7c5c2dc1532aa7478ce26f3):**

```java
public class UIHelper {

    // Private constructor to hide the public one (Good according to SonarCloud)
    private UIHelper() {}

    public static void placeUIComp(JPanel rootPanel, JComponent component, int gridX, int gridY,
                                   int gridWidth, int gridHeight, int gridWeightX) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = gridX;
        constraints.gridy = gridY;
        constraints.gridwidth = gridWidth;
        constraints.gridheight = gridHeight;
        constraints.weightx = gridWeightX;
        rootPanel.add(component, constraints);
    }
}

```

```java
JLabel labelUsername = new JLabel("Username:");
UIHelper.placeUIComp(rootPanel, labelUsername, 0, 1, 1, 1, 1);
```

Dieser Code wiederholt sich für alle UI-Komponenten.

### Refactorings

#### Refactoring 1: Rename method
Der Methodenname `onRefresh` is für ein Observer-Callable nicht besonders aussagekräftig. Stattdessen wird jetzt der
Name `onSongListChanged`verwendet.

**Vorher:** <br>
![Rename method before](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/marcauberer/music-manager/main/media/refactoring1-before.plantuml&fmt=svg)

**Nachher (Commit ID: 2a72e489650af0ad4f4b022fd222dea15a92be71):** <br>
![Rename method after](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/marcauberer/music-manager/main/media/refactoring1-after.plantuml&fmt=svg)

#### Refactoring 2: Extract method
Um die Oberflächenkomponenten auf der UI zu platzieren, nutze ich den `GridBagLayoutManager`. Um diesen zu nutzen, bedarf
es `LayoutConstraints`. Da alle JFrame-Klassen diese Funktionalität benötigen, reicht es nicht eine Methoden-Extraktion
innerhalb einer JFrame-Klasse zu machen. Stattdessen habe ich mich dazu entschieden eine neue Klasse `UIHelper` zu erstellen,
der eine statische Methode `placeUIComp` anbietet um eine Komponente zu platzieren. Dieser übergibt man das Root Panel,
die zu platzierende Komponente sowie die Koordinaten und Dimensionen der Komponente auf dem Root Panel.

**Vorher:** <br>
![Rename method before](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/marcauberer/music-manager/main/media/refactoring2-before.plantuml&fmt=svg)

**Nachher (Commit ID: fae1236a349dcdeea7c5c2dc1532aa7478ce26f3):** <br>
![Rename method after](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/marcauberer/music-manager/main/media/refactoring2-after.plantuml&fmt=svg)

Im Code ist es eventuell anschaulicher: 
[vorher](https://github.com/marcauberer/music-manager/blob/191aa635316bc549318f3bae780af175a4c122bc/3-mm-plugin/src/main/java/com/marc_auberer/musicmanager/plugin/ui/MusicManagerUI.java#L50-L142) -
[nachher](https://github.com/marcauberer/music-manager/blob/fae1236a349dcdeea7c5c2dc1532aa7478ce26f3/3-mm-plugin/src/main/java/com/marc_auberer/musicmanager/plugin/ui/MusicManagerUI.java#L50-L114)

## Kapitel 8: Entwurfsmuster

### Entwurfsmuster 1: Builder Pattern
Für die Erstellung von Song-Instanzen werden nicht immer alle Felder eines
Songs benötigt. Wenn beispielsweise die Bpm-Zahl eines Songs unbekannt ist,
soll eine Erstellung einer Song-Instanz trotzdem möglich sein. Als Abhilfe
kommt hier der `SongBuilder` zum Einsatz. Hier können die einzelnen Angaben
über Builder-Methoden getätigt werden. Am Ende kann das fertige Song-Objekt
mit der Methode `build()` erzeugt werden.

![Observer Pattern](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/marcauberer/music-manager/main/media/builder-pattern.plantuml&fmt=svg)

### Entwurfsmuster 2: Observer Pattern
Das Observer-Pattern kommt an zwei Stellen im Code zum Einsatz. Zum einen
beim Login, sodass die Haupt-Klasse `MusicManager.java` immer den Überblick
über den aktuellen Zustand erhält und JFrames ein- und ausblenden kann.
Zum anderen zur aktualisierung der Song-Liste auf der Benutzeroberfläche.
Hier benachrichtigt der `SongService` die UI über Veränderungen an der
Song-Liste sodass sich diese aktualisieren kann.

![Observer Pattern](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/marcauberer/music-manager/main/media/observer-pattern.plantuml&fmt=svg)