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
        String db = rq.getParameter("db");
        String tb = rq.getParameter("tb");
        String dburl = myconfig.getInstance().getProperty("hivemeta_db_connection_url");
        String dbusername = myconfig.getInstance().getProperty("hivemeta_db_account");
        String dbpassword = myconfig.getInstance().getProperty("hivemeta_db_password");
        String driver = "com.mysql.jdbc.Driver";
        String collist = "";
        String typelist ="";
        String tbltype ="";
        String tblupt ="";
        try {
            Class.forName(driver);
            Connection conn = (Connection) DriverManager.getConnection(dburl, dbusername, dbpassword);
            Statement statement = (Statement) conn.createStatement();
            String _sql = "select * from (select t.TBL_ID from TBLS t join DBS d on t.DB_ID=d.DB_ID where d.NAME='"+db+"' and t.TBL_NAME='"+tb+"' ) tmp join COLUMNS_V2 c on tmp.TBL_ID=c.CD_ID";
            ResultSet rs = statement.executeQuery(_sql);
            while (rs.next()) {
                if(!collist.equals("")){
                    collist+=",";
                }
                String tmp = rs.getString("COLUMN_NAME");
                collist = collist + tmp;
                if(!typelist.equals("")){
                    typelist+=",";
                }
                String tmp2 = rs.getString("TYPE_NAME");
                typelist = typelist + tmp2;
            }
            rs = statement.executeQuery("select t.TBL_TYPE from TBLS t join DBS d on t.DB_ID=d.DB_ID where d.NAME='"+db+"' and t.TBL_NAME='"+tb+"'");
            if(rs.next()){
                tbltype = rs.getString("TBL_TYPE");
            }
            if(tbltype.equals("MANAGED_TABLE")){
                rs = statement.executeQuery("select FROM_UNIXTIME(PARAM_VALUE, '%Y-%m-%d %H:%m') as upt from TABLE_PARAMS tp join (select t.TBL_NAME,t.TBL_ID from TBLS t join DBS d on t.DB_ID=d.DB_ID where d.NAME='"+db+"' and t.TBL_NAME='"+tb+"') nt on tp.TBL_ID=nt.TBL_ID where PARAM_KEY=\"transient_lastDdlTime\";");
                if(rs.next()){
                    tblupt = rs.getString("upt");
                }
            }
            else{
                tblupt = "";
            }
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            collist = "null";
            typelist ="null";
            tbltype ="null";
            tblupt ="null";
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            collist = "null";
            typelist ="null";
            tbltype ="null";
            tblupt ="null";
//            e.printStackTrace();
        }
        if(collist.equals("")){
            collist = "null";
        }
        if(typelist.equals("")){
            typelist = "null";
        }
        if(tbltype.equals("")){
            tbltype = "null";
        }
        out.write(collist+";"+typelist+";"+tbltype+";"+tblupt);
    }
}