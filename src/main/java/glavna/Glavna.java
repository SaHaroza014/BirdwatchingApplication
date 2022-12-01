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

        System.out.println("Dobro dosli u program za unos podataka s Birdwatchinga!");
        System.out.println("<<----------------------------------------------------->>");
        IstrazivacUnos[] istrazivaci = new IstrazivacUnos[1];

        for(int i = 0; i < istrazivaci.length;i++){
            System.out.print("Unesite ime istrazivaca: ");
            String ime = myObj.nextLine();
            ime = ime.substring(0,1).toUpperCase() + ime.substring(1);
            System.out.print("Unesite prezime istrazivaca: ");
            String prezime = myObj.nextLine();
            prezime = prezime.substring(0,1).toUpperCase() + prezime.substring(1);
            System.out.print("Unesite ime institucije kojoj pripada istrazivac: ");
            String institucija = myObj.nextLine().toUpperCase();
            System.out.print("Unesite telefonski broj istrazivaca: "); // dodati da broj ne moze biti dulji/kraci od fiksne vrijednosti
            Double broj = myObj.nextDouble();
            myObj.nextLine();

            System.out.print("Za koliko lokacija zelite unijeti nalaze? (min. 1): ");
            int lokNum = myObj.nextInt();
            Lokalitet[] lokaliteti = new Lokalitet[lokNum];
            for(int l = 0; l < lokaliteti.length; l++){
                // Unos lokaliteta, tj. naziva i datuma...
                System.out.print("Unesite naziv lokacije: ");
                String nazivL = myObj.nextLine();
                myObj.nextLine();
                System.out.print("Uneiste datum istrazivanja: ");
                String datumL = myObj.nextLine();
                Date date1=new SimpleDateFormat("dd.MM.yyyy.").parse(datumL);

                lokaliteti[l] = new Lokalitet(nazivL, date1, istrazivaci);

                int numObservation = 0;
                //kasnije napraviti da se unosi dok se ne unese odredjeni znak (ili tome nesto slicno)
                System.out.print("Koliko opservacija zelite unijeti? (min. 5): ");
                numObservation=myObj.nextInt();
                BirdUnos[] unosi = new BirdUnos[numObservation];
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
                    System.out.print(">> Unesite brojnost: ");
                    Integer brojnost = myObj.nextInt();
                    myObj.nextLine();
                    System.out.print(">> Unesite spol vrste (M/F/U): ");
                    String spol = myObj.nextLine().toUpperCase();
                    System.out.print(">> Unesite kategoriju vrste: ");
                    String kategorija = myObj.nextLine();
                    System.out.println("<<--------------------------------------->>");

                    unosi[u] = new BirdUnos(nazivZnanstveni, nazivHrvatski, brojnost, spol, kategorija);
                }
                istrazivaci[l] = new IstrazivacUnos(ime, prezime, institucija, broj, unosi);
                System.out.println(">> Ukupno je uneseno: " + istrazivaci[0].countUnos(istrazivaci[0]) + " vrsta:");
                for(int x = 0 ; x < unosi.length;x++){
                    System.out.println((x+1) + ".) "+(unosi[x].getNazivLatinski())+ " " + " "+(unosi[x].getBrojnost())+" "+unosi[x].getSpol());
                }
                //ispis koliko je muzjaka, zenki i nepoznatog spola zabiljezeno kod vrsta:
                int[] cont2 = unosi[0].countGender(unosi);
                System.out.println(">> Ukupno je uneseno " + cont2[0] + " muzjaka, " + cont2[1] + " zenki i " + cont2[2] + " vrsta nepoznatog spola.");
            }
        }
    }
}
