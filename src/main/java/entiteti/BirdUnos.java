package entiteti;

public class BirdUnos extends VrstaZnacajke implements Analiza{
    private String nazivLatinski;
    private String nazivHrvatski;
    private Integer brojnost;
    private String spol;


    public BirdUnos(String nazivLatinski, String nazivHrvatski, Integer brojnost, String spol, String kategorija) {
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

    @Override
    public int[] countGender(BirdUnos[] b) {
        int[] container = new int[3];
        int countM = 0;
        int countF = 0;
        int countU = 0;

        for(int i = 0; i < b.length; i ++){
            if(b[i].getSpol().equals("M")){
                countM++;
                container[0] = countM;
            }
            else if(b[i].getSpol().equals("F")){
                countF++;
                container[1] = countF;
            }
            else{
                countU++;
                container[2] = countU;
            }
        }
        return container;
    }
}
