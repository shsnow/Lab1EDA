import java.util.ArrayList;

public class MaxHeap 
{
    private ArrayList<tripleta> Heap;

    public MaxHeap() 
    {
        Heap = new ArrayList<tripleta>();
    }

    private int parent(int pos) {
        return (pos-1)/2;
    }

    private int leftChild(int pos) {
        return (2*pos)+1;
    }

    private int rightChild(int pos) {
        return (2*pos)+2;
    }

    private void swap(int fpos, int spos) {
        tripleta tmp;
        tmp = (tripleta)(Heap.get(fpos));
        Heap.set(fpos,Heap.get(spos));
        Heap.set(spos,tmp);
    }

    private void downHeapify(int pos) 
    {
        if (pos >= ((Heap.size()-1)/2) && pos <= (Heap.size()-1))
        {
            return;
        }
        if ((((tripleta)Heap.get(pos)).compareTo(((tripleta)Heap.get(leftChild(pos)))) > 0) || 
            (((tripleta)Heap.get(pos)).compareTo(((tripleta)Heap.get(rightChild(pos)))) > 0))
        {
            if (((tripleta)Heap.get(rightChild(pos))).compareTo(((tripleta)Heap.get(leftChild(pos)))) > 0)
            {
                swap(pos, leftChild(pos));
                downHeapify(leftChild(pos));
            } 
            else 
            {
                swap(pos, rightChild(pos));
                downHeapify(rightChild(pos));
            }
        }
    }

    private void heapifyUp(int pos) 
    {
        tripleta temp = (tripleta)Heap.get(pos);
        while(pos>0 && ((tripleta)Heap.get(parent(pos))).compareTo(temp) > 0)
        {
            Heap.set(pos,(tripleta)Heap.get(parent(pos)));
            pos = parent(pos);
        }
        Heap.set(pos,temp);
    }

    public void insert(tripleta element) 
    {
        Heap.add(element);
        int current = Heap.size()-1;
        heapifyUp(current);
    }

    public void print() 
    {
        for (int i=0; i<(Heap.size()-1)/2; i++) 
        {
            System.out.print(+ ((tripleta)Heap.get(i)).get_conteo() + ": L- " + ((tripleta)Heap.get((2*i)+1)).get_conteo() + " R- " + ((tripleta)Heap.get((2*i)+2)).get_conteo());
            System.out.println();
        }
    }

    public tripleta extractMax() 
    {
        tripleta max = ((tripleta)Heap.get(0));
        Heap.set(0,(tripleta)Heap.get(Heap.size()-1));
        Heap.remove(Heap.size()-1);
        downHeapify(0);
        return max;
    }

    public static void main(String[] arg)
    {
        MaxHeap maxHeap = new MaxHeap();
        tripleta t1, t2, t3, t4, t5, t6, t7;
        t1 = new tripleta("A", "B", 6);
        t2 = new tripleta("A", "C", 4);
        t3 = new tripleta("B", "A", 10);
        t4 = new tripleta("B", "C", 1);
        t5 = new tripleta("B", "D", 21);
        t6 = new tripleta("D", "F", 17);
        t7 = new tripleta("R", "X", 13);
        maxHeap.insert(t1);
        maxHeap.insert(t2);
        maxHeap.insert(t3);
        maxHeap.insert(t4);
        maxHeap.insert(t5);
        maxHeap.insert(t6);
        maxHeap.insert(t7);

        maxHeap.print();
        System.out.println("The max is " + maxHeap.extractMax().get_conteo());
    }
}