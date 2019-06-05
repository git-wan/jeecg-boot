package org.jeecg.modules.ws.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionUtil {

    public static void isConnection(List<PageData> pds){

/*        for (int ii = 0; ii < pds.size(); ii++) {// 开threadNum个线程

        }*/
        pds.forEach(pageData -> {
            WorkerThread t = new WorkerThread(pageData);
            t.start();
            t.getPd();
        });
        }

    public static class WorkerThread extends Thread {

        private static PageData pd;

        private static List<Thread> runningThreads = new ArrayList<Thread>();

        public WorkerThread(PageData pd) {
            this.pd = pd ;
        }

        public PageData getPd(){
            return  pd;
        }

        @Override
        public void run() {
            regist(this);// 线程开始时注册
            // 打印开始标记
            System.out.println(Thread.currentThread().getName() + "开始...");
            Boolean flag = getConnection(pd);
            if(flag){
                pd.put("status",true);
            }else{
                pd.put("status",false);
            }

            unRegist(this);// 线程结束时取消注册
            // 打印结束标记
            System.out.println(Thread.currentThread().getName() + "结束.");
        }

        public void regist(Thread t) {
            synchronized (runningThreads) {
                runningThreads.add(t);
            }
        }

        public void unRegist(Thread t) {
            synchronized (runningThreads) {
                runningThreads.remove(t);
            }
        }

        public static boolean hasThreadRunning() {
            // 通过判断runningThreads是否为空就能知道是否还有线程未执行完
            return (runningThreads.size() > 0);
        }

        public static Boolean getConnection(PageData pd) {
            String host=pd.get("host").toString();
            String dbname= pd.get("dbname").toString();
            String user = pd.get("user").toString();
            String password = pd.get("password").toString();
            Connection conn = null;
            try {
                Class.forName("oracle.jdbc.OracleDriver");
                String url="jdbc:oracle:thin:@"+host+":1521:"+dbname;
                //建立连接
                conn = DriverManager.getConnection(url,user,password);
            }catch (Exception e) {
                e.printStackTrace();
                //System.err.println("连接失败");
                return false;
            }finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            }
            return true;
        }
    }
    }

