package example.app;

import java.io.IOException;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.concurrent.ConstantInitializer;
import org.apache.commons.lang3.concurrent.ConcurrentException;

import com.ibm.example.cdi.api.ExampleQualifier;

@WebServlet("/")
public class TestServlet extends HttpServlet {

    private ConstantInitializer<String> local = new ConstantInitializer<String>("World");
	
    @Inject @ExampleQualifier
    ConstantInitializer<String> injected;
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream sos = response.getOutputStream();
        try {
            sos.println(injected.get() + " " + local.get());
        } catch (ConcurrentException ignored) {}
        sos.flush();
        sos.close();
    }
}
