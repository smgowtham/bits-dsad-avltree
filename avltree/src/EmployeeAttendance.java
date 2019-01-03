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

public class EmployeeAttendance {

    public static void main(String[] args) throws IOException {
    	// Steps to calculate the time
    	 NumberFormat formatter = new DecimalFormat("#0.00000");
    	long startTime = System.nanoTime();
    	long endTime ;
    	long totalTime;
    	
        File file = new File("C://Users//tvengal.ORADEV//workspace//DSAD-1//src/input4.txt");
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
//        System.out.println("Pre-order traversal" + " of constructed tree is : ");
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

            /*    if (inputChoice == -1)
                    inputChoice = Integer.parseInt(sc.nextLine()); */

                int searchId = 0;
                switch (inputChoice) {
                case 0:
                    System.out.println("Thank you for using Employee Attendence Reporting system. Have a good day. Bye!");
                    break loop;
                case 1:
                    System.out.println("In-order traversal of tree:");
                    
                	startTime = System.nanoTime();
                	
                	//System.out.println("Timer task started at:"+startTime);
                    employeeTree.inOrder(employeeTree.root);
                    endTime = System.nanoTime();
                	totalTime = endTime - startTime;
                	//System.out.println("Timer task finished at:"+ endTime);
                	System.out.println("\nTotal Duration for printTree() execution:" + formatter.format(totalTime/1000d)+" milliseconds");
                    
                	
                    break;
                case 2:
                	startTime = System.nanoTime();
                	System.out.println("Number of employees in org: " + employeeTree.getHeadcount(employeeTree.root));
                    endTime = System.nanoTime();
                	totalTime = endTime - startTime;
                	//System.out.println("Timer task finished at:"+ endTime);
                	System.out.println("\nTotal Duration for getHeadCount() execution:" + formatter.format(totalTime/1000d)+" milliseconds");
                    
                    break;
                case 3:
                    System.out.println("Enter the employee Id to Search:");
                    searchId = getInput();
                    startTime = System.nanoTime();
                    if (employeeTree.searchID(employeeTree.root, searchId))
                    {
                        System.out.println("Employee Id: " + searchId + " is Present today.");
                    	endTime = System.nanoTime();
                    	totalTime = endTime - startTime;
                    	//System.out.println("Timer task finished at:"+ endTime);
                    	System.out.println("\nTotal Duration for searchId() execution:" + formatter.format(totalTime/1000d)+" milliseconds");
                }
                    else
                    {
                        System.out.println("Employee Id: " + searchId + " is  not Present today.");
                    		endTime = System.nanoTime();
                    		totalTime = endTime - startTime;
                    		//System.out.println("Timer task finished at:"+ endTime);
                    		System.out.println("\nTotal Duration for printTree() execution:" + formatter.format(totalTime/1000d)+" milliseconds");
                    }
                 
                    break;
                case 4:
                    System.out.println("Enter the employee Id to search how often:");
                    searchId = getInput();
                    startTime = System.nanoTime();
                    int numberOfTimes = employeeTree.howOften(employeeTree.root, searchId);
                    if (numberOfTimes == 0)
                    {
                        System.out.println("Employee Id: " + searchId + " has not entered the office today");
                    	endTime = System.nanoTime();
                		totalTime = endTime - startTime;
                		//System.out.println("Timer task finished at:"+ endTime);
                		System.out.println("\nTotal Duration for searchId() execution:" + formatter.format(totalTime/1000d)+" milliseconds");
                    }
                    else
                    {
                        System.out.println("Employee Id: " + searchId + " has entered the office" + numberOfTimes + " times");
                    	endTime = System.nanoTime();
                    	totalTime = endTime - startTime;
                    	//System.out.println("Timer task finished at:"+ endTime);
                    	System.out.println("\nTotal Duration for searchId() execution:" + formatter.format(totalTime/1000d)+" milliseconds");
                    }
                    break;
                case 5:
                	startTime = System.nanoTime();
                	EmployeeNode frequentVisitor = employeeTree.frequentVisitor(employeeTree.root);
                    if (frequentVisitor == null)
                        System.out.println("Tree is empty");
                    else
                    {
                    	System.out.println("Frequent visitor employee id: " + frequentVisitor.empId + " has entered " + frequentVisitor.attCount + " times");
                       	endTime = System.nanoTime();
                    	totalTime = endTime - startTime;
                    	//System.out.println("Timer task finished at:"+ endTime);
                    	System.out.println("\nTotal Duration for frequentVisitor() execution:" + formatter.format(totalTime/1000d)+" milliseconds");
                    }
                    break;
                case 6:
                    
                
                 System.out.println("Enter the employee Id for lower Range id:");
                    int id1 = getInput();
                   System.out.println("Enter the employee Id for upper Range id:");
                    int id2 = getInput();
                   if (id1>id2) //compare lower range is lower than upper range
                   {
                   System.out.println("Error: Range entry not valid. Please make sure that lower range is lower than upper range.");
                   break; //break out of option 6
                   }
                  startTime = System.nanoTime(); //gets start time of method call
                   
                  employeeTree.printRangePresent(employeeTree.root, id1, id2);
                   
                   endTime = System.nanoTime();
               	   totalTime = endTime - startTime;
               	//System.out.println("Timer task finished at:"+ endTime);
               	System.out.println("\nTotal Duration for printRangePesent() execution:" + formatter.format(totalTime/1000d)+" milliseconds");
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
    
    static int getInput() throws NumberFormatException
    {
        int input = -1;
        Scanner sc = new Scanner(System.in);
        
        input = Integer.parseInt(sc.nextLine());

        return input;
    }
        
}

