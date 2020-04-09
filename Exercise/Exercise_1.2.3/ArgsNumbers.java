/** Assume that args are numbers, and add them */
public class ArgsNumbers{
    public static void main(String[] args){
        int sum = 0;
        for(String s : args){
            sum += Integer.parseInt(s);
        }
        System.out.println(sum);
    }
}