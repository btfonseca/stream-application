import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;

import com.streamaplication.controller.StreamApplicationController;
import com.streamaplication.model.InputTopic;


public class StreamApplicationTest {
	
	private HttpServletRequest request;
	private StreamApplicationController controller;
	private InputTopic inputTopic;
	
	
	@Before
	public void Setup() {
		this.controller = new StreamApplicationController();
		StreamApplicationRequest streamRequest = new StreamApplicationRequest();
		this.request = streamRequest.getHttpServletRequest();
	}
	
	@Test
	public void convertRequestToInputTopic() throws Exception {
		this.inputTopic = this.controller.convertRequestToInputTopic(request);
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
}
