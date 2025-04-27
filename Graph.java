
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
    
    public static void main(String[] args) {
        Graph graph = new Graph(5);

        Stadt stadt1 = new Stadt("Berlin");
        Stadt stadt2 = new Stadt("München");
        Stadt stadt3 = new Stadt("Hamburg");
        Stadt stadt4 = new Stadt("Köln");
        Stadt stadt5 = new Stadt("Frankfurt");

        graph.erstelleKnoten(stadt1);
        graph.erstelleKnoten(stadt2);
        graph.erstelleKnoten(stadt3);
        graph.erstelleKnoten(stadt4);
        graph.erstelleKnoten(stadt5);

        graph.erstelleKante("Berlin", "München", 1, false);
        graph.erstelleKante("Berlin", "Hamburg", 1, false);
        graph.erstelleKante("München", "Köln", 1, false);
        graph.erstelleKante("Hamburg", "Frankfurt", 1, false);
        graph.erstelleKante("Köln", "Frankfurt", 1, false);

        graph.breitensuche("Berlin");

    }
    
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
    
    public void breitensuche(String stadtname){
        int index  = nenneIndexVon(stadtname);
        System.out.println(index);
        if (index == -1){
            System.out.println("Startknoten existiert nicht");
        }else{
            int[] warteschlange;
            warteschlange = new int[anzahl];
            for (int i = 0; i < warteschlange.length; i++){
                warteschlange[i] = -1;
            }
            for (int i = 0; i < anzahl; i++) {
                knoten[i].setBesucht(false);
                knoten[i].setFertig(false);
            }
            
            int vorne = 0;
            int hinten = 0;
            int abgelaufen = 0;
            
            warteschlange[0] = index;
            hinten++;
            
            while (vorne < hinten){
                
                for (int i = 0; i < kanten[warteschlange[vorne]].length; i++){
                    if (kanten[warteschlange[vorne]][i] != -1 && !knoten[i].getBesucht()){
                        knoten[i].setBesucht(true);
                        
                        System.out.println(knoten[i].getInhalt().getName());
                        System.out.println("Knoten " + knoten[i].getInhalt().getName() + " ist jetzt besucht");
                        
                        warteschlange[hinten] = i;
                        hinten++;
                        abgelaufen++;
                    }
                }
                System.out.println("Ausgangsknoten " + knoten[warteschlange[vorne]].getInhalt().getName() + " fertig!");
                vorne++;
            }
            
            for (int i = 0; i < warteschlange.length; i++){
                if (warteschlange[i] != -1){
                    System.out.println(knoten[warteschlange[i]].getInhalt().getName());
                }
            }
            
            System.out.println("Abgelaufen: " + abgelaufen);
        }
        
    }
}

