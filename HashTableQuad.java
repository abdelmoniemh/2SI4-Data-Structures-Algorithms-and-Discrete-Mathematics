import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;

public class HashTableQuad {

    private Integer[] table;
    private int tableSize;
    private int elements = 0;
    private double load = 0;
    private int maxNum;
    private ArrayList<Integer> keys = new ArrayList<Integer>();

    

    private boolean isPrime(int n) {
        if (n <= 3) return n > 1;
        else if (n % 2 == 0 || n % 3 == 0) return false;
        int i = 5;
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
            i += 6;
        }

        return true;
    }

    public HashTableQuad(int maxNum, double load){

        int tableSize = (int)Math.floor(maxNum/load);

        while(!isPrime(tableSize))
            tableSize++;

        table = new Integer[tableSize];
        this.tableSize = tableSize;
        this.load = load;
        this.maxNum = maxNum;
    }

    public void insert(int n){
        if (isIn(n))
            return;
        
        int i = 0;
        int hash = n%tableSize;
        int original = n%tableSize;
        do{
            hash = (original+i*i)%tableSize;
            i++;
        } while(table[hash] != null);

        table[hash] = n;
        keys.add(n);

        elements++;
        float currentFactor = (float)elements;
        currentFactor /= (float)tableSize;
        if (currentFactor > load){
            rehash();
        }
    }

    public int insertCount(int n){   
        if (isIn(n))
            return isInCount(n);

        float currentFactor = (float)(elements+1);
        currentFactor /= (float)tableSize;
        if (currentFactor > load)
            fastRehash();
        
        int i = 0;
        int probe = 0;
        int hash = n%tableSize;
        int original = n%tableSize;
        do{
            hash = (original+i*i)%tableSize;
            probe++;
            i++;
        } while(table[hash] != null);
        

        table[hash] = n;

        elements++;

        
        return probe;
    }

    
    
    public int isInCount(int n){
        int probe = 0;
        for (int i = 0;i<tableSize;i++){
            if (table[i] != null)
                probe++;
                if (table[i] == (Integer)n)
                    break;
                
                    
                
        }
        return probe;
    }


    private void rehash(){
        BigInteger rehashLen =  BigInteger.valueOf(2*maxNum);
        rehashLen = rehashLen.nextProbablePrime();
        HashTableQuad rehash = new HashTableQuad(rehashLen.intValue(), load);
        for (int i = 0;i<table.length;i++){
            if (table[i] != null){
                rehash.insert(table[i].intValue());
            }                   
        }
        this.table = rehash.table;
        this.keys = rehash.keys;
        this.tableSize = rehash.tableSize;
        this.elements = rehash.elements;
        this.load = rehash.load;
        this.maxNum = rehash.maxNum;

    }

    private void fastRehash(){
        HashTableQuad rehash = new HashTableQuad(2*maxNum, load);

        for (int i:keys){
            rehash.insert(i);            
        }
        this.table = rehash.table;
        this.keys = rehash.keys;
        this.tableSize = rehash.tableSize;
        this.elements = rehash.elements;
        this.load = rehash.load;
        this.maxNum = rehash.maxNum;

    }



    public boolean isIn(int n){
        for (Integer i:keys){
            if (i == (Integer)n){
                return true; 
            }                
        } 
        
        return false;
    }


    public void printKeys(){
        for (int i = 0;i<tableSize;i++){
            if (table[i] != null)
                System.out.printf("%d, ",table[i]);
        }
        System.out.printf("\n");

    }

    public void printKeysAndIndexes(){
        for (int i = 0;i<tableSize;i++){
            if (table[i] != null)
                System.out.printf("%d, %d\n",i, table[i]);
        }
        
    }

    public int getTableSize(){
        return tableSize;
    }
    public int getNumKeys(){
        return elements;
    }
    public double getMaxLoadFactor(){
        return load;
    }



}