import java.io.Serializable;

public class AddingResponse implements Serializable {
    private int _sum;

    AddingResponse(int num1, int num2) {
        _sum = num1 + num2;
    }

    public int get_sum() {
        return _sum;
    }

}
