package entiteti.unos;

import java.util.Date;

public class Lokalitet {
    private String nazivLokacije;
    private Date datum;

    private String nacinOpazanja;   //napraviti enum

    private IstrazivacUnos[] istrazivac;

    public Lokalitet(String nazivLokacije, Date datum, IstrazivacUnos[] istrazivac) {
        this.nazivLokacije = nazivLokacije;
        this.datum = datum;
        this.istrazivac=istrazivac;
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
}
