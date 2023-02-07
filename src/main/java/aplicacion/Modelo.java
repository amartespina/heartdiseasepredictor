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

package aplicacion;



import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.Instance;
import weka.core.Attribute;
import weka.core.DenseInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author angel
 */

 /**
 * https://txikiboo.wordpress.com/2014/01/16/archivos-arff-weka/
 * https://ccia.esei.uvigo.es/docencia/MRA/practicas/api-weka/api-weka.html#SECTION00031000000000000000
 * file:///C:/Users/angel/OneDrive%20-%20Fundaci%C3%B3n%20Universitaria%20San%20Pablo%20CEU/Documentos/Insituto/Universidad/4%C2%BA%20Curso/TFG/proyecto/SpringBoot/springboot/test_data/test.arff
 * https://stackoverflow.com/questions/12953958/how-to-create-an-arff-file-from-an-array-in-java
 * https://stackoverflow.com/questions/2271926/how-to-read-a-file-from-a-jar-file
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
    public void aprenderModelo(){
        try{
            //create RandomForest
            Classifier cls = new RandomForest();
            //Creamos el Dataset cargándolo de un fchero ARFF
           // Instances InstanciaEntrenamiento = new Instances(new BufferedReader(new FileReader("./training_data/fallosCardiacosPersonalizado.arff")));
            Instances InstanciaEntrenamiento = new Instances(new BufferedReader(new FileReader("./src/main/resources/fallosCardiacosPersonalizado.arff")));
       
            // Establecemos que el último atributo va a ser el  Atributo Clase. El atributo clase es la variable que deseamos predecir.
            InstanciaEntrenamiento.setClassIndex(InstanciaEntrenamiento.numAttributes()-1);
            //Entenamos el clasificador con InstanciaEntrenamiento
            cls.buildClassifier(InstanciaEntrenamiento);
            // Serializamos el modelo 
          ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/main/resources/models/fallosCardiacosPersonalizado.model"));
            oos.writeObject(cls);
            oos.flush();
            oos.close();
            } 
            catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
**/
    /**
     * Almacena los valores de la consulta en una instancia. 
     * @param edad: Edad del sujeto
     * @param sexo: Sexo del sujeto
     * @param presArtReposo: Presión Arterial Reposo del sujeto. [mm Hg]
     * @param colesterol: Colestero del sujeto
     * @param glucemiaAyunas: Glucemia del Sujeto ==> 1 if > 120 mg/dl | 0 en el resto de casos 
     * @param frecuenciaCardiacaMax: Valor entre 60 y 202
     * @return Resultado de la Clasificación. 0 ==> Paciente sin Insuficiencia Cardíaca. 1==> Insuficiencia Cardíaca 
     */

    public String generarInstanciaConsulta(Integer edad, String sexo,Integer presArtReposo, Integer colesterol, Integer glucemiaAyunas, Integer frecuenciaCardiacaMax){
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
            Classifier clasificador  = (Classifier) weka.core.SerializationHelper.read("./model/fallosCardiacosPersonalizado.model");
            return valoresAtributos[(int) clasificador.classifyInstance(InstancesConsulta.instance(0))];
            }catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            return "Error al intentar leer el modelo";
        }        
    }
} 
