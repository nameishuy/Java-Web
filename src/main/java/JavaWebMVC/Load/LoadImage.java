package JavaWebMVC.Load;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoadImage
 */
@WebServlet("/image/*")
public class LoadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String imagePath;

	public LoadImage() {
		super();

	}

	@Override
	public void init() throws ServletException {
		imagePath = System.getProperty("catalina.home") + File.separator + "assets/user/image";
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqImage = request.getPathInfo();
		if (reqImage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		File file = new File(imagePath, URLDecoder.decode(reqImage, "UTF-8"));
		if (!file.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		String ContentType = getServletContext().getMimeType(file.getName());
		if (ContentType == null || !ContentType.startsWith("image")) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		response.reset();
		response.setContentType(ContentType);
		response.setHeader("Content-Length", String.valueOf(file.length()));
		
		Files.copy(file.toPath(),response.getOutputStream());
	}

}
