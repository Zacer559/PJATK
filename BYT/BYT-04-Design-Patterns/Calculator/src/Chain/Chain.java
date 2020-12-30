package Chain;

import DTO.Request;

public interface Chain {

    void setNextChain(Chain nextChain);

    void calculate(Request request);

}
