package entiteti.unos;

public class IstrazivacUnos {
    private String ime;
    private String prezime;
    private String institucija;
    private Integer broj;

    private BirdUnos[] zapazanja;

    public IstrazivacUnos(String ime, String prezime, String institucija, Integer broj, BirdUnos[] zapazanja) {
        this.ime = ime;
        this.prezime = prezime;
        this.institucija = institucija;
        this.broj = broj;
        this.zapazanja=zapazanja;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getInstitucija() {
        return institucija;
    }

    public void setInstitucija(String institucija) {
        this.institucija = institucija;
    }

    public Integer getBroj() {
        return broj;
    }

    public void setBroj(Integer broj) {
        this.broj = broj;
    }
}
