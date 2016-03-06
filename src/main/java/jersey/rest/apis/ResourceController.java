package jersey.rest.apis;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Sandeep L Hegde
 * @author email : sandeephegde1990@gmail.com
 */

@Path("/gateway")
public class ResourceController {

	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPaymentGatewayDetails(@QueryParam("query") String query,
			@QueryParam("currency") String currency) {
		
		MysqlDatabaseHelper db = new MysqlDatabaseHelper();
		VtServices vtServices = new VtServices();
		try {
			if(query != null) {
				vtServices = db.getDetailsFromQuery(query);
			}
			
			if(currency != null) {
				vtServices = db.getDetailsFromCurrency(currency);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Gson jsonConverter = new GsonBuilder().create();

		return Response.ok(jsonConverter.toJson(vtServices),MediaType.APPLICATION_JSON).build();	
	}
	
	@GET
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPaymentGatewayDetails() {
		
		MysqlDatabaseHelper db = new MysqlDatabaseHelper();
		Integer count = 0;
		try {
			count = db.getDetailsOfCount();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Gson jsonConverter = new GsonBuilder().create();

		return Response.ok(jsonConverter.toJson(count),MediaType.APPLICATION_JSON).build();	
	}
}