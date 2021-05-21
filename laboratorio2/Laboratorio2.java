

import java.util.*; 
import java.io.*;   
import java.lang.*;
import java.lang.reflect.Array; 

public class Laboratorio2{
    public static void main(String[] args) throws IOException{
    
        BufferedReader reader;
        BufferedWriter writer;
      
        
        //File folder = new File("./csv");
        //File folder = new File("C:\\Users\\Cristián Rodríguez\\Documents\\NetBeansProjects\\Laboratorio2\\src\\laboratorio2\\csv");
        File folder = new File("C:\\Users\\Jonas\\Documents\\GitHub\\Lab1EDA\\laboratorio2\\csv");
        
        File[] fileList = folder.listFiles();
        ArrayList<String> files = new ArrayList<String>();//nombres
        ArrayList<String> filenames = new ArrayList<String>(); //rutas
        
        /*
        //para el arbol binario (la 4)
        ArrayList<ArrayList<ArrayList<String>>> datasets = new ArrayList<ArrayList<ArrayList<String>>>();
        ArrayList<ArrayList<tripleta>> datasets_triple = new ArrayList<ArrayList<tripleta>>();
        */
        MaxPQ<tripleta> Bigpq = new MaxPQ<tripleta>();
        ArrayList<tripleta> BigArr = new ArrayList<tripleta>();
        ArrayList<ArrayList<tripleta>> dataSet_Triple = new ArrayList<ArrayList<tripleta>>();
        ArrayList<tripleta> triple_data = new ArrayList<tripleta>();
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
                //Arreglo de tripletas
                ArrayList<tripleta> t_count = new ArrayList<tripleta>();
                //quita la categoria
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
                    if(!found){ //cat= nombre categoria sin scv ni mx-
                        //tripleta new_tripleta = new tripleta(cat, prod_name);
                        t_count.add(new tripleta(cat,prod_name));
                    }     
                }
                //datasets.add(dataset);
                //añade trpleta a arraylist de tripletas
                for(tripleta  t: t_count){
                    triple_data.add(t);
                } 
           
                //Lista usada para los ordenamientos
                ArrayList<tripleta> datasetSort = new ArrayList<tripleta>(); 
                BigArr.addAll(t_count);
                datasetSort.addAll(t_count);

                /*System.out.println("Arreglo desordenado" + cont );
                for(tripleta t : datasetSort){
                    System.out.print(t.get_conteo() + " ");
                }
                System.out.println("");
                */
                //necesitamos la Cola de prioridad en todos los ciclos
                MaxPQ<tripleta> pq = new MaxPQ<tripleta>();
                
                if(cont == 0){//para que funcione en el primer archivo.
        
                    SelectionSort selectionSort = new SelectionSort();
                        
                    //Selection Sort:
                    long nano_startTime1 = System.nanoTime();
                    selectionSort.sort(datasetSort);
                    long nano_endTime1 = System.nanoTime();
                    
                    /*
                    System.out.println("Arreglo ordenado con SelectionSort:");
                    selectionSort.printArray(datasetSort);
                    */
                    
                    System.out.println("");
                    
                    System.out.println("Tiempo ejecución SelectionSort nano segundos: " + (nano_endTime1 - nano_startTime1 ));
                    
                    Collections.copy(datasetSort,t_count);
                    
                    /* System.out.println("Arreglo desordenado");
                    for(tripleta t : datasetSort){
                        System.out.print(t.get_conteo() + " ");
                    }*/
                    System.out.println("");
                    
                    //MERGE SORT:
                    MergeSort mergeSort = new MergeSort(datasetSort);
                
                    long nano_startTime2 = System.nanoTime();
                    mergeSort.sort(0, datasetSort.size()-1);
                    long nano_endTime2 = System.nanoTime();
                    System.out.println("Tiempo ejecución MergeSort nano segundos: " + (nano_endTime2 - nano_startTime2 ));
                    
                    /*System.out.println("Arreglo ordenado con MergeSort:");
                    for(tripleta t : datasetSort){
                        System.out.print(t.get_conteo() + " ");
                    }*/
                    System.out.println("");
            
        
                    Collections.copy(datasetSort,t_count);
                    
                    /*System.out.println("Arreglo desordenado");
                    for(tripleta t : datasetSort){
                        System.out.print(t.get_conteo() + " ");
                    }*/
                    System.out.println("");
                    

                    //HEAP SORT:
                    //System.out.println("Arreglo ordenado con HeapSort");
                    tripleta[] data;
                    data = new tripleta[datasetSort.size()];
                    
                    long nano_startTime3 = System.nanoTime();
                    for(int i =0; i<datasetSort.size(); i++){
                        data[i] = datasetSort.get(i);    
                        pq.insert(data[i]);
                        Bigpq.insert(data[i]);
                    }
                    long nano_endTime3 = System.nanoTime();
                    System.out.println("Tiempo ejecución HeapSort nano segundos: " + (nano_endTime3 - nano_startTime3 ));
                            
                    /* while(!pq.isEmpty()){
                        System.out.print(pq.delMax().get_conteo() + " ");
                    }
                        */
                }
                else{//otros ciclos
                    tripleta[] data;
                    data = new tripleta[datasetSort.size()];
                
                    for(int i =0; i<datasetSort.size(); i++){
                        data[i] = datasetSort.get(i);    
                        pq.insert(data[i]);
                        Bigpq.insert(data[i]);
                    }
            
                }
                
               
                //PQ que obtiene los 3 elementos más y menos comprados de cada archivo csv.
                tripleta[] data;
                data = new tripleta[t_count.size()];
            
