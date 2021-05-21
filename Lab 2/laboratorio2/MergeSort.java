package laboratorio2;

import Laboratorio2.tripleta;
import java.util.ArrayList;

public class MergeSort{

    private ArrayList<tripleta> inArr;
    
    public ArrayList<tripleta> getSortedArray() {
        return inArr;
    }
 
    public MergeSort(ArrayList<tripleta> inArr){
        this.inArr = inArr;
    }
    
    
    public void sort(int l,int r){
        
        //sort till you breakdown your list to single element
        if(l<r && (r-l)>=1){
            int mid = l +(r - l)/2;
            sort(l, mid);
            sort(mid+1, r);        
            
            //merging Sorted array produce above into one sorted array
            merger(l,mid,r);            
        }       
    }   
    
    public void merger(int l,int m,int r){
        
        //Below is the mergedarray that will be sorted array Array[i-m] , Array[(m+1)-r]
        ArrayList<tripleta> mergesortArr = new ArrayList<tripleta>();
        
        int leftIndex = l;
        int rightIndex = m+1;
        
        while(leftIndex<=m && rightIndex<=r){

            if(inArr.get(leftIndex).compareTo(inArr.get(rightIndex)) > 0){
                mergesortArr.add(inArr.get(leftIndex));
                leftIndex++;
            }else{
                mergesortArr.add(inArr.get(rightIndex));
                rightIndex++;
            }

            /*
            if(inArr.get(leftIndex)<=inArr.get(rightIndex)){
                mergesortArr.add(inArr.get(leftIndex));
                leftIndex++;
            }else{
                mergesortArr.add(inArr.get(rightIndex));
                rightIndex++;
            }
            */
        }       
        
        //Either of below while loop will execute

        while(leftIndex<=m){
            mergesortArr.add(inArr.get(leftIndex));
            leftIndex++;
        }
        
        while(rightIndex<=r){
            mergesortArr.add(inArr.get(rightIndex));
            rightIndex++;
        }

        int i = 0;
        int j = l;
        //Setting sorted array to original one
        while(i<mergesortArr.size()){
            inArr.set(j, mergesortArr.get(i++));
            j++;
        }
    }



    public static void main(String args[])
    {
    }
}