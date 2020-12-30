import java.io.Serializable;

public class AddingRequest implements Serializable {
    private int _num1, _num2;


    AddingRequest(int num1, int num2) {

        _num1 = num1;
        _num2 = num2;
    }


    public int get_num1() {
        return _num1;
    }

    public void set_num1(int _num1) {
        this._num1 = _num1;
    }

    public int get_num2() {
        return _num2;
    }

    public void set_num2(int _num2) {
        this._num2 = _num2;
    }


}
