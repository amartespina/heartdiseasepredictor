/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;

import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.Instance;
import weka.core.converters.ConverterUtils;
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


ArrayList<Attribute> atributos = new ArrayList<>(Arrays.asList(Age,Sex,RestingBP,Cholesterol,FastingBS,MaxHR,HeartDisease));
/**
 * Instances isnt ==> Instancia con todo el conjunto de datos de entrenamiento
 * Instances data ==> Instancia creada a partir de los datos del usuario. 
 * 
 */

 /**
  * Creamos clasificador.  RandomForest
  * Entrenamos el clasificador con el conjunto de entrenamiento. Instances int = leerInstancias("Algunos")
  * cls.buildClassifier. 
  *
  *Serializamos el objeto con ALgunosAtributos.
  *
  */

  /** 
    private Instances leerInstancias(String ficherArff){
        try{
            // Crea un dataset y lo carga desde el fichero ARFF
            Instances inst = new Instances(new BufferedReader(new FileReader(ficherArff)));
            System.out.println(inst);
            // Establece atributo clase
            inst.setClassIndex(inst.numAttributes() - 1);
            //System.out.println(inst);
            return inst;
        }catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    


    public void aprenderModelo()
    {
        try {
            // create RandomForest
            Classifier cls = new RandomForest();
            // train. Entrena el clasificador con el conjunto de entrenamiento.
            Instances inst = new Instances"./training_data/heartAlgunosAtributos.arff");
            cls.buildClassifier(inst);

            // serialize model
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./models/AlgunosAtributosRandomForest.model"));
            oos.writeObject(cls);
            oos.flush();
            oos.close();
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    */ 

    public void aprenderModelo(){
        try{
            //create RandomForest
            Classifier cls = new RandomForest();
            //train.ENtrena el clasificador con el conjunto de entrenamiento.
            Instances InstanciaEntrenamiento = new Instances(new BufferedReader(new FileReader("./training_data/heartAlgunosAtributos.arff")));
            System.out.println("El numero de atributos de InstanciaEntrenamiento es" + InstanciaEntrenamiento.numAttributes());
            InstanciaEntrenamiento.setClassIndex(InstanciaEntrenamiento.numAttributes()-1);
            cls.buildClassifier(InstanciaEntrenamiento);
            // Serializamos el modelo 
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./models/AlgunosAtributosRandomForest.model"));
            oos.writeObject(cls);
            oos.flush();
            oos.close();
            } 
            catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    // APrender modelo aparentemente hecho. Tenemos que mezclar GenerarARFF en AplicarMOdelo

    public String pruebaArgumentos(Integer Id, String cadena){
        return cadena;
    }
/**
    Original public String aplicarModelo() {
        try{
            String[] valoresAtributos = {"0", "1"};
            // Leemos el modelo creado anteriormente. 
            Classifier clasificador  = (Classifier) weka.core.SerializationHelper.read("./models/AlgunosAtributosRandomForest.model");
            // Creamos instancia con los datos aportados por el usuario. 
            Instances data = leerInstancias("./test_data/test.arff");
            //Clasificamos la instancia que recibimos como parametro. 
            return valoresAtributos[(int) clasificador.classifyInstance(data.instance(0))];
        }catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            return "Error al intentar leer el modelo";
        }

        
    }
    */
    public String GenerarInstanciaTest(Integer ValorAge, String ValorSexo,Integer ValorRestingBP, Integer ValorCholesterol, Integer ValorFastingBS, Integer ValorMaxHR){
       Instances InstancesTest = new Instances("InstancesTest1",atributos,atributos.size());
        Instance instancia = new DenseInstance(atributos.size());
        System.out.println("El tamaño del atributos de Instancia es " + atributos.size());
        //System.out.println("Esto es la instancia" + instancia);
        instancia.setValue(Age,ValorAge);
        instancia.setValue(Sex,ValorSexo);
        instancia.setValue(RestingBP,ValorRestingBP);
        instancia.setValue(Cholesterol,ValorCholesterol);
        instancia.setValue(FastingBS,ValorFastingBS);
        instancia.setValue(MaxHR,ValorMaxHR);
        InstancesTest.add(instancia);
        InstancesTest.setClassIndex(InstancesTest.numAttributes()-1);
        System.out.println("Esto es InstancesTest" + InstancesTest);
        try{
            String[] valoresAtributos={"0","1"};
            //Leemos el modelo creado anteriormente. 
            Classifier clasificador  = (Classifier) weka.core.SerializationHelper.read("./models/AlgunosAtributosRandomForest.model");
            return valoresAtributos[(int) clasificador.classifyInstance(InstancesTest.instance(0))];
            }catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            return "Error al intentar leer el modelo";
        }

    }



}
