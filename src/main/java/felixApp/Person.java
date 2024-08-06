package felixApp;

public class Person {
    private String lauf;
    private String wievielterLauf;
    private String jahr;
    private String dienstgrad;
    private String nameVorname;
    private String einheit;
    private String platz;
    private String laufstrecke;
    private String altersklasseJahrgang;
    private String zeit;
    private String ortDatum;

    public Person(String lauf, String wievielterLauf, String jahr, String dienstgrad, String nameVorname, String einheit, String platz, String laufstrecke, String altersklasseJahrgang, String zeit, String ortDatum) {
        this.lauf = lauf;
        this.wievielterLauf = wievielterLauf;
        this.jahr = jahr;
        this.dienstgrad = dienstgrad;
        this.nameVorname = nameVorname;
        this.einheit = einheit;
        this.platz = platz;
        this.laufstrecke = laufstrecke;
        this.altersklasseJahrgang = altersklasseJahrgang;
        this.zeit = zeit;
        this.ortDatum = ortDatum;
    }

    // Getter und Setter Methoden
    public String getLauf() { return lauf; }
    public void setLauf(String lauf) { this.lauf = lauf; }
    public String getWievielterLauf() { return wievielterLauf; }
    public void setWievielterLauf(String wievielterLauf) { this.wievielterLauf = wievielterLauf; }
    public String getJahr() { return jahr; }
    public void setJahr(String jahr) { this.jahr = jahr; }
    public String getDienstgrad() { return dienstgrad; }
    public void setDienstgrad(String dienstgrad) { this.dienstgrad = dienstgrad; }
    public String getNameVorname() { return nameVorname; }
    public void setNameVorname(String nameVorname) { this.nameVorname = nameVorname; }
    public String getEinheit() { return einheit; }
    public void setEinheit(String einheit) { this.einheit = einheit; }
    public String getPlatz() { return platz; }
    public void setPlatz(String platz) { this.platz = platz; }
    public String getLaufstrecke() { return laufstrecke; }
    public void setLaufstrecke(String laufstrecke) { this.laufstrecke = laufstrecke; }
    public String getAltersklasseJahrgang() { return altersklasseJahrgang; }
    public void setAltersklasseJahrgang(String altersklasseJahrgang) { this.altersklasseJahrgang = altersklasseJahrgang; }
    public String getZeit() { return zeit; }
    public void setZeit(String zeit) { this.zeit = zeit; }
    public String getOrtDatum() { return ortDatum; }
    public void setOrtDatum(String ortDatum) { this.ortDatum = ortDatum; }
}
