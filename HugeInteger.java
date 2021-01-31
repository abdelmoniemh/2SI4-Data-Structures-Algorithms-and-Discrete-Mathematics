import java.lang.Math;
public class HugeInteger{

    private int[] HugeInteger;
    private char HugeIntegerSign;

    public HugeInteger(String Val){
        // convert string val into huge integer
        // only - sign is allowed (first character only) 
        // any other discrepencies throw exception

        int fl = Val.length()-1;
        if (Val.charAt(0) == '-'){ // defines sign of integer
            HugeIntegerSign = '-';
            Val = Val.substring(1);
        } else {
            HugeIntegerSign = '+';
        }
        
        for (int i=0;i<fl-1;i++){ //checks for leading zeros // -1 to allow 0 to be a valid input
            if (Val.charAt(0) != '0')
                break;
            else
                Val = Val.substring(1); 
        }
            
        fl = Val.length();
        HugeInteger = new int[fl];
        for (int i = 0;i<fl;i++){ // populates array with values
            try{
                HugeInteger[i] = Integer.parseInt(Character.toString(Val.charAt(i)));
            } catch (Exception  NumberFormatException){
               System.out.println("\nInvalid Input\n");
               throw NumberFormatException;
            }
            
        }
    }

    public HugeInteger(int n){
        // generate random HugeInteger of n digits
        // digit[0] > 0; digit[1:n] >= 0; digit[0:n]<10
        // n >= 1

        HugeInteger = new int[n];
        HugeIntegerSign = '+';
      
        for (int i = 0; i<n ;i++){
            if (i == 0){
                HugeInteger[i] = (int)(Math.random()*(9)) + 1;
            } else {
                HugeInteger[i] = (int)(Math.random()*(10));
            }
            
        }
      }

    public void negate(){
        for (int i = 0;i<HugeInteger.length;i++){
            HugeInteger[i] *= -1;
        }
    }

    public HugeInteger abs(){
        String x = HugeInteger.toString();
        if (x.charAt(0) == '-')
            x = x.substring(1);
        HugeInteger X = new HugeInteger(x);
        return X;
    }

    public HugeInteger add(HugeInteger h) {

        if (HugeIntegerSign == '+' && h.HugeIntegerSign == '-'){ //(+)+(-) == (+)-(+)
            HugeInteger absH = h.abs();
            return this.subtract(absH);
        }
        if (HugeIntegerSign == '-' && h.HugeIntegerSign == '+'){ //(-)+(+) == (+)-(+)
            HugeInteger absThis = this.abs();
            return h.subtract(absThis);
        }
        if (HugeIntegerSign == '-' && h.HugeIntegerSign == '-'){ //(-)+(-) == -((+)+(+))
            HugeInteger absH = h.abs();
            HugeInteger absThis = this.abs();
            HugeInteger added = absThis.add(absH);
            added.HugeIntegerSign = '-';
            return added;
        }
                
        int[] solution = new int[h.HugeInteger.length + HugeInteger.length];
        int smallerLen = (HugeInteger.length>=h.HugeInteger.length)?h.HugeInteger.length:HugeInteger.length;
        int longerLen = (HugeInteger.length>=h.HugeInteger.length)?HugeInteger.length:h.HugeInteger.length;
        int[] longerInt = (HugeInteger.length>=h.HugeInteger.length)?HugeInteger:h.HugeInteger;
        int[] shorterInt = (HugeInteger.length>=h.HugeInteger.length)?h.HugeInteger:HugeInteger;
        String strSolution = "";

        for (int i = 0;i<smallerLen;i++){
            System.out.printf("Array@Loop[%d]\n", i);
            System.out.printf("[");
            for (int x = 0;x<solution.length;x++){
                System.out.printf("%d, ", solution[x]);
            }
            System.out.printf("]\n");
                
            int longerIndex = longerInt[longerLen-1-i];
            int shorterIndex = shorterInt[smallerLen-1-i];
            int current = solution[solution.length-1-i] += (longerIndex+shorterIndex);
            int carry = 0;
            if (current >= 10){
                solution[solution.length-1-i] += (solution[solution.length-1-i] + (longerIndex+shorterIndex)/10)%10;
                carry =  (longerIndex+shorterIndex)/10;
            } else {
                solution[solution.length-1-i] += (longerIndex+shorterIndex)%10;
            }
                       
            int carrySum = solution[solution.length-2-i] + carry;
            if (carrySum >= 10){
                solution[solution.length-2-i] += (solution[solution.length-2-i] + (longerIndex+shorterIndex)/10)%10;
                solution[solution.length-3-i] += (solution[solution.length-2-i] + (longerIndex+shorterIndex)/10)/10;
            } else {
                solution[solution.length-2-i] += carrySum;
            }
                
        }
        
        //for (int i = smallerLen ;i<longerLen;i++){
        //   solution[i] += longerInt[longerLen - i -1];
        //}

        for (int i = 0;i<solution.length;i++){
            strSolution += Integer.toString(solution[i]);
        }

        //System.out.printf("%s\n",strSolution);
        HugeInteger Solution = new HugeInteger(strSolution);
        return Solution;
    }

