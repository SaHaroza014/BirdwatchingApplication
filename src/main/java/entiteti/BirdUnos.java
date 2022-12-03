package entiteti;

public class BirdUnos extends VrstaZnacajke{
    private String nazivLatinski;
    private String nazivHrvatski;
    private Integer brojnost;
    private String spol;

    public BirdUnos(String nazivLatinski, String nazivHrvatski, Integer brojnost, String spol, int kategorija) {
        super(kategorija);
        this.nazivLatinski = nazivLatinski;
        this.nazivHrvatski = nazivHrvatski;
        this.brojnost = brojnost;
        this.spol = spol;
    }

    public String getNazivLatinski() {
        return nazivLatinski;
    }

    public void setNazivLatinski(String nazivLatinski) {
        this.nazivLatinski = nazivLatinski;
    }

    public String getNazivHrvatski() {
        return nazivHrvatski;
    }

    public void setNazivHrvatski(String nazivHrvatski) {
        this.nazivHrvatski = nazivHrvatski;
    }

    public Integer getBrojnost() {
        return brojnost;
    }

    public void setBrojnost(Integer brojnost) {
        this.brojnost = brojnost;
    }

    public String getSpol() {
        return spol;
    }

    public void setSpol(String spol) {
        this.spol = spol;
    }


    @Override
    public void izvjestaj() {
        System.out.println("Zabiljezeno je: ");
    }

}
