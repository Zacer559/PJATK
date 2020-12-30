import java.io.Serializable;

public class EchoResponse implements Serializable {

    private static final long serialVersionUID = 1234567L;

    private String _msg;


    EchoResponse(String msg) {
        _msg = msg;
    }


    public String get_msg() {
        return _msg;
    }


}
