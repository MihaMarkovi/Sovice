import javax.swing.*;    
import java.awt.event.*;
import java.util.ArrayList;
import java.sql.*;

public class ComboBoxExample {
JFrame f;

    ComboBoxExample(){    
        f = new JFrame("ComboBox Example");   
        
        final JLabel label = new JLabel();          
        label.setHorizontalAlignment(JLabel.CENTER);  
        label.setSize(400, 100);
        
        JTextArea txtArea = new JTextArea();
        //txtArea.setEditable(false);
        txtArea.setBounds(50, 200, 450, 3000);
        JScrollPane scrollPane = new JScrollPane(txtArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(50, 200, 450, 300);
        
        JButton b = new JButton("Prikaži");
        b.setBounds(420, 100, 75, 20);
        
        String[] languages = {"kdo je kreiral tabelo, kdaj je bila kreirana, kdaj odstranjena","kdo je vstavljal podatke, kdaj, katere podatke","kdo je ogledoval podatke, kdaj, katere","kdo je ažuriral podatke, kdaj, katere","kdo je brisal podatke, kdaj katere"};        
        final JComboBox cb = new JComboBox(languages);
        cb.setBounds(50, 100, 350, 20);
        
        f.add(cb);
        f.add(label);
        f.add(b);
        //f.add(txtArea);
        f.add(scrollPane);
        
        f.setLayout(null);
        f.setSize(550, 550);
        f.setVisible(true);
        
        b.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {       
                String data = "Povpraševanje za: "   + cb.getItemAt(cb.getSelectedIndex())+ ", so:";  
                label.setSize(500,300);
                label.setText(data);
                String vprasanje = "";
                txtArea.selectAll();
                txtArea.replaceSelection("");
                
                ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
                backend.main(new String[0]);
                if(cb.getItemAt(cb.getSelectedIndex()).equals("kdo je kreiral tabelo, kdaj je bila kreirana, kdaj odstranjena"))
                {
                    vprasanje = "Ustvari";
                    try{
                        array = backend.getCreated();
                    }catch(SQLException exc){}
                }
                if(cb.getItemAt(cb.getSelectedIndex()).equals("kdo je vstavljal podatke, kdaj, katere podatke"))
                {
                    vprasanje = "Vstavi";
                    try{
                        array = backend.getInserted();
                    }catch(SQLException exc){}
                }
                if(cb.getItemAt(cb.getSelectedIndex()).equals("kdo je ogledoval podatke, kdaj, katere"))
                {
                    vprasanje = "Ogleduje";
                    try{
                        array = backend.getSelected();
                    }catch(SQLException exc){}
                }
                if(cb.getItemAt(cb.getSelectedIndex()).equals("kdo je ažuriral podatke, kdaj, katere"))
                {
                    vprasanje = "Ažurira";
                    try{
                        array = backend.getUpdated();
                    }catch(SQLException exc){}
                }
                if(cb.getItemAt(cb.getSelectedIndex()).equals("kdo je brisal podatke, kdaj katere"))
                {
                    vprasanje = "Briše";
                    try{
                        array = backend.getDroped();
                    }catch(SQLException exc){}
                }
                
                /*
                String[] podatki = SaveAndLoad.dobiVen(vprasanje);
                for(int i = 0; i < podatki.length; i++)
                {
                    txtArea.append(podatki[i] + "\n");
                }*/
                for(int i = 0; i < array.size(); i++)
                {
                    txtArea.append(array.get(i) + "\n");
                    System.out.println(array.get(i));
                }
            }  
        });
    }  


    public static void main(String[] args) {    
        new ComboBoxExample();         
    }    
}  