    public HugeInteger subtract(HugeInteger h) {

        if (this.compareTo(h) == 0){
            return new HugeInteger("0");
        } 
        else if (HugeIntegerSign == '+' && h.HugeIntegerSign == '-'){ //(+)-(-) == (+)+(+)
            HugeInteger absH = h.abs();
            return this.add(absH);
        }
        else if (HugeIntegerSign == '-' && h.HugeIntegerSign == '+'){ //(-)-(+) == -((+)+(+))
            HugeInteger absThis = this.abs();
            HugeInteger added = absThis.add(h);
            added.HugeIntegerSign = '-';
            return added;
        }
        else if (HugeIntegerSign == '-' && h.HugeIntegerSign == '-'){ //-x--y == y-x
            HugeInteger lead = h.abs();
            return lead.subtract(this.abs());
        } 
        else if (this.compareTo(h) == -1) {
            HugeInteger sub = h.subtract(this);
            sub.HugeIntegerSign = '-';
            return sub;
        }
        
        
        int[] solution = new int[(HugeInteger.length>=h.HugeInteger.length)?HugeInteger.length:h.HugeInteger.length];//whichever one is longer
        int smallerLen = (HugeInteger.length>=h.HugeInteger.length)?h.HugeInteger.length:HugeInteger.length;
        int longerLen = (HugeInteger.length>=h.HugeInteger.length)?HugeInteger.length:h.HugeInteger.length;
        int[] longerInt = (HugeInteger.length>=h.HugeInteger.length)?HugeInteger:h.HugeInteger; // this
        int[] shorterInt = (HugeInteger.length>=h.HugeInteger.length)?h.HugeInteger:HugeInteger; // other
        String strSolution = "";
        
        for (int i = 0;i<smallerLen;i++){
            int lonIndex = longerLen-1-i;
            int sorIndex = smallerLen-1-i;
            if (longerInt[lonIndex]>=shorterInt[sorIndex]){
                solution[solution.length-1-i] += longerInt[lonIndex]-shorterInt[sorIndex];
            } else {
                int index = 0;
                int tenX = 1;
                System.out.printf("tenx = %d\n", tenX);
                if (longerInt[lonIndex-1] == 0){
                    while (longerInt[lonIndex-index] == 0){
                        System.out.printf("tenx = %d\n", tenX);
                        tenX *= 10;
                        try {
                            if (longerInt[lonIndex-index] != 0){
                                longerInt[lonIndex-index] -= 1;
                                tenX *= 10;
                                break;
                            } 
                        } finally {
                                //System.out.printf("logic error");
                            }
                        index++;
                    }
                    for (int j =0;j<index;j++) {
                        solution[solution.length-1-i-j] += (tenX + longerInt[lonIndex]-shorterInt[sorIndex])%10;
                    }
                } else {
                    longerInt[lonIndex-1] -= 1;
                    longerInt[lonIndex] += 10;
                    solution[solution.length-1-i] += longerInt[lonIndex]-shorterInt[sorIndex];
                }
            }
                
        }
        
        //for (int i = smallerLen ;i<longerLen;i++){
        //    solution[i] += longerInt[longerLen-i-1];
        //}

        for (int i = 0;i<solution.length;i++){
            strSolution += Integer.toString(solution[i]);
        }


        System.out.printf("%s\n",strSolution);
        HugeInteger Solution = new HugeInteger(strSolution);
        return Solution;
    }

    public HugeInteger multiply(HugeInteger h) {
        HugeInteger solution = new HugeInteger("123");
        return solution;
    }

    public int compareTo(HugeInteger h) { /// test for leading zeros
        //return -1 if self < h
        //return 1 if self > h
        //return 0 if self == h

        
        if (java.util.Arrays.equals(HugeInteger, h.HugeInteger) && HugeIntegerSign == h.HugeIntegerSign)
            return 0;
        if (HugeIntegerSign == '+' && h.HugeIntegerSign == '-')
            return 1;
        if (HugeIntegerSign == '-' && h.HugeIntegerSign == '+')
            return -1;
        if (HugeIntegerSign == '+' && h.HugeIntegerSign == '+'){
            if (HugeInteger.length > h.HugeInteger.length)
                return 1;
            if (HugeInteger.length < h.HugeInteger.length)
                return -1;
            if (HugeInteger.length == h.HugeInteger.length){
                for (int i = 0;i<HugeInteger.length;i++){
                    if (HugeInteger[i] > h.HugeInteger[i])
                        return 1;
                    if (HugeInteger[i] < h.HugeInteger[i])
                        return -1;
                    }
                }
            }
        if (HugeIntegerSign == '-' && h.HugeIntegerSign == '-'){
            if (HugeInteger.length > h.HugeInteger.length)
                return -1;
            if (HugeInteger.length < h.HugeInteger.length)
                return 1;
            if (HugeInteger.length == h.HugeInteger.length){
                for (int i = 0;i<HugeInteger.length;i++){
                    if (HugeInteger[i] > h.HugeInteger[i])
                        return -1;
                    if (HugeInteger[i] < h.HugeInteger[i])
                        return 1;
                    }
                }

        }
            

        return 2;
        
    }

    public String toString(){
        //return HugeInteger as string
        //cant have leading zeros
        //append sign first
        
        String output = (HugeIntegerSign == '-')?"-":"";
        String Val = "";

        for (int i = 0;i<HugeInteger.length;i++){
            Val += Integer.toString(HugeInteger[i]);
        }

        for (int i=0;i<Val.length()-1;i++){
            if (Val.charAt(0) != '0')
                break;
            else
                Val = Val.substring(1); 
        }
        
        output += Val;

        return output;
    }
}