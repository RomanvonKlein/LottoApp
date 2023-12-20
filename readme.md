# Lotto App
Die Lotto App ermöglicht das erzeugen von Tippreihen für die Spiele "Lotto - 6 aus 49" und "Eurojackpot - 5 aus 50 und 2 aus 10". Dabei ist es möglich, Unglückszahlen abzuspeichern, die bei der Ausgaber der Tippreihen berücksichtigt werden.
## Benutzerdokumentation
Die Steuerung der Anwendung erfolgt über die Kommandozeile. Die entstandene Jar-Datei kann mit dem Terminal über ```java -jar app.jar```ausgeführt werden. Wird die Anwendung ohne Parameter aufgerufen, wird eine Tippreihe für das Spiel "Lotto - 6 aus 49" mit der abgespeicherten Konfiguration ausgegeben. Möchte man genau kontrollieren, für welches Spiel die Tippreihe ausgegeben wird, kann man dies mit dem Parameter ```game [gamename]```tun. ```gamename```kann dabei ```lotto``` (für "Lotto - 6 aus 49") oder ```eurogame``` (für "Eurojackpot - 5 aus 50 und 2 aus 10") sein.


Um neue Unglückszahlen hinzuzufügen, kann der Befehl ```blacklist add [number1, number2, ...]``` genutzt werden. Die Zahlen müssen dafür für zumindest ein Spiel legitim sein. Zu jeder Zeit können maximal 6 Unglückszahlen festgelegt werden. 

Bereits eingespeicherte Unglückszahlen können mit dem Befehl ```blacklist remove [number1, number2, ...]``` wieder entfernt werden.

Änderungen an dern Unglückszahlen werden direkt abgespeichert und beim nächsten Start der Anwedung wieder geladen.
## Entwicklerdokumentation
Der Build-Vorgang für dieses Projekt ist mit Gradle und Java umgesetzt worden. Um das Projekt zu kompilieren kann der Gradle-Wrapper Befehl ```gradlew build``` genutzt werden. Die erzeugte Jar-Datei kann dann unter ```./app/build/libs/app.jar``` gefunden werden.