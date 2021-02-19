import java.math.BigInteger;
public class Main {
    public static void main(String[] args) {
        String x = "-9";
        String y = "-0";


        HugeInteger number = new HugeInteger(x);
        HugeInteger number2 = new HugeInteger(y);
        BigInteger n1 = new BigInteger(x);
        BigInteger n2 = new BigInteger(y);


        //HugeInteger Y2 = new HugeInteger(n1.add(n2).toString());
        //HugeInteger Y1 = new HugeInteger(number.add(number2).toString());

        
        HugeInteger X2 = new HugeInteger(n1.multiply(n2).toString());
        HugeInteger X1 = new HugeInteger(number.multiply(number2).toString());
        //System.out.println(X1);
        int test = X1.compareTo(X2);
        //HugeInteger BigIntSum = new HugeInteger(n1.subtract(n2).toString());
        System.out.printf("subtract Comparison to bigInt: %d\n",test);
        //System.out.printf("add Comparison to bigInt: %d\n",Y1.compareTo(Y2));


    }
}
