import java.io.*;

public class Glowna {

	public static void main(String[] args) {
		ParenStack st = new ParenStack();
		String fileName = "Z:\\ex.txt";
		String line = null;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == ')' | line.charAt(i) == '(') {
						if (line.charAt(i) == '(')
							st.push('(');
						if (line.charAt(i) == ')') {
							if (st.getTop() != '(') {
								System.out.println("Error at " + i + " replace with" + st.getTop());
								st.pop();
							} else
								st.pop();
						}
					}

					if (line.charAt(i) == '{' | line.charAt(i) == '}') {
						if (line.charAt(i) == '{')
							st.push('{');
						if (line.charAt(i) == '}') {
							if (st.getTop() != '{') {
								System.out.println("Error at " + i + " replace with" + st.getTop());
								st.pop();
							}else
								st.pop();
						} 
					}

					if (line.charAt(i) == '[' | line.charAt(i) == ']') {
						if (line.charAt(i) == '[')
							st.push('[');
						if (line.charAt(i) == ']') {
							if (st.getTop() != '[') {
								System.out.println("Error at " + i + " replace with" + st.getTop());
								st.pop();
							} else
								st.pop();
						}
					}
				}
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}

	}

	/*
	 * public static int check(char a) { switch (a) { case '(': return 1; case ')':
	 * return 2; case '{':return 3; case '}':return 4; case '[': return 5; case ']':
	 * return 6; default: return 0; }
	 * 
	 * }
	 * 
	 * public static void result (char p) {
	 * 
	 * 
	 * }
	 */

}
