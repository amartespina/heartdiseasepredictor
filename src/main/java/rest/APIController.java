/*Copyright 2023 Ángel Martínez Espina
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/


package rest;

import aplicacion.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author angel
 */


@RestController

/*
 * Ejemplos de Consultas: 
 * Resultado 0 ==> localhost:8080/aprenderModelo?edad=40&sexo=M&presArtReposo=140&colesterol=289&glucemiaAyunas=0&frecuenciaCardiacaMax=172
 * Resultado 1==> localhost:8080/aprenderModelo?edad=49&sexo=F&presArtReposo=160&colesterol=180&glucemiaAyunas=0&frecuenciaCardiacaMax=156
 * mvn build 
 */
public class APIController {
	
	@GetMapping("/aprenderModelo")
	public String  modelo(@RequestParam Integer edad, String sexo,Integer presArtReposo, Integer colesterol, Integer glucemiaAyunas, Integer frecuenciaCardiacaMax){
		Modelo modelo = new Modelo();
		//modelo.aprenderModelo();
		return("Al aplicar RandomForest a los datos aportados, el resultado es " + modelo.generarInstanciaConsulta(edad,sexo,presArtReposo,colesterol,glucemiaAyunas,frecuenciaCardiacaMax));
		
	}

	@GetMapping("/prueba")
	public String pruebaa(){
		return("esto es la prueba");
		
	}

}
