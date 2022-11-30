package glavna;

import entiteti.BirdUnos;
import entiteti.IstrazivacUnos;
import entiteti.Lokalitet;
import entiteti.Nomenklatura;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Glavna {
    public static void main(String[] args) throws ParseException {

        Scanner myObj = new Scanner(System.in);

        int numObservation = 0;
        //kasnije napraviti da se unosi dok se ne unese odredjeni znak (ili tome nesto slicno)
        System.out.print("Koliko opservacija zelite unijeti? (min. 5): ");
        numObservation=myObj.nextInt();

        BirdUnos[] unosi = new BirdUnos[numObservation];
        IstrazivacUnos[] istrazivaci = new IstrazivacUnos[1];
        Lokalitet[] lokaliteti = new Lokalitet[1];

        for(int l = 0; l < lokaliteti.length; l++){
            // Unos lokaliteta, tj. naziva i datuma...
            System.out.print("Unesite naziv lokacije: ");
            String nazivL = myObj.nextLine();
            myObj.nextLine();
            System.out.print("Uneiste datum istrazivanja: ");
            String datumL = myObj.nextLine();
            Date date1=new SimpleDateFormat("dd.MM.yyyy.").parse(datumL);


            System.out.print("Unesite ime istrazivaca: ");
            String ime = myObj.nextLine();
            ime = ime.substring(0,1).toUpperCase() + ime.substring(1);
            System.out.print("Unesite prezime istrazivaca: ");
            String prezime = myObj.nextLine();
            prezime = prezime.substring(0,1).toUpperCase() + prezime.substring(1);
            System.out.print("Unesite ime institucije kojoj pripada istrazivac: ");
            String institucija = myObj.nextLine().toUpperCase();
            System.out.print("Unesite telefonski broj istrazivaca: "); // dodati da broj ne moze biti dulji/kraci od fiksne vrijednosti
            Integer broj = myObj.nextInt();
            myObj.nextLine();

            //unosi opservacija
            for(int u = 0; u < unosi.length;u++) {
                // program za konvert " " u "_":
                System.out.print("Unesite hrvatski naziv ptice: ");
                String nazivHrvatski = myObj.nextLine();
                String naziv2 = Character.toLowerCase(nazivHrvatski.charAt(0)) + nazivHrvatski.substring(1)
                        .replaceAll(" ", "_").toLowerCase();

                Nomenklatura test = Nomenklatura.valueOf(naziv2);
                // trazi po enum postoji li uneseni 'hrvatski naziv ptice' kako bi se dodao znanstveni naziv
                // kasnije dodati Exception koji javlja da nije dobro uneseno, tj. da se pokusa ponovno...
                //i ako je vec unesena vrsta da se ne moze unijeti opet u istom sessionu unosa...
                String nazivZnanstveni = null;
                for (Nomenklatura nom : Nomenklatura.values()) {
                    if (nom.equals(test)) {
                        nazivZnanstveni = nom.getVrsta();
                    }
                }

                System.out.print("Unesite brojnost: ");
                Integer brojnost = myObj.nextInt();
                myObj.nextLine();
                System.out.print("Unesite spol vrste (M/F/U): ");
                String spol = myObj.nextLine();

                unosi[u] = new BirdUnos(nazivZnanstveni, nazivHrvatski, brojnost, spol);
            }
            istrazivaci[l] = new IstrazivacUnos(ime, prezime, institucija, broj, unosi);
            lokaliteti[l] = new Lokalitet(nazivL, date1, istrazivaci);
        }

            System.out.println("Istrazivac " +(istrazivaci[0].getIme()+ " " + istrazivaci[0].getPrezime() + " iz institucije '" + (istrazivaci[0].getInstitucija()) + "' unio je: "));
            for(int i = 0 ; i < unosi.length;i++){
                System.out.println((i+1) + ".) "+(unosi[i].getNazivLatinski())+ " " + " "+(unosi[i].getBrojnost())+" "+unosi[i].getSpol());
            }
    }

}
