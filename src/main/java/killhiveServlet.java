import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class killhiveServlet extends HttpServlet{

    public void doGet( HttpServletRequest rq,HttpServletResponse rp ) throws IOException,ServletException{
        PrintWriter out=rp.getWriter();
        String username = (String) rq.getSession().getAttribute("username");
        if(username==null){
            out.write("sessionerror");
            return;
        }
        int jobno = Integer.parseInt((String)rq.getSession().getAttribute("j"));
        String status = userdata.getInstance().getUserStatus(username, jobno);
        if(status!="running"){
            out.write("no running");
            return;
        }
        String jobid = userdata.getInstance().getUserjobid(username, jobno);
        if(jobid=="null"){
            out.write("null");
            return;
        }
        Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c","hadoop job -kill "+jobid},null,null);
        userdata.getInstance().setUserjobid(username, "null", jobno);
        out.write("KILL JOB "+jobid);
    }
}