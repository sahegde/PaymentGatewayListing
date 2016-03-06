package jersey.rest.apis;

import java.sql.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Objects.ToStringHelper;

public class MysqlDatabaseHelper {
	// JDBC driver name and database URL
	final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final static String DB_URL = "jdbc:mysql://localhost/test";

	// Database credentials
	final static String USER = "root";
	final static String PASS = "";

	static Connection conn = null;
	private static Connection getConnection() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		return conn;
	}
	
	public VtServices getDetailsFromQuery(String query) throws Exception {
		conn = getConnection();
		Statement stmt = conn.createStatement();

		System.out.println("Query is "+query);
		String sql = "SELECT * FROM VT_Services where name='"+query+"'";
		
		ResultSet rs = stmt.executeQuery(sql);

		VtServices vtServices = new VtServices();
		
    	while(rs.next()) {
    		VtService vtService = new VtService();
    		vtService.setId(rs.getInt(1));
    		vtService.setName(rs.getString(2));
    		vtService.setCurrencies(rs.getString(10));
    		vtServices.getVtServices().add(vtService);
    	}
    	
		rs.close();		
		return vtServices;
	}
	
	public VtServices getDetailsFromCurrency(String currency) throws Exception {
		
		System.out.println("Currency is "+currency);
		conn = getConnection();
		Statement stmt = conn.createStatement();

		ObjectMapper mapper = new ObjectMapper();
		String sql = "SELECT * FROM VT_Services";
		
		ResultSet rs = stmt.executeQuery(sql);

		VtServices vtServices = new VtServices();
		
    	while(rs.next()) {
    		Currencies cc = mapper.readValue(rs.getString(10),Currencies.class);
    		if(cc.currencies.indexOf(currency) > -1) {
        		VtService vtService = new VtService();
        		vtService.setId(rs.getInt(1));
        		vtService.setName(rs.getString(2));
        		vtServices.getVtServices().add(vtService);
    		}
    	}
    	
		rs.close();		
		return vtServices;
	}
	
	public int getDetailsOfCount() throws Exception{
		conn = getConnection();
		Statement stmt = conn.createStatement();

		String sql = "SELECT count(*) FROM VT_Services";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		int count = 0;
    	while(rs.next()) {
    		count = rs.getInt(1);
    		break;
    	}
    	
		rs.close();		
		return count;
	}
	
}
