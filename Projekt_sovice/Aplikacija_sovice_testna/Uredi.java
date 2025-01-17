 import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.Scanner;
 
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
class urejanje{
    public static Connection conn = null;
        
        
    public static void insert(String imeTabele, String ime, String priimek,String komentar){
        ZoneId zoneId= ZoneId.of("Europe/Paris");
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        DateTimeFormatter datum = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatiranDatum = zonedDateTime.format(datum);
        try {
            String driverName = "com.mysql.jdbc.Driver"; 
            Class.forName(driverName);
            conn = DriverManager.getConnection("jdbc:mysql://193.2.190.23:3306/remote11","remote", "remote"); //povezava do database
            System.out.println("Successfully Connected to the database!");System.out.println("INSERT INTO `remote11`.`"+imeTabele+"` (`ime`,`priimek`,`datum`, `komentar_1`) VALUES ('"+ime+"', '"+priimek+"', '"+formatiranDatum+"', '"+komentar+"');");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find the database driver " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Could not connect to the database " + e.getMessage());
        }
        try {
        Statement st = conn.createStatement();
        st.executeUpdate("INSERT INTO 'remote11' '" + imeTabele + "' ('ime','priimek','datum', 'komentar_1') VALUES ('" + ime + "', '" + priimek + "', '" + formatiranDatum + "', '" + komentar + "');");
        
        SaveAndLoad.shrani(ime, "Vstavi", "Podatki: " + ime + ", " + priimek + ", " + formatiranDatum + ", " + komentar);
        
        conn.close();
        }
        
        catch (SQLException e) {
                System.out.println("inserting was not successful " + e.getMessage());
        }
        
    }
    
    
    public static String prikaziTabelo(String imeTabele){
        Connection connection = null;
        String vsebina="";
        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            //ustvarjanje povezave z database
            connection = DriverManager.getConnection("jdbc:mysql://193.2.190.23:3306/remote11","remote", "remote");
        } catch (ClassNotFoundException q) {
            System.out.println("Could not find the database driver " + q.getMessage());
        } catch (SQLException q) {   
            System.out.println("Could not connect to the database " + q.getMessage());
        }
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM "+imeTabele+";"); 
        
            while (rs.next()) {
                String ime = rs.getString("ime");
                String priimek = rs.getString("priimek");
                String datum = rs.getString("datum");
                String komentar = rs.getString("komentar_1");
                vsebina += ime+", "+priimek+", "+datum+", "+komentar+"\n";
            }
        } 
        catch (SQLException l) {
            System.out.println("Could not get database metadata " + l.getMessage());
        }
        SaveAndLoad.shrani(LoginPage.userID, "Ogleduje", "podatki: " + vsebina);
        return vsebina;
    }
    
    
    public static void delete(String imeTabele, String ime, String priimek){
        try {
            String driverName = "com.mysql.jdbc.Driver"; 
            Class.forName(driverName);
            conn = DriverManager.getConnection("jdbc:mysql://193.2.190.23:3306/remote11","remote", "remote"); //povezava do database
            System.out.println("Successfully Connected to the database!");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find the database driver " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Could not connect to the database " + e.getMessage());
        }
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM `remote11`.`"+imeTabele+"` WHERE  `ime`='"+ime+"' AND `priimek`='"+priimek+"';");
            //DELETE FROM `remote11`.`ime tabele` WHERE  `ime`='ime tabele' AND `priimek`='ime tabele' AND `datum`='2021-02-14' AND `komentar_1`='ime tabele' LIMIT 1;
            conn.close();
        }
        
        catch (SQLException e) {
            System.out.println("Could not delete data from the database " + e.getMessage());
        }
        SaveAndLoad.shrani(LoginPage.userID, "Briše", "podatki: " + imeTabele + ", " + ime + ", " + priimek);
    }
    
    
    public static void update(String imeTabele, String stolpec, String vsebina, String imeSpr, String priimekSpr){
        try {
            String driverName = "com.mysql.jdbc.Driver"; 
            Class.forName(driverName);
            conn = DriverManager.getConnection("jdbc:mysql://193.2.190.23:3306/remote11","remote", "remote"); //povezava do database
            System.out.println("Successfully Connected to the database!");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find the database driver " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Could not connect to the database " + e.getMessage());
        }
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("UPDATE `remote11`.`"+imeTabele+"` SET `"+stolpec+"`='"+vsebina+"' WHERE  `ime`='"+imeSpr+"' AND `priimek`='"+priimekSpr+"';");
            conn.close();
        }
        
        catch (SQLException e) {
            System.out.println("Error updating record" + e.getMessage());
        }
        SaveAndLoad.shrani(LoginPage.userID, "Ažurura", "podatki: " + vsebina);
    }
    
    
    public static void ustvarTabelo(String ImeTabele){
        try {
            String driverName = "com.mysql.jdbc.Driver"; 
            Class.forName(driverName);
            conn = DriverManager.getConnection("jdbc:mysql://193.2.190.23:3306/remote11","remote", "remote"); //povezava do database
            System.out.println("Successfully Connected to the database!");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find the database driver " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Could not connect to the database " + e.getMessage());
        }
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("CREATE TABLE `remote11`.`"+ImeTabele+"` (`ime` VARCHAR(50) NULL DEFAULT NULL,`priimek` VARCHAR(50) NULL DEFAULT NULL,`datum` DATE  NULL DEFAULT NULL,`komentar_1` VARCHAR(50) NULL DEFAULT NULL)");
            conn.close();
        }
        
        catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
        SaveAndLoad.shrani(LoginPage.userID, "Ustvari", "ustvarjena " + LocalDate.now());
    }
    
    
  public static void izbrisiTabelo(String ImeTabele){
        try {
        String driverName = "com.mysql.jdbc.Driver"; 
        Class.forName(driverName);
        conn = DriverManager.getConnection("jdbc:mysql://193.2.190.23:3306/remote11","remote", "remote"); //povezava do database
        System.out.println("Successfully Connected to the database!");
    }catch (ClassNotFoundException e) {
        System.out.println("Could not find the database driver " + e.getMessage());
    } catch (SQLException e) {
        System.out.println("Could not connect to the database " + e.getMessage());
    }
    try {
    Statement st = conn.createStatement();
    st.executeUpdate("DROP TABLE `"+ImeTabele+"`;");
    conn.close();
    }
    
    catch (SQLException e) {
            System.out.println("Error deleting table: " + e.getMessage());
    }
    }
}



