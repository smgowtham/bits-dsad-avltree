import java.io.PrintWriter;

/**
 * This class is the implementation of the Binary tree ADT and its operations.
 * In this case we are using an AVL tree as an Binary tree ADT.
 */
public class EmpBT {
    EmployeeNode root;
    
    private PrintWriter fileWriter;
    
    private int headCount;
    private int rangeCounter;

    public EmpBT() {
        headCount = 0;
    }

    /**
     * Operation 2 in assignment.
     * 
     * Returns the stored head count that was calculated during readEmployees.
     */
    public int getHeadCount() {
        return headCount;
    }

    public int getRangeCounter() {
        return rangeCounter;
    }

    public void setfileWriter(PrintWriter fileWriter) {
        this.fileWriter = fileWriter;
        rangeCounter = 0;
    }
    
    public void closefileWriter() {
        fileWriter.close();
    }
    

    /***
     * Operation 1 of the assignment
     *
     * Inserts the employee node into the tree
     *
     * @param node The input root node
     * @param empId The employee Id to be inserted
     * @return Address of the Root of the balanced BST
     */
    EmployeeNode readEmployees(EmployeeNode node, int empId) { 
    
        /* If no nodes in tree (Root is null), Insert the a new node and return */
        if (node == null) {
            headCount++;
            return (new EmployeeNode(empId));
        }

        if (empId < node.empId)
            node.left = readEmployees(node.left, empId);
        else if (empId > node.empId)
            node.right = readEmployees(node.right, empId);
        else // Duplicate means employee entering the second time, increment attCount
            node.attCount += 1; 
        
        
        /* Get height of the ancestor node */
        node.height = 1 + findMax(height(node.left),
                height(node.right)); 
  
        /* Get balance of this ancestor node to check if tree became unbalanced due to addition*/
        int balance = getBalance(node);

        // If ancestor node is unbalanced then tree needs to be balanced
        // Balance using one of the 4 possible rotatios
        // Left Left Rotation 
        if (balance > 1 && empId < node.left.empId)
            return rightRotate(node);

        // Right Right Rotation 
        if (balance < -1 && empId > node.right.empId)
            return leftRotate(node);

        // Left Right Rotation 
        if (balance > 1 && empId > node.left.empId) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Rotation 
        if (balance < -1 && empId < node.right.empId) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        } 
    
    		/* Return the node pointer. */
        return node;
    }

    /**
     * Alternate way to calculate Operation 2 in assignment by travesal.
     * 
     * 
     * @param emp The root node of the tree
     * @return The number of employees that entered the organization, 0 if none.
     */
    int getHeadcountByTravesal(EmployeeNode emp) {
        if (emp == null)
            return 0;
        else
            return(getHeadcountByTravesal(emp.left) + 1 + getHeadcountByTravesal(emp.right));
        
      }

    /***
     * Operation 3 of the assignment.
     * 
     * @param emp The Root node of the employee Tree
     * @param id The Employee Id to search
     * @return True, If employee exists
     */
    boolean searchID(EmployeeNode emp, int id) {
    	
    	if (emp != null) {
            if (emp.empId == id)
                return true;
            else if (id < emp.empId) // Search in left tree
              return searchID(emp.left, id);
            else // Search in right tree
               return searchID(emp.right, id);
                     
        }
    
        return false;
    }

    /***
     * Operation 4 of the Assignment.
     * 
     * Search how often an employee enters the org or office
     * @param emp The Root node of the employee Tree
     * @param id The Employee Id to search
     * @return The number of time the employee enters
     */
    int howOften(EmployeeNode emp, int id) {
        if (emp != null) {
            if (emp.empId == id)
                return emp.attCount;
            else if (id < emp.empId) // Search in left tree
                return howOften(emp.left, id);
            else // Search in right tree
                return howOften(emp.right, id);
        }
        return 0;
    }

    /***
     * Operation 5 of the Assignment.
     *
     * Search frequentVisitor employee of the org
     * @param emp The Root node of the employee Tree
     * @return The number of time the employee enters
     */
    
    EmployeeNode frequentVisitor(EmployeeNode emp) {
        if (emp == null)
            return null;

        EmployeeNode maxNode = emp;

        EmployeeNode leftMaxNode = frequentVisitor(emp.left);
        EmployeeNode rightMaxNode = frequentVisitor(emp.right);

        if (leftMaxNode == null && rightMaxNode == null)
            return maxNode;
        if(leftMaxNode!= null && leftMaxNode.getAttCount() > maxNode.getAttCount())
            maxNode = leftMaxNode;
        if (rightMaxNode!= null && rightMaxNode.getAttCount() > maxNode.getAttCount())
            maxNode = rightMaxNode;
        
        return maxNode;
    }

    /***
     * Operation 6 of the Assignment.
     *
     * This function prints the ids in the range id1 to id2 and how often they have entered the organization in a file name output.txt
     * @param emp The Root node of the employee Tree
     * @param startValue Lower range employee id
     * @param endValue Upper range of employee id
     */
   void printRange(EmployeeNode emp, int startValue, int endValue)
   {
       if (emp != null) {
           if (emp.empId >=startValue && emp.empId <= endValue) {
               printRange(emp.left, startValue, endValue);
//               System.out.println(emp); //print the node
               rangeCounter++;
               fileWriter.println(emp); //Write the node value to file
               printRange(emp.right, startValue, endValue);
           }
           else if (emp.empId > endValue) // Search in left tree
               printRange(emp.left, startValue, endValue);
           else if (emp.empId < startValue)// Search in right tree
               printRange(emp.right, startValue, endValue);
           else 
               return;

       }
       return;
   }
    // Returns the height of the tree
    int height(EmployeeNode employeeNode) {
        if (employeeNode == null)
            return 0;

        return employeeNode.height;
    }

    // Function to compare two integers
    int findMax(int valueA, int valueB) {
        return (valueA > valueB) ? valueA : valueB;
    }

    // RightRotate the given root node. 
    EmployeeNode rightRotate(EmployeeNode rootNode) {
        EmployeeNode left = rootNode.left;
        //Node at the right of left
        EmployeeNode lrNode = left.right;

        // right rotation
        left.right = rootNode;
        rootNode.left = lrNode;
        // Height values updated after rotation
        rootNode.height = findMax(height(rootNode.left), height(rootNode.right)) + 1;
        left.height = findMax(height(left.left), height(left.right)) + 1;

        //root element
        return left;
    }

    // LeftRotate the given root node.
    EmployeeNode leftRotate(EmployeeNode rootNode) {
        EmployeeNode right = rootNode.right;
        EmployeeNode rlNode = right.left;

        // Left rotation
        right.left = rootNode;
        rootNode.right = rlNode;

        // Height values updated after rotation
        rootNode.height = findMax(height(rootNode.left), height(rootNode.right)) + 1;
        right.height = findMax(height(right.left), height(right.right)) + 1;

        //root element
        return right;
    }

    // Returns the balance value of a Node
    int getBalance(EmployeeNode node) {
        if (node == null)
            return 0;

        return height(node.left) - height(node.right);
    }


    // A utility function to print preorder traversal 
    // of the tree. 
    // The function also prints number of occurrences of every node 
    void preOrder(EmployeeNode node) {
        if (node != null) {
            System.out.println(node);
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    
    // A utility function to print Inorder traversal 
    // of the tree. 
    // The function also prints number of occurrences of  every node 
    void inOrder(EmployeeNode node) {
        if (node != null) {
        	inOrder(node.left);
        	System.out.println(node);
            inOrder(node.right);
        }
    }
}