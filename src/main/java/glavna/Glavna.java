package glavna;

import entiteti.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Glavna {
    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);
    public static void main(String[] args) throws ParseException {

        final String[] kategorije = {"pjevica", "grabljivica", "vodarica", "sova", "curlin", "nocna ptica"};

        logger.info("Program je uspjesno pokrenut!");
        Scanner myObj = new Scanner(System.in);

        System.out.println("Dobro dosli u program za unos podataka s Birdwatchinga!");
        System.out.println("<<----------------------------------------------------->>");
        IstrazivacUnos[] istrazivaci = new IstrazivacUnos[1];
        Zbirka zbirka = new Zbirka(1,2);

        for(int i = 0; i < istrazivaci.length;i++) {
            System.out.print("Unesite ime istrazivaca: ");
            String ime = myObj.nextLine();
            ime = ime.substring(0, 1).toUpperCase() + ime.substring(1);
            System.out.print("Unesite prezime istrazivaca: ");
            String prezime = myObj.nextLine();
            prezime = prezime.substring(0, 1).toUpperCase() + prezime.substring(1);
            System.out.print("Unesite ime institucije kojoj pripada istrazivac: ");
            String institucija = myObj.nextLine().toUpperCase();

            boolean tocno = true;
            double broj = (double) 0;
            while(tocno){
                try{
                    System.out.print("Unesite telefonski broj istrazivaca: "); // dodati da broj ne moze biti dulji/kraci od fiksne vrijednosti
                    broj = myObj.nextDouble();
                    logger.info("unesena je vrijdenost: " + broj);
                    tocno=false;
                }
                catch(InputMismatchException e){
                    logger.info(e.getMessage(),e);
                    System.out.println("Unesena je pogresna vrijednost!");
                }
                myObj.nextLine();
            }

            System.out.print("Za koliko lokacija zelite unijeti nalaze? (min. 1): ");
            int lokNum = myObj.nextInt();
            Lokalitet[] lokaliteti = new Lokalitet[lokNum];
            BirdUnos[] unosi = new BirdUnos[0];
            for (int l = 0; l < lokaliteti.length; l++) {
                System.out.print("Unesite naziv " + (l+1) + ". lokacije: ");
                myObj.nextLine();
                String nazivL = myObj.nextLine();
                System.out.print("Unesite datum istrazivanja: ");
                String datumL = myObj.nextLine();
                Date date1 = new SimpleDateFormat("dd.MM.yyyy.").parse(datumL);

                int numObservation = 0;
                //kasnije napraviti da se unosi dok se ne unese odredjeni znak (ili tome nesto slicno)
                System.out.print("Koliko zelite unijeti opservacija? (min. 5): ");
                numObservation = myObj.nextInt();
                unosi = new BirdUnos[numObservation];
                myObj.nextLine();
                //unosi opservacija
                for (int u = 0; u < unosi.length; u++) {
                    // program za konvert " " u "_":
                    System.out.println("<<--------------------------------------->>");
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
                    System.out.println(">> Odaberite kategoriju vrste: ");
                    for(int kat=0; kat < kategorije.length;kat++){
                        System.out.println((kat+1) + ".) " + kategorije[kat]);
                    }
                    System.out.print("Odabir: ");
                    int kategorija = myObj.nextInt();
                    myObj.nextLine();

                    unosi[u] = new BirdUnos(nazivZnanstveni, nazivHrvatski, brojnost, spol, kategorija);
                }
                lokaliteti[l] = new Lokalitet(nazivL, date1, unosi);
            }
            int lokUnosiCounter = 0;
            for (Lokalitet lokalitet : lokaliteti) {
                lokUnosiCounter += lokalitet.countUnos(lokalitet);
            }
            System.out.println("Istrazivac je sveukupno unio " + lokUnosiCounter + " vrste na " + lokaliteti.length + " lokacije.");
            for (int x = 0; x < lokaliteti.length; x++) {
                System.out.println("'"+lokaliteti[x].getNazivLokacije()+"':");
                for (int y = 0; y < lokaliteti[x].countUnos(lokaliteti[x]);y++) {
                    System.out.println((y + 1) + ".) " + (lokaliteti[x].getUnosi()[y].getNazivLatinski()) + " " + " " + (lokaliteti[x].getUnosi()[y].getBrojnost()) + " " + lokaliteti[x].getUnosi()[y].getSpol());
                }
            }
            //ispis koliko je muzjaka, zenki i nepoznatog spola zabiljezeno kod vrsta:
            int[] cont2 = lokaliteti[0].countGender(lokaliteti);
            System.out.println(">> Ukupno je uneseno " + cont2[0] + " muzjaka, " + cont2[1] + " zenki i " + cont2[2] + " vrsta nepoznatog spola.");
            istrazivaci[i] = new IstrazivacUnosBuilder()
                    .setIme(ime)
                    .setPrezime(prezime)
                    .setInstitucija(institucija)
                    .setBroj(broj)
                    .setLokaliteti(lokaliteti)
                    .createIstrazivacUnos();
        }
    }
}
