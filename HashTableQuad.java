import java.math.BigInteger;
public class HashTableQuad {

    private Integer[] table;
    private int[] keys;
    private int tableSize;
    private int elements = 0;
    private double load = 0;
    private int maxNum;

    public HashTableQuad(int maxNum, double load){

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
        
        int i = 1;
        int hash = n%tableSize;
        do{
            hash = (hash+i^2)%tableSize;
            i++;
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
            return isInCount(n)+1;
        
        int i = 1;
        int probe=0;
        int hash = n%tableSize;
        do{
            hash = (hash+i^2)%tableSize;
            i++;
            probe++;
        } while(table[hash] != null);

        table[hash] = n;

        elements++;
        float currentFactor = (float)elements;
        currentFactor /= (float)tableSize;
        if (currentFactor > load){
            rehash();
        }
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
