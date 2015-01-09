import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class downloadServlet extends HttpServlet{

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        try{
            ServletContext context=getServletContext();
            OutputStream out;
            InputStream in;
//            String user = (String) request.getSession().getAttribute("username");
            String user = "t";

            String str_time = request.getParameter("t");

            String dir="/userdata/"+user+"/";

            in= context.getResourceAsStream(dir+str_time+"_result"+".txt");
            int length=in.available();
            response.setContentType("application/force-download");
            response.setHeader("Content-Length",String.valueOf(length));
            response.setHeader("Content-Disposition", "attachment;filename=\""+str_time+"_result"+".txt"+"\" ");

            out=response.getOutputStream();
            int bytesRead = 0;
            byte[] buffer = new byte[512];
            while ((bytesRead = in.read(buffer)) != -1){
                out.write(buffer, 0, bytesRead);
            }

            in.close();
            out.close();
        }catch (NullPointerException e){
            response.getWriter().write("download error");
            response.getWriter().close();
        }
    }
}
