package jersey.rest.apis;

import java.util.ArrayList;
import java.util.List;

public class VtServices {
	public List<VtService> getVtServices() {
		return vtSerices;
	}

	public void setVtService(List<VtService> vtSerices) {
		this.vtSerices = vtSerices;
	}

	List<VtService> vtSerices;
	
	public VtServices() {
		vtSerices = new ArrayList<VtService>();
	}
}
