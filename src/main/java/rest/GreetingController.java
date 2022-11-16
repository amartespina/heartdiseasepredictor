package rest;

import aplicacion.*;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping("/tumbalacasa")
	public String tumba(){
		return "tumba la casa"; 
	}
	
	@GetMapping("/aprenderModelo")
	public String  modelio(){
		Modelo modelo = new Modelo();
		modelo.aprenderModelo();
		System.out.println("Hasta aqui se ha aprendido el modelo");
		return(modelo.aplicarModelo()); 		
	}
}
