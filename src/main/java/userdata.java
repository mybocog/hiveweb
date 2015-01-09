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
        public String status1;// init   running  finished
        public String status2;
        public String sql1;
        public String sql2;
        public userinfo(String username){
            account = username;
            jobid1="";
            jobid2="";
            para1="";
            para2="";
            status1="";
            status2="";
            sql1="";
            sql2="";
        }
    }

    Map<String, userinfo> userdataset = new HashMap<String, userinfo>();

    public synchronized void initUser(String username, int i){
        userdata.getInstance().setUserStatus(username,"init",i);
        userdata.getInstance().setUsersql(username,"",i);
        userdata.getInstance().setUserjobid(username,"",i);
        userdata.getInstance().setUserpara(username,"",i);
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