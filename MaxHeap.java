import org.graalvm.compiler.nodes.extended.NullCheckNode;

public class MaxHeap {
    private Integer[] Heap;
    private int heapSize;
    private int arraySize;

    public MaxHeap(int n){
        Heap = new Integer[2*n];
        arraySize = n;
        heapSize = 0;
    }

    public MaxHeap(Integer[] array){
        arraySize = 2*array.length;
        Heap = new Integer[arraySize];
        for (Integer i:array){
            insert((int)i);
        }

    }

    public void insert(int n){
        if ((heapSize+1)<arraySize){
            Heap[heapSize] = (Integer)n;
            heapSize++;
            heapifyUp();
        } else {
            resize();
            Heap[heapSize] = (Integer)n;
            heapSize++;
            heapifyUp();
        }

    }
    
    private void resize(){
        Integer[] temp = new Integer[2*arraySize];
        for (int i =0;i<heapSize;i++){
            temp[i] = Heap[i];
        }
        Heap = temp;
        arraySize = temp.length;
    }

    private int deleteMax(){
        int maxValue = Heap[0].intValue();
        swap(0, heapSize-1);
        Heap[heapSize-1] = null;
        heapifyDown();
        heapSize--;
        return maxValue;

    }

    public String toString(){
        String output = "";
        for (int i = 0;i<heapSize;i++)
            output += Heap[i].toString() + ",";
        return output;

    }

    public static void heapsort(Integer[] arrayToSort){
        MaxHeap newHeap = new MaxHeap(arrayToSort);
        int newHeapSize = newHeap.getSizeHeap();
        Integer[] temp = new Integer[newHeapSize];
        for (int i = 0;i<newHeapSize;i++){
            //System.out.println(i);
            temp[i] = Integer.valueOf(newHeap.deleteMax());
        }
        for (int i = 0;i<newHeapSize;i++){
            arrayToSort[i] = temp[i];
        }
    }

    public int getSizeArr(){
        return arraySize;
    }
    
    public int getSizeHeap(){
        return heapSize;
    }

    public Integer[] getHeap(){
        return Heap;
    }

    public void heapifyUp(){
        int index = heapSize-1;
        while(hasParent(index) && Heap[index]>getParent(index)){
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    public void heapifyDown(){
        int index = 0;
        while (hasLeftChild(index)){
            int biggerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && (getRightChild(index) > Heap[biggerChildIndex]))
                biggerChildIndex = getRightChildIndex(index);
            
            if (Heap[index] > Heap[biggerChildIndex]){
                break;
            } else {
                if (Heap[biggerChildIndex] != null)
                    swap(index, biggerChildIndex);
            }
            index = biggerChildIndex;    
        }
        
    }

    public void swap(int indexX, int indexY){
        Integer temp = Heap[indexX];
        Heap[indexX] = Heap[indexY];
        Heap[indexY] = temp;

    }

    public Integer getParent(int index){return Heap[getParentIndex(index)];}
    public int getParentIndex(int index){return (index-1)/2;}
    public boolean hasParent(int index){return getParentIndex(index)>=0;}

    public Integer getLeftChild(int index){return Heap[getLeftChildIndex(index)];}
    public int getLeftChildIndex(int index){return 2*index+1;}
    public boolean hasLeftChild(int index){
        if (Heap[ getLeftChildIndex(index)] != null)
            return getLeftChildIndex(index)<heapSize;
        else
            return false;
    }

    public Integer getRightChild(int index){return Heap[getRightChildIndex(index)];}
    public int getRightChildIndex(int index){return 2*index +2;}
    public boolean hasRightChild(int index){
        if (Heap[getRightChildIndex(index)] != null)
            return getRightChildIndex(index)<heapSize;
        else
            return false;
    }

}