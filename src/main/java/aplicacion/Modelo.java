/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;

import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomForest;
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
 *
 * @author angel
 */

 /**
 * https://txikiboo.wordpress.com/2014/01/16/archivos-arff-weka/
 * https://ccia.esei.uvigo.es/docencia/MRA/practicas/api-weka/api-weka.html#SECTION00031000000000000000
 * file:///C:/Users/angel/OneDrive%20-%20Fundaci%C3%B3n%20Universitaria%20San%20Pablo%20CEU/Documentos/Insituto/Universidad/4%C2%BA%20Curso/TFG/proyecto/SpringBoot/springboot/test_data/test.arff
 * https://stackoverflow.com/questions/12953958/how-to-create-an-arff-file-from-an-array-in-java
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


    public void aprenderModelo(){
        try{
            //create RandomForest
            Classifier cls = new RandomForest();
            //Creamos el Dataset cargándolo de un fchero ARFF
            Instances InstanciaEntrenamiento = new Instances(new BufferedReader(new FileReader("./training_data/fallosCardiacosPersonalizado.arff")));
            // Establecemos que el último atributo va a ser el  Atributo Clase. El atributo clase es la variable que deseamos predecir.
            InstanciaEntrenamiento.setClassIndex(InstanciaEntrenamiento.numAttributes()-1);
            //Entenamos el clasificador con InstanciaEntrenamiento
            cls.buildClassifier(InstanciaEntrenamiento);
            // Serializamos el modelo 
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./models/fallosCardiacosPersonalizado.model"));
            oos.writeObject(cls);
            oos.flush();
            oos.close();
            } 
            catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public String generarInstanciaConsulta(Integer ValorAge, String ValorSexo,Integer ValorRestingBP, Integer ValorCholesterol, Integer ValorFastingBS, Integer ValorMaxHR){
       Instances InstancesConsulta = new Instances("InstancesConsulta",atributos,atributos.size());
        Instance instancia = new DenseInstance(atributos.size());
        instancia.setValue(Age,ValorAge);
        instancia.setValue(Sex,ValorSexo);
        instancia.setValue(RestingBP,ValorRestingBP);
        instancia.setValue(Cholesterol,ValorCholesterol);
        instancia.setValue(FastingBS,ValorFastingBS);
        instancia.setValue(MaxHR,ValorMaxHR);
        InstancesConsulta.add(instancia);
        // Establecemos el atributo clase. 
        InstancesConsulta.setClassIndex(InstancesConsulta.numAttributes()-1);
        try{
            String[] valoresAtributos={"0","1"};
            //Leemos el modelo creado anteriormente. 
            Classifier clasificador  = (Classifier) weka.core.SerializationHelper.read("./models/fallosCardiacosPersonalizado.model");
            return valoresAtributos[(int) clasificador.classifyInstance(InstancesConsulta.instance(0))];
            }catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            return "Error al intentar leer el modelo";
        }


        
    }
}
