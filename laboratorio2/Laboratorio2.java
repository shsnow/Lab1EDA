package laboratorio2;


import Laboratorio2.MaxPQ;
import Laboratorio2.tripleta;
import java.util.*; 
import java.io.*;   


public class Laboratorio2{
    public static void main(String[] args) throws IOException{
    
        BufferedReader reader;
        BufferedWriter writer;
        
        
        //File folder = new File("./csv");
        File folder = new File("C:\\Users\\Cristián Rodríguez\\Documents\\NetBeansProjects\\Laboratorio2\\src\\laboratorio2\\csv");
        //File folder = new File("C:\\Users\\Jonas\\Documents\\GitHub\\Lab1EDA\\csv");
        
        File[] fileList = folder.listFiles();
        ArrayList<String> files = new ArrayList<String>();//nombres
        ArrayList<String> filenames = new ArrayList<String>(); //rutas
        //para el arbol binario (la 4)
        ArrayList<ArrayList<ArrayList<String>>> datasets = new ArrayList<ArrayList<ArrayList<String>>>();
        ArrayList<ArrayList<tripleta>> datasets_triple = new ArrayList<ArrayList<tripleta>>();
        
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
        }try{
            int indice =0;
            
            int largo = filenames.get(0).length();
            
            String outfilename = filenames.get(0).substring(0, largo-4) + new String("_tres") + filenames.get(0).substring(largo-4);
            writer = new BufferedWriter(new FileWriter(outfilename, false));
            for(String fn : filenames){
                ArrayList<ArrayList<String>> dataset = new ArrayList<ArrayList<String>>();   
                //System.out.println(fn);
                    
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
                //
                ArrayList<tripleta> t_count = new ArrayList<tripleta>();
                //quita el categoria
                String cat = fileList[cont].getName();
                cat = cat.substring(3, cat.length()-4);
                //
                //cuantas veces esta en la lista
                for(int progress_index = 1; progress_index < dataset.size(); progress_index++){//Linea 0 no aporta datos
                    String prod_name = dataset.get(progress_index).get(3);//nombre del producto 
                    boolean found = false;
                // define las tripletas    
                    for(tripleta search : t_count){
                        if(search.get_producto().equals(prod_name)){
                            found = true;
                            search.incConteo();    
                        }
                    }
                    //  
                    if(!found){ //cat= nombre categoria sin scv ni mx-
                        //tripleta new_tripleta = new tripleta(cat, prod_name);
                        t_count.add(new tripleta(cat,prod_name));
                    }     
                }
                datasets.add(dataset);
                datasets_triple.add(t_count);
                //
                //cola prioridad  escribir en un archivo los 3 productos mas y menos comprados por archivo
                //
           
            // comparar OE y tiempo de 3 algoritmos de ordenamiento
            ArrayList<tripleta> datasetSort = datasets_triple.get(0);
            ArrayList<tripleta> datasetUnsort = datasets_triple.get(0);
            int length = datasetSort.size() -1;
           long inicio,fin;
           duble duracion;

            inicio = System.nanoTime();
            MergeSort mergeSort = new MergeSort(datasetSort);
            mergeSort.sort(0, length);
            fin = System.nanoTime();
            duracion = (double)(fin - inicio)/1000000;
            System.out.println("el tiempo de duracion del mergeSort es : " + duracion);
            datasetSort = datasetUnsort;

            inicio = System.nanoTime();
            SelectionSort selectionSort = new SelectionSort();       
            selectionSort.sort(datasetSort);     
            fin = System.nanoTime();
            duracion = (double)(fin - inicio)/1000000;
            System.out.println("el tiempo de duracion del selectionSort es : " + duracion);
            datasetSort = datasetUnsort;

            inicio = System.nanoTime();
            QuickSort quickSort = new QuickSort(datasetSort);
            quickSort.sort(0, length);
            fin = System.nanoTime();
            duracion = (double)(fin - inicio)/1000000;
            System.out.println("el tiempo de duracion del heapSort es : " + duracion);
            
            MaxPQ<tripleta> pq = new MaxPQ<tripleta>();
            if(indice ==0){//para que funcione en el primer archivo, falta comparar tiempos y OE.
          //  Selection Sort:
          
          //  selectionSort.sort(datasetSort);
          //  selectionSort.printArray(datasetSort); //funciona
            
            
            
           // datasetSort = datasetUnsort;
           //MERGE SORT:
           //mergeSort.sort(0, datasetSort.size()-1);   //funciona, comprobar imprimiendo el datasetSort
           
           
           //HEAP SORT:
          /*  tripleta[] data;
            data = new tripleta[datasetUnsort.size()];
            
            for(int i =0; i<datasetUnsort.size(); i++){
            data[i] = datasetUnsort.get(i);    
            pq.insert(data[i]);
            }

            while(!pq.isEmpty()){
            System.out.println(pq.delMax().get_conteo());
        }
        */

            }
            
            tripleta[] data;
            data = new tripleta[t_count.size()];
            
            for(int i =0; i<t_count.size(); i++){
            data[i] = t_count.get(i);    
            pq.insert(data[i]);
            }    
            
        ArrayList<String> tres = new ArrayList<String>();
        tres =  pq.print3(pq);
        //System.out.println(tres);
           
        writer.write("Tres productos más y menos solicitados de " +cat + "\n");
        for(int i =0; i< tres.size(); i++){
            writer.write(tres.get(i) + "\n");
            
            }
            writer.write("\n");
            //datasetSort = datasetUnsort;
            //quickSort.sort(0, datasetSort.size()-1);
            
            //
            // arbol binario compara todos los dataset
            // 
        reader.close();
       
        cont++;  
            }
             writer.close();
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
    }
  
}