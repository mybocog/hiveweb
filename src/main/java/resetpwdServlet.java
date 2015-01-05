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

public class resetpwdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
//       String newpassword2 = request.getParameter("newpassword2");

        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(oldpassword.getBytes());
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
                    MessageDigest mdnew = MessageDigest.getInstance("MD5");
                    mdnew.update(newpassword.getBytes());
                    String md5pwdnew = new BigInteger(1, mdnew.digest()).toString(16);
                    sql = "UPDATE user SET password = '"+md5pwdnew+"' WHERE account = '"+ username+"'";
                    int rsnew = statement.executeUpdate(sql);
                    response.getWriter().write("ok");
                }
                else{
                    response.getWriter().write("loginerror");
                    return;
                }

            }
            else{
                response.getWriter().write("loginerror");
                return;
            }
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
