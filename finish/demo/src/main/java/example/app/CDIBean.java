package example.app;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class CDIBean {
    
	@WithSpan
	public void exampleMethod() {
		System.out.println("Example Method");
	}
}
