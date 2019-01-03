import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EmployeeAttendance {

    public static void main(String[] args) throws IOException {
        
        File file =
                new File("./avltree/testdata/input1.txt");
        Scanner sc = new Scanner(file);
        
        EmpBT employeeTree = new EmpBT();

        while (sc.hasNextLine())
        {
            int employeeId = Integer.parseInt(sc.nextLine());
            System.out.println("Inserting Node: " + employeeId);
            
            employeeTree.root = employeeTree.insert(employeeTree.root, employeeId);
        }

        System.out.println("Preorder traversal" +
                " of constructed tree is : ");
        employeeTree.preOrder(employeeTree.root);
    }
        
}
