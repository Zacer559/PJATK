public class CsvWriter {
    public CsvWriter() {
    }

    public void write(String[][] lines) {
        //changed loop type
        for (String[] line : lines) writeLine(line);
    }

    private void writeLine(String[] fields) {
        //parsed code to shorter
        if (fields.length != 0) {
            writeField(fields[0]);

            for (int i = 1; i < fields.length; i++) {
                System.out.print(",");
                writeField(fields[i]);
            }
        }
        System.out.println();
    }

    private void writeField(String field) {
        if (field.indexOf(',') != -1 || field.indexOf('\"') != -1)
            writeQuoted(field);
        else
            System.out.print(field);
    }

    private void writeQuoted(String field) {
        System.out.print('\"');
        for (int i = 0; i < field.length(); i++) {
            char c = field.charAt(i);
            if (c == '\"')
                System.out.print("\"\"");
            else
                System.out.print(c);
        }
        System.out.print('\"');
    }
}