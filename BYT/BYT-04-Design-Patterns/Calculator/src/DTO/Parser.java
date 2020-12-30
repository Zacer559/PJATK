package DTO;

public interface Parser {

    static Request parse(String input) {
        Request request = null;
        try {
            String[] splitted = input.split(" ");
            char operation = splitted[1].charAt(0);
            int first = Integer.parseInt(splitted[0]);
            int second = Integer.parseInt(splitted[2]);
            request = new Request(first, second, operation);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("""
                    Try writing equation with spaces for example "15 + 10"     """);
        } catch (NumberFormatException e) {
            System.err.println("""
                    Insert numbers not letters like: "15 + 10"     """);
        }
        return request;
    }

}
