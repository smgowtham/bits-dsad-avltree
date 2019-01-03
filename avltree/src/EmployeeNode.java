public class EmployeeNode {
    private int empId, attCount;
    private EmployeeNode left, right;

    public int getAttCount() {
        return attCount;
    }

    public int getEmpId() {
        return empId;
    }

    public EmployeeNode getLeft() {
        return left;
    }

    public EmployeeNode getRight() {
        return right;
    }

    public void setAttCount(int attCount) {
        this.attCount = attCount;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setLeft(EmployeeNode left) {
        this.left = left;
    }

    public void setRight(EmployeeNode right) {
        this.right = right;
    }
}