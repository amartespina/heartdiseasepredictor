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
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 *
 * @author angel
 */
public class Modelo {
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
            Instances inst = leerInstancias("./training_data/heartAlgunosAtributos.arff");
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

    public String aplicarModelo() {
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



}
