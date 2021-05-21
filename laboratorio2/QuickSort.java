
package laboratorio2;

import Laboratorio2.tripleta;
import java.util.*;

public class QuickSort {
    
    private static ArrayList<tripleta> arr = new ArrayList<tripleta>();
            
        public QuickSort(ArrayList<tripleta> inputArr){
            QuickSort.arr = inputArr;
        }
     
        public void sort(int low,int high){
            int q;
            if(low<high){
                q = partition(low, high);
                sort(low, q);
                sort(q+1, high);
            }
        }
     
        public ArrayList<tripleta> getSortedArray(){
            return QuickSort.arr;
        }
     
        int partition(int low,int high){

            int init = low;
            int length = high;
            //el pivote sera el ultimo elemento de las tripletas
            tripleta pivot = arr.get(high);

                    
            while(true){

                while(arr.get(length).compareTo(pivot) < 0 && length>low){
                    length--;
                }
                
                while(arr.get(init).compareTo(pivot) > 0 && init<high){
                    init++;
                }
                /*
                while(inputArray.get(length)>pivot && length>low){
                    length--;
                }
                
                while(inputArray.get(init)<pivot && init<high){
                    init++;
                }
                */

                if(init<length){
                    tripleta temp;
                    temp = arr.get(init);
                    arr.set(init,arr.get(length));
                    arr.set(length,temp);
                    length--;
                    init++;

                }else{

                    return length;
                }
            }
            
        }
}