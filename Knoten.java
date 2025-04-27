
/**
 * Beschreiben Sie hier die Klasse Knoten.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Knoten
{
    private Stadt inhalt;
    private boolean besucht;
    private boolean fertig;
    
    public Knoten(Stadt stadt) {
        this.inhalt = stadt;
        this.besucht = false;
        this.fertig = false;
    }
    
    public Stadt getInhalt() {
        return inhalt;
    }
    
    public void setBesucht(boolean besucht) {
        this.besucht = besucht;
    }
    
    public boolean getBesucht() {
        return besucht;
    }
    
    public void setFertig(boolean fertig) {
        this.fertig = fertig;
    }
    
    public boolean getFertig(boolean fertig) {
        return fertig;
    }
}
