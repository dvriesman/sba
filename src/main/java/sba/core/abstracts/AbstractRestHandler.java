package sba.core.abstracts;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

public class AbstractRestHandler {
	
	public class RestResponse {
		
		public RestResponse(String message) {
			this.message = message;
		}
		private String message;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}

    @ExceptionHandler(Exception.class)
    public @ResponseBody RestResponse handleUncaughtException(Exception ex, WebRequest request, HttpServletResponse response) {
        response.setHeader("Content-Type", "application/json");
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new RestResponse(ex.toString());
    }
	   
}
