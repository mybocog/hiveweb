import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class loginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            String md5pwd = new BigInteger(1, md.digest()).toString(16);
            String dburl = myconfig.getInstance().getProperty("db_connection_url");
            String dbusername = myconfig.getInstance().getProperty("db_account");
            String dbpassword = myconfig.getInstance().getProperty("db_password");
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            Connection conn = (Connection) DriverManager.getConnection(dburl, dbusername, dbpassword);
            Statement statement = (Statement) conn.createStatement();
            String sql = "select password from user where account='"+username+"'";
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                if(rs.getString("password").equals(md5pwd)){
                    String userdir=getServletContext().getRealPath("/userdata/"+username);
                    Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c","mkdir -p "+userdir},null,null);
                    request.getSession().setMaxInactiveInterval(-1);
                    request.getSession().setAttribute("username", username);
                    response.sendRedirect("adhoc.jsp");
                    return;
                }
            }
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
//            e.printStackTrace();
        }
//        }
        response.sendRedirect("index.jsp");
//        out.write("wrong username or password");  

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}  
