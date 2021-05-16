import java.util.ArrayList;

//arrayList
public class SelectionSort{
    
    void sort(ArrayList<tripletas> arr)
    {  
        int n = arr.size();

        for (int i = 0; i < n-1; i++)
        {
            int min_idx = i;
            for (int j = i+1; j < n; j++){ 
                if(arr.get(j).compareTo(min_idx) > 0){
                   min_idx = j; 
                }
            }
            tripleta temp = arr.get(min_idx);
            arr.set(min_idx,arr.get(i));
            arr.set(i,temp);
        }
    }
    void printArray(ArrayList<tripletas> arr)
    {
        int n = arr.size();
        for (int i=0; i<n; ++i)
            System.out.print(arr.get(i).get_conteo() +" ");
        System.out.println();
    }
    public static void main(String args[]){
    }
}