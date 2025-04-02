
/**
 * Beschreiben Sie hier die Klasse Graph.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Graph {
    private int anzahl;
    private int[][] kanten;
    private Knoten[] knoten;
    
    public Graph(int maxKnoten) {
        this.anzahl = 0; // Setzt die Anzahl der Knoten auf 0
        this.kanten = new int[maxKnoten][maxKnoten]; // Erstelle die Adjazenzmatrix
        this.knoten = new Knoten[maxKnoten]; // Erstelle das Array für die Knoten
        
        // Initialisierung der Adjazenzmatrix mit -1 (kein Pfad vorhanden)
        for (int i = 0; i < maxKnoten; i++) {
            for (int j = 0; j < maxKnoten; j++) {
                kanten[i][j] = -1; 
            }
        }
    }
    
    public boolean erstelleKnoten(Stadt stadt) {
        if (anzahl < knoten.length) { // Platz-Check
            knoten[anzahl] = new Knoten(stadt); 
            anzahl++; // Erhöht die Anzahl der gespeicherten Knoten
            return true; // Praktisch für später
        }
        return false; // Kein Platz mehr, praktisch für später
    }
    
    // Gibt den Index eines Knotens anhand seines Stadtnamens zurück oder -1, falls nicht gefunden
    private int nenneIndexVon(String stadtname) {
        for (int i = 0; i < anzahl; i++) { 
            if (knoten[i].getInhalt().getName().compareToIgnoreCase(stadtname) == 0) {
                return i; // Falls der Name übereinstimmt, wird der Index zurückgegeben
            }
        }
        return -1; // Falls kein passender Knoten gefunden wurde, wird -1 zurückgegeben
    }
    
    // Erstellt eine Kante zwischen zwei Knoten mit einem bestimmten Gewicht
    public boolean erstelleKante(String von, String nach, int gewicht, boolean ungerichtet) {
        int indexVon = nenneIndexVon(von); // Angabe von dem Index der Startstadt
        int indexNach = nenneIndexVon(nach); // Angabe von dem Index der Zielstadt
        
        if (indexVon != -1 && indexNach != -1 && indexVon != indexNach) { 
            kanten[indexVon][indexNach] = gewicht; 
            if (ungerichtet) {
                kanten[indexNach][indexVon] = gewicht; // andere Richtung
            }
            return true; // Erfolg
        }
        return false; // Misserfolg
    }
}

