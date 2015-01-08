import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class handlesqlServlet extends HttpServlet{
    public void doGet( HttpServletRequest rq,HttpServletResponse rp ) throws IOException,ServletException{
        PrintWriter out = rp.getWriter();
        String db = rq.getParameter("db");
        int jobno = Integer.parseInt(rq.getParameter("j"));
        String username ="test";
//        String username = (String) rq.getSession().getAttribute("username");
//        if(username==null){
//            rp.sendRedirect("login.html");
//        }
        ServletContext context=getServletContext();
        byte[] arrayStr = rq.getParameter("sql").getBytes("iso-8859-1");
        String sql = new String(arrayStr, "UTF-8");
        if(sql==null){sql="wrong sql";}
        String newsql = sql.replaceAll("(\r\n|\r|\n|\n\r)", " ");
        System.out.println(sql);
        System.out.println(newsql);
        out.write(sql);
/*

        String str_time = rq.getParameter("t");

        //make three files: 1 hivesql  2 error stream  3 input stream
        String dir="/userdata/"+username+"/";
        String sqlpath=context.getRealPath(dir+str_time+"_sql"+".q");
        String errorpath=context.getRealPath(dir+str_time+"_error"+".txt");
        String resultpath=context.getRealPath(dir+str_time+"_result"+".txt");
        PrintWriter sqlout = new PrintWriter(new BufferedWriter(new FileWriter(sqlpath)));
        sqlout.write("use "+db+";set hive.cli.print.header=true;set mapred.job.queue.name=hiveweb;\r\n");
        if(newsql.length()<=30){
            sqlout.write("set  mapred.job.name="+username+": "+newsql+";\r\n");
        }
        else{
            String tmp = newsql.substring(0, 30);
            sqlout.write("set  mapred.job.name="+username+": "+tmp+";\r\n");
        }
        sqlout.write(newsql);
        sqlout.close();

        if(newsql.startsWith("show") || newsql.startsWith("SHOW") || newsql.startsWith("desc") || newsql.startsWith("DESC") )
        {
            hiveExe he = new hiveExe(username,str_time,sqlpath,errorpath,resultpath,db,false,1);
            he.start();
        }
        else
        {
            hiveExe he = new hiveExe(username,str_time,sqlpath,errorpath,resultpath,db,true,1);
            he.start();
        }

        synchronized(this){
            String logpath=context.getRealPath("/userdata/run.log");
            PrintWriter logout = new PrintWriter(new BufferedWriter(new FileWriter(logpath,true)));
            logout.write(str_time+"\t"+username+"\t"+newsql+"\r\n");
            logout.close();
        }

*/

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}