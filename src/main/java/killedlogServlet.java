import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Stack;

public class killedlogServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        if(username==null){
            response.sendRedirect("index.jsp");
        }
        PrintWriter out = response.getWriter();

        String killlogpath=getServletContext().getRealPath("/userdata/kill.log");
        BufferedReader reader = new BufferedReader(new FileReader(killlogpath));
        String line;
        Stack<String> ss = new Stack<String>();
        while((line = reader.readLine()) != null){
            ss.push(line);
        }
        while (!ss.empty()){
            String ele = ss.pop();
            String col [] = ele.split("\t");
            out.write("<tr>");
            out.write("<td>");
            out.write(col[0]);
            out.write("</td>");
            out.write("<td>");
            out.write(col[1]);
            out.write("</td>");
            out.write("<td>");
            out.write(col[2]);
            out.write("</td>");
            out.write("</tr>");
        }

    }
}