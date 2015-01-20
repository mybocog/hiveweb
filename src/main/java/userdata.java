import java.util.Map;
import java.util.HashMap;

public class userdata {

    private static final userdata instance = new userdata();

    public static userdata getInstance() {
        return instance;
    }

    public class userinfo{
        public String account;
        public String jobid1;
        public String jobid2;
        public String para1;
        public String para2;
        public String status1;// init   running  finished error
        public String status2;
        public String sql1;
        public String sql2;
        public int linenum1;
        public int linenum2;
        public String db1;
        public String db2;
        public userinfo(String username){
            account = username;
            jobid1="";
            jobid2="";
            para1="";
            para2="";
            status1="init";
            status2="init";
            sql1="";
            sql2="";
            linenum1=0;
            linenum2=0;
            db1="";
            db2="";
        }
    }

    Map<String, userinfo> userdataset = new HashMap<String, userinfo>();

    public synchronized void initUser(String username, int i){
        userdata.getInstance().setUserStatus(username,"init",i);
        userdata.getInstance().setUsersql(username,"",i);
        userdata.getInstance().setUserjobid(username,"",i);
        userdata.getInstance().setUserpara(username,"",i);
        userdata.getInstance().setUserlinenum(username,0,i);
        userdata.getInstance().setUserdb(username,"",i);
    }

    public int getUserlinenum(String username, int i){
        if(userdataset.containsKey(username)){
            if(i==1){
                return userdataset.get(username).linenum1;
            }
            else if(i==2){
                return userdataset.get(username).linenum2;
            }
        }
        return 0;
    }

    public String getUserdb(String username, int i){
        if(userdataset.containsKey(username)){
            if(i==1){
                return userdataset.get(username).db1;
            }
            else if(i==2){
                return userdataset.get(username).db2;
            }
        }
        return null;
    }

    public String getUserStatus(String username, int i){
        if(userdataset.containsKey(username)){
            if(i==1){
                return userdataset.get(username).status1;
            }
            else if(i==2){
                return userdataset.get(username).status2;
            }
        }
        return null;
    }

    public String getUserjobid(String username, int i){
        if(userdataset.containsKey(username)){
            if(i==1){
                return userdataset.get(username).jobid1;
            }
            else if(i==2){
                return userdataset.get(username).jobid2;
            }
        }
        return null;
    }

    public String getUserpara(String username, int i){
        if(userdataset.containsKey(username)){
            if(i==1){
                return userdataset.get(username).para1;
            }
            else if(i==2){
                return userdataset.get(username).para2;
            }
        }
        return null;
    }

    public String getUsersql(String username, int i){
        if(userdataset.containsKey(username)){
            if(i==1){
                return userdataset.get(username).sql1;
            }
            else if(i==2){
                return userdataset.get(username).sql2;
            }
        }
        return null;
    }

    public synchronized void setUserlinenum(String username, int ln, int i){
        if(userdataset.containsKey(username)){
            userinfo u = userdataset.get(username);
            if(i==1){
                u.linenum1=ln;
            }
            else if(i==2){
                u.linenum2=ln;
            }
            userdataset.put(username, u);
        }
        else {
            userinfo u = new userinfo(username);
            if(i==1){
                u.linenum1=ln;
            }
            else if(i==2){
                u.linenum2=ln;
            }
            userdataset.put(username, u);
        }
    }

    public synchronized void setUserdb(String username, String db, int i){
        if(userdataset.containsKey(username)){
            userinfo u = userdataset.get(username);
            if(i==1){
                u.db1=db;
            }
            else if(i==2){
                u.db2=db;
            }
            userdataset.put(username, u);
        }
        else {
            userinfo u = new userinfo(username);
            if(i==1){
                u.db1=db;
            }
            else if(i==2){
                u.db2=db;
            }
            userdataset.put(username, u);
        }
    }

    public synchronized void setUserStatus(String username, String status, int i){
        if(userdataset.containsKey(username)){
            userinfo u = userdataset.get(username);
            if(i==1){
                u.status1=status;
            }
            else if(i==2){
                u.status2=status;
            }
            userdataset.put(username, u);
        }
        else {
            userinfo u = new userinfo(username);
            if(i==1){
                u.status1=status;
            }
            else if(i==2){
                u.status2=status;
            }
            userdataset.put(username, u);
        }
    }

    public synchronized void setUserjobid(String username, String jobid, int i){
        if(userdataset.containsKey(username)){
            userinfo u = userdataset.get(username);
            if(i==1){
                u.jobid1=jobid;
            }
            else if(i==2){
                u.jobid2=jobid;
            }
            userdataset.put(username, u);
        }
        else {
            userinfo u = new userinfo(username);
            if(i==1){
                u.jobid1=jobid;
            }
            else if(i==2){
                u.jobid2=jobid;
            }
            userdataset.put(username, u);
        }
    }

    public synchronized void setUserpara(String username, String para, int i){
        if(userdataset.containsKey(username)){
            userinfo u = userdataset.get(username);
            if(i==1){
                u.para1=para;
            }
            else if(i==2){
                u.para2=para;
            }
            userdataset.put(username, u);
        }
        else {
            userinfo u = new userinfo(username);
            if(i==1){
                u.para1=para;
            }
            else if(i==2){
                u.para2=para;
            }
            userdataset.put(username, u);
        }
    }

    public synchronized void setUsersql(String username, String sql, int i){
        if(userdataset.containsKey(username)){
            userinfo u = userdataset.get(username);
            if(i==1){
                u.sql1=sql;
            }
            else if(i==2){
                u.sql2=sql;
            }
            userdataset.put(username, u);
        }
        else {
            userinfo u = new userinfo(username);
            if(i==1){
                u.sql1=sql;
            }
            else if(i==2){
                u.sql2=sql;
            }
            userdataset.put(username, u);
        }
    }
}