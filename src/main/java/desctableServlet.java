import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.util.Date;


public class desctableServlet extends HttpServlet{

    public void doGet( HttpServletRequest rq,HttpServletResponse rp ) throws IOException,ServletException{
        PrintWriter out=rp.getWriter();
        String username = (String) rq.getSession().getAttribute("username");
        if(username==null){
            return;
        }
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
            String _sql = "select * from (select CD_ID from (select t.SD_ID from TBLS t join DBS d on t.DB_ID=d.DB_ID where d.NAME='"+db+"' and t.TBL_NAME='"+tb+"' ) tmp join SDS s on tmp.SD_ID=s.SD_ID) tmp2 join COLUMNS_V2 c on tmp2.CD_ID=c.CD_ID order by  integer_idx";
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
                rs = statement.executeQuery("select PARAM_VALUE as upt from TABLE_PARAMS tp join (select t.TBL_NAME,t.TBL_ID from TBLS t join DBS d on t.DB_ID=d.DB_ID where d.NAME='"+db+"' and t.TBL_NAME='"+tb+"') nt on tp.TBL_ID=nt.TBL_ID where PARAM_KEY=\"transient_lastDdlTime\";");
                if(rs.next()){
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    tblupt = df.format(new Date(Long.parseLong(rs.getString("upt"))*1000))+" updated";
                }
            }
            else{
                rs = statement.executeQuery("select max(CREATE_TIME) as upt from (select t.TBL_ID from TBLS t join DBS d on t.DB_ID=d.DB_ID where d.NAME='"+db+"' and t.TBL_NAME='"+tb+"' ) tmp join PARTITIONS p on tmp.TBL_ID=p.TBL_ID");
                if(rs.next()){
                    long l = (long)rs.getInt("upt")*1000;
                    if(l>0){
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        tblupt = df.format(new Date(l))+" partitioned";
                    }
                    else{
                        String basedb = myconfig.getInstance().getProperty("base_hive_db");
                        rs = statement.executeQuery("select PARAM_VALUE as upt from TABLE_PARAMS tp join (select t.TBL_NAME,t.TBL_ID from TBLS t join DBS d on t.DB_ID=d.DB_ID where d.NAME='"+basedb+"' and t.TBL_NAME='"+tb+"') nt on tp.TBL_ID=nt.TBL_ID where PARAM_KEY=\"transient_lastDdlTime\";");
                        if(rs.next()){
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            tblupt = df.format(new Date(Long.parseLong(rs.getString("upt"))*1000))+" updated";
                        }
                        else{
                            tblupt="";
                        }
                    }
                }
            }
            conn.close();
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            collist = "error";
            typelist ="error";
            tbltype ="error";
            tblupt ="error";
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            collist = "error";
            typelist ="error";
            tbltype ="error";
            tblupt ="error";
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