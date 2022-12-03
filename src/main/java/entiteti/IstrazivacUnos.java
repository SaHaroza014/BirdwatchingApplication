package entiteti;

public class IstrazivacUnos{
    private String ime;
    private String prezime;
    private String institucija;
    private Double broj;

    private Lokalitet[] lokaliteti;

    public IstrazivacUnos(String ime, String prezime, String institucija, Double broj, Lokalitet[] lokaliteti) {
        this.ime = ime;
        this.prezime = prezime;
        this.institucija = institucija;
        this.broj = broj;
        this.lokaliteti=lokaliteti;
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

    public Double getBroj() {
        return broj;
    }

    public void setBroj(Double broj) {
        this.broj = broj;
    }

}
