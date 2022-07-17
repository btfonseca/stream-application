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


public class StreamApplicationTest {
	
	private HttpServletRequest request;
	private StreamApplicationController controller;
	private InputTopic inputTopic;
	private HttpServletResponse response;
	
	
	@Before
	public void Setup() throws JsonSyntaxException, IOException {
		this.controller = new StreamApplicationController();
		StreamApplicationRequest streamRequest = new StreamApplicationRequest();
		this.request = streamRequest.getHttpServletRequest();
		this.inputTopic = controller.convertRequestToInputTopic(this.request);
		StreamApplicationResponse streamResponse = new StreamApplicationResponse();
		this.response = streamResponse.getHttpServletResponse();
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
	public void testClientNotExist() throws Exception {
		Assert.assertTrue(!controller.clientExist(this.inputTopic.getClientId()));
	}
	
	@Test
	public void flushPrintWriter() throws Exception {
		this.controller.flushPrintWriter(this.response, "Enviando mensagem de teste.");
	}
}