public class Uredi extends Application  {
    private StackPane root = new StackPane();
    private Stage stage;
@Override
    public void start(Stage primaryStage) {
       primaryStage.setTitle("aplikacija"); //naslov aplikacije

       Label povprasevanje =new Label("Povpraševanje: "); //tekst
       Label pomoc =new Label("Pomoč: ");
      String text="Da ustvariš tabelo, moraš zapisati ime tabele"+"\n"+"Da izbrišeš tabelo, moraš zapisati ime željene tabele (pomagaj si s prikazom imen vseh tabel)"+"\n"+" Da vstaviš podatke moraš zapisati ime tabele ter željeno vstavljeno ime, priimek, komentar,(datum se vstavi avtomatsko)"+"\n"+"Za posodablanje podatkov vpišeš ime tabele, v kateri želiš posodabljati, ime stolpca v kateremu želiš posodobiti vsebino(npr., ime,priimek,datum,komenter_1), vsebino katera bo spremenjena ter ime in priimek, da definiraš katero vrstico v stolpcu boš posodobil."+"\n"+"Za brisanje podatkov vneseš ime tabele, iz katere bi želeli izbrisati vrstico ter ime in priimek, v tej vrstici.) ";
      Label tekst =new Label(text);
         // creating buttons 
        Label prikaziText =new Label("");
        
        
        Button ustvari = new Button("Ustvari tabelo");// nov gumb 
            TextField imeTabele= new TextField("ime tabele");//oknček za vpisovanje teksta
            
        Button izbrisi = new Button("Izbriši tabelo");// nov gumb
            TextField imeIzbris= new TextField("ime tabele");//oknček za vpisovanje teksta
            
        Button vstavi = new Button("vstavi podatke v tabelo");// nov gumb
            TextField imeTabele1= new TextField("ime tabele");//oknček za vpisovanje teksta
            TextField ime= new TextField("ime");//oknček za vpisovanje teksta
            TextField priimek= new TextField("priimek");//oknček za vpisovanje teksta
            TextField komentar= new TextField("komentar");//oknček za vpisovanje teksta
            
        Button posodobi = new Button("Posodobi podatke v tabeli");// nov gumb
            TextField imePosodobi= new TextField("ime tabele");//oknček za vpisovanje teksta
            TextField stolpec= new TextField("ime stolpca");//oknček za vpisovanje teksta
            TextField vsebina= new TextField("spremenjena vsebina");//oknček za vpisovanje teksta
            TextField imeSpr= new TextField("ime vrstice");//oknček za vpisovanje teksta
            TextField priimekSpr= new TextField("priimek vrstice");//oknček za vpisovanje teksta
            
        Button brisi = new Button("Izbriši podatke iz tabele");// nov gumb
             TextField imeTBR= new TextField("ime tabele");//oknček za vpisovanje teksta
            TextField imeBR= new TextField("ime stolpca");//oknček za vpisovanje teksta
            TextField priimekBR= new TextField("priimek stolpca");
            
        Button vsebinaP = new Button("prikaži vsebino tabele");// nov gumb
            TextField imeVsebina= new TextField("ime tabele");
            Label prikaziV =new Label("");
            
        Button prikazi = new Button("prikaži vsebino database");// nov gumb
       
        
        // creating Gridpane object 
        GridPane gridPaneG = new GridPane(); // glavno okno, ki vsebuje vsa "podokna" in tekst s pomočjo
        GridPane gridPaneL = new GridPane();// "podokno" na levi strani, katerega del so vsi gumbi in vpisna polja (urejanje)
        
        gridPaneL.add(povprasevanje,0,1); //dodajanje v levo "podokno"
        gridPaneL.add(ustvari,0,2);     //dodajanje v levo "podokno"
            gridPaneL.add(imeTabele,1,2); //dodajanje v levo "podokno"
        gridPaneL.add(izbrisi,0,3); //dodajanje v levo "podokno"
            gridPaneL.add(imeIzbris,1,3); //dodajanje v levo "podokno"
        gridPaneL.add(vstavi,0,4); //dodajanje v levo "podokno"
           gridPaneL.add(imeTabele1,1,4); //dodajanje v levo "podokno"
           gridPaneL.add(ime,2,4); //dodajanje v levo "podokno"
           gridPaneL.add(priimek,3,4); //dodajanje v levo "podokno"
           gridPaneL.add(komentar,4,4);//dodajanje v levo "podokno" 
        gridPaneL.add(posodobi,0,5);//dodajanje v levo "podokno"
             gridPaneL.add(imePosodobi,1,5);//dodajanje v levo "podokno"
             gridPaneL.add( stolpec,2,5);//dodajanje v levo "podokno"
             gridPaneL.add( vsebina,3,5);//dodajanje v levo "podokno"
             gridPaneL.add(imeSpr,4,5);//dodajanje v levo "podokno"
             gridPaneL.add(priimekSpr,5,5);//dodajanje v levo "podokno"
        gridPaneL.add(brisi,0,6);//dodajanje v levo "podokno"
            gridPaneL.add(imeTBR,1,6);//dodajanje v levo "podokno"
            gridPaneL.add(imeBR,2,6);//dodajanje v levo "podokno"
            gridPaneL.add(priimekBR,3,6);//dodajanje v levo "podokno"
        gridPaneL.add(vsebinaP,0,7);
            gridPaneL.add(imeVsebina,1,7);
             gridPaneL.add(prikaziV,0,8);
        gridPaneL.setVgap(10);//Sets the vertical gap between components to the specified value.
        
       GridPane gridPane = new GridPane(); //novo okno (desna stran z prikazom imena tabel)
       gridPane.add(prikazi,0,2); //dodajanje v okno
       gridPane.add(prikaziText,0,3); //dodajanje v okno
       
       gridPaneG.add(tekst,1,3);//dodajanje v okno
       gridPaneG.add(gridPaneL,1,1);//dodajanje v okno
       gridPaneG.add(gridPane,2,1);//dodajanje v okno
       gridPaneG.add(pomoc,1,2);//dodajanje v okno
       
       
        //eventi za klike na gumb

         EventHandler<ActionEvent> event0 = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
      Connection connection = null;
      try {
      String driverName = "com.mysql.jdbc.Driver";
      Class.forName(driverName);
      //ustvarjanje povezave z database
      connection = DriverManager.getConnection("jdbc:mysql://193.2.190.23:3306/remote11","remote", "remote");
        } catch (ClassNotFoundException q) {
      System.out.println("Could not find the database driver " + q.getMessage());
        } catch (SQLException q) {   
      System.out.println("Could not connect to the database " + q.getMessage());
        }
        try {
      // pridobivanje podatkov
      DatabaseMetaData metadata = connection.getMetaData();
      // specifične želje objektov; tabele
      String[] types = {"TABLE"};
      ResultSet resultSet = metadata.getTables(null, null, "%", types);
      String txtTable="";
      while (resultSet.next()) {
        String tableName = resultSet.getString(3); 
        txtTable += tableName+"\n";
        System.out.println("Table : " + tableName );
      }
      prikaziText.setText(txtTable);
      } catch (SQLException l) {
      System.out.println("Could not get database metadata " + l.getMessage());
        }
      }
    }; 
            prikazi.setOnAction(event0);  
            
    
           /*#za ustvarjanje tabele*/
            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
                public void handle(ActionEvent e) 
                { 
                 String ime= String.valueOf(imeTabele.getText()); //branje iz vpisnega okenca
                 urejanje.ustvarTabelo(ime.toString()) ;
                } 
            }; 
            ustvari.setOnAction(event);  // klicanje eventa ob pritisku na gumb
            
            
            
           /*#za brisanje tabele*/
           EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() { 
                public void handle(ActionEvent e) 
                { 
                 String ime1= String.valueOf(imeIzbris.getText());//branje iz vpisnega okenca
                 urejanje.izbrisiTabelo(ime1.toString()) ;
                } 
            }; 
            izbrisi.setOnAction(event1);  // klicanje eventa ob pritisku na gumb
            
            
            
            /*#za vstavlanje podatkov v tabelo*/ 
           EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() { 
                public void handle(ActionEvent e) 
                { 
                    String imeTabele= String.valueOf(imeTabele1.getText());//branje iz vpisnega okenca
                    String ime1= String.valueOf(ime.getText());//branje iz vpisnega okenca
                    String priimek1= String.valueOf(priimek.getText());//branje iz vpisnega okenca
                    String komentar1= String.valueOf(komentar.getText());//branje iz vpisnega okenca
                 urejanje.insert(imeTabele,ime1,priimek1,komentar1);
                } 
            }; 
            vstavi.setOnAction(event2);  // klicanje eventa ob pritisku na gumb
            
            
            
             /*#za posodablanje podatkov v tabelo*/
           EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() { 
                public void handle(ActionEvent e) 
                { 
                    String tabela= String.valueOf(imePosodobi.getText());//branje iz vpisnega okenca
                    String stolpec1= String.valueOf(stolpec.getText());//branje iz vpisnega okenca
                    String vsebina1= String.valueOf(vsebina.getText());//branje iz vpisnega okenca
                    String sprIme= String.valueOf(imeSpr.getText());//branje iz vpisnega okenca
                    String sprPriimek= String.valueOf(priimekSpr.getText());//branje iz vpisnega okenca
                 urejanje.update(tabela,stolpec1,vsebina1,sprIme,sprPriimek) ;
                } 
            }; 
            posodobi.setOnAction(event3);  // klicanje eventa ob pritisku na gumb
            
             /*#za brisanje podatkov v tabeli*/
           EventHandler<ActionEvent> event4 = new EventHandler<ActionEvent>() { 
                public void handle(ActionEvent e) 
                { 
                    String tabelaIme= String.valueOf(imeTBR.getText());//branje iz vpisnega okenca
                    String imebr= String.valueOf(imeBR.getText());//branje iz vpisnega okenca
                    String priimekbr= String.valueOf(priimekBR.getText());//branje iz vpisnega okenca
                    urejanje.delete(tabelaIme,imebr,priimekbr) ;
                } 
            }; 
            brisi.setOnAction(event4);  // klicanje eventa ob pritisku na gumb
            
            /*#za prikazovanje vsebine tabele*/
            EventHandler<ActionEvent> event5 = new EventHandler<ActionEvent>() { 
                public void handle(ActionEvent e) 
                { 
                 String ime= String.valueOf(imeVsebina.getText()); //branje iz vpisnega okenca
                 prikaziV.setText(urejanje.prikaziTabelo(ime.toString()));
                } 
            }; 
            vsebinaP.setOnAction(event5);  // klicanje eventa ob pritisku na gumb
  
           gridPaneL.setHgap(30); 
           gridPaneL.setVgap(30); //razmik med elementi
           primaryStage.getIcons().add(new Image("sovica03.png"));//slikica sovice v skrajnem levem kotu aplikacije

            Scene scene = new Scene(gridPaneG, 1600, 700); 
            primaryStage.setScene(scene); 
            primaryStage.show(); 
        }
    public Uredi(String [] args)
        {
            Stage stage = new Stage();
            start(stage);
            launch(args);
        }
    
}