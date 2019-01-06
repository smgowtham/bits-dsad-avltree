import java.io.PrintWriter;

/**
 * This class is the implementation of the Binary tree ADT and its operations.
 * In this case we are using an AVL tree as an Binary tree ADT.
 */
public class EmpBT {
    EmployeeNode root;
    
    private PrintWriter fileWriter;
    
    public void setfileWriter(PrintWriter fileWriter) {
        this.fileWriter = fileWriter;
    }
    
    public void closefileWriter() {
        System.out.println("Range output written to file "+ System.getProperty("user.dir")+ "output.txt");
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
        if (node == null)
            return (new EmployeeNode(empId));

        if (empId < node.empId)
            node.left = readEmployees(node.left, empId);
        else if (empId > node.empId)
            node.right = readEmployees(node.right, empId);
        else // Duplicate means employee entering the second time, increment attCount
            node.attCount += 1; 
        
        
        /* Get height of the ancestor node */
        node.height = 1 + max(height(node.left),
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
     * Operation 2 in assignment.
     * 
     * 
     * @param emp The root node of the tree
     * @return The number of employees that entered the organization, 0 if none.
     */
    int getHeadcount(EmployeeNode emp) {
        if (emp == null)
            return 0;
        else
            return(getHeadcount(emp.left) + 1 + getHeadcount(emp.right));
        
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
    // A utility function to get the height of the tree 
    int height(EmployeeNode N) {
        if (N == null)
            return 0;

        return N.height;
    }

    // A utility function to get maximum of two integers 
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y 
    // See the diagram given above. 
    EmployeeNode rightRotate(EmployeeNode y) {
        EmployeeNode x = y.left;
        EmployeeNode T2 = x.right;

        // Perform rotation 
        x.right = y;
        y.left = T2;

        // Update heights 
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root 
        return x;
    }

    // A utility function to left rotate subtree rooted with x 
    // See the diagram given above. 
    EmployeeNode leftRotate(EmployeeNode x) {
        EmployeeNode y = x.right;
        EmployeeNode T2 = y.left;

        // Perform rotation 
        y.left = x;
        x.right = T2;

        //  Update heights 
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root 
        return y;
    }

    // Get Balance factor of node N 
    int getBalance(EmployeeNode N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
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