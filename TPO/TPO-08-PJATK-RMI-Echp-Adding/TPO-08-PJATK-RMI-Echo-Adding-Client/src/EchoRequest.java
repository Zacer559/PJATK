import java.io.Serializable;

public class EchoRequest implements Serializable {

    private static final long serialVersionUID = 1234567L;

    private String _msg;

    public EchoRequest() {
    }

    EchoRequest(String msg) {
        _msg = msg;
    }

    public String get_msg() {
        return _msg;
    }

    public void set_msg(String _msg) {
        this._msg = _msg;
    }


}