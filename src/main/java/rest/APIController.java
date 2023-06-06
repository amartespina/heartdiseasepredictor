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


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aprendizajeautomatico.*;

/**
 * @author angel
 */



 @RestController
public class APIController {
	
	@GetMapping(value = "/")
	public String  modelo(@RequestParam Integer edad, String sexo,Integer presArtReposo, Integer colesterol, Integer glucemiaAyunas, Integer frecuenciaCardiacaMax){
		Modelo modelo = new Modelo();
		//modelo.aprenderModelo();
		return("{" + "Edad: " + edad + ", " + "Sexo: " + sexo + ", " + "Presión Arterial en Reposo : " + presArtReposo + ", " + "Colesterol: " + colesterol + ", " + "Gluecemia en Ayunas: " + glucemiaAyunas + ", "  +  "Frecuencia Cardiaca Maxima: " + frecuenciaCardiacaMax + ", " + "Enfermedad Cardiaca: "  + modelo.realizarConsulta(edad,sexo,presArtReposo,colesterol,glucemiaAyunas,frecuenciaCardiacaMax)+ "} \n");
		
	}

	@GetMapping("/error")
		public String handleError(){
			return "{\"error\": \"syntax error\"}";
		}
	

	
}
