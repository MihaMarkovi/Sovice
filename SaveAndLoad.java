/**
 * Write a description of class Shranjevanje here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class SaveAndLoad
{
    public static void main(String[] args)
    {
        shrani("test", "shranjujem", "podatki");
    }
    
    public static void shrani(String ime, String akcija, String podatki)
    {
        try
        {
            File file = new File("Shranjeni_podatki.txt");
            if(!file.exists())
            {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter("Shranjeni_podatki.txt", true);
            fw.write(akcija + ":" + ime + ":" + LocalDate.now() + ":" + podatki + "\n");
            fw.close();
        }
        catch(IOException e)
        {
            System.out.println("an error has occurred");
        }
    }
    
    public static void dodaj(String podatki)
    {
        
    }
    
    public static String[] dobiVen(String iskanje)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("Shranjeni_podatki.txt"));
            int i = 0;
            String line = br.readLine();
            while(line != null)
            {
                if(line.contains(iskanje))
                {
                    i++;
                }
                line = br.readLine();
            }
            String[] odgovor = new String[i];
            br.mark(0);
            i = 0;
            line = br.readLine();
            while(line != null)
            {
                odgovor[i] = line;
                line = br.readLine();
                i++;
            }
            br.close();
            return odgovor;
        }
        catch(IOException e)
        {
            return null;
        }
    }
}
