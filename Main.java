//Urejeno in komentirano

//koda:

import javax.swing.ImageIcon;

/*
 * To je 'start' programa. Glavna metoda ustvari ikono (ker je tako lepše) in zažene pozdravno okno.
 */public class Main
 {
    public static void main(String[] arggs) {
       ImageIcon image = new ImageIcon("sovica03.png"); //ustvari ikono
       IDandPasswords idandPasswords = new IDandPasswords();  
       
       Pozdravna poz = new Pozdravna();
    }
}
