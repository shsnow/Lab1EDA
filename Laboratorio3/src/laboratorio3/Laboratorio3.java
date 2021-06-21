
package laboratorio3;

import Laboratorio3.MaxPQ;
import Laboratorio3.quintupla;

import java.util.*; 
import java.io.*;   
import java.lang.*;

public class Laboratorio3{
    public static void main(String[] args) throws IOException{
    
        BufferedReader reader;
        BufferedWriter writer;

        //File folder = new File("./csv");
        File folder = new File("C:\\Users\\Cristián Rodríguez\\Documents\\NetBeansProjects\\Laboratorio3\\src\\laboratorio3\\csv");
        //File folder = new File("C:\\Users\\Jonas\\Documents\\GitHub\\Lab1EDA\\laboratorio2\\csv");
        
        File[] fileList = folder.listFiles();
        ArrayList<String> files = new ArrayList<String>();//nombres
        ArrayList<String> filenames = new ArrayList<String>(); //rutas
        Map<String, ArrayList<BST>> mapaArboles = new HashMap<String,ArrayList<BST>>();//mapa relacionará categoria con arboles

        ArrayList<quintupla> triple_data = new ArrayList<quintupla>();
        int cont =0;
        for(int i = 0; i < fileList.length; i++){
            if(fileList[i].isFile()){
                try{
                    filenames.add(fileList[i].getCanonicalPath());
                    files.add(fileList[i].getName());
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            } 
        }
        try{
            int largo = filenames.get(0).length();  
            String outfilename = filenames.get(0).substring(0, largo-4) + new String("_tres") + filenames.get(0).substring(largo-4);
            writer = new BufferedWriter(new FileWriter(outfilename, false));
            
            for(String fn : filenames){
                ArrayList<ArrayList<String>> dataset = new ArrayList<ArrayList<String>>();      
                
                reader = new BufferedReader(new FileReader(fn));              
                //lee por lineas ||
                String line = reader.readLine();
        
                while(line != null){
                    ArrayList<String> parsing1 = new ArrayList<String>(); 
                    String[] row1;
                    row1 = line.split("\\|",-1);
                    for(String x : row1){
                        parsing1.add(x);
                    } 
                    dataset.add(parsing1);
                    line = reader.readLine();
                }
                //Arreglo de quintuplas
                ArrayList<quintupla> t_count = new ArrayList<quintupla>();
                //quita la categoria
                String cat = fileList[cont].getName();
                cat = cat.substring(3, cat.length()-4);
                
                //cuantas veces esta en la lista
                for(int progress_index = 1; progress_index < dataset.size(); progress_index++){//Linea 0 no aporta datos
                    String prod_name = dataset.get(progress_index).get(3);//nombre del producto 
                
               
                   String starsString  = dataset.get(progress_index).get(4);   
               
                   
                    String precioString = dataset.get(progress_index).get(9);
                    
                    float stars =0;
                    float precio =0;
                   
                    if(!starsString.isEmpty()){
                        try{
                         stars = Float.parseFloat(starsString); //saca estrellas del producto
                        }catch (NumberFormatException ex){//caso que no pueda convertirse en float
                        }
                    }

                    if(!precioString.isEmpty()){
                        try{
                          precio = Float.parseFloat(precioString); //saca precio max
                        }catch (NumberFormatException ex){
                        }
                    }
                    
                    boolean found = false;
                // Verifica repetidos
                    for(quintupla search : t_count){
                        if(search.get_producto().equals(prod_name)){
                            found = true;
                            search.incConteo();
                            
                            float oldstars = search.get_stars();//estrellas de quintupla antigua mismo nombre.
                            float oldprecio = search.get_precioMax();
                            
                            if(oldstars == 0){ //caso que no haya informacion de las estrellas anteriormente
                            search.set_stars(stars);
                            }else{
                            search.newPromStar(oldstars);//Se recalcula el promedio
                            }

                           if(oldprecio > 0){//habia precio anterior
                               search.incConteoPrecioMax();
                               if(precio >0){//y además tenemos un precio nuevo
                                   search.newPromPrecio(precio);
                               }
                           }else{//no habia precio anterior
                               if(precio >0){//pero hay precio nuevo
                               search.set_precioMax(precio);
                               }
                            }
                        }
                    }    
                    if(!found){ //cat= nombre categoria sin scv ni mx-
                        t_count.add(new quintupla(cat,prod_name,stars,precio));
                    }     
                }
                System.out.println(t_count.size());
                
               /* //añade quintupla a arraylist de quintuplas (Verificar si es util)
                for(quintupla  t: t_count){
                    triple_data.add(t);
                }*/
                
                /*
                //verificacion calculo precio promedio
                for(quintupla t : t_count){
                    System.out.println(t.get_producto() + " Conteo: " + t.get_conteo() + " estrellas: " + t.get_stars() + " precio: " + t.get_precioMax());
                }
                */
                
                //creamos arboles y los metemos al array de arboles
                ArrayList<BST> arboles = new ArrayList<BST>();
                arboles.add(new BST());
                arboles.add(new BST());
                    
            Map<Float,ArrayList<quintupla>> starsMap = new HashMap<Float,ArrayList<quintupla>>();
                ArrayList<quintupla> aux1;
               for(quintupla quin : t_count){

                if(starsMap.containsKey(quin.get_stars())){
                    aux1 = starsMap.get(quin.get_stars());
                    aux1.add(quin);
                    starsMap.put(quin.get_stars(), aux1); 
                }
                else{
                    
                    starsMap.put(quin.get_stars(),new ArrayList<quintupla>());
                    starsMap.get(quin.get_stars()).add(quin);
                }
            }   
                for(Float k: starsMap.keySet()){
                arboles.get(0).put(k, starsMap.get(k));
                }    
                
                
            Map<Float,ArrayList<quintupla>> preciosMap = new HashMap<Float,ArrayList<quintupla>>();
                
               for(quintupla quin : t_count){
                if(quin.get_precioMax() != 0){
                if(preciosMap.containsKey(quin.get_precioMax())){
                    aux1 = preciosMap.get(quin.get_precioMax());
                    aux1.add(quin);
                    preciosMap.put(quin.get_precioMax(), aux1); 
                }
                else{
                    
                    preciosMap.put(quin.get_precioMax(),new ArrayList<quintupla>());
                    preciosMap.get(quin.get_precioMax()).add(quin);
                }
            }
        }   
                for(Float k: preciosMap.keySet()){
                arboles.get(1).put(k, preciosMap.get(k));
                } 
                
      
              //pasamos el array de arboles al mapa (mapaArboles)
              
              mapaArboles.put(cat, arboles);
              
                System.out.println(mapaArboles.get(cat));
              
                cont++;
            }
            
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }
}
        
    