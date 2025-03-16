
/**
 * Beschreiben Sie hier die Klasse Graph.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Graph {
    // Anzahl der aktuell gespeicherten Knoten
    private int anzahl;
    // Adjazenzmatrix zur Speicherung der Kanten
    private int[][] kanten;
    // Array zur Speicherung der Knoten
    private Knoten[] knoten;
    
    // Konstruktor zur Initialisierung des Graphen mit einer maximalen Anzahl an Knoten
    public Graph(int maxKnoten) {
        this.anzahl = 0; // Setzt die Anzahl der Knoten auf 0
        this.kanten = new int[maxKnoten][maxKnoten]; // Erstelle die Adjazenzmatrix
        this.knoten = new Knoten[maxKnoten]; // Erstelle das Array für die Knoten
        
        // Initialisierung der Adjazenzmatrix mit -1 (kein Pfad vorhanden)
        for (int i = 0; i < maxKnoten; i++) {
            for (int j = 0; j < maxKnoten; j++) {
                kanten[i][j] = -1; // -1 bedeutet, dass keine Verbindung existiert
            }
        }
    }
    
    // Fügt einen neuen Knoten zum Graphen hinzu, falls Platz vorhanden ist
    public boolean erstelleKnoten(Stadt stadt) {
        if (anzahl < knoten.length) { // Prüft, ob noch Platz im Array ist
            knoten[anzahl] = new Knoten(stadt); // Erstellt einen neuen Knoten mit der Stadt
            anzahl++; // Erhöht die Anzahl der gespeicherten Knoten
            return true; // Rückgabe von true, wenn der Knoten erfolgreich hinzugefügt wurde
        }
        return false; // Rückgabe von false, falls kein Platz mehr vorhanden ist
    }
    
    // Gibt den Index eines Knotens anhand seines Stadtnamens zurück oder -1, falls nicht gefunden
    private int nenneIndexVon(String stadtname) {
        for (int i = 0; i < anzahl; i++) { // Durchläuft das Array der gespeicherten Knoten
            if (knoten[i].getInhalt().getName().compareToIgnoreCase(stadtname) == 0) {
                return i; // Falls der Name übereinstimmt, wird der Index zurückgegeben
            }
        }
        return -1; // Falls kein passender Knoten gefunden wurde, wird -1 zurückgegeben
    }
    
    // Erstellt eine Kante zwischen zwei Knoten mit einem bestimmten Gewicht
    public boolean erstelleKante(String von, String nach, int gewicht, boolean ungerichtet) {
        int indexVon = nenneIndexVon(von); // Bestimme den Index der Startstadt
        int indexNach = nenneIndexVon(nach); // Bestimme den Index der Zielstadt
        
        if (indexVon != -1 && indexNach != -1 && indexVon != indexNach) { // Überprüft, ob gültige Indizes vorhanden sind und nicht identisch
            kanten[indexVon][indexNach] = gewicht; // Setzt das Gewicht der Kante in der Matrix
            if (ungerichtet) { // Falls die Kante ungerichtet ist
                kanten[indexNach][indexVon] = gewicht; // Setzt die Kante auch in die andere Richtung
            }
            return true; // Rückgabe von true, wenn die Kante erfolgreich erstellt wurde
        }
        return false; // Rückgabe von false, falls die Kante nicht erstellt werden konnte
    }
}

