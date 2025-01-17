import java.util.HashMap;

public class IDandPasswords {
    static HashMap<String, String> logininfo = new HashMap<String, String>();

    IDandPasswords() {      
        logininfo.put("remote", "remote");  //default za vpis
        logininfo.put("user", "password");      
    }

    public static void addIDAndPass(String userID, String password2)// doda v hashmap iz register
    {
        logininfo.put(userID, password2);
        // izpisiGesla();     //namenjeno testiranju napak --> lažje je videt kje je problem če vidiš kakšna gesla 'delujejo'
        //je potencialno nevarno, zato bi se ta del moral pri dejanski aplikaciji odstranit/zavarovat, da ne bo imel vsak možnosti izpisat vseh gesl
    }

    /*
     * getter za HashMap. Protected, ker so sensitive podatki not
     */
    protected static HashMap getLoginInfo() {
        return logininfo;
    }
        /** izpis gesel - pazljivo s tem**/
            /* public static void izpisiGesla() {
            for(String i: logininfo.keySet()) {
            System.out.print(i+"\t" +"= ");
            System.out.println(logininfo.get(i));
            }
            }
             */
}
