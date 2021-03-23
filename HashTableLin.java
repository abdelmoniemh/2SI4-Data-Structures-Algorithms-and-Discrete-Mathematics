import java.math.BigInteger;

public class HashTableLin {

    private Integer[] table;
    private int[] keys;
    private int tableSize;
    private int elements = 0;
    private double load = 0;
    private int maxNum;

    

    public HashTableLin(int maxNum, double load){

        BigInteger tableSize = BigInteger.valueOf((int)Math.floor(maxNum/load));
        tableSize = tableSize.nextProbablePrime();
        table = new Integer[tableSize.intValue()];
        this.tableSize = tableSize.intValue();
        this.load = load;
        this.maxNum = maxNum;
    }

    public void insert(int n){
        if (isIn(n))
            return;
        
        int i = 0;
        int hash = n%tableSize;
        do{
            hash = (hash+1)%tableSize;

        } while(table[hash] != null);

        table[hash] = n;

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
        
        int i = 0;
        int hash = n%tableSize;
        do{
            hash = (hash+1)%tableSize;
            i++;
        } while(table[hash] != null);

        table[hash] = n;

        elements++;
        float currentFactor = (float)elements;
        currentFactor /= (float)tableSize;
        if (currentFactor > load){
            rehash();
        }
        return i;
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
        HashTableLin rehash = new HashTableLin(rehashLen.intValue(), load);
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

    public boolean isIn(int n){
        for (int i = 0;i<table.length;i++){
            if (table[i] == (Integer)n){
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