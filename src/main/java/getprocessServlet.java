import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class getprocessServlet extends HttpServlet{
    public void doGet( HttpServletRequest rq,HttpServletResponse rp ) throws IOException,ServletException{
        PrintWriter out=rp.getWriter();
        ServletContext context=getServletContext();
        String gettype = rq.getParameter("g");
        int jobno = Integer.parseInt(rq.getParameter("j"));
        int totallinenum = Integer.parseInt(rq.getParameter("l"));
        int max_result_line_num = 1000;
        String username ="test";
        //String username = (String) rq.getSession().getAttribute("username");
        //if(username==null){
        //    rp.sendRedirect("login.html");
        //}
        String dir="/userdata/"+username+"/";
        String status = userdata.getInstance().getUserStatus(username, jobno);
        String parameter = userdata.getInstance().getUserpara(username, jobno);
        try{
            BufferedReader reader;
            if(gettype.equals("e")){
                if(status=="finished"){
                    out.write("finished");
                    return;
                }
                else if(status=="error"){
                    out.write("error");
                    userdata.getInstance().setUserStatus(username, "init", jobno);
                    return;
                }
                String errorpath=context.getRealPath(dir+parameter+"_error"+".txt");
                reader = new BufferedReader(new FileReader(errorpath));
                List<String> strList = new ArrayList();
                String line;
                while ((line = reader.readLine()) != null){
                    strList.add(line);
                }
                int i=0;
                int size = strList.size();
                for(String str : strList){
                    i+=1;
                    if(i>(size-totallinenum)){
                        if(str.startsWith("Starting Job")){
                            if(str.charAt(36)==','){
                                userdata.getInstance().setUserjobid(username, str.substring(15, 36), jobno);
                            }
                            else if(str.charAt(37)==','){
                                userdata.getInstance().setUserjobid(username, str.substring(15, 37), jobno);
                            }
                            else if(str.charAt(38)==','){
                                userdata.getInstance().setUserjobid(username, str.substring(15, 38), jobno);
                            }
                            else if(str.charAt(39)==','){
                                userdata.getInstance().setUserjobid(username, str.substring(15, 39), jobno);
                            }
                            else if(str.charAt(40)==','){
                                userdata.getInstance().setUserjobid(username, str.substring(15, 40), jobno);
                            }
                            else if(str.charAt(41)==','){
                                userdata.getInstance().setUserjobid(username, str.substring(15, 41), jobno);
                            }
                            else if(str.charAt(42)==','){
                                userdata.getInstance().setUserjobid(username, str.substring(15, 42), jobno);
                            }

                        }
                        out.write(str+"</br>");
                    }
                }
                int n;
                if( (n = totallinenum-size)>0){
                    for(int j=0;j<n;j++)
                    {
                        out.write("</br>");
                    }
                }
            }
            else{
                userdata.getInstance().setUserStatus(username, "init" ,jobno);
                String resultpath=context.getRealPath(dir+parameter+"_result"+".txt");
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(resultpath),"UTF-8"));
                int i=0;
                String line;
                out.write("<table class=\"table table-hover table-bordered table-striped\">");
                while ((line = reader.readLine()) != null){
                    i+=1;
                    if(i>max_result_line_num){break;}
                    if(i==1){
                        out.write("<thead><tr>");
                        String cols[] = line.split("\t");
                        for(String col:cols){
                            out.write("<th>");
                            if(col.equals("")){col="NULL";};
                            out.write(col);
                            out.write("</th>");
                        }
                        out.write("<thead></tr>");
                    }
                    else{
                        out.write("<tr>");
                        String cols[] = line.split("\t");
                        for(String col:cols){
                            out.write("<td>");
                            if(col.equals("")){col="NULL";};
                            out.write(col);
                            out.write("</td>");
                        }
                        out.write("</tr>");
                    }
                }
                out.write("</table>");
            }
            reader.close();
            out.close();
        }
        catch  (FileNotFoundException e) {
        }
    }

}