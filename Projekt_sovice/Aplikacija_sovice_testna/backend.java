
import java.sql.*;
import java.util.ArrayList;

public class backend
{
    static Connection conn=null;

    public static void main(String[] args){
        try {
            if(conn == null || conn.isClosed()) {
                try 
                {
                    Class.forName("org.mariadb.jdbc.Driver");
                } 
                catch (ClassNotFoundException ex) 
                {
                    System.out.println("CLassNotFoundException: " + ex.getMessage());
                    System.exit(1);
                }
                conn = DriverManager.getConnection("jdbc:mysql://193.2.190.23:3306/remote11","remote","remote");
            }
            System.out.println(getCreated());
            //System.out.println(getUpdated());
            //System.out.println(getInserted());
            //System.out.println(getSelected());
            //System.out.println(getDroped());
        } 
        catch (SQLException throwables) 
        {
            throwables.printStackTrace();
        }
    }

    private static ResultSet runSql(String query){
        Statement statementmt=null;
        ResultSet resultSet=null;
        try 
        {
            statementmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statementmt.executeQuery(query);
        }
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return resultSet;
    }
    
    //kdo je kreiral tabelo, kdaj je bila kreirana, kdaj odstranjena
    public static ArrayList<ArrayList<String>> getCreated() throws SQLException
    {
        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
        int index=0;
        ResultSet resultSet = runSql("SELECT user_host, event_time, argument FROM mysql.general_log WHERE argument LIKE '%CREATE TABLE%'AND argument NOT LIKE '%SHOW%'AND argument NOT LIKE '%SELECT%'AND argument NOT LIKE '%DELETE%'AND argument NOT LIKE '%USE%'AND argument NOT LIKE '%GRANT%';");
        while(resultSet.next())
        {
            String userHost = resultSet.getString(1);
            for(int i=userHost.length()-1; i>=0; i--)
            {
                if(userHost.charAt(i)=='[')
                {
                    userHost = userHost.substring(i+1, userHost.length()-1);
                    break;
                }
            }

            String tableName = resultSet.getString(3);
            if(tableName.startsWith("/*"))
            {
                tableName = tableName.substring(tableName.indexOf("/")+3);
            }
            try 
            {
                tableName = tableName.split(" ")[2];
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                continue;
            }
            try {
                for (int i = 0; i < tableName.length(); i++)
                {
                    if (tableName.charAt(i) == '(')
                    {
                        tableName = tableName.substring(0, i - 1);
                    }
                }
            }
            catch (StringIndexOutOfBoundsException e)
            {
                continue;
            }
            if (tableName.charAt(0) == '`' || tableName.charAt(0) == '\'')
            {
                tableName = tableName.substring(1, tableName.length() - 1);
            }

            ret.add(new ArrayList<String>());
            ret.get(index).add(userHost);
            ret.get(index).add(tableName);
            ret.get(index).add(resultSet.getString(2));

            index++;
        }

        resultSet = runSql("SELECT user_host, event_time, argument FROM mysql.general_log WHERE argument LIKE '%DROP TABLE%' AND argument NOT LIKE '%SHOW%' AND argument NOT LIKE '%SELECT%' ORDER BY event_time DESC;");
        index=0;
        while(resultSet.next())
        {
            String tableName = resultSet.getString(3);
            if(tableName.startsWith("/*"))
            {
                tableName = tableName.substring(tableName.indexOf("/")+3);
            }
            tableName = tableName.split(" ")[2];
            if (tableName.charAt(0) == '`' || tableName.charAt(0) == '\'')
            {
                tableName = tableName.substring(1, tableName.length() - 1);
            }
            
            for(int i=0; i < ret.size(); i++)
            {
                if(ret.get(i).get(1).equals(tableName))
                {
                    ret.get(i).add(resultSet.getString(2));
                    break;
                }
            }
        }
        return ret;
    }
    
