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


// OKNO// 
public class aplikacija2 extends Application  {
    private StackPane root = new StackPane();
    private Stage stage;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("aplikacija"); //naslov aplikacije

        Label lbl1 = new Label("Prijavljen si v:");
        Label lbl2 =new Label("Strežnik");
        TextField streznik= new TextField("193.2.190.23  ");
        Label lbl3 = new Label("Vrata");
        TextField vrata =new TextField("3306");
        Label lbl4 = new Label("Tabela");
        TextField tabela= new TextField("remote11");
        Label lbl5 = new Label("Uporabnik");
        TextField uporabnik= new TextField("remote");
        Label lbl6 =  new Label("Geslo");
        TextField geslo= new TextField("remote");
      
        // creating buttons 
        Button povprasevanje = new Button("Povprašuj"); 
        Button urejaj = new Button("Urejaj"); 
        Label opozorilo=new Label(" ");
        opozorilo.setTextFill(Color.web("#ff0000"));
       
        GridPane gridPaneG= new GridPane();// ta vsebuje oba podgridpane-a
        
        // creating Gridpane object 
        GridPane gridPaneL = new GridPane();
          //kakor tabelna mreža
        gridPaneL.add(povprasevanje,0,2);
        gridPaneL.add(urejaj,0,4);
        gridPaneL.setVgap(10);
        
        GridPane gridPane = new GridPane();
        //desn gridpane
        gridPane.add(lbl1,1,0);
        gridPane.add(lbl2,1,1);
        gridPane.add(streznik,2,1);
        gridPane.add(lbl3,1,2);
        gridPane.add(vrata,2,2);
        gridPane.add(lbl4,1,3);
        gridPane.add(tabela,2,3);
        gridPane.add(lbl5,1,4);
        gridPane.add(uporabnik,2,4);
        gridPane.add(lbl6,1,5);
        gridPane.add(geslo,2,5);
        gridPane.add(opozorilo,2,7);
        gridPane.setStyle("-fx-background-color:#c7c7c7;");
        gridPane.setVgap(10);
         
        Label cheat =new Label(" ");
        gridPaneG.add(cheat,0,0);
        gridPaneG.add(gridPaneL,1,1);
        gridPaneG.add(gridPane,2,1);
        
        gridPaneG.setHgap(30); 
        gridPaneG.setVgap(30); //razmik med elementi
        primaryStage.getIcons().add(new Image("sovica03.png"));

        // event za povpraševanje
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                
                new ComboBoxExample(); 
                
            } 
        };
        povprasevanje.setOnAction(event);  // klicanje eventa ob pritisku na gumb
        
          // event za urejanje
        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
               Uredi okno =new Uredi(new String [6] );
          
            } 
        }; 
        urejaj.setOnAction(event1);  // klicanje eventa ob pritisku na gumb
 
        Scene scene = new Scene(gridPaneG, 800, 600); 
        primaryStage.setScene(scene); 
        primaryStage.show(); 
    }


    public static void main(String[] args) {
       launch(args);
    }
    
    public aplikacija2()
    {
        Stage stage = new Stage();
        start(stage);
    }
}