import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class myconfig {

    private static final myconfig instance = new myconfig();
    String configfile = "conf/hiveweb.properties";
    Properties prop = new Properties();

    public static myconfig getInstance() {
        return instance;
    }

    private myconfig() {
        try {
            String path =  System.getProperty("user.dir");
            FileInputStream fis;
            fis = new FileInputStream(new File(path + configfile));
            prop.load(fis);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public String getProperty(String k){
        return prop.getProperty(k);
    }

    public int getMaxline(String username,String db){
        int maxline = 0;
        String colname = "max_"+db;
        try{
            String dburl = myconfig.getInstance().getProperty("hiveweb_db_connection_url");
            String dbusername = myconfig.getInstance().getProperty("hiveweb_db_account");
            String dbpassword = myconfig.getInstance().getProperty("hiveweb_db_password");
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            Connection conn = (Connection) DriverManager.getConnection(dburl, dbusername, dbpassword);
            Statement statement = (Statement) conn.createStatement();
            String _sql = "select "+colname+" from privilege where account='"+username+"'";
            ResultSet rs = statement.executeQuery(_sql);
            if(rs.next()){
                maxline = rs.getInt(colname);
            }
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return maxline;
    }

    public boolean checkAuthority(String username, String db) throws URISyntaxException, IOException{
        String dbset = "";
        try{
            String dburl = myconfig.getInstance().getProperty("db_connection_url");
            String dbusername = myconfig.getInstance().getProperty("db_account");
            String dbpassword = myconfig.getInstance().getProperty("db_password");
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            Connection conn = (Connection) DriverManager.getConnection(dburl, dbusername, dbpassword);
            Statement statement = (Statement) conn.createStatement();
            String _sql = "select db from privilege where account='"+username+"'";
            ResultSet rs = statement.executeQuery(_sql);
            if(rs.next()){
                dbset = rs.getString("db");
            }
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

//	  String dbset = this.getProperty("auth_"+username);
        if(dbset.contains(db)){
            return true;
        }
        else{
            return false;
        }
    }



}