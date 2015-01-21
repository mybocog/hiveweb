import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class historyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = (String) request.getSession().getAttribute("username");
        if(username==null){
            out.write("sessionerror");
            return;
        }

        String historylogpath=getServletContext().getRealPath("/userdata/"+username+"/history.log");
        BufferedReader reader = new BufferedReader(new FileReader(historylogpath));
        String line;

        Stack<String> ss = new Stack<String>();
        while((line = reader.readLine()) != null){
            ss.push(line);
        }
        int max = Integer.parseInt(myconfig.getInstance().getProperty("history_number"));
        if(max<=0){
            max = 20;
        }
        while (!ss.empty() && max>0){
            max=max-1;
            String ele = ss.pop();
            String col [] = ele.split("\\t");
            System.out.println(col[0]);
            System.out.println(col[1]);
            out.write("<tr>");
            out.write("<td>");
            String tmp = new String(col[1].getBytes("UTF-8"),"ISO-8859-1");
            out.write(tmp);
            out.write("</td>");
            out.write("<td>");
            String ds=col[0].substring(0,4)+"-"+col[0].substring(4,6)+"-"+col[0].substring(6,8)+" "+col[0].substring(8,10)
                    +":"+col[0].substring(10,12)+":"+col[0].substring(12,14);
            out.write("<a href=\"/download?&t="+col[0]+"\">"+ds+" download</a>");
            out.write("</td>");
            out.write("</tr>");
        }

    }
}