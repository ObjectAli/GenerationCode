
public class Triad {
    private String label1;
    private String operand1;
    private String operand2;
    private String operation;
    private String label2;
    private String label3;

    public Triad(String label1, String operand1, String operand2, String operation, String label2, String label3) {
        this.label1 = label1;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = operation;
        this.label2 = label2;
        this.label3 = label3;
    }

    public String getOperand1() {
        return operand1;
    }

    public String getOperand2() {
        return operand2;
    }

    public String getOperation() {
        return operation;
    }

    public String getLabel1() {
        return label1;
    }

    public String getLabel2() {
        return label2;
    }

    public String getLabel3() {
        return label3;
    }

    public void setOperand1(String operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(String operand2) {
        this.operand2 = operand2;
    }

    public void setOperation(String action) {
        this.operation = action;
    }

    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    public void setLabel3(String label3) {
        this.label3 = label3;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s", label1, operand1, operand2, operation, label2, label3);
    }
}
