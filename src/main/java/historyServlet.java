import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class historyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = "test";
        String historylogpath=getServletContext().getRealPath("/userdata/"+username+"/history.log");
        BufferedReader reader = new BufferedReader(new FileReader(historylogpath));
        String line;
        while((line = reader.readLine()) != null){
            String col [] = line.split("\t");
            out.write("<tr>");
            out.write("<td>");
            out.write(col[1]);
            out.write("</td>");
            out.write("<td>");
            out.write("<a href=\"/download?&t=\""+col[0]+"\">"+col[0]+" download</a>");
            out.write("</td>");
            out.write("</tr>");
        }
    }
}