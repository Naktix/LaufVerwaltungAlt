package felixApp;

public class Team {
    private String lauf;
    private String wievielterLauf;
    private String jahr;
    private String einheit;
    private String teilnehmerzahl;
    private String teamwertung;
    private String platz;
    private String gesamtzeit;
    private String bester1;
    private String bester2;
    private String bester3;
    private String ortDatum;

    public Team(String lauf, String wievielterLauf, String jahr, String einheit, String teilnehmerzahl, String teamwertung, String platz, String gesamtzeit, String bester1, String bester2, String bester3, String ortDatum) {
        this.lauf = lauf;
        this.wievielterLauf = wievielterLauf;
        this.jahr = jahr;
        this.einheit = einheit;
        this.teilnehmerzahl = teilnehmerzahl;
        this.teamwertung = teamwertung;
        this.platz = platz;
        this.gesamtzeit = gesamtzeit;
        this.bester1 = bester1;
        this.bester2 = bester2;
        this.bester3 = bester3;
        this.ortDatum = ortDatum;
    }

    // Getter und Setter Methoden
    public String getLauf() { return lauf; }
    public void setLauf(String lauf) { this.lauf = lauf; }
    public String getWievielterLauf() { return wievielterLauf; }
    public void setWievielterLauf(String wievielterLauf) { this.wievielterLauf = wievielterLauf; }
    public String getJahr() { return jahr; }
    public void setJahr(String jahr) { this.jahr = jahr; }
    public String getEinheit() { return einheit; }
    public void setEinheit(String einheit) { this.einheit = einheit; }
    public String getTeilnehmerzahl() { return teilnehmerzahl; }
    public void setTeilnehmerzahl(String teilnehmerzahl) { this.teilnehmerzahl = teilnehmerzahl; }
    public String getTeamwertung() { return teamwertung; }
    public void setTeamwertung(String teamwertung) { this.teamwertung = teamwertung; }
    public String getPlatz() { return platz; }
    public void setPlatz(String platz) { this.platz = platz; }
    public String getGesamtzeit() { return gesamtzeit; }
    public void setGesamtzeit(String gesamtzeit) { this.gesamtzeit = gesamtzeit; }
    public String getBester1() { return bester1; }
    public void setBester1(String bester1) { this.bester1 = bester1; }
    public String getBester2() { return bester2; }
    public void setBester2(String bester2) { this.bester2 = bester2; }
    public String getBester3() { return bester3; }
    public void setBester3(String bester3) { this.bester3 = bester3; }
    public String getOrtDatum() { return ortDatum; }
    public void setOrtDatum(String ortDatum) { this.ortDatum = ortDatum; }
}
