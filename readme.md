# Lotto App
Die Lotto App ermöglicht das erzeugen von Tippreihen für die Spiele "Lotto - 6 aus 49" und "Eurojackpot - 5 aus 50 und 2 aus 10". Dabei ist es möglich, Unglückszahlen abzuspeichern, die bei der Ausgaber der Tippreihen berücksichtigt werden.
## Benutzerdokumentation
Die Steuerung der Anwendung erfolgt über die Kommandozeile. Die entstandene Jar-Datei kann mit dem Terminal über ```java -jar app.jar```ausgeführt werden. Wird die Anwendung ohne Parameter aufgerufen, wird eine Tippreihe für das Spiel "Lotto - 6 aus 49" mit der abgespeicherten Konfiguration ausgegeben. Möchte man genau kontrollieren, für welches Spiel die Tippreihe ausgegeben wird, kann man dies mit dem Parameter ```game [gamename]```tun. ```gamename```kann dabei ```lotto``` (für "Lotto - 6 aus 49") oder ```eurogame``` (für "Eurojackpot - 5 aus 50 und 2 aus 10") sein.


Um neue Unglückszahlen hinzuzufügen, kann der Befehl ```blacklist add [number1, number2, ...]``` genutzt werden. Die Zahlen müssen dafür für zumindest ein Spiel legitim sein. Zu jeder Zeit können maximal 6 Unglückszahlen festgelegt werden. 

Bereits eingespeicherte Unglückszahlen können mit dem Befehl ```blacklist remove [number1, number2, ...]``` wieder entfernt werden.

Änderungen an dern Unglückszahlen werden direkt abgespeichert und beim nächsten Start der Anwedung wieder geladen.
## Entwicklerdokumentation

### Projekt setup
Der Build-Vorgang für dieses Projekt ist mit Gradle und Java umgesetzt worden. Um das Projekt zu kompilieren kann der Gradle-Wrapper Befehl ```gradlew build``` genutzt werden. Die erzeugte Jar-Datei kann dann unter ```./app/build/libs/app.jar``` gefunden werden.

Die Einstiegsprozedur befindes sich unter ```./app/src/main/java/lottoapp/App.java#main```, die Tests sind im Verzeichnis ```./app/src/test/java/lottoapp/```.

### Abhängigkeiten
Als einzige Abhängigkeit wird Gson von google als Version 2.9.0 verwendet.

### Code-Struktur
Die Einstiegsprozedur in der Klasse ```App``` verarbeitet zunächst die parameter die beim Start der Anwendung mitgegeben wurden. Anschließend wird der Nutzer zu neuen Eingaben aufgefordert bis er sich entscheidet das Programm zu beenden. Die Eingaben werden an die entsprechenden ```ICommandProcessor``` Implementationen weitergeleitet. Die parsen die restlichenn Parameter und führen entsprechend Aktionen aus.

Neue Kommandos können implementiert werden, indem weitere Instanzen von Klassen, die das Interface ```ICommandProcessor``` implementieren, in die HashMap ```CommandMap``` in der ```App``` Klasse eingefügt werden.

Die Vorhandenen Spiele implementieren das Interface ```IGame``` und werden in der Klasse ```GameCommandProcessor``` verwaltet. Um weitere Spiele hinzuzufügen, muss ebenso das Interface ```IGame``` implementiert, und die Instanzen in der HashMap ```gameMap``` hinterlegt werden.

Das Logging ist mit ```java.util.logging``` implementiert. Es sollte für jeden Start eine seperate Logdatei unter ```./logs/``` angelegt werden. Der Output für die Logs wird nicht im Terminal für den Nutzer ausgegeben.

Die Blacklist mit den Unglückszahlen wird mithilfe der ```Storage``` Klasse unter ```./blacklist.json``` im JSON format abgespeichert und wird für jede Sitzung eingelesen.

Falls der Nutzer ein Kommando falsch eingibt, wird entweder eine ```IllegalArgumentException``` oder eine ```BadCommandSyntaxException``` geworfen. Ist die generelle Syntax falsch, wird die ```BadCommandSyntaxException``` ausgeworfen, sind die eingegebenen Werte ungültig, erfolgt der auswurf einer ```IllegalArgumentException```.
Diese Exceptions werden innerhalb des Programms gehandhabt und eine verständliche Rückmeldung and den Benutzer ausgegeben.