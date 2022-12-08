package glavna;

import entiteti.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import iznimke.NeispravanUnos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.plaf.nimbus.NimbusStyle;


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
                    logger.info("Unesena je vrijdenost: " + broj);
                    tocno=false;
                }
                catch(InputMismatchException e){
                    logger.info(e.getMessage(),e);
                    System.out.println("Unesena je pogresna vrijednost za telefonski broj!");
                }
                myObj.nextLine();
            }

            boolean tocno2 = true;
            int lokalitetiNum = 1;
            while (tocno2){
                try{
                    System.out.print("Za koliko lokacija zelite unijeti nalaze? (min. 1): ");
                    lokalitetiNum = myObj.nextInt();
                    logger.info("Unesen broj za lokalitete je: " + lokalitetiNum);
                    tocno2=false;
                } catch (InputMismatchException e){
                    logger.info(e.getMessage(), e);
                    System.out.println("Unesena je pogresna vrijednost za broj lokaliteta!");
                }
                myObj.nextLine();
            }
            Lokalitet[] lokaliteti = new Lokalitet[lokalitetiNum];
            BirdUnos[] unosi = new BirdUnos[0];
            for (int l = 0; l < lokaliteti.length; l++) {
                System.out.print("Unesite naziv " + (l + 1) + ". lokacije: ");
//                myObj.nextLine();
                String nazivL = myObj.nextLine();

                boolean tocno3 = true;
                Date date2 = null;
                while (tocno3) {
                    try {
                        System.out.print("Unesite datum istrazivanja (dd.mm.yyyy.): ");
                        String datumL = myObj.nextLine();
                        date2 = new SimpleDateFormat("dd.MM.yyyy.").parse(datumL);
                        logger.info("Unesen je datum: " + date2);
                        tocno3=false;
                    } catch (ParseException e) {
                        logger.info(e.getMessage(), e);
                        System.out.println("Unesena je pogresna vrijednost datuma!");
                    }
                }

                Date date1 = date2;

                int numObservation = 0;
                //kasnije napraviti da se unosi dok se ne unese odredjeni znak (ili tome nesto slicno)
                boolean tocno4 = true;
                while(tocno4){
                    try{
                        System.out.print("Koliko zelite unijeti opservacija? (min. 5): ");
                        numObservation = myObj.nextInt();
                        tocno4=false;
                    }catch (InputMismatchException e){
                        logger.info(e.getMessage(), e);
                        System.out.println("Unesena je pogesna vrijednost za broj unosa!");
                    }
                    myObj.nextLine();
                }
                String nazivZnanstveni = null;
                String nazivHrvatski = null;
                unosi = new BirdUnos[numObservation];
                //unosi opservacija
                for (int u = 0; u < unosi.length; u++) {
                    // program za konvert " " u "_":
                    System.out.println("<<--------------------------------------->>");

                    boolean tocno5 = true;
                    while(tocno5){
                        try{
                            System.out.print("Unesite hrvatski naziv ptice: ");
                            nazivHrvatski = myObj.nextLine();
                            String naziv2 = Character.toLowerCase(nazivHrvatski.charAt(0)) + nazivHrvatski.substring(1)
                                    .replaceAll(" ", "_").toLowerCase();
                            Nomenklatura test = Nomenklatura.valueOf(naziv2);
                            // trazi po enum postoji li uneseni 'hrvatski naziv ptice' kako bi se dodao znanstveni naziv
                            // kasnije dodati Exception koji javlja da nije dobro uneseno, tj. da se pokusa ponovno...
                            //i ako je vec unesena vrsta da se ne moze unijeti opet u istom sessionu unosa...
                            nazivZnanstveni = null;
                            for (Nomenklatura nom : Nomenklatura.values()) {
                                if (nom.equals(test)) {
                                    nazivZnanstveni = nom.getVrsta();
                                }
                            }
                            tocno5 = false;
                        } catch (IllegalArgumentException e){
                            logger.info(e.getMessage(),e);
                            System.out.println("Unesena je nepostojeca vrijednost za vrstu!");
                        }
//                        myObj.nextLine();
                    }

                    boolean tocno6=true;
                    int brojnost = 0;
                    while(tocno6){
                        try{
                            System.out.print(">> Unesite brojnost: ");
                            brojnost = myObj.nextInt();
                            tocno6=false;
                        }catch (InputMismatchException e){
                            logger.info(e.getMessage(), e);
                            System.out.println("Unesena je pogesna vrijednost za brojnost!");
                        }
                        myObj.nextLine();
                    }

                    boolean tocno7=true;
                    String spol = null;
                    while(tocno7){
                        try{
                            System.out.print(">> Unesite spol vrste (M/F/U): ");
                            spol = myObj.nextLine().toUpperCase();
                            unesiSpol(spol);
                            tocno7=false;
                        }catch (NeispravanUnos e){
                            System.out.println("Nemam pojma");
                        }
//                        myObj.nextLine();
                    }
                    System.out.println(">> Odaberite kategoriju vrste: ");
                    for (int kat = 0; kat < kategorije.length; kat++) {
                        System.out.println((kat + 1) + ".) " + kategorije[kat]);
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
    private static void unesiSpol(String spol) throws NeispravanUnos{ //unchecked exception
        if(!spol.equals("M")){
            throw new NeispravanUnos("Fucked up mate");
        } else{
            System.out.println("Sve je ok!");
        }
    }
}
