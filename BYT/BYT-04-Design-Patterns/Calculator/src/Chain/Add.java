package Chain;

import DTO.Request;
import DTO.Response;

public class Add implements Chain {
    private Chain nextInChain;

    @Override
    public void setNextChain(Chain nextChain) {
        nextInChain = nextChain;
    }

    @Override
    public void calculate(Request request) {
        if (request.getOperation() == '+') {
            Response res = new Response(request.getNum1() + request.getNum2());
            request.setResponse(res);
        } else {
            nextInChain.calculate(request);
        }
    }
}
