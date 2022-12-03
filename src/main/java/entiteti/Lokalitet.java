package entiteti;

import java.util.Date;

/**
 * countUnos() metoda prebrojava ukupan unos podataka po lokaciji
 */
public non-sealed class Lokalitet implements Istrazivac, Analiza {
    private String nazivLokacije;
    private Date datum;

    private String nacinOpazanja;   //napraviti enum

    private BirdUnos[] unosi;

    public Lokalitet(String nazivLokacije, Date datum, BirdUnos[] unosi) {
        this.nazivLokacije = nazivLokacije;
        this.datum = datum;
        this.unosi=unosi;
    }

    public String getNazivLokacije() {
        return nazivLokacije;
    }

    public void setNazivLokacije(String nazivLokacije) {
        this.nazivLokacije = nazivLokacije;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public BirdUnos[] getUnosi() {
        return unosi;
    }

    public void setUnosi(BirdUnos[] unosi) {
        this.unosi = unosi;
    }

    @Override
    public int countUnos(Lokalitet lok) {
        int counter = 0;
        for(int i = 0; i < lok.unosi.length; i++){
            if(lok.unosi[i].getBrojnost()>0){
                counter++;
            }
        }
        return counter;
    }
    @Override
    public int[] countGender(Lokalitet[] lok) {
        int[] container = new int[3];
        int countM = 0;
        int countF = 0;
        int countU = 0;

        for(int i = 0; i < lok.length; i ++){
            for(int j = 0; j < lok[i].getUnosi().length;j++) {
                if (lok[i].getUnosi()[j].getSpol().equals("M")) {
                    countM++;
                    container[0] = countM;
                } else if (lok[i].getUnosi()[j].getSpol().equals("F")) {
                    countF++;
                    container[1] = countF;
                } else {
                    countU++;
                    container[2] = countU;
                }
            }
        }
        return container;
    }

}
