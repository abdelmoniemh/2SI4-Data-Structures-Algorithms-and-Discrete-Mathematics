import java.math.BigInteger;
public class Main {
    public static void main(String[] args) {
        String x = "1233";
        String y = "7896";
        //String x = "894276582937089472389467895438905734857396073894563505367856348748743";
        //String y = "123564876245637214536758293847536452367836478235923478235498239236483";
        HugeInteger number = new HugeInteger(x);
        HugeInteger number2 = new HugeInteger(y);
        BigInteger n1 = new BigInteger(x);
        BigInteger n2 = new BigInteger(y);
        System.out.println(number.add(number2));
        System.out.println(n1.add(n2));
	}
}
