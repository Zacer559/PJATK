package DTO;

public class Request {

    private final int num1;
    private final int num2;
    private final char operator;
    private Response response = null;

    public Request(int number1, int number2, char operation) {
        super();
        this.num1 = number1;
        this.num2 = number2;
        this.operator = operation;
    }

    public char getOperation() {
        return operator;
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
