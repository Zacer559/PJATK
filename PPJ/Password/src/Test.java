
public class Test {

	public static void main(String[] args) {

		char[][] passwords = { "AbcDe93".toCharArray(), "A1b:A1b>".toCharArray(), "Ab:Acb<".toCharArray(),
				"abc123><".toCharArray(), "Zorro@123".toCharArray() };

		for (int i = 0; i < passwords.length; i++) {
			for (int j = 0; j < passwords[i].length; j++) {
				System.out.print(passwords[i][j]);

			}
			System.out.println();

			if (passwords[i].length < 8) {
				System.out.println("Too Short");
			}
			int count = 0;

			for (int j = 0; j < passwords[i].length; j++) {
				char buff = passwords[i][j];
				for (int p = j + 1; p < passwords[i].length; p++) {
					if (buff == passwords[i][p]) {
						count = count + 2;
					}

				}

			}
			if (count >= 6) {
				System.out.println("Too few diffrent characters");
			}
			count = 0;
			for (char p = 48; p <= 57; p++) {
				for (int j = 0; j < passwords[i].length; j++) {
					if (passwords[i][j] == p)
						count++;

				}
			}
			if (count == 0)
				System.out.println("No digit");

			count = 0;
			for (char p = 65; p <= 90; p++) {
				for (int j = 0; j < passwords[i].length; j++) {
					if (p == passwords[i][j])
						count++;

				}

			}
			if (count == 0)
				System.out.println("No uppercase letter");

			count = 0;
			for (char p = 97; p <= 122; p++) {
				for (int j = 0; j < passwords[i].length; j++) {
					if (p == passwords[i][j])
						count++;

				}

			}
			if (count == 0)
				System.out.println("No lowercase letter");

			for (int j = 0; j < passwords[i].length; j++) {
				if (passwords[i][j] >= 33 && passwords[i][j] <= 47 || passwords[i][j] >= 58 && passwords[i][j] <= 64
						|| passwords[i][j] >= 91 && passwords[i][j] <= 96
						|| passwords[i][j] >= 123 && passwords[i][j] <= 126)
					;
			}
			count++;
			if (count == 0)
				System.out.println("No non alpha numeric");
		}

	}

}
