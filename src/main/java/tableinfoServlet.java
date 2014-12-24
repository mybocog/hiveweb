import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class tableinfoServlet extends HttpServlet{

    public void doGet( HttpServletRequest rq,HttpServletResponse rp ) throws IOException,ServletException{
        PrintWriter out=rp.getWriter();
        String username = "test";
        String pypath=getServletContext().getRealPath("/gettableinfo.py");
        Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c","python "+pypath},null,null);
    }
}