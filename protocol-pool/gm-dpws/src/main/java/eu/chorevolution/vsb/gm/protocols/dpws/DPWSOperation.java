package eu.chorevolution.vsb.gm.protocols.dpws;

import java.util.ArrayList;
import java.util.List;

import org.ws4d.java.communication.CommunicationException;
import org.ws4d.java.schema.Element;
import org.ws4d.java.schema.SchemaUtil;
import org.ws4d.java.security.CredentialInfo;
import org.ws4d.java.service.InvocationException;
import org.ws4d.java.service.Operation;
import org.ws4d.java.service.parameter.ParameterValue;
import org.ws4d.java.service.parameter.ParameterValueManagement;
import org.ws4d.java.types.QName;

import eu.chorevolution.vsb.gmdl.utils.Data;

public class DPWSOperation extends Operation {
	
	private final static String	LAT	= "lat";
	private final static String	LON	= "lon";
	private final static String	REPLY	= "reply";
	private BcDPWSSubcomponent subcomponentRefparam = null;
	
	public DPWSOperation(BcDPWSSubcomponent subcomponentRefparam) {
		// TODO Auto-generated constructor stub
		super("DPWSOperation", new QName("BasicServices", DPWSDevice.DOCU_NAMESPACE));
		this.subcomponentRefparam = subcomponentRefparam;
		Element nameElemLat = new Element(new QName(LAT, DPWSDevice.DOCU_NAMESPACE), SchemaUtil.TYPE_STRING);
		Element nameElemLon = new Element(new QName(LON, DPWSDevice.DOCU_NAMESPACE), SchemaUtil.TYPE_STRING);
		setInput(nameElemLat);
		setInput(nameElemLon);
	}
	@Override
	protected ParameterValue invokeImpl(ParameterValue arg0, CredentialInfo arg1) throws InvocationException, CommunicationException{
		// TODO Auto-generated method stub
		
		String lat = ParameterValueManagement.getString(arg0, "lat");
		String lon = ParameterValueManagement.getString(arg0, "lon");
		List<Data<?>> datas = new ArrayList<Data<?>>();
		datas.add(new Data<String>("lon", "String", true, lon, "QUERY"));
		datas.add(new Data<String>("lat", "String", true, lat, "QUERY"));
		System.err.println("Get Request : &lat="+lat+"&lon="+lon);
		subcomponentRefparam.mgetOneway(null, datas);
		ParameterValue result = createOutputValue(); 
		return result;
	}

}
