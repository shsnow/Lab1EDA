public class QuickSort {
    private static ArrayList<tripleta> inputArray = new ArrayList<tripleta>();
            
        public QuickSort(ArrayList<tripleta> inputArr){
            QuickSort.inputArray = inputArr;
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
            return QuickSort.inputArray;
        }
     
        int partition(int low,int high){

            int init = low;
            int length = high;
            //el pivote sera el ultimo elemento de las tripletas
            int pivot = inputArray.get(high);

                    
            while(true){

                while(inputArray.get(length).compareTo(pivot) < 0 && length>low){
                    length--;
                }
                
                while(inputArray.get(init).compareTo(pivot) > 0 && init<high){
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
                    temp = inputArray.get(init);
                    inputArray.set(init,inputArray.get(length));
                    inputArray.set(length,temp);
                    length--;
                    init++;

                }else{
                    System.out.println("\n---------Iteration highs---------");
                    return length;
                }
            }
            
        }
}