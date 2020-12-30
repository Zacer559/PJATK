public class Main {
    public static void main(String[] args) {
        int host = 8085;
        if (args.length == 1)
            host = Integer.valueOf(args[0]);
        if (args.length > 1)
            throw new IllegalArgumentException("Too many arguments");


        Proxy myProxy = new Proxy(host);
        myProxy.listen();

    }


}
