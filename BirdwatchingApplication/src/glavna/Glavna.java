package glavna;

import entiteti.unos.BirdUnos;

import java.util.Scanner;

public class Glavna {
    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object


        BirdUnos[] unosi = new BirdUnos[3];

        for(int i = 0; i < unosi.length;i++) {
            System.out.print("Unesite znanstveni naziv ptice: ");
            String nazivZnanstveni = myObj.nextLine();
            System.out.print("Unesite hrvatski naziv ptice: ");
            String nazivHrvatski = myObj.nextLine();
            System.out.print("Unesite brojnost: ");
            Integer brojnost = myObj.nextInt();
            myObj.nextLine();
            System.out.print("Unesite spol vrste (M/F): ");
            String spol = myObj.nextLine();

            unosi[i] = new BirdUnos(nazivZnanstveni, nazivHrvatski, brojnost, spol);
        }
            for(int i = 0 ; i < unosi.length;i++){
                System.out.println("Unesen je podatak: "+(unosi[i].getNazivLatinski())+ " " +(unosi[i].getNazivHrvatski())+" "+(unosi[i].getBrojnost())+" "+unosi[i].getSpol());
            }
    }

}
