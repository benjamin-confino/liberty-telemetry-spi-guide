package example.app;

import java.io.IOException;

import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/filtered")
public class TestServlet extends HttpServlet {
	
    @Inject 
    CDIBean bean;
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        bean.exampleMethod();
    }
}
