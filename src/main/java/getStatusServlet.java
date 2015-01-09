import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class getstatusServlet extends HttpServlet{

    public void doGet( HttpServletRequest rq,HttpServletResponse rp ) throws IOException,ServletException{
        PrintWriter out=rp.getWriter();
        int jobno = Integer.parseInt(rq.getParameter("j"));
        String username="test";
//        String username = (String) rq.getSession().getAttribute("username");
        String status = userdata.getInstance().getUserStatus(username, jobno);
        if(status==null || status.equals("init")){
            out.write("init");
            return;
        }
        String parameter = userdata.getInstance().getUserpara(username, jobno);
        String sql = userdata.getInstance().getUsersql(username, jobno);
        out.write(status+";"+parameter+";"+sql);
    }
}