public class EmployeeNode {
    public int empId, attCount;
    public int height;
    public EmployeeNode left, right;

    EmployeeNode(int empId) {
        this.empId = empId;
        attCount = 1;
        height = 1;
    }

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