import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class myconfig {

    private static final myconfig instance = new myconfig();
    String configfile = "hiveweb.properties";
    Properties prop = new Properties();

    public static myconfig getInstance() {
        return instance;
    }

    private myconfig() {
        try {
            prop.load(this.getClass().getClassLoader().getResourceAsStream(configfile));
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
        try{
            String dburl = myconfig.getInstance().getProperty("db_connection_url");
            String dbusername = myconfig.getInstance().getProperty("db_account");
            String dbpassword = myconfig.getInstance().getProperty("db_password");
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            Connection conn = (Connection) DriverManager.getConnection(dburl, dbusername, dbpassword);
            Statement statement = (Statement) conn.createStatement();
            String _sql = "select maxline from dbpriv where account='"+username+"' and db='"+db+"'";
            ResultSet rs = statement.executeQuery(_sql);
            if(rs.next()){
                maxline = rs.getInt("maxline");
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

}