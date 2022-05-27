# Dokumentation zum Programmentwurf

## Music Manager
**Name**: Auberer, Marc <br>
**Matrikelnummer**: 1577789
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
cd 4-mm-plugin
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

#### Application-Schicht
*ToDo*

#### Plugin-Schicht
*ToDo*

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
*ToDo*

#### Negativ-Beispiel
*ToDo*

### Analyse GRASP: Hohe Kohäsion

#### Positiv-Beispiel
*ToDo*

#### Negativ-Beispiel
*ToDo*

### Don't Repeat Yourself (DRY)
*ToDo*

## Kapitel 5: Unit tests

### 10 Unit tests

Die folgende Tabelle umfasst eine Auswahl der Unit-Tests für die MusicManager-Applikation.

*ToDo*

### ATRIP - Automatic
*ToDo*

### ATRIP - Thorough
*ToDo*

#### Positiv-Beispiel
*ToDo*

#### Negativ-Beispiel
*ToDo*

### ATRIP - Professional

#### Positiv-Beispiel
*ToDo*

#### Negativ-Beispiel
*ToDo*

### Code Coverage
*ToDo*

### Fakes und Mocks

#### Mock 1
*ToDo*

#### Mock 2
*ToDo*

## Kapitel 6: Domain Driven Design

### Ubiquitous Language
*ToDo*

### Entities
*ToDo*

### Value Objects
*ToDo*

### Repositories
*ToDo*

### Aggregates
*ToDo*

## Kapitel 7: Refactoring

### Code Smells

#### Code Smell 1: <name>
*ToDo*

#### Code Smell 2: <name>
*ToDo*

### Refactorings

#### Refactoring 1
*ToDo*

#### Refactoring 2
*ToDo*

## Kapitel 8: Entwurfsmuster

### Entwurfsmuster 1: Builder Pattern
Für die Erstellung von Song-Instanzen werden nicht immer alle Felder eines
Songs benötigt. Wenn beispielsweise die Bpm-Zahl eines Songs unbekannt ist,
soll eine Erstellung einer Song-Instanz trotzdem möglich sein. Als Abhilfe
kommt hier der `SongBuilder` zum Einsatz. Hier können die einzelnen Angaben
über Builder-Methoden getätigt werden. Am Ende kann das fertige Song-Objekt
mit der Methode `build()` erzeugt werden.

### Entwurfsmuster 2: Observer Pattern
Das Observer-Pattern kommt an zwei Stellen im Code zum Einsatz. Zum einen
beim Login, sodass die Haupt-Klasse `MusicManager.java` immer den Überblick
über den aktuellen Zustand erhält und JFrames ein- und ausblenden kann.
Zum anderen zur aktualisierung der Song-Liste auf der Benutzeroberfläche.
Hier benachrichtigt der `SongService` die UI über Veränderungen an der
Song-Liste sodass sich diese aktualisieren kann.