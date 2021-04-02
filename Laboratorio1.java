package laboratorio1;

import java.util.*; 
import java.lang.*;
import java.io.*;   


public class Laboratorio1 {
    public static void main(String[] args) {
    
        BufferedReader reader;
        BufferedWriter writerC;
        BufferedWriter writerP;
        // File folder = new File("./mx-amazon-devices.csv");
        File folder = new File("C:\\Users\\Jonas\\Documents\\GitHub\\Lab1EDA\\csv");
        File[] fileList = folder.listFiles();
        
        ArrayList<String> filenames = new ArrayList<String>(); 
       
        int cont =0;
        
        for (int i = 0; i < fileList.length; i++) {
        if(fileList[i].isFile()) {
            try{
         filenames.add(fileList[i].getCanonicalPath());
            }catch(IOException e){
                 e.printStackTrace();
            }
            
            
      
  }
}

        try{

        for(String fn : filenames){
            
        ArrayList<ArrayList<String>> dataset = new ArrayList<ArrayList<String>>();
        String outfilenameC = fn.substring(0,fn.length() - 4) + new String("_outCola") + fn.substring(fn.length()- 4);
        String outfilenameP = fn.substring(0,fn.length() - 4) + new String("_outPila") + fn.substring(fn.length()- 4);
        

         
        reader = new BufferedReader(new FileReader(fn));
        
        writerC = new BufferedWriter(new FileWriter(outfilenameC, false));
        writerP = new BufferedWriter(new FileWriter(outfilenameP, false));
        
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
        
        ArrayList<tripleta> t_count = new ArrayList<tripleta>();
     
       
       String n = fileList[cont].getName();
             n= n.substring(3, n.length()-4);
   
       
        for(int progress_index = 1; progress_index < dataset.size(); progress_index++){//Linea 0 no aporta datos
            
        String prod_name = dataset.get(progress_index).get(3);//nombre del producto
    
       
        boolean found = false;
        
        for(tripleta search : t_count){
 
       
        if(search.get_producto().equals(prod_name)){
            found = true;
         
            search.incConteo();
           
            }
        }
          
        if(!found){
                                            //n= nombre archivo sin scv ni mx-
            tripleta new_tripleta = new tripleta(n, prod_name);
            t_count.add(new tripleta(n,prod_name));

                }
            
        }
        
        Collections.sort(t_count);
        
        cola queue = new cola();
        pila stack = new pila();
        
        for(tripleta t_d : t_count){
            queue.enqueue(t_d);
        }
        //escribe ordenado
         writerC.write("Productos más solicitados de cada categoría:" + "\n");
         for(tripleta t_d : t_count){  
            if(t_d.get_conteo()==1){
            
        writerC.write(t_d.get_categoria() + "-" + t_d.get_producto() + ", " + t_d.get_conteo() +"vez" +"\n");}
            else{
             writerC.write(t_d.get_categoria() + "-" + t_d.get_producto() + ", comprado " + t_d.get_conteo() +" veces"+"\n");
            }
        }
        
        
        while(!queue.isEmpty()){
            stack.push((tripleta)queue.dequeue());
        }
 
        
        writerP.write("Productos más solicitados en orden creciente: " + "\n");
        while(!stack.isEmpty()){    //escribe nuevo orden
            tripleta t_p;
            t_p = (tripleta)stack.pop();
            if(t_p.get_conteo()==1){
              writerP.write(t_p.get_categoria() + "-" + t_p.get_producto() + ", comprado " + t_p.get_conteo() + " vez"+"\n");
            }
            else{
             writerP.write(t_p.get_categoria() + "-" + t_p.get_producto() + ", comprado " + t_p.get_conteo() + " veces"+"\n");
            }
            
        }

        reader.close();
        writerC.close();
        writerP.close();
        cont++;
        }
        
        }catch(IOException e){
            e.printStackTrace();
        }
       
    }
    
}
