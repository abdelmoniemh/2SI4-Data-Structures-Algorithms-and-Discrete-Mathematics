import java.math.BigInteger;
public class Main {
    public static void main(String[] args) {
        String x = "10000";
        String y = "379";
        //String y = "894276582937089472389467895438905734857396073894563505367856348748743";
        //String x = "8197253647645762123564876245637214536758293847536452367836478235923478235498239236483";
        HugeInteger number = new HugeInteger(x);
        HugeInteger number2 = new HugeInteger(y);
        BigInteger n1 = new BigInteger(x);
        BigInteger n2 = new BigInteger(y);
        System.out.println(number.subtract(number2));
        System.out.println(n1.subtract(n2));
        HugeInteger BigIntSum = new HugeInteger(n1.subtract(n2).toString());
        System.out.printf("Comparison to bigInt: %d\n",BigIntSum.compareTo(number.subtract(number2)));
	}
}
