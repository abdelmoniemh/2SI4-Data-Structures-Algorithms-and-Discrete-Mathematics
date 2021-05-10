public class MaxHeap {
    private Integer[] Heap;
    private int heapSize;
    private int arraySize;

    public MaxHeap(int n){ // Time : O(1) // Space : S(n)
        Heap = new Integer[2*n];
        arraySize = n;
        heapSize = 0;
    }

    public MaxHeap(Integer[] array){ // Time : O(nlogn) // Space : S(n)
        arraySize = 2*array.length;
        Heap = new Integer[arraySize];
        for (Integer i:array){
            insert((int)i);
        }

    }

    public void insert(int n){ // Average Time : Log(n) Worst Case Time : O(n) // Average Space : 1 Worst Case Space : S(n)
        if ((heapSize+1)<arraySize){
            Heap[heapSize] = (Integer)n;
            heapSize++;

            int index = heapSize-1;
            while(hasParent(index) && Heap[index]>getParent(index)){
                swap(index, getParentIndex(index));
                index = getParentIndex(index);
            }

        } else {
            resize();
            Heap[heapSize] = (Integer)n;
            heapSize++;

            int index = heapSize-1;
            while(hasParent(index) && Heap[index]>getParent(index)){
                swap(index, getParentIndex(index));
                index = getParentIndex(index);
            }

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

    private int deleteMax(){ // Time: Log(n) // Space: 1 
        int maxValue = Heap[0].intValue();
        swap(0, heapSize-1);
        Heap[heapSize-1] = null;

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

        heapSize--;
        return maxValue;

    }

    public String toString(){
        String output = "";
        for (int i = 0;i<heapSize;i++)
            output += Heap[i].toString() + ",";
        return output;

    }

    public static void heapsort(Integer[] arrayToSort){ // Time: O(nlogn)
        MaxHeap newHeap = new MaxHeap(arrayToSort);
        int newHeapSize = newHeap.getSizeHeap();
        for (int i = 0;i<newHeapSize;i++){
            arrayToSort[i] = Integer.valueOf(newHeap.deleteMax());
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