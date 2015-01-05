import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;

class hiveExe extends Thread {
    String username=null;
    String para=null;
    String sqlpath=null;
    String errorpath=null;
    String resultpath = null;
    int result_max_line=0;
    int jobno=1;

    public hiveExe(String u, String p, String sp, String ep, String rp, String db, boolean limit, int jobno){
        this.username=u;
        this.para=p;
        this.sqlpath=sp;
        this.errorpath=ep;
        this.resultpath=rp;
        this.jobno=jobno;
        if(limit) {
            result_max_line = myconfig.getInstance().getMaxline(username, db) + 1;
            if(result_max_line<=0){
                result_max_line=1;
            }
        }
        else {
            result_max_line =1024;
        }
    }

    public void run() {
        Process process=null;
        try {
            process = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c","hive -f "+sqlpath+" 2> "+errorpath},null,null);
            userdata.getInstance().setUserStatus(username, "running", jobno);
            userdata.getInstance().setUserpara(username, para, jobno);
            PrintWriter resultout = new PrintWriter(new BufferedWriter(new FileWriter(resultpath)));
            InputStreamReader inputstream = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(inputstream);
            String line="";
            int i=0;
            while( (line=input.readLine())!=null && i<result_max_line ){
                resultout.write(line+"\r\n");
                i=i+1;
            }
            resultout.close();
            process.destroy();
            if(i>0){
                userdata.getInstance().setUserStatus(username, "finished", jobno);
                userdata.getInstance().setUserjobid(username, "null", jobno);
            }
            else{
                userdata.getInstance().setUserStatus(username, "error", jobno);
                userdata.getInstance().setUserjobid(username, "null", jobno);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}