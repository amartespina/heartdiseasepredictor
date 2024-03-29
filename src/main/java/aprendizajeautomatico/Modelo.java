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

package aprendizajeautomatico;


import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.Instance;
import weka.core.Attribute;
import weka.core.DenseInstance;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author angel
 */


public class Modelo {
    // Los Atributos Sexo y HeartDisease son de tipo Nominal porque
    // representa un valor tomado de un conjunto discreto de valores posibles
    List<String> AtributosSexo = Arrays.asList("M","F");
    List<String> AtributosHeartDisease = Arrays.asList("0","1");

    // Declaramos Atributos de Tipo Numerico
    Attribute Age = new Attribute("Age");
    Attribute RestingBP = new Attribute("RestingBP");
    Attribute Cholesterol = new Attribute("Cholesterol");
    Attribute FastingBS = new Attribute("FastingBS");
    Attribute MaxHR = new Attribute("MaxHR");
    Attribute HeartDisease = new Attribute("HeartDisease",AtributosHeartDisease);
    Attribute Sex = new Attribute("Sex",AtributosSexo);

    // ArrayList que va a contener todos los atributos
    ArrayList<Attribute> atributos = new ArrayList<>(Arrays.asList(Age,Sex,RestingBP,Cholesterol,FastingBS,MaxHR,HeartDisease));


/**
 * Entrena el clasificador utilizando RandomForest a partir de un fichero arff.
 * Genera un archivo model
 */
    
    public void aprenderModelo(){
        try{
            //create RandomForest
            Classifier cls = new RandomForest();
            //Creamos el Dataset cargándolo de un fchero ARFF
            Instances InstanciaEntrenamiento = new Instances(new BufferedReader(new FileReader("./src/main/java/resources/fallosCardiacosPersonalizado.arff")));
            // Establecemos que el último atributo va a ser el  Atributo Clase. El atributo clase es la variable que deseamos predecir.
            InstanciaEntrenamiento.setClassIndex(InstanciaEntrenamiento.numAttributes()-1);
            //Entenamos el clasificador con InstanciaEntrenamiento
            cls.buildClassifier(InstanciaEntrenamiento);
            // Serializamos el modelo 
          ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/main/resources/fallosCardiacosPersonalizado.model"));
            oos.writeObject(cls);
            oos.flush();
            oos.close();
            System.out.println("El modelo se ha aprendido correctamente");
            } 
            catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    /**
     * Plantea una consulta con los datos de un paciente a un modelo aprendido
     * y este responde con un pronóstico que puede ser paciente con insuficiencia cardíaca o 
     * sin insuficiencia cardíaca
     * @param edad: Edad del sujeto
     * @param sexo: Sexo del sujeto
     * @param presArtReposo: Presión Arterial Reposo del sujeto. [mm Hg]
     * @param colesterol: Colestero del sujeto
     * @param glucemiaAyunas: Glucemia del Sujeto ==> 1 if > 120 mg/dl | 0 en el resto de casos 
     * @param frecuenciaCardiacaMax: Valor entre 60 y 202
     * @return Resultado de la Clasificación. 0 ==> Paciente sin enfermedad cardiovascular. 1==> Paciente con enfermedad cardiovascular 
     */
    public String realizarConsulta(Integer edad, String sexo,Integer presArtReposo, Integer colesterol, Integer glucemiaAyunas, Integer frecuenciaCardiacaMax){
        // Creamos una Instances. ( Representación en memoria de una colección de ejemplos)
       Instances InstancesConsulta = new Instances("InstancesConsulta",atributos,atributos.size());
       // Creamos una Instance. (Almacena los valores de un ejemplo). Los valores tomarán los valores introducidos por el usuario.
        Instance instancia = new DenseInstance(atributos.size());
        instancia.setValue(Age,edad);
        instancia.setValue(Sex,sexo);
        instancia.setValue(RestingBP,presArtReposo);
        instancia.setValue(Cholesterol,colesterol);
        instancia.setValue(FastingBS,glucemiaAyunas);
        instancia.setValue(MaxHR,frecuenciaCardiacaMax);
        // Añadimos la Instance a la Instances. 
        InstancesConsulta.add(instancia);
        // Establecemos el atributo clase. 
        InstancesConsulta.setClassIndex(InstancesConsulta.numAttributes()-1);
        try{
            String[] valoresAtributos={"0","1"};
            //Leemos el modelo creado anteriormente. 
            Classifier clasificador  = (Classifier) weka.core.SerializationHelper.read("./src/main/resources/fallosCardiacosPersonalizado.model");
            return valoresAtributos[(int) clasificador.classifyInstance(InstancesConsulta.instance(0))];
            }catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            return "Error al intentar leer el modelo";
        }        
    }
} 
