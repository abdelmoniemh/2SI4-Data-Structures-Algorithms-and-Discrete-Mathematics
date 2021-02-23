import java.lang.Math;
import java.util.Arrays;
public class HugeInteger{

    private int[] HugeInteger;
    private char HugeIntegerSign;

    public HugeInteger(String Val){
        // convert string val into huge integer
        // only - sign is allowed (first character only) 
        // any other discrepencies throw exception
        //System.out.println(Val+"\n");

        int fl = Val.length()-1;
        if (Val.charAt(0) == '-'){ // defines sign of integer
            HugeIntegerSign = '-';
            Val = Val.substring(1);
        } else {
            HugeIntegerSign = '+';
        }
        
        if (Val.length() == 0){
            //System.out.println("\nInvalid Input\n");
            throw new NumberFormatException();
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
               //System.out.println("\nInvalid Input\n");
               throw NumberFormatException;
            }
            
        }
        if (Val.equals("0")){
            HugeIntegerSign = '+';
        }
    }

    public HugeInteger(int n){
        // generate random HugeInteger of n digits
        // digit[0] > 0; digit[1:n] >= 0; digit[0:n]<10
        // n >= 1

        HugeInteger = new int[n];
        HugeIntegerSign = '+';
      
        if (n <= 0){
            //System.out.println("\nInvalid Input\n");
            throw new NumberFormatException();
        }

        for (int i = 0; i<n ;i++){
            if (i == 0){
                HugeInteger[i] = (int)(Math.random()*(9)) + 1;
            } else {
                HugeInteger[i] = (int)(Math.random()*(10));
            }
            
        }
      }


    public HugeInteger absoluteValue(){ // helper funtion that returns the absolute value of a HugeInteger
        String x = this.toString();
        if (x.charAt(0) == '-')
            x = x.substring(1);
        HugeInteger X = new HugeInteger(x);
        return X;
    }

    public HugeInteger add(HugeInteger h) {

        if (HugeIntegerSign == '+' && h.HugeIntegerSign == '-'){ //(+)+(-) == (+)-(+)
            HugeInteger absH = h.absoluteValue();
            return this.subtract(absH);
        }
        if (HugeIntegerSign == '-' && h.HugeIntegerSign == '+'){ //(-)+(+) == (+)-(+)
            HugeInteger absThis = this.absoluteValue();
            return h.subtract(absThis);
        }
        if (HugeIntegerSign == '-' && h.HugeIntegerSign == '-'){ //(-)+(-) == -((+)+(+))
            HugeInteger absH = h.absoluteValue();
            HugeInteger absThis = this.absoluteValue();
            HugeInteger added = absThis.add(absH);
            added.HugeIntegerSign = '-';
            return added;
        }
                
        int[] solution = new int[h.HugeInteger.length + HugeInteger.length];
        int shorterLen = (HugeInteger.length>=h.HugeInteger.length)?h.HugeInteger.length:HugeInteger.length;
        int longerLen = (HugeInteger.length>=h.HugeInteger.length)?HugeInteger.length:h.HugeInteger.length;
        int[] longerInt = (HugeInteger.length>=h.HugeInteger.length)?HugeInteger:h.HugeInteger;
        int[] shorterInt = (HugeInteger.length>=h.HugeInteger.length)?h.HugeInteger:HugeInteger;
        String strSolution = "";

        for (int i = 0;i<shorterLen;i++){ // handles addition of overlaping unitplaces
                            
            int longerIndex = longerInt[longerLen-1-i];
            int shorterIndex = shorterInt[shorterLen-1-i];
            int index = solution[solution.length-1-i];
            int sum = longerIndex + shorterIndex + index;
            solution[solution.length-1-i] = sum%10;
            solution[solution.length-2-i] = sum/10;
            
        }
        
        for (int i = longerLen - (shorterLen+1);i>=0;i--){ // handles the unique unitplaces from the larger number
            int sum = solution[i+shorterLen]+ longerInt[i];
            solution[i+shorterLen] = sum%10;
            solution[i+shorterLen-1] = sum/10;
        }
        

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
            HugeInteger absH = h.absoluteValue();
            return this.add(absH);
        }
        else if (HugeIntegerSign == '-' && h.HugeIntegerSign == '+'){ //(-)-(+) == -((+)+(+))
            HugeInteger absThis = this.absoluteValue();
            HugeInteger added = absThis.add(h);
            added.HugeIntegerSign = '-';
            return added;
        }
        else if (HugeIntegerSign == '-' && h.HugeIntegerSign == '-'){ //-x--y == y-x
            HugeInteger lead = h.absoluteValue();
            return lead.subtract(this.absoluteValue());
        } 
        else if (this.compareTo(h) == -1) {
            HugeInteger sub = h.subtract(this);
            sub.HugeIntegerSign = '-';
            return sub;
        }
        
        HugeIntegerSign = '+';
        
        int[] solution = new int[(HugeInteger.length>=h.HugeInteger.length)?HugeInteger.length+5:h.HugeInteger.length+5];//whichever one is longer
        int shorterLen = (HugeInteger.length>=h.HugeInteger.length)?h.HugeInteger.length:HugeInteger.length;
        int longerLen = (HugeInteger.length>=h.HugeInteger.length)?HugeInteger.length:h.HugeInteger.length;
        int[] longerInt = (HugeInteger.length>=h.HugeInteger.length)?HugeInteger:h.HugeInteger; // this
        int[] shorterInt = (HugeInteger.length>=h.HugeInteger.length)?h.HugeInteger:HugeInteger; // other
        String strSolution = "";

    
            for (int i = 0; i<longerLen; i++) {
                int longerIndex = longerInt[longerLen-1-i];
                

                if (i < shorterLen){
                    int shorterIndex = shorterInt[shorterLen-1-i];    
                    if (longerIndex >= shorterIndex){
                        // no carry case
                        solution[solution.length-1-i] += (longerIndex - shorterIndex);
                    } else {
                        // carry case
                        longerInt[longerLen-1-i-1] -= 1;
                        longerIndex += 10;
                        solution[solution.length-1-i] += (longerIndex - shorterIndex);
                    }
                } else {
                    solution[solution.length-1-i] += longerIndex; // copy from longer input when done subtracting
                }
            }

            for (int i = solution.length-1; i>=0 ;i--){
                if (solution[i]<0){
                    solution[i-1] -= 1;
                    solution[i] += 10;
                }
            }

            for (int i = 0;i<solution.length;i++){
                strSolution += Integer.toString(solution[i]);
            }
            //System.out.println(strSolution);
            HugeInteger Solution = new HugeInteger(strSolution);
            return Solution;
    }

    public HugeInteger multiply(HugeInteger h) {
        if (this.toString().equals("0") || h.toString().equals("0")){
            return new HugeInteger("0");
        }
        int[] solution = new int[HugeInteger.length+h.HugeInteger.length+5];//whichever one is longer
        int shorterLen = (HugeInteger.length>=h.HugeInteger.length)?h.HugeInteger.length:HugeInteger.length;
        int longerLen = (HugeInteger.length>=h.HugeInteger.length)?HugeInteger.length:h.HugeInteger.length;
        int[] longerInt = (HugeInteger.length>=h.HugeInteger.length)?HugeInteger:h.HugeInteger; // this
        int[] shorterInt = (HugeInteger.length>=h.HugeInteger.length)?h.HugeInteger:HugeInteger; // other
        String strSolution = "";
        int index = solution.length; //  gradeschool multiplication approach
        for (int i = 0; i<shorterLen; i++){  // smaller number
            for (int j = 0; j<longerLen; j++){ // bigger number
                int operations = (solution[solution.length-1-j-i] + shorterInt[shorterLen-1-i]*longerInt[longerLen-1-j]);
                solution[solution.length-1-j-i] = operations%10;
                solution[solution.length-2-j-i] += operations/10; //carry
                 
            }
        }
        
        for (int i = 0;i<solution.length;i++){
            strSolution += Integer.toString(solution[i]);
        }
        HugeInteger Solution = new HugeInteger(strSolution);
        if ((HugeIntegerSign == '+' && h.HugeIntegerSign == '-' || HugeIntegerSign == '-' && h.HugeIntegerSign == '+')) // handles the sign of the result
            Solution.HugeIntegerSign = '-';
        else if  (HugeIntegerSign == '-' && h.HugeIntegerSign == '-' || HugeIntegerSign == '+' && h.HugeIntegerSign == '+')
            Solution.HugeIntegerSign = '+';
        else 
            Solution.HugeIntegerSign = '+';

        return Solution;
    }

    public int compareTo(HugeInteger h) { /// test for leading zeros
        //return -1 if self < h
        //return 1 if self > h
        //return 0 if self == h

        //

        
        if (java.util.Arrays.equals(HugeInteger, h.HugeInteger) && HugeIntegerSign == h.HugeIntegerSign)// handle the signs first 
            return 0;
        if (HugeIntegerSign == '+' && h.HugeIntegerSign == '-')
            return 1;
        if (HugeIntegerSign == '-' && h.HugeIntegerSign == '+')
            return -1;
        if (HugeIntegerSign == '+' && h.HugeIntegerSign == '+'){
            if (HugeInteger.length > h.HugeInteger.length)// handles based on length
                return 1;
            if (HugeInteger.length < h.HugeInteger.length)
                return -1;
            if (HugeInteger.length == h.HugeInteger.length){// if lenght == start at first number of each look for the bigger number
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