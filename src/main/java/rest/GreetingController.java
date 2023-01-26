package rest;

import aplicacion.*;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Template;
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

	@GetMapping("/crearModelo")
	public String CrearModelo(@RequestParam Integer id, String cadena ){
		return cadena; 

		
	}
	

	@GetMapping("pruebaArgumentos")
	public String prueba(@RequestParam Integer id, String cadena){
		Modelo modelo = new Modelo();
		return("eoso" + modelo.pruebaArgumentos(id,cadena));
	}
	@GetMapping("/aprenderModelo")
	public String  modelo(@RequestParam Integer ValorAge, String ValorSexo,Integer ValorRestingBP, Integer ValorCholesterol, Integer ValorFastingBS, Integer ValorMaxHR){
		Modelo modelo = new Modelo();
		modelo.aprenderModelo();
		System.out.println("Hasta aqui se ha aprendido el modelo");
		return("Al aplicar RandomForest a los datos aportados, el resultado es " + modelo.GenerarInstanciaTest(ValorAge,ValorSexo,ValorRestingBP,ValorCholesterol,ValorFastingBS,ValorMaxHR));
			//40,"M",140,289,0,172)); 		
	}// localhost:8080/aprenderModelo?ValorAge=40&ValorSexo=M&ValorRestingBP=140&ValorCholesterol=289&ValorFastingBS=0&ValorMaxHR=172
}

