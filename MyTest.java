import java.math.BigInteger;
public class MyTest {
    public static void main(String[] args) {

        //Constructor 1
        try {
            HugeInteger constTest1 = new HugeInteger("00000000000000-123");
            System.out.println("CONSTRUCTOR TEST 1 : FAILED");
        } catch (Exception NumberFormatException) {
            System.out.println("CONSTRUCTOR TEST 1 : PASSED");
        }

        try {
            HugeInteger constTest2 = new HugeInteger("00000000000000-!DA123");
            System.out.println("CONSTRUCTOR TEST 2 : FAILED");
        } catch (Exception NumberFormatException) {
            System.out.println("CONSTRUCTOR TEST 2 : PASSED");
        }

        try {
            HugeInteger constTest3 = new HugeInteger("0");
            System.out.println("CONSTRUCTOR TEST 3 : PASSED");
        } catch (Exception NumberFormatException) {
            System.out.println("CONSTRUCTOR TEST 3 : FAILED");
        }

        try {
            HugeInteger constTest4 = new HugeInteger("-123");
            System.out.println("CONSTRUCTOR TEST 3 : PASSED");
        } catch (Exception NumberFormatException) {
            System.out.println("CONSTRUCTOR TEST 3 : FAILED");
        }

        //Constructor 2
        try {
            HugeInteger const2Test1 = new HugeInteger(0);
            System.out.println("CONSTRUCTOR 2 TEST 1 : FAILED");
        } catch (Exception NumberFormatException) {
            System.out.println("CONSTRUCTOR 2 TEST 1 : PASSED");
        }

        try {
            HugeInteger const2Test2 = new HugeInteger(3);
            System.out.println("CONSTRUCTOR 2 TEST 1 : PASSED");
        } catch (Exception NumberFormatException) {
            System.out.println("CONSTRUCTOR 2 TEST 1 : FAILED");
        }


        // ADD
        String sameLen1 = "123";
        String sameLen2 = "123";      
        HugeInteger test11 = new HugeInteger(sameLen1);
        BigInteger test11A = new BigInteger(sameLen1);
        HugeInteger test12 = new HugeInteger(sameLen2);
        BigInteger test12A = new BigInteger(sameLen2);
        if (test11.add(test12).toString().equals(test11A.add(test12A).toString())){
            System.out.println("Test 1 ADD: Passed");
        } else {
            System.out.println(test11.add(test12).toString());
            System.out.println(test11A.add(test12A).toString());
            System.out.println("Test 1 ADD: Failed");
        }

        String sameLenCarry1 = "999";
        String sameLenCarry2 = "999";
        HugeInteger test21 = new HugeInteger(sameLenCarry1);
        BigInteger test21A = new BigInteger(sameLenCarry1);
        HugeInteger test22 = new HugeInteger(sameLenCarry2);
        BigInteger test22A = new BigInteger(sameLenCarry2);
        if (test21.add(test22).toString().equals(test21A.add(test22A).toString())){
            System.out.println("Test 2 ADD: Passed");
        } else {
            System.out.println("Test 2 ADD: Failed");
        }

        String diffLenCarry1 = "999999";
        String diffLenCarry2 = "111";
        HugeInteger test31 = new HugeInteger(diffLenCarry1);
        BigInteger test31A = new BigInteger(diffLenCarry1);
        HugeInteger test32 = new HugeInteger(diffLenCarry2);
        BigInteger test32A = new BigInteger(diffLenCarry2);
        if (test31.add(test32).toString().equals(test31A.add(test32A).toString())){
            System.out.println("Test 3 ADD: Passed");
        } else {
            System.out.println("Test 3 ADD: Failed");
        }
    }
}
