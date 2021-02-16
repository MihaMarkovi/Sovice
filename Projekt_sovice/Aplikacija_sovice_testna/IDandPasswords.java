import java.util.HashMap;

public class IDandPasswords {
    static HashMap<String, String> logininfo = new HashMap<String, String>();
    //String userID;
    //String password2;
    IDandPasswords() {
        logininfo.put("test","test");   //testno
    }
    public static void addIDAndPass(String userID, String password2)// doda v hashmap iz register
    {
       logininfo.put(userID, password2);
       izpisiGesla();
    }

    /*
     * getter za HashMap. Protected, ker so sensitive podatki not
     */
    protected static HashMap getLoginInfo() {
        return logininfo;
    }
    public static void izpisiGesla() {
    for(String i: logininfo.keySet()) {
           System.out.print(i+"\t" +"= ");
           System.out.println(logininfo.get(i));
        }
    }
}