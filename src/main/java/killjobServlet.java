import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class killjobServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String jobid = request.getParameter("jobid");
//        Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c","hadoop job -kill "+jobid},null,null);
        out.write("killjobServlet");
    }
}