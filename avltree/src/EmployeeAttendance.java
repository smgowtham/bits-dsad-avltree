/**
 * 
 */

/**
 * @author tvengal
 *
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.io.PrintWriter;

/***
 * This is the main class that is responsible for reading the files and displays
 * a menu for various operations that can be performed.
 */
public class EmployeeAttendance {

    private static long startTime;
    private static long endTime ;
    
    private static NumberFormat formatter = new DecimalFormat("#0.00000");

    public static void main(String[] args) throws IOException {

        File file = new File("C://Users//tvengal.ORADEV//workspace//DSAD-1/src/input4.txt");
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
        System.out.println("******Welcome to DSAD Attendance Reporting System***********************");
        loop: while(true) {
        	
        	System.out.println("\n*****************************");
            System.out.println("-----------MENU--------------");
            System.out.println("Enter the Operation to do:");
            System.out.println("-----------------------------");
            System.out.println("0. Exit");
            System.out.println("1. Print tree inOrder");
            System.out.println("2. Get HeadCount ");
            System.out.println("3. searchID");
            System.out.println("4. howOften");
            System.out.println("5. frequentVisitor");
            System.out.println("6. printRangePresent");
            System.out.println("-----------------------------");
            System.out.println("Enter your choice:");

            try {
                int inputChoice = getInput();

                int searchId = 0;
                switch (inputChoice) {
                case 0:
                    System.out.println("Thank you for using Employee Attendence Reporting system. Have a good day. Bye!");
                    break loop;
                case 1:
                    System.out.println("Printing In-order traversal of tree:");
                    recordStartTime();
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
                    searchId = getInput();
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
                    searchId = getInput();
                    recordStartTime();
                    int numberOfTimes = employeeTree.howOften(employeeTree.root, searchId);
                    if (numberOfTimes == 0) {
                        System.out.println("Employee Id: " + searchId + " has not entered the office today");
                    }
                    else {
                        System.out.println("Employee Id: " + searchId + " has entered the office " + numberOfTimes + " times");
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
                    int startValue = getInput();
                    System.out.println("Enter the employee Id for upper Range id:");
                    int endValue = getInput();
                    if(startValue > endValue) //Invalid input 
                     System.out.println("Invalid range. Start value should be less than end value");                 
                    recordStartTime();
                  //  employeeTree.printRange(employeeTree.root, startValue, endValue, fw);
                    employeeTree.setfileWriter(new PrintWriter("output.txt", "UTF-8"));
                    employeeTree.printRange(employeeTree.root, startValue, endValue);
                    employeeTree.closefileWriter();
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
    
    private static int getInput() throws NumberFormatException
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
        
}
