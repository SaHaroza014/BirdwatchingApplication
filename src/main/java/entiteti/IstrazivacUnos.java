package entiteti;

public non-sealed class IstrazivacUnos implements Istrazivac{
    private String ime;
    private String prezime;
    private String institucija;
    private Double broj;

    private BirdUnos[] zapazanja;

    public IstrazivacUnos(String ime, String prezime, String institucija, Double broj, BirdUnos[] zapazanja) {
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

    public Double getBroj() {
        return broj;
    }

    public void setBroj(Double broj) {
        this.broj = broj;
    }

    @Override
    public int countUnos(IstrazivacUnos ist) {
        int countSve = 0;
        for(int i = 0; i < ist.zapazanja.length;i++){
            if(ist.zapazanja[i].getBrojnost()>0){
                countSve++;
            }
        }
        return countSve;
    }
}
