import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class desctableServlet extends HttpServlet{

    public void doGet( HttpServletRequest rq,HttpServletResponse rp ) throws IOException,ServletException{
        PrintWriter out=rp.getWriter();
        String username = "test";
        String dburl = myconfig.getInstance().getProperty("hivemeta_db_connection_url");
        String dbusername = myconfig.getInstance().getProperty("hivemeta_db_account");
        String dbpassword = myconfig.getInstance().getProperty("hivemeta_db_password");
        String driver = "com.mysql.jdbc.Driver";
        String dblist = "";
        try {
            Class.forName(driver);
            Connection conn = (Connection) DriverManager.getConnection(dburl, dbusername, dbpassword);
            Statement statement = (Statement) conn.createStatement();
            String _sql = "select db from dbpriv where account='" + username + "'";
            ResultSet rs = statement.executeQuery(_sql);
            while (rs.next()) {
                if(!dblist.equals("")){
                    dblist+=",";
                }
                String tmp = rs.getString("db");
                dblist = dblist + tmp;
            }
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        out.write(dblist);
    }
}