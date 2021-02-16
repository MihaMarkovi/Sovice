import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Container;
import java.io.*;
import javax.imageio.*;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

/*
 * Okno namenjeno 'prihodu' v program. Uporabnik ima na voljo 2 možnosti. Če nima 'računa', ga lahko ustvari. Če ga ima, se lahko samo prijavi.
 * Ob pritiski na ustrezen gumb se zažene ustrezno okno.
 */
public class Pozdravna implements ActionListener {
    JFrame frame;
    JButton registracijaKnof;
    JButton prijavaKnof;
    JLabel pozdrav;

   BufferedImage img;

    public Pozdravna() {
        frame = new JFrame("Pozdravna stran!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //da se zapre ko klikns x
        frame.setSize(500,500);                                 //velikost okvirja
        frame.getContentPane().setBackground(new Color(160,165,230));
        frame.setResizable(false);                              //okno je fiksne velikosti
        frame.setResizable(false);

        ImageIcon ikona = new ImageIcon("sovica03.png"); //ustvari ikono 
        frame.setIconImage(ikona.getImage());   // ikonca frame-a
        ImageIcon sovica = new ImageIcon("sovica03.png"); 

 /** pozdravni message */ 

  /*# pozdrav - (!) poravnava, da je vse na sredni */ 
        JLabel pozdrav = new JLabel("Pozdravljen v Sovicah!"); //JLabel.CENTER     
        //pozdrav.setVerticalAlignment(JLabel.CENTER);
        //pozdrav.setHorizontalAlignment(JLabel.CENTER);
        // JLabel pozdrav = new JLabel("Pozdravljen v Sovicah!",sovica,JLabel.CENTER); //JLabel.CENTER

        pozdrav.setFont(new Font("Comic Sans",Font.ITALIC, 25));
        pozdrav.setBounds(100, 100, 300, 50);

 //sovica.setBounds(100, 175, 300, 250));
 /** knofi **/
registracijaKnof = new JButton("Registracija");

        registracijaKnof.setBounds(50, 350, 200, 50);
        registracijaKnof.setFocusable(false);
        registracijaKnof.addActionListener(this);
        prijavaKnof = new JButton("Prijava");
        prijavaKnof.setBounds(255, 350, 200, 50);
        prijavaKnof.setFocusable(false);
        prijavaKnof.addActionListener(this);

        JLabel image = new JLabel();
        image.setBounds(150, 150, 200, 175);

        img = null;
        try {
            img = ImageIO.read(new File("sovica03.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imgIcon = new ImageIcon(dimg);
        image.setIcon(imgIcon);

        /**------dodajanje na frame---------**/

        frame.add(image);
        frame.add(pozdrav); 
        frame.add(registracijaKnof);
        frame.add(prijavaKnof); 

        frame.setLocationRelativeTo(null);                      //odpre na sredini
  frame.setLayout(null);                                         
        frame.setVisible(true);                                 //da se prižge

    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == registracijaKnof) {
            RegisterPage reg = new RegisterPage();               //odpremo RegisterPage
    }

        if(e.getSource() == prijavaKnof) {
            frame.dispose(); 
            LoginPage log = new LoginPage(IDandPasswords.getLoginInfo());    //odpremo LoginPage in ji damo hashmap imen in gesel
  }
    }
}
