import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2015/1/22.
 */
public class adhoc2Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");
        if(username==null){
            resp.sendRedirect("/");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/adhoc2.jsp").forward(req,resp);
    }
}
