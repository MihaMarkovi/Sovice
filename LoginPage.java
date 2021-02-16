import java.util.HashMap;               /** importanje **/
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
import javafx.application.*;

public class LoginPage implements ActionListener {  

    JFrame frame = new JFrame("Prijavna stran");    //naslov okna

    JLabel messageLabel = new JLabel();
    JTextField userIDField = new JTextField();   
    JPasswordField userPasswordField = new JPasswordField();

    JButton loginButton = new JButton("Login");         //ustvarimo 2 knofa in 2 labela
    JButton resetButton = new JButton("Reset");
    JButton RegisterButton = new JButton("Registriraj se");

    JLabel userIDLabel = new JLabel("Uporabik:");
    JLabel userPasswordLabel = new JLabel("Geslo:");
    JLabel registerLabel = new JLabel("Včlani se: ");

    
    JLabel naslov = new JLabel("Prijavite se");

    
    public static String userID = "";
    ImageIcon image = new ImageIcon("sovica03.png"); //ustvari ikono
    HashMap<String,String> logininfo = new HashMap<String,String>();
    /** konstruktor za hashmap **/
    LoginPage(HashMap<String, String> loginInfoOriginal) {

        logininfo = loginInfoOriginal; 
        userIDLabel.setBounds(50,100,75,25);
        userPasswordLabel.setBounds(50,150,75,25);

        /** message label **/    

        messageLabel.setBounds(125, 250, 250, 35);               //postaviš
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));   //določiš font

        naslov.setBounds(150,20,200,50);
        naslov.setFont(new Font("Calibri", Font.PLAIN, 25));    //izlepšaj

        userIDField.setBounds(125,100,200,25);                   //kamor uporabnik vnese
        userPasswordField.setBounds(125,150,200,25);             //kamor uporabnik vnese

        /**# login button **/
        loginButton.setBounds(125, 200, 100, 25);
        loginButton.setFocusable(false);  //da se ne pojavi kvadratek ko kliknes na njega
        loginButton.addActionListener(this);

        resetButton.setBounds(225, 200, 100, 25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        /*# dodajanje gor **/
        
        frame.add(naslov);
        frame.add(userIDLabel);                     //na ekran dodaš 2 labela.
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(registerLabel);

        frame.add(userIDField);
        frame.add(userPasswordField);

        frame.add(loginButton);
        frame.add(resetButton);
        frame.setIconImage(image.getImage()); 

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //da se zapre ko klikns x
        frame.setSize(420,420);                                 //velikost okvirja
        frame.setLayout(null);                                  //odpre se na sredini ekrana
        frame.setVisible(true);                                 //da se vidi
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /** Reset button **/
        if (e.getSource() == resetButton) {     //če je bil pritisnjen reset
            userIDField.setText("");            //pobriši oba polja
            userPasswordField.setText("");
        }

        /** Login button **/
        if (e.getSource()==loginButton) {
            userID = userIDField.getText();                  //kar podas v polje shrani v userID in password
            String password = String.valueOf(userPasswordField.getPassword());    //dobi password, pretvori v string in shrani password
            /** preveri, ali se tvoji podatki ujemajo s tistimi v HashMap-u **/
            if(logininfo.containsKey(userID))  {                //Username pravilen
                if(logininfo.get(userID).equals(password)) {    //geslo pravilno
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login successful");

                    frame.dispose();    //da se ugasne login page

                    Platform.runLater(new Runnable(){
                            @Override
                            public void run()
                            {
                                aplikacija2 app = new aplikacija2();    //da se prižge aplikacija2
                            }
                        });
                }
                else {
                    messageLabel.setForeground(Color.red);     //username taprav ampak geslo napačno
                    messageLabel.setText("Wrong password!");
                }
            }
            else {
                messageLabel.setForeground(Color.red);          //username-a ni v HashMap-u
                messageLabel.setText("Username not found!");
            }
        }
    }
}
