import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.text.NumberFormat;
import java.text.DecimalFormat;

/***
 * This is the main class that is responsible for reading the files and displays
 * a menu for various operations that can be performed.
 */
public class EmployeeAttendance {

    private static long startTime;
    private static long endTime ;
    private static EmpBT employeeTree;
    
    private static NumberFormat formatter = new DecimalFormat("#0.00000");

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Employee Attendance Reporting system!");
        loop: while(true) {

            displayMenu();
            try {
                int inputChoice = getIntegerInput();

                int searchId = 0;
                switch (inputChoice) {
                case 0:
                    System.out.println("Thank you for using Employee Attendence Reporting system. Have a good day. Bye!");
                    break loop;
                case 1:
                    Scanner fileScanner = readFile();
                    
                    if(fileScanner == null) // If file cannot be found reload.
                     continue ;
                    
                    employeeTree =new EmpBT();

                    recordStartTime();
                    while (fileScanner.hasNextLine())
                    {
                        try {
                            int employeeId = Integer.parseInt(fileScanner.nextLine());
                            employeeTree.root = employeeTree.readEmployees(employeeTree.root, employeeId);
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("File contains invalid inputs. Use integer values.");
                            continue ;
                        }
                        //System.out.println("Inserting Node: " + employeeId);
                    }
                    System.out.println("Read Employees complete. Inorder traversal of tree:");
                    employeeTree.inOrder(employeeTree.root);
                    recordEndTime();
                    break;
                case 2:
                    recordStartTime();
                	System.out.println("Number of employees in org: " + employeeTree.getHeadcount(employeeTree.root));
                	recordEndTime();
                    break;
                case 3:
                    System.out.println("Enter the employee Id to Search:");
                    searchId = getIntegerInput();
                    recordStartTime();
                    if (employeeTree.searchID(employeeTree.root, searchId)) {
                        System.out.println("Employee Id: " + searchId + " is Present today.");
                    }
                    else {
                        System.out.println("Employee Id: " + searchId + " is  not Present today.");
                    }
                    recordEndTime();
                    break;
                case 4:
                    System.out.println("Enter the employee Id to search how often:");
                    searchId = getIntegerInput();
                    
                    recordStartTime();
                    int numberOfTimes = employeeTree.howOften(employeeTree.root, searchId);
                    if (numberOfTimes == 0) {
                        System.out.println("Employee Id: " + searchId + " has not entered the office today");
                    }
                    else {
                        System.out.println("Employee Id: " + searchId + " has entered the office" + numberOfTimes + " times");
                    }
                    recordEndTime();
                    break;
                case 5:
                    recordStartTime();
                	EmployeeNode frequentVisitor = employeeTree.frequentVisitor(employeeTree.root);
                    if (frequentVisitor == null)
                        System.out.println("Tree is empty");
                    else {
                    	System.out.println("Frequent visitor employee id: " + frequentVisitor.empId + " has entered " + frequentVisitor.attCount + " times");
                    }
                    recordEndTime();
                    break;
                case 6: 
                    System.out.println("Enter the employee Id for lower Range id:");
                    int startValue = getIntegerInput();
                    System.out.println("Enter the employee Id for upper Range id:");
                    int endValue = getIntegerInput();
                    
                    if(startValue > endValue) //Invalid input 
                     System.out.println("Invalid range. Start value should be less than end value");
                    
                    recordStartTime();
                    employeeTree.printRange(employeeTree.root, startValue, endValue);
                    recordEndTime();
                break;
                case 7:
                    System.out.println("In-order traversal of tree:");

                    recordStartTime();
                    employeeTree.inOrder(employeeTree.root);
                    recordEndTime();

                    break;
                default:
                    System.out.println("Invalid Input. Enter a value between 0 and 6");
                break;
                }
            }// End of try
            catch(NumberFormatException e)
            {
                System.out.println("Input is invalid, Please enter a valid input in integer format.");
                continue;
            }
        } // End of while
    }

    private static void displayMenu() {
        System.out.println("\n*****************************");
        System.out.println("-----------MENU--------------");
        System.out.println("Enter the Operation to do:");
        System.out.println("-----------------------------");
        System.out.println("0. Exit");
        System.out.println("1. Read employees");
        System.out.println("2. Get HeadCount ");
        System.out.println("3. searchID");
        System.out.println("4. howOften");
        System.out.println("5. frequentVisitor");
        System.out.println("6. printRangePresent");
        System.out.println("7. print tree inorder");
        System.out.println("-----------------------------");
        System.out.println("Enter your choice:");
    }

    private static int getIntegerInput() throws NumberFormatException
    {
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }

    private static void recordStartTime() throws NumberFormatException
    {
        startTime = System.nanoTime();;
//        System.out.println("Timer task started at:"+ startTime);
    }
    
    private static void recordEndTime() throws NumberFormatException
    {
        endTime = System.nanoTime();;
//        System.out.println("Timer completed at:"+ endTime);
        System.out.println("Time Taken:"+ formatter.format((endTime-startTime)/1000000d)+" milliseconds.");
    }
    
    private static Scanner readFile()
    {
        Scanner sc = null;
        String filePath = "";
        try {
            String currentDir = System.getProperty("user.dir");
            System.out.println("Current working directory:" + currentDir);
            System.out.println("Enter input file path(Press enter to use input.txt in current path):");

            Scanner inputScanner = new Scanner(System.in);
            filePath = inputScanner.nextLine();
            
            if(filePath.equals(""))
            {
                filePath = currentDir + "/input.txt";
            }

            System.out.println("Reading file at: "+ filePath);
            File file = new File(filePath);
            sc =  new Scanner(file);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found at location: "+ filePath);
        }
        return sc;
    }
        
}

