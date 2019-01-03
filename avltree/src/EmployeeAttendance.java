import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EmployeeAttendance {

    public static void main(String[] args) throws IOException {
        
        File file = new File("./avltree/src/input.txt");
        Scanner sc = new Scanner(file);
        
        EmpBT employeeTree = new EmpBT();

        while (sc.hasNextLine())
        {
            try {
                int employeeId = Integer.parseInt(sc.nextLine());
                employeeTree.root = employeeTree.readEmployees(employeeTree.root, employeeId);
            }
            catch (NumberFormatException e)
            {
                System.out.println("File contains invalid inputs. Use integer values.");
                System.exit(1);
            }
//            System.out.println("Inserting Node: " + employeeId);
            
           
        }

//        System.out.println("Preorder traversal" + " of constructed tree is : ");
        loop: while(true) {
            System.out.println("\nEnter the Operation to do:");
            System.out.println("-----------------------------");
            System.out.println("0. Exit");
            System.out.println("1. Print tree");
            System.out.println("2. Get HeadCount ");
            System.out.println("3. searchID");
            System.out.println("4. howOften");
            System.out.println("5. frequentVisitor");
            System.out.println("6. printRangePresent");
            System.out.println("-----------------------------");
            System.out.println("Enter your choice:");

            try {
                int inputChoice = getInput();

                if (inputChoice == -1)
                    inputChoice = Integer.parseInt(sc.nextLine());

                int searchId = 0;
                switch (inputChoice) {
                case 0:
                    System.out.println("Bye");
                    break loop;
                case 1:
                    System.out.println("Preorder travesal of tree:");
                    employeeTree.preOrder(employeeTree.root);
                    break;
                case 2:
                    System.out.println("Number of employees in org: " + employeeTree.getHeadcount(employeeTree.root));
                    break;
                case 3:
                    System.out.println("Enter the employee Id to Search:");
                    searchId = getInput();
                    if (employeeTree.searchID(employeeTree.root, searchId))
                        System.out.println("Employee Id " + searchId + "is Present.");
                    else
                        System.out.println("Employee Id " + searchId + "is  not Present.");
                    break;
                case 4:
                    System.out.println("Enter the employee Id to search how often:");
                    searchId = getInput();
                    int numberOfTimes = employeeTree.howOften(employeeTree.root, searchId);
                    if (numberOfTimes == 0)
                        System.out.println("Employee Id " + searchId + "has not entered the org.");
                    else
                        System.out.println("Employee Id " + searchId + "has entered " + numberOfTimes + " times");
                    break;
                case 5:
                    EmployeeNode frequentVisitor = employeeTree.frequentVisitor(employeeTree.root);
                    if (frequentVisitor == null)
                        System.out.println("Tree is empty");
                    else
                        System.out.println("Frequent visitor employee id " + frequentVisitor.empId + " has entered " + frequentVisitor.attCount + " times");
                    break;
                case 6:
                    System.out.println("Not yet implemented:");
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
                }
            }// End of try
            catch(NumberFormatException e)
            {
                System.out.println("Input is invalid, Enter a valid input.");
                continue;
            }
        } // End of while
    }
    
    static int getInput() throws NumberFormatException
    {
        int input = -1;
        Scanner sc = new Scanner(System.in);
        
        input = Integer.parseInt(sc.nextLine());

        return input;
    }
        
}
