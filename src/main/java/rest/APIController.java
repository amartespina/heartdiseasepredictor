package rest;

import aplicacion.*;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

/*
 * Ejemplos de Consultas: 
 * localhost:8080/aprenderModelo?ValorAge=40&ValorSexo=M&ValorRestingBP=140&ValorCholesterol=289&ValorFastingBS=0&ValorMaxHR=172
 * localhost:8080/aprenderModelo?ValorAge=49&ValorSexo=F&ValorRestingBP=160&ValorCholesterol=180&ValorFastingBS=0&ValorMaxHR=156
 */
public class APIController {
	
	@GetMapping("/aprenderModelo")
	public String  modelo(@RequestParam Integer ValorAge, String ValorSexo,Integer ValorRestingBP, Integer ValorCholesterol, Integer ValorFastingBS, Integer ValorMaxHR){
		Modelo modelo = new Modelo();
		modelo.aprenderModelo();
		return("Al aplicar RandomForest a los datos aportados, el resultado es " + modelo.generarInstanciaConsulta(ValorAge,ValorSexo,ValorRestingBP,ValorCholesterol,ValorFastingBS,ValorMaxHR));
			
		
		
	}

}

