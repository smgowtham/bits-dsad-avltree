import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EmployeeAttendance {

    public static void main(String[] args) throws IOException {
        
        File file =
                new File("./avltree/testdata/input1.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine())
        {
            System.out.println(sc.nextLine()); 
        }
        
        System.out.println("This is the basic structure.");
    }
        
}
