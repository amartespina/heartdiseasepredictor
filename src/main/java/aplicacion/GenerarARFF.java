package aplicacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;



public class GenerarARFF{

// Los Atributos Sexo y HeartDisease son de tipo Nominal porque
// representa un valor tomado de un conjunto discreto de valores posibles
List<String> AtributosSexo = Arrays.asList("M","F");
List<String> AtributosHearthDisease = Arrays.asList("0","1");

// Declaramos Atributos de Tipo Numerico
Attribute Age = new Attribute("Age");
Attribute RestingBP = new Attribute("RestingBP");
Attribute Cholesterol = new Attribute("Cholesterol");
Attribute FastingBS = new Attribute("FastingBS");
Attribute MaxHR = new Attribute("MaxHR");
Attribute HeartDisease = new Attribute("HearthDisease",AtributosHearthDisease);
Attribute Sex = new Attribute("Sex",AtributosSexo);


ArrayList<Attribute> atributos = new ArrayList<>(Arrays.asList(Age,Sex,RestingBP,Cholesterol,FastingBS,MaxHR,HeartDisease));


public Instances GenerarInstanciaTest(Integer ValorAge, String ValorSexo,Integer ValorRestingBP, Integer ValorCholesterol, Integer ValorFastingBS, Integer ValorMaxHR){
        Instances InstancesTest = new Instances("InstancesTest1",atributos,atributos.size());
        Instance instancia = new DenseInstance(atributos.size());
        //System.out.println("Esto es la instancia" + instancia);
        instancia.setValue(Age,ValorAge);
        instancia.setValue(Sex,ValorSexo);
        instancia.setValue(RestingBP,ValorRestingBP);
        instancia.setValue(Cholesterol,ValorCholesterol);
        instancia.setValue(FastingBS,ValorFastingBS);
        instancia.setValue(MaxHR,ValorMaxHR);
        InstancesTest.add(instancia);
        System.out.println(InstancesTest);
        return InstancesTest;

    
        
        

        
        
        
        /** 
        double[] InstanciaTestValue = new double[InstanciaTest.numAttributes()];
        InstanciaTestValue[0]=2;
        InstanciaTestValue[1]=3;
        DenseInstance denseInstance1 = new DenseInstance(1.0,InstanciaTestValue);
        InstanciaTest.add(denseInstance1);
        System.out.println(InstanciaTest);*/
    }
    
    

/**    public void InstanciaDensa(){
    Instance instanciaDensa = new DenseInstance(10);
    instanciaDensa.setValue(Age,20);
    instanciaDensa.setValue(Sex,"M");
    instanciaDensa.setValue(RestingBP,200);
    instanciaDensa.setValue(Cholesterol,200);
    instanciaDensa.setValue(FastingBS,200);
    instanciaDensa.setValue(MaxHR,200);
    System.out.println(instanciaDensa);

    }
    */
}





/**
 * https://txikiboo.wordpress.com/2014/01/16/archivos-arff-weka/
 * https://ccia.esei.uvigo.es/docencia/MRA/practicas/api-weka/api-weka.html#SECTION00031000000000000000
 * file:///C:/Users/angel/OneDrive%20-%20Fundaci%C3%B3n%20Universitaria%20San%20Pablo%20CEU/Documentos/Insituto/Universidad/4%C2%BA%20Curso/TFG/proyecto/SpringBoot/springboot/test_data/test.arff
 * https://stackoverflow.com/questions/12953958/how-to-create-an-arff-file-from-an-array-in-java
 */