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

public class tableinfoServlet extends HttpServlet{

    public void doGet( HttpServletRequest rq,HttpServletResponse rp ) throws IOException,ServletException{
        PrintWriter out=rp.getWriter();
        String username = (String) rq.getSession().getAttribute("username");
        if(username==null){
            out.write("sessionerror");
            return;
        }
        String db = rq.getParameter("db");
        String dburl = myconfig.getInstance().getProperty("hivemeta_db_connection_url");
        String dbusername = myconfig.getInstance().getProperty("hivemeta_db_account");
        String dbpassword = myconfig.getInstance().getProperty("hivemeta_db_password");
        String driver = "com.mysql.jdbc.Driver";
        String tblist = "";
        try {
            Class.forName(driver);
            Connection conn = (Connection) DriverManager.getConnection(dburl, dbusername, dbpassword);
            Statement statement = (Statement) conn.createStatement();
            String _sql = "select t.TBL_NAME from DBS d join TBLS t on d.DB_ID=t.DB_ID where d.NAME='"+db+"'";
            ResultSet rs = statement.executeQuery(_sql);
            while (rs.next()) {
                if(!tblist.equals("")){
                    tblist+=",";
                }
                String tmp = rs.getString("TBL_NAME");
                tblist = tblist + tmp;
            }
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            tblist = "error";
//            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            tblist = "error";
//            e.printStackTrace();
        }
        if(tblist.equals("")){
            tblist = "null";
        }
        out.write(tblist);
    }
}