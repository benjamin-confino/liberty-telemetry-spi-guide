package example.app;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UnusedBean {
    // The presence of a bean makes this a CDI archive so we can inject into TestServlet
}
