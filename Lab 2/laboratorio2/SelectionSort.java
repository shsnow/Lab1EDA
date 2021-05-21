
package laboratorio2;

import java.util.ArrayList;
import Laboratorio2.tripleta;
//arrayList
public class SelectionSort{
    public SelectionSort(){
    }
   
    void sort(ArrayList<tripleta> arr){  
        int n = arr.size();

        for (int i = 0; i < n; i++)
        {
            int min_idx = i;
            for (int j = i+1; j < n; j++){ 
                if(arr.get(j).compareTo(arr.get(min_idx)) > 0){//es arr.get(Min_idx), para comparar tripleta con tripleta
                   min_idx = j; 
                }
            }
            tripleta temp = arr.get(min_idx);
            arr.set(min_idx,arr.get(i));
            arr.set(i,temp);
        }
    }
    void printArray(ArrayList<tripleta> arr)
    {
        int n = arr.size();
        for (int i=0; i<n; ++i)
            System.out.print(arr.get(i).get_conteo() +" ");
        System.out.println();
    }
    public static void main(String args[]){
        
       
    }
}