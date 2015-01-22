import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class getStatusServlet extends HttpServlet{

    public void doGet( HttpServletRequest rq,HttpServletResponse rp ) throws IOException,ServletException{
        PrintWriter out=rp.getWriter();
        String username = (String) rq.getSession().getAttribute("username");
        if(username==null){
            return;
        }

        int jobno = Integer.parseInt(rq.getParameter("j"));
        String status = userdata.getInstance().getUserStatus(username, jobno);
        if(status==null || status.equals("") || status.equals("init")){
            out.write("init");
            return;
        }
        String parameter = userdata.getInstance().getUserpara(username, jobno);
        String sql = userdata.getInstance().getUsersql(username, jobno);
        String tmp = new String(sql.getBytes("UTF-8"),"ISO-8859-1");
        out.write(status+";"+parameter+";"+tmp);
    }
}