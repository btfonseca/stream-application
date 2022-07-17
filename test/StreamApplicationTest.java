import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonSyntaxException;
import com.streamaplication.controller.StreamApplicationController;
import com.streamaplication.model.InputTopic;
import com.streamaplication.model.ResponseIpStack;


public class StreamApplicationTest {
	
	private HttpServletRequest request;
	private StreamApplicationController controller;
	private InputTopic inputTopic;
	private HttpServletResponse response;
	private String jsonIpStack;
	private ResponseIpStack responseIpStack;
	
	
	@Before
	public void Setup() throws JsonSyntaxException, IOException {
		this.controller = new StreamApplicationController();
		StreamApplicationRequest streamRequest = new StreamApplicationRequest();
		this.request = streamRequest.getHttpServletRequest();
		this.inputTopic = controller.convertRequestToInputTopic(this.request);
		StreamApplicationResponse streamResponse = new StreamApplicationResponse();
		this.response = streamResponse.getHttpServletResponse();
		
		jsonIpStack = "{\r\n" + 
				"    \"clientId\": \"bruno\",\r\n" + 
				"    \"timestamp\": \"1658005264404\",\r\n" + 
				"    \"ip\": \"179.182.160.131\",\r\n" + 
				"    \"latitude\": \"-15.888279914855957\",\r\n" + 
				"    \"longitude\": \"-48.127891540527344\",\r\n" + 
				"    \"country_name\": \"Brazil\",\r\n" + 
				"    \"region_name\": \"Federal District\",\r\n" + 
				"    \"city\": \"Brasília\"\r\n" + 
				"}";
	}
	
	@Test
	public void convertRequestToInputTopic() throws Exception {
		assertEquals(InputTopic.class, this.inputTopic.getClass());
	}
	
	@Test
	public void getEpochMilli() throws Exception {
		String epoch = ""+controller.getEpochMilli();
		assertEquals(13, epoch.length());
	}
	
	@Test
	public void getForwarderIpRequest() throws Exception {
		assertEquals(String.class, controller.getForwarderIpRequest(request).getClass());
	}
	
	@Test
	public void flushPrintWriter() throws Exception {
		this.controller.flushPrintWriter(this.response, "Enviando mensagem de teste.");
	}
	
	@Test
	public void convertJsonToResponseIpStack() throws Exception {
		this.responseIpStack = this.controller.convertJsonToResponseIpStack(jsonIpStack);
		assertEquals(ResponseIpStack.class, this.responseIpStack.getClass());
	}
	
	
	//TODO TESTE DE INTEGRAçÂO
	@Test
	public void testGetIpStack() throws Exception {
		InputTopic it = new InputTopic();
		Assert.assertEquals(String.class, controller.sendRequestToIpStack(it, this.request).getClass());
	}
	
	@Test
	public void testAddClienInCache() throws Exception {
		String clientId = "Bruno";
		this.controller.addClientInCache(clientId, 1658005264404L);
	}
	
	@Test
	public void testClientNotExist() throws Exception {
		Assert.assertTrue(!controller.clientExist(this.inputTopic.getClientId()));
	}
}
