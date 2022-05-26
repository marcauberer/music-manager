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

#### Ausführbare JAR-Datei finden

```shell
cd 1-mm-plugin-database
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

