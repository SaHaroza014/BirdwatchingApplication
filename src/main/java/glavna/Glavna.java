package glavna;

import entiteti.unos.BirdUnos;
import entiteti.unos.IstrazivacUnos;
import entiteti.unos.Lokalitet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Glavna {
    public static void main(String[] args) throws ParseException {

        Scanner myObj = new Scanner(System.in);

        int numObservation = 0;
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
            System.out.print("Unesite prezime istrazivaca: ");
            String prezime = myObj.nextLine();
            System.out.print("Unesite ime institucije kojoj pripada istrazivac: ");
            String institucija = myObj.nextLine();
            System.out.print("Unesite telefonski broj istrazivaca: "); // dodati da broj ne moze biti dulji/kraci od fiksne vrijednosti
            Integer broj = myObj.nextInt();
            myObj.nextLine();

            //unosi opservacija
            for(int u = 0; u < unosi.length;u++) {
                System.out.print("Unesite znanstveni naziv ptice: ");
                String nazivZnanstveni = myObj.nextLine();
                System.out.print("Unesite hrvatski naziv ptice: ");
                String nazivHrvatski = myObj.nextLine();
                System.out.print("Unesite brojnost: ");
                Integer brojnost = myObj.nextInt();
                myObj.nextLine();
                System.out.print("Unesite spol vrste (M/F): ");
                String spol = myObj.nextLine();

                unosi[u] = new BirdUnos(nazivZnanstveni, nazivHrvatski, brojnost, spol);
            }
            istrazivaci[l] = new IstrazivacUnos(ime, prezime, institucija, broj, unosi);
            lokaliteti[l] = new Lokalitet(nazivL, date1, istrazivaci);
        }

            System.out.println("Istrazivac " +(istrazivaci[0].getIme()+ " " + istrazivaci[0].getPrezime() + " iz institucije " + (istrazivaci[0].getInstitucija()) + " unio je: "));
            for(int i = 0 ; i < unosi.length;i++){
                System.out.println((i+1) + ".) "+(unosi[i].getNazivLatinski())+ " " +(unosi[i].getNazivHrvatski())+" "+(unosi[i].getBrojnost())+" "+unosi[i].getSpol());
            }
    }

}
