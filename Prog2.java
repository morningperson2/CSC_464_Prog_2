import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Prog2 {
    private static int KruskalCost = 0;
    private static int PrimCost = 0;
    private static Set PrimMCST = new HashSet();
    private static Set KruskalMCST = new HashSet();

    public static void main(String args[]){
        Scanner myScanner = new Scanner(System.in);
        System.out.print("Please enter file path/name : ");
        String fileName = myScanner.nextLine();

        try{
            File file = new File(fileName);
            FileReader fReader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(fReader);
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
