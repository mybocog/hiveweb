import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class handlesqlServlet extends HttpServlet{
    public void doGet( HttpServletRequest rq,HttpServletResponse rp ) throws IOException,ServletException{
        PrintWriter out = rp.getWriter();
        String db = rq.getParameter("db");
        int jobno = Integer.parseInt(rq.getParameter("j"));
        String str_time = rq.getParameter("t");
        String username ="t";
//        String username = (String) rq.getSession().getAttribute("username");
//        if(username==null){
//            rp.sendRedirect("index.jsp");
//        }
        ServletContext context=getServletContext();
        byte[] arrayStr = rq.getParameter("sql").getBytes("iso-8859-1");
        String sql = new String(arrayStr, "UTF-8");
        if(sql==null){sql="wrong sql";}
        String newsql = sql.replaceAll("(\r\n|\r|\n|\n\r)", " ");
        userdata.getInstance().initUser(username, jobno);
        userdata.getInstance().setUsersql(username,sql,jobno);
        String queue_name = myconfig.getInstance().getProperty("job_queue_name");
        if(queue_name==null||queue_name.equals("")){
            queue_name="default";
        }

        //make three files: 1 hivesql  2 error stream  3 input stream
        String dir="/userdata/"+username+"/";
        String sqlpath=context.getRealPath(dir+str_time+"_sql"+".q");
        String errorpath=context.getRealPath(dir+str_time+"_error"+".txt");
        String resultpath=context.getRealPath(dir+str_time+"_result"+".txt");
        PrintWriter sqlout = new PrintWriter(new BufferedWriter(new FileWriter(sqlpath)));
        sqlout.write("use "+db+";set hive.cli.print.header=true;set mapred.job.queue.name="+queue_name+";\r\n");
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
            hiveExe he = new hiveExe(username,str_time,sqlpath,errorpath,resultpath,db,false,jobno);
            he.start();
        }
        else
        {
            hiveExe he = new hiveExe(username,str_time,sqlpath,errorpath,resultpath,db,true,jobno);
            he.start();
        }

        synchronized(this){
            String logpath=context.getRealPath("/userdata/run.log");
            PrintWriter logout = new PrintWriter(new BufferedWriter(new FileWriter(logpath,true)));
            logout.write(str_time+"\t"+username+"\t"+newsql+"\r\n");
            logout.close();
        }

        synchronized(this){
            String logpath=context.getRealPath("/userdata/"+username+"/history.log");
            PrintWriter logout = new PrintWriter(new BufferedWriter(new FileWriter(logpath,true)));
            logout.write(str_time+"\t"+newsql+"\r\n");
            logout.close();
        }

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}