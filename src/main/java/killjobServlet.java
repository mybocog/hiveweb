import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class killjobServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = (String) request.getSession().getAttribute("username");
        if(username==null){
            out.write("sessionerror");
            return;
        }
        ServletContext context=getServletContext();
//        PrintWriter out = response.getWriter();
        String jobid = request.getParameter("jobid");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        synchronized(this){
            String logpath=context.getRealPath("/userdata/kill.log");
            PrintWriter logout = new PrintWriter(new BufferedWriter(new FileWriter(logpath,true)));
            logout.write(df.format(new Date())+"\t"+username+"\t"+jobid+"\r\n");
            logout.close();
        }
        Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c","hadoop job -kill "+jobid},null,null);

    }
}