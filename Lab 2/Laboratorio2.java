import java.util.*; 
import java.io.*;   


public class Laboratorio2{
    public static void main(String[] args) throws IOException{
    
        BufferedReader reader;
        //File folder = new File("./csv");
        //File folder = new File("C:\\Users\\Cristián Rodríguez\\Documents\\NetBeansProjects\\Laboratorio1\\src\\laboratorio1\\csv");
        File folder = new File("C:\\Users\\Jonas\\Documents\\GitHub\\Lab1EDA\\csv");
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
        }

        try{
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



            }


            // comparar OE y tiempo de 3 algoritmos de ordenamiento
            ArrayList<tripleta> datasetSort = datasets_triple.get(0);
            ArrayList<tripleta> datasetUnsort = datasets_triple.get(0);
            QuickSort quickSort = new QuickSort(datasetSort);
            MergeSort mergeSort = new MergeSort(datasetSort);
            SelectionSort selectionSort = new SelectionSort();
            //adaptar los sort a vectores y usar las tripletas para comparar
            selectionSort.sort(datasetSort);
            
            datasetSort = datasetUnsort;
            mergeSort.sort(0, datasetSort.size()-1);
            
            datasetSort = datasetUnsort;
            quickSort.sort(0, datasetSort.size()-1);
            //


            // arbol binario compara todos los dataset



            //
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
    }
  
}