                for(int i =0; i<t_count.size(); i++){
                    data[i] = t_count.get(i);    
                    pq.insert(data[i]);
                }    
            
                ArrayList<String> tres = new ArrayList<String>();
                tres =  pq.print3(pq);
                //System.out.println(tres);
           
                writer.write("Tres productos más y menos solicitados de " +cat + ":" + "\n");
                for(int i =0; i< tres.size(); i++){
                    writer.write(tres.get(i) + "\n");
                }
                writer.write("\n");
                    
                //BST que responde a preg del usuario.
           
            
            
            
                //Ideas: meter TODAS tripletas en la pq, de forma que esten ordenadas,
                    //luego podemos meterlas con su contador asociado a la cantidad comprada
                    //ahi hacer el cambio en el bst tal que si 2 tienen mismo contador, las meta en el arraylist de tripletas
            
            
            
            
                reader.close();
                cont++;  
            }
             writer.close();
             System.out.println(Bigpq.size());
             System.out.println(Bigpq.max().get_conteo());
             
              
            BST<Integer, ArrayList<tripleta>> bst = new BST<Integer,ArrayList<tripleta>>();
            
              while(!Bigpq.isEmpty()){
                BigArr.add(Bigpq.delMax());
            }
            boolean primero =true;
            ArrayList<tripleta> aux = new ArrayList<tripleta>();
            for(int i =0;i<BigArr.size()-1;i++){
            if(primero == true){
            aux.add(BigArr.get(i));
            primero = false;
            }
            if((BigArr.get(i).get_conteo() == BigArr.get(i+1).get_conteo())){
            aux.add(BigArr.get(i+1));
            
                if(i ==BigArr.size() -1){
                bst.put(BigArr.get(i).get_conteo(), aux);
                }
            
            }else{
                bst.put(BigArr.get(i).get_conteo(), aux);
                aux.clear();
                primero = true;
            }
            }
            //la 4
            BST<Integer, ArrayList<tripleta>> theArbol = new BST<Integer,ArrayList<tripleta>>();
            //<cantidad, tripletas con esa cantidad>
            //definir tripleta con los conteo
            //añadir tripletas de a una
            Map<Integer,ArrayList<tripleta>> cantidadTriple = new HashMap<Integer,ArrayList<tripleta>>();
            ArrayList<tripleta> auxi;
            for(tripleta tri : triple_data){

                if(cantidadTriple.containsKey(tri.get_conteo())){
                    cantidadTriple.get(tri).add(tri);
                    
                }
                else{
                    auxi = new ArrayList<tripleta>();
                    auxi.add(tri);
                    cantidadTriple.put(tri.get_conteo(),auxi);
                }

            }   
            //meter todo al theArbol
            for(Integer k: cantidadTriple.keySet()){
                theArbol.put(k, cantidadTriple.get(k));
            }  

            //crear arraylist para cada cantidad de ventaas meter todos con la misma cantidad a ese arreglo


            //fin 4
            //test
            Scanner sc = new Scanner(System.in);
            System.out.println("Qué desea hacer? Seleccione un numero: ");
            System.out.println("1. Productos que han vendido más de x unidades");
            System.out.println("2. Productos que han vendido menos de x unidades");
            System.out.println("3. Productos que han vendido exactamente x unidades");
            int opcion = sc.nextInt();
            
            if(opcion == 1){
               
                System.out.println("Ingrese unidad x");
                int x = sc.nextInt();
                
                System.out.println(bst.keys(x, bst.max()));
          
            }
            
            if(opcion ==2){
                System.out.println("Ingrese unidad x");
                int x = sc.nextInt();
                
                System.out.println(bst.keys(bst.min(),x));
                
            }
            if(opcion == 3){
                System.out.println("Ingrese unidad x");
                int x = sc.nextInt();
                
                bst.get(x).forEach((t) -> {
                    System.out.println("producto " + t.get_producto() + "conteo "+ t.get_conteo());
                });
            }
            // fin test
            
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
    }
  
}



   /*
            while(!Bigpq.isEmpty()){
                BigArr.add(Bigpq.delMax());
            }
            boolean primero =true;
            ArrayList<tripleta> aux = new ArrayList<tripleta>();
            for(int i =0;i<BigArr.size()-1;i++){
            if(primero == true){
            aux.add(BigArr.get(i));
            primero = false;
            }
            if((BigArr.get(i).get_conteo() == BigArr.get(i+1).get_conteo())){
            aux.add(BigArr.get(i+1));
            
                if(i ==BigArr.size() -1){
                bst.put(BigArr.get(i).get_conteo(), aux);
                }
            
            }else{
                bst.put(BigArr.get(i).get_conteo(), aux);
                aux.clear();
                primero = true;
            }

           
            }
            
             
            */