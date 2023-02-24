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

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aprendizajeautomatico.*;

/**
 * @author angel
 */


@RestController

/*
 * Ejemplos de Consultas: 
 * Resultado 0 ==> localhost:8080/?edad=40&sexo=M&presArtReposo=140&colesterol=289&glucemiaAyunas=0&frecuenciaCardiacaMax=172 
 * https://stackoverflow.com/questions/5744919/generating-output-in-java
 * https://dzone.com/articles/spring-boot-secured-by-lets-encrypt
 */
public class APIController {
	
	@GetMapping(value = "/")
	public String  modelo(@RequestParam Integer edad, String sexo,Integer presArtReposo, Integer colesterol, Integer glucemiaAyunas, Integer frecuenciaCardiacaMax){
		Modelo modelo = new Modelo();
		//modelo.aprenderModelo();
		return("{" + "Edad: " + edad + ", " + "\n" + "Sexo: " + sexo + ", " + "Presión Arterial en Reposo : " + presArtReposo + ", " + "Colesterol: " + colesterol + ", " + "Gluecemia en Ayunas: " + glucemiaAyunas + ", "  +  "Frecuencia Cardiaca Maxima: " + frecuenciaCardiacaMax + ", " + "Insuficiencia Cardiaca: "  + modelo.realizarConsulta(edad,sexo,presArtReposo,colesterol,glucemiaAyunas,frecuenciaCardiacaMax)+ "}");
		
	}

	@GetMapping(value = "/error")
	public String  error(){
		//modelo.aprenderModelo();
		return("Has llegado a la página de error");
	}


	@GetMapping("/aprenderModelo")
	public void  prueba(){
		Modelo modelo = new Modelo();
		modelo.aprenderModelo();
	}
	
	

}
