public class EmpBT {
    EmployeeNode root;

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

    EmployeeNode insert(EmployeeNode node, int empId) { 
  
        /* 1.  Perform the normal BST insertion */
        if (node == null)
            return (new EmployeeNode(empId));

        if (empId < node.empId)
            node.left = insert(node.left, empId);
        else if (empId > node.empId)
            node.right = insert(node.right, empId);
        else // Duplicate empIds not allowed 
            node.attCount += 1; 
  
        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left),
                height(node.right)); 
  
        /* 3. Get the balance factor of this ancestor 
              node to check whether this node became 
              unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there 
        // are 4 cases Left Left Case 
        if (balance > 1 && empId < node.left.empId)
            return rightRotate(node);

        // Right Right Case 
        if (balance < -1 && empId > node.right.empId)
            return leftRotate(node);

        // Left Right Case 
        if (balance > 1 && empId > node.left.empId) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case 
        if (balance < -1 && empId < node.right.empId) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        } 
  
        /* return the (unchanged) node pointer */
        return node;
    }

    // A utility function to print preorder traversal 
    // of the tree. 
    // The function also prints height of every node 
    void preOrder(EmployeeNode node) {
        if (node != null) {
            System.out.print("\nEmpId:"+node.empId + " AttendanceCount:"+node.attCount);
            preOrder(node.left);
            preOrder(node.right);
        }
    }
}
