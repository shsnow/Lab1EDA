package laboratorio3;

import Laboratorio3.MaxPQ;
import Laboratorio3.quintupla;
import laboratorio3.MST;

import java.util.*;
import java.io.*;
import java.lang.*;

public class Laboratorio3 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader;
        BufferedWriter writer;

        //File folder = new File("./csv");
        File folder = new File("C:\\Users\\Cristián Rodríguez\\Documents\\NetBeansProjects\\Laboratorio3\\src\\laboratorio3\\csv");
        //File folder = new File("C:\\Users\\Jonas\\Documents\\GitHub\\Lab1EDA\\laboratorio2\\csv");

        File[] fileList = folder.listFiles();
        ArrayList<String> files = new ArrayList<String>();//nombres
        ArrayList<String> filenames = new ArrayList<String>(); //rutas
        Map<String, ArrayList<BST>> mapaArboles = new HashMap<String, ArrayList<BST>>();//mapa relacionará categoria con arboles

        ArrayList<quintupla> triple_data = new ArrayList<quintupla>();
        int cont = 0;
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {
                try {
                    filenames.add(fileList[i].getCanonicalPath());
                    files.add(fileList[i].getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            int largo = filenames.get(0).length();
   
            for (String fn : filenames) {
                ArrayList<ArrayList<String>> dataset = new ArrayList<ArrayList<String>>();
                
            //writer = new BufferedWriter(new FileWriter(fn, false)); 

                reader = new BufferedReader(new FileReader(fn));
                //lee por lineas ||
                String line = reader.readLine();

                while (line != null) {
                    ArrayList<String> parsing1 = new ArrayList<String>();
                    String[] row1;
                    row1 = line.split("\\|", -1);
                    for (String x : row1) {
                        parsing1.add(x);
                    }
                    dataset.add(parsing1);
                    line = reader.readLine();
                }
                //Arreglo de quintuplas
                ArrayList<quintupla> t_count = new ArrayList<quintupla>();
                //quita la categoria
                String cat = fileList[cont].getName();
                cat = cat.substring(3, cat.length() - 4);

                //cuantas veces esta en la lista
                for (int progress_index = 1; progress_index < dataset.size(); progress_index++) {//Linea 0 no aporta datos
                    String prod_name = dataset.get(progress_index).get(3);//nombre del producto 

                    String starsString = "0";
                    String precioString = "0";
                    if (dataset.get(progress_index).size() >= 8) {
                        starsString = dataset.get(progress_index).get(4);
                        precioString = dataset.get(progress_index).get(9);

                    }

                    float stars = 0;
                    float precio = 0;

                    if (!starsString.isEmpty()) {
                        try {
                            stars = Float.parseFloat(starsString); //saca estrellas del producto
                        } catch (NumberFormatException ex) {//caso que no pueda convertirse en float
                        }
                    }

                    if (!precioString.isEmpty()) {
                        try {
                            precio = Float.parseFloat(precioString); //saca precio max
                        } catch (NumberFormatException ex) {
                        }
                    }

                    boolean found = false;
                    // Verifica repetidos
                    for (quintupla search : t_count) {
                        if (search.get_producto().equals(prod_name)) {
                            found = true;
                            search.incConteo();

                            float oldstars = search.get_stars();//valor de estrellas guardado anteriormente
                            float oldprecio = search.get_precioMax();

                            if (oldstars > 0f) {//habia informacion de estrellas anteriormente
                                if (stars > 0f) {//hay nueva puntuacion estrellas
                                    search.incConteoStars();
                                    search.set_stars(search.newPromStar(stars));//recalcula el promedio.
                                }
                            } else {//no habia info de estrellas anteriormente
                                if (stars > 0) {
                                    search.incConteoStars();
                                    search.set_stars(stars);

                                }
                            }

                            if (oldprecio > 0f) {//habia precio anterior

                                if (precio > 0f) {//y además tenemos un precio nuevo
                                    search.incConteoPrecioMax();
                                    search.set_precioMax(search.newPromPrecio(precio));
                                }
                            } else {//no habia precio anterior
                                if (precio > 0f) {//pero hay precio nuevo
                                    search.incConteoPrecioMax();
                                    search.set_precioMax(precio);
                                }
                            }
                        }
                    }
                    if (!found) { //cat= nombre categoria sin scv ni mx-
                        t_count.add(new quintupla(cat, prod_name, stars, precio));
                    }
                }
                System.out.println(t_count.size());

                /* //añade quintupla a arraylist de quintuplas (Verificar si es util)
                for(quintupla  t: t_count){
                    triple_data.add(t);
                }*/
                System.out.println(cat);
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

                Map<Float, ArrayList<quintupla>> starsMap = new HashMap<Float, ArrayList<quintupla>>();
                ArrayList<quintupla> aux1;
                for (quintupla quin : t_count) {

                    if (starsMap.containsKey(quin.get_stars())) {
                        aux1 = starsMap.get(quin.get_stars());
                        aux1.add(quin);
                        starsMap.put(quin.get_stars(), aux1);
                    } else {

                        starsMap.put(quin.get_stars(), new ArrayList<quintupla>());
                        starsMap.get(quin.get_stars()).add(quin);
                    }
                }
                for (Float k : starsMap.keySet()) {
                    arboles.get(0).put(k, starsMap.get(k));
                }

                Map<Float, ArrayList<quintupla>> preciosMap = new HashMap<Float, ArrayList<quintupla>>();

                for (quintupla quin : t_count) {
                    if (quin.get_precioMax() != 0) {
                        if (preciosMap.containsKey(quin.get_precioMax())) {
                            aux1 = preciosMap.get(quin.get_precioMax());
                            aux1.add(quin);
                            preciosMap.put(quin.get_precioMax(), aux1);
                        } else {

                            preciosMap.put(quin.get_precioMax(), new ArrayList<quintupla>());
                            preciosMap.get(quin.get_precioMax()).add(quin);
                        }
                    }
                }

                for (Float k : preciosMap.keySet()) {
                    arboles.get(1).put(k, preciosMap.get(k));
                }

                //pasamos el array de arboles al mapa (mapaArboles)
                mapaArboles.put(cat, arboles);

                // System.out.println(mapaArboles.get(cat));
                cont++;

                //matriz adyacencia.
                int tamQuintuplas = t_count.size();

                float[][] mAdyacencia = new float[tamQuintuplas][tamQuintuplas];
                for (int i = 0; i < tamQuintuplas; i++) {
                    for (int j = 0; j < tamQuintuplas; j++) {
                        mAdyacencia[i][j] = (float) Integer.MAX_VALUE;

                    }
                }

                for (int i = 0; i < tamQuintuplas; i++) {
                    for (int j = 0; j < tamQuintuplas; j++) {
                        if (i != j) {
                            if (t_count.get(i).get_precioMax() != 0f && t_count.get(j).get_precioMax() != 0f) {

                                mAdyacencia[i][j] = Math.abs(t_count.get(i).get_precioMax() - t_count.get(j).get_precioMax());
                            } else {

                            }
                        } else {
                        }
                    }
                }

                //ejectutamos algoritmos
                //guardamos la informacion en archivo respectivo
                int contador = 0;
                MST alg1 = new MST();
                if (t_count.get(0).get_precioMax() != 0f) {

                    alg1.prim(mAdyacencia, tamQuintuplas, contador);

                } else {
                    while (t_count.get(contador).get_precioMax() == 0f && contador < t_count.size() - 1) {
                        contador++;
                    }
                    if (t_count.get(contador).get_precioMax() != 0f) {//verificacion extra en caso que ningun elemento del csv tenga precio max
                        alg1.prim(mAdyacencia, tamQuintuplas,contador);
                    } else {
                        System.out.println("No hay elementos para analizar con prim.");
                    }
                }
                
                    SP alg2 = new SP();

                    if (t_count.get(0).get_precioMax() != 0f) {

                        alg2.dijkstra(mAdyacencia, tamQuintuplas, 0);

                    } else {
                        while (t_count.get(contador).get_precioMax() == 0f && contador < t_count.size() - 1) {
                            contador++;
                        }
                        if (t_count.get(contador).get_precioMax() != 0f) {
                            alg2.dijkstra(mAdyacencia, tamQuintuplas, contador);
                        } else {
                            System.out.println("No hay elementos para analizar con dijkstra.");
                        }
                    }

                }

            }catch (NullPointerException e) {
            e.printStackTrace();
          }
        }
    }
    /*prim: encuentra ruta en que todos los nodos estén conectados
dijkstra: Encuentra la ruta optima entre 1 nodo y todo el resto.

Explicación:

 Al ejecutar el algoritmo de prim, este analiza las diferencias de los precios máximos entre ellas.

Al ejecutar el algoritmo de Dijkstra, se analiza la diferencia de precios maximos de un producto con las del resto, 
para imprimir en pantalla una "ruta", que es una sucecion de precios maximos que dada la primer diferencia de precios, 
el siguiente termino en la suceción es la diferencia más cercana a la anterior (básicamente, la diferencia con el valor 
numérico más cercano al anterior).





     */
