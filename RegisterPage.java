import java.io.FileWriter;
import java.io.IOException;
//import java.io.


import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class RegisterPage implements ActionListener {

    /** estetika **/
    JFrame frame = new JFrame("Registracija");
    ImageIcon image = new ImageIcon("sovica03.png"); //ustvari ikono

    JLabel userIDLabel = new JLabel("Vnesite ime :");
    JLabel passwordLabel = new JLabel("Vnesite geslo :");
    JLabel ponovnoPasswordLabel = new JLabel("Ponovite geslo :");
    JLabel naslov = new JLabel("Ustvarite račun");
    JLabel passNope;
    JLabel passYop;

    JTextField userIDField = new JTextField("");
    JPasswordField paswordField = new JPasswordField();
    JPasswordField ponovnoPasswordField  =new JPasswordField();

    JButton shraniKn = new JButton("Shrani");
    JButton zbrisiKn = new JButton("Počisti");

    public RegisterPage() {
        naslov.setBounds(150,20,200,50);
        naslov.setFont(new Font("Calibri", Font.PLAIN, 25));    //izlepšaj

        userIDLabel.setBounds(30,100,100,25);
        passwordLabel.setBounds(30,150,100,25);
        ponovnoPasswordLabel.setBounds(30,200,100,25);             

        passYop = new JLabel("Uporabnik shranjen!");         //passNope
        passYop.setBounds(30, 300, 200, 25);
        passYop.setVisible(false);
        passYop.setForeground(Color.GREEN);
        passYop.setFont(new Font("Calibri", Font.PLAIN, 18));

        passNope = new JLabel("Gesli se ne ujemata!");         //passNope
        passNope.setBounds(30, 300, 200, 25);
        passNope.setVisible(false);
        passNope.setForeground(Color.RED);
        passNope.setFont(new Font("Calibri", Font.PLAIN, 22));

        userIDField.setBounds(125,100,200,25);
        paswordField.setBounds(125,150,200,25);
        ponovnoPasswordField.setBounds(125,200,200,25);

        /*# knof **/

        /** shrani **/
        shraniKn.setBounds(30, 250, 100, 25);
        shraniKn.setFocusable(false);
        shraniKn.addActionListener(this);
        /** zbrisi **/
        zbrisiKn.setBounds(255,250 , 100, 25);
        zbrisiKn.setFocusable(false);
        zbrisiKn.addActionListener(this);

        /** dodajanje **/
        frame.add(naslov);
        frame.add(userIDLabel);
        frame.add(passwordLabel);
        frame.add(ponovnoPasswordLabel);
        frame.add(passNope);
        frame.add(passYop);

        frame.add(userIDField);
        frame.add(paswordField);
        frame.add(ponovnoPasswordField);

        frame.add(shraniKn);
        frame.add(zbrisiKn);

        //frame.setIconImage(sovica.getImage());
        frame.setSize(420,420);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /** Reset **/    //dela
        if (e.getSource() == zbrisiKn) {     //če je bil pritisnjen reset
            userIDField.setText("");            //pobriši oba polja
            paswordField.setText(""); 
            ponovnoPasswordField.setText(""); 
            passNope.setVisible(false);         //reset napisa
            passYop.setVisible(false);

        }
        if ((e.getSource()==shraniKn)){
            String userID = userIDField.getText();                      //podano se shrani v userID in password
            String password = String.valueOf(paswordField.getPassword());                                   //dobi password, pretvori v string in shrani password
            String password2 = String.valueOf(ponovnoPasswordField.getPassword());
            if(password.equals(password2)) {            /** passworda se ujemata**/
                IDandPasswords.addIDAndPass(userID, password2); /** shrani v hashmap **/
                /** napiše Nope al Yop **/
                passNope.setVisible(false);     //da se ne prekrije
                passYop.setVisible(true);
                
                frame.dispose();
            }
            else {  /** se ne ujemata **/
                System.out.println("Preverite vnešeno geslo...");
                passYop.setVisible(false);
                passNope.setVisible(true);
            }
        }

    }
}