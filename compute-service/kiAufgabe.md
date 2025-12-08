Wie ich vorgegangen bin
Für meinen Berechnungsservice habe ich ChatGPT genutzt, um den Code zu strukturieren und die technische Umsetzung zu vereinfachen. Ausgangspunkt war die Idee, einen einfachen Rechner zu bauen, der mathematische Ausdrücke wie „3+5*2“ verarbeiten kann.
Ich habe der KI erklärt, dass ich einen Endpoint benötige, der:
Berechnungen annimmt,
diese speichert,
den Ausdruck auswertet
und das Ergebnis wieder zurückgibt.
Vorgehen mit ChatGPT
ChatGPT hat folgenden Ansatz vorgeschlagen:
REST-Controller
Endpoints zum Erstellen, Abrufen und Auflisten von Berechnungen.
Eigenes Evaluate-System
ChatGPT erklärte, wie man Tokenizing, Operator-Prioritäten und Klammern korrekt verarbeitet.
Dadurch entstand ein kleiner mathematischer Parser.
Speicherlösung
Da keine Datenbank erforderlich war, wurde eine ConcurrentHashMap als In-Memory-Store empfohlen.
Strukturierung des Codes
Controller-Klasse
Datenmodell Calculation
Evaluate-Methode
Tokenizer
Fehlerbehandlung
Ich habe diesen Code übernommen, an mein Projekt angepasst und integriert.
Damit funktioniert der Berechnungsservice vollständig.


## This service is no longer in use :(