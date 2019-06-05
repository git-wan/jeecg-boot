package org.jeecg.modules.ws.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DbConnectable {
	
	public static Boolean getConnection(String host,String dbname,String user,String password) {
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
	
	
	
	
	public static List<PageData> getTable(String host,String dbname,String user,String password) {
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<PageData> list = null;
		//String sql="select b.tablespace_name TABLENAME, b.bytes/1024/1024 TABLESIZE, (b.bytes-sum(nvl(a.bytes,0)))/1024/1024 USED, substr((b.bytes-sum(nvl(a.bytes,0)))/(b.bytes)*100,1,5) UTILIZATION  from dba_free_space a,dba_data_files b where a.file_id=b.file_id group by b.tablespace_name,b.file_name,b.bytes order by b.tablespace_name ";
		String sql = "SELECT a.tablespace_name TABLENAME, total/1024/1024  TABLESIZE,( total - free )/1024/1024  USED,Round(( total - free ) / total, 4) * 100 UTILIZATION FROM   (SELECT tablespace_name,Sum(bytes) free FROM   DBA_FREE_SPACE  GROUP  BY tablespace_name) a,(SELECT tablespace_name,Sum(bytes) total FROM   DBA_DATA_FILES GROUP  BY tablespace_name) b WHERE  a.tablespace_name = b.tablespace_name";
		try {			//,free  "表空间剩余大小"
			Class.forName("oracle.jdbc.OracleDriver");
			String url="jdbc:oracle:thin:@"+host+":1521:"+dbname;			
				//建立连接
			conn = DriverManager.getConnection(url,user,password);
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();//1.得到ResultSetMetaDataResultSetMetaData  rsmd=rs.getMetaData();
            list = new ArrayList<PageData>();
			while(rs.next()){
            	PageData pd = new PageData();
            	String TABLENAME = rs.getString("TABLENAME");
            	String TABLESIZE = rs.getDouble("TABLESIZE")+"";
            	String USED = rs.getDouble("USED")+"";
            	String UTILIZATION = rs.getDouble("UTILIZATION")+"";
            	pd.put("TABLENAME", TABLENAME);
            	pd.put("TABLESIZE", TABLESIZE);
            	pd.put("USED", USED);
            	pd.put("UTILIZATION", UTILIZATION);
            	list.add(pd);
            }
		}catch (Exception e) {
				//e.printStackTrace();
				return null;
			}finally {
			if (conn != null) {
				try {
					conn.close();
					if (ps != null) {
					ps.close();
					}
					if (rs != null) {
					rs.close();
					}
				} catch (SQLException e) {
					//e.printStackTrace();
					return null;
				}
			}
			
		}
		return list;
	}
}
