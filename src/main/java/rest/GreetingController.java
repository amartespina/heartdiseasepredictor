package rest;

import aplicacion.*;
import java.util.concurrent.atomic.AtomicLong;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class GreetingController {
	
	@GetMapping("/aprenderModelo")
	public String  modelo(@RequestParam Integer ValorAge, String ValorSexo,Integer ValorRestingBP, Integer ValorCholesterol, Integer ValorFastingBS, Integer ValorMaxHR){
		Modelo modelo = new Modelo();
		modelo.aprenderModelo();
		System.out.println("Hasta aqui se ha aprendido el modelo");
		return("Al aplicar RandomForest a los datos aportados, el resultado es " + modelo.GenerarInstanciaTest(ValorAge,ValorSexo,ValorRestingBP,ValorCholesterol,ValorFastingBS,ValorMaxHR));
			
		
		//40,"M",140,289,0,172)); 		
	}// localhost:8080/aprenderModelo?ValorAge=40&ValorSexo=M&ValorRestingBP=140&ValorCholesterol=289&ValorFastingBS=0&ValorMaxHR=172
	
	//49,F,160,180,0,156
	//localhost:8080/aprenderModelo?ValorAge=49&ValorSexo=F&ValorRestingBP=160&ValorCholesterol=180&ValorFastingBS=0&ValorMaxHR=156
}

