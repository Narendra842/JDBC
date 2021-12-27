package com.advjava.jdbc.rowset;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.rowset.OracleCachedRowSet;

public class RowSet_OracleCacheRowSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(InputStream is = new FileInputStream("src/com/advjava/jdbc/common/driverInfo.properties");
				OracleCachedRowSet crowset= new OracleCachedRowSet();)
		{
			Properties props=new Properties();
			props.load(is);
			crowset.setUrl(props.getProperty("jdbc.url"));
			crowset.setUsername(props.getProperty("jdbc.user"));
			crowset.setPassword(props.getProperty("jdbc.password"));
			crowset.setCommand("SELECT * FROM STUDENT");
			crowset.execute();
			
          while(crowset.next())
          {
        	  System.out.println(crowset.getInt(1)+"  "+crowset.getString(2)+"   "+crowset.getString(3)+"   "+crowset.getString(4));
          }
          System.out.println("Stop DB Software for some time");
          Thread.sleep(40000);
          crowset.absolute(2);
          crowset.updateString(3, "Parli Vaijanath");
          crowset.updateRow();
          System.out.println("Start Db software Now");
          Thread.sleep(60000);
          crowset.acceptChanges();
          
          while(crowset.next())
          {
        	  System.out.println(crowset.getInt(1)+"  "+crowset.getString(2)+"   "+crowset.getString(3)+"   "+crowset.getString(4));
          }
			
		}//try
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}//main

}//class