    //kdo je vstavljal podatke, kdaj, katere podatke
    public static ArrayList<ArrayList<String>> getInserted() throws SQLException
    {
        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
        int index=0;
        ResultSet resultSet = runSql("SELECT user_host, event_time, argument FROM mysql.general_log WHERE argument LIKE '%INSERT%' AND argument NOT LIKE '%SELECT%' AND argument NOT LIKE '%FROM%';");
        
        while(resultSet.next())
        {
            String userHost = resultSet.getString(1);
            for(int i=userHost.length()-1; i>=0; i--)
            {
                if(userHost.charAt(i)=='[')
                {
                    userHost = userHost.substring(i+1, userHost.length()-1);
                    break;
                }
            }
            
            ret.add(new ArrayList<String>());
            ret.get(index).add(userHost);
            ret.get(index).add(resultSet.getString(2));
            ret.get(index).add(resultSet.getString(3));
            index++;
        }
        return ret;
    }
    
    //kdo je ogledoval podatke, kdaj, katere
    public static ArrayList<ArrayList<String>> getSelected() throws SQLException
    {
        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
        int index=0;
        ResultSet resultSet = runSql("SELECT user_host, event_time, argument FROM mysql.general_log WHERE argument LIKE '%SELECT%';");
        
        while(resultSet.next())
        {
            String userHost = resultSet.getString(1);
            for(int i=userHost.length()-1; i>=0; i--)
            {
                if(userHost.charAt(i)=='[')
                {
                    userHost = userHost.substring(i+1, userHost.length()-1);
                    break;
                }
            }
            
            
            ret.add(new ArrayList<String>());
            ret.get(index).add(userHost);
            ret.get(index).add(resultSet.getString(2));
            ret.get(index).add(resultSet.getString(3));
            index++;
        }
        return ret;
    }
    
    // kdo je ažuriral podatke, kdaj, katere
    public static ArrayList<ArrayList<String>> getUpdated() throws SQLException
    {
        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
        int index=0;
        ResultSet resultSet = runSql("SELECT user_host, event_time, argument FROM mysql.general_log WHERE argument LIKE '%UPDATE%' AND argument NOT LIKE '%SELECT%' AND argument NOT LIKE '%CREATE TABLE%' AND argument NOT LIKE '%SAFE_UPDATES%' AND argument NOT LIKE '%ACCESS%' AND argument NOT LIKE '%TCP/IP%';");
        
        while(resultSet.next())
        {
            String userHost = resultSet.getString(1);
            for(int i=userHost.length()-1; i>=0; i--)
            {
                if(userHost.charAt(i)=='[')
                {
                    userHost = userHost.substring(i+1, userHost.length()-1);
                    break;
                }
            }
            
            ret.add(new ArrayList<String>());
            ret.get(index).add(userHost);
            ret.get(index).add(resultSet.getString(2));
            ret.get(index).add(resultSet.getString(3));
            index++;
        }
        return ret;
    }
    
    //kdo je brisal podatke, kdaj katere
    public static ArrayList<ArrayList<String>> getDroped() throws SQLException
    {
        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
        int index=0;
        ResultSet resultSet = runSql("SELECT user_host, event_time, argument FROM mysql.general_log WHERE argument LIKE '%DROP%' AND argument NOT LIKE '%ALTER%' AND argument NOT LIKE '%DROP TABLE%' AND argument NOT LIKE '%SELECT%';");
        
        while(resultSet.next())
        {
            String userHost = resultSet.getString(1);
            for(int i=userHost.length()-1; i>=0; i--)
            {
                if(userHost.charAt(i)=='[')
                {
                    userHost = userHost.substring(i+1, userHost.length()-1);
                    break;
                }
            }
            
            
            ret.add(new ArrayList<String>());
            ret.get(index).add(userHost);
            ret.get(index).add(resultSet.getString(2));
            ret.get(index).add(resultSet.getString(3));
            index++;
        }
        return ret;
    }
}