import java.math.BigInteger;
public class Main {
    public static void main(String[] args) {
        String x = "100000000";
        String y = "099999999";
        //String y = "894276582937089472389467895438905734857396073894563505367856348748743";
        //String x = "8197253647645762123564876245637214536758293847536452367836478235923478235498239236483";
        HugeInteger number = new HugeInteger(x);
        HugeInteger number2 = new HugeInteger(y);
        BigInteger n1 = new BigInteger(x);
        BigInteger n2 = new BigInteger(y);
        HugeInteger X2 = new HugeInteger(n1.subtract(n2).toString());
        System.out.println(X2);
        HugeInteger X1 = new HugeInteger(number.subtract(number2).toString());
        System.out.println(X1);
        
        //HugeInteger BigIntSum = new HugeInteger(n1.subtract(n2).toString());
        System.out.printf("Comparison to bigInt: %d\n",X1.compareTo(X2));
	}
}
