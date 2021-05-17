import java.util.ArrayList;

public class test {

    public static void main(String[] args) {
        ArrayList<tripleta> datasets_triple = new ArrayList<tripleta>();
        tripleta t1, t2, t3, t4, t5, t6, t7;
        t1 = new tripleta("A", "B", 6);
        t2 = new tripleta("A", "C", 4);
        t3 = new tripleta("B", "A", 10);
        t4 = new tripleta("B", "C", 1);
        t5 = new tripleta("B", "D", 21);
        t6 = new tripleta("D", "F", 17);
        t7 = new tripleta("R", "X", 13);
        datasets_triple.add(t1);
        datasets_triple.add(t2);
        datasets_triple.add(t3);
        datasets_triple.add(t4);
        datasets_triple.add(t5);
        datasets_triple.add(t6);
        datasets_triple.add(t7);


        ArrayList<tripleta> datasetSort = datasets_triple;
        ArrayList<tripleta> datasetUnsort = datasets_triple;
        QuickSort quickSort = new QuickSort(datasetSort);
        MergeSort mergeSort = new MergeSort(datasetSort);
        SelectionSort selectionSort = new SelectionSort();
        //adaptar los sort a vectores y usar las tripletas para comparar
        selectionSort.sort(datasetSort);
        
        //datasetSort = datasetUnsort;
        //mergeSort.sort(0, datasetSort.size()-1);
        
        //datasetSort = datasetUnsort;
        //quickSort.sort(0, datasetSort.size()-1);
        //

    }
}
