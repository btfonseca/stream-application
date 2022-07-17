package com.streamaplication.resource;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.streamaplication.controller.StreamApplicationController;
import com.streamaplication.model.InputTopic;

public class InicializeStreamApplication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StreamApplicationController controller = new StreamApplicationController();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputTopic it = controller.convertRequestToInputTopic(req);
		if (it.getClientId() == null) {
			this.controller.flushPrintWriter(resp, "Precisa informar 'ClientId' como par�metro.");
		} else if (controller.clientExist(it.getClientId())) {
			this.controller.flushPrintWriter(resp, "Aguarde alguns minutos");
		} else {

		}
	}  
	
}
