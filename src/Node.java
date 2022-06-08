public class Node {
    private String c;
    private int value;
    private String code;
    private String side;
    private int qPlace;
    private Node childLeft;
    private Node childRight;

    public int getqPlace() {
        return qPlace;
    }

    public void setqPlace(int qPlace) {
        this.qPlace = qPlace;
    }

    public Node(String c, int value, int qPlace) {
        this.c = c;
        this.value = value;
        this.qPlace = qPlace;
    }

    public Node(String c, int value, int qPlace, Node childLeft, Node childRight) {
        this.c = c;
        this.value = value;
        this.qPlace = qPlace;
        this.childLeft = childLeft;
        this.childRight = childRight;
        code = "";
    }


    public Node getChildLeft() {
        return childLeft;
    }

    public void setChildLeft(Node childLeft) {
        this.childLeft = childLeft;
    }

    public Node getChildRight() {
        return childRight;
    }

    public void setChildRight(Node childRight) {
        this.childRight = childRight;
    }

    public String getC() {
        return c;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setC(String c) {
        this.c = c;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    @Override
    public String toString() {
        return "Node{" +
                "c=" + c +
                ", value=" + value +
                ", code=" + code +
                ", side='" + side + '\'' +
                '}';
    }
}
