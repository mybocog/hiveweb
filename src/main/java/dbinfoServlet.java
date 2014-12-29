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

public class dbinfoServlet extends HttpServlet{

    public void doGet( HttpServletRequest rq,HttpServletResponse rp ) throws IOException,ServletException{
        PrintWriter out=rp.getWriter();
        String username = "test";
        String dburl = myconfig.getInstance().getProperty("db_connection_url");
        String dbusername = myconfig.getInstance().getProperty("db_account");
        String dbpassword = myconfig.getInstance().getProperty("db_password");
        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
            Connection conn = (Connection) DriverManager.getConnection(dburl, dbusername, dbpassword);
            Statement statement = (Statement) conn.createStatement();
            String _sql = "select forbiddentable from privilege where account='" + username + "'";
            ResultSet rs = statement.executeQuery(_sql);
            if (rs.next()) {
//                ftset = rs.getString("forbiddentable");
            }
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}