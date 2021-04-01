
package laboratorio1;

import java.util.*; 
import java.lang.*;
import java.io.*;   


public class Laboratorio1 {
    public static void main(String[] args) {
    
        BufferedReader reader;
        BufferedWriter writer;
        // File folder = new File("./mx-amazon-devices.csv");
        File folder = new File("C:\\Users\\Cristián Rodríguez\\Documents\\NetBeansProjects\\Laboratorio1\\src\\laboratorio1\\csv");
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
            
            
        // System.out.println(fileList[i].getName());
  } 
}
   // System.out.println(filenames.size());    
   
       //String[] filenames =  {"./mx-amazon-devices.csv"};
        try{
        System.out.println(filenames.size());
        for(String fn : filenames){
        ArrayList<ArrayList<String>> dataset = new ArrayList<ArrayList<String>>();
        String outfilename = fn.substring(0,fn.length() - 4) + new String("_out") + fn.substring(fn.length()- 4);
        //System.out.println(outfilename);  
        
        reader = new BufferedReader(new FileReader(fn));
        writer = new BufferedWriter(new FileWriter(outfilename, false));
        
        String line = reader.readLine();
        
        while(line != null){
                     ArrayList<String> parsing1 = new ArrayList<String>(); 
                     String[] row1;
                     row1 = line.split("\\|",-1);//string linea ahora es un arreglo de strings divididos por |
                     for(String x : row1){
                         
                         parsing1.add(x);
                     }
                     
                     dataset.add(parsing1);
                     line = reader.readLine();
                 }
        
        ArrayList<tripleta> t_count = new ArrayList<tripleta>();
     
       
       String n = fileList[cont].getName();
             n= n.substring(3, n.length()-4);
       //     System.out.println(n);
       
        for(int progress_index = 1; progress_index < dataset.size(); progress_index++){//Linea 0 no aporta datos
            
        String prod_name = dataset.get(progress_index).get(3);//nombre del producto
       // System.out.println("nombre: "+prod_name);
        boolean found = false;
        
        for(tripleta search : t_count){
            
       
            
        if(search.get_producto().equals(prod_name)){
            System.out.println("get producto: " +search.get_producto());
            found = true;
         
            search.incConteo();
           
        }
          
        if(!found){
                                            //n= nombre archivo sin scv ni mx-
            tripleta new_tripleta = new tripleta(n, prod_name);
            t_count.add(new_tripleta);
            
        }
          
            }
        
        }
        
        Collections.sort(t_count);
        
        cola queue = new cola();
        pila stack = new pila();
        
        for(tripleta t_d : t_count){
            queue.enqueue(t_d);
        }
        
        while(!queue.isEmpty()){
            stack.push((tripleta)queue.dequeue());
        }
        
        while(!stack.isEmpty()){
            tripleta t_p;
            t_p = (tripleta)stack.pop();
            writer.write(t_p.get_categoria() + "-" + t_p.get_producto() + ", comprado " + t_p.get_conteo() + "veces"+"\n");
        }
        
        
       /* for(tripleta t_d : t_count){  //imprime ordenado
            
        writer.write(t_d.get_categoria() + ", " + t_d.get_producto() + ", " + t_d.get_conteo() +"\n");
        }*/
        
       /* for(ArrayList a : dataset){   //imprime tal cual, sin orden ni nada
            
            for(int j = 0; j < a.size()-1; j++){
            
                writer.write(a.get(j).toString());
                writer.write("|");
                writer.write(a.get(j).toString());
             }
       
        writer.write("\n");

        }*/ 

        reader.close();
        writer.close();
        cont++;
        }
        
        }catch(IOException e){
            e.printStackTrace();
        }
       
    }
    
}
