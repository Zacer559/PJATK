package eu.glowacki.utp.assignment08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class InputParser {
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	// 1. Use regular expresssions (Pattern) for validating input data
	// U¿yæ regularnych wyra¿eñ (Pattern) do walidacji danych wejœciowych
	//
	// 2. Convert input string representing date using SimpleDateFormat "yyyy-MM-dd"
	// Konwersjê wejœciowego ci¹gu znaków reprezentuj¹cego datê nale¿y oprzeæ np.
	// DateFormat
	// SimpleDateFormat format "yyyy-MM-dd"
	private static String patt = new String(
			"[A-Z]{1}[a-z]+\\s[A-Z]{1}[a-z]+\\s([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))");

	public static List<Person> parse(File file) {
		List<Person> personList = new ArrayList<>();
		try {
			String line;
			BufferedReader read = new BufferedReader(new FileReader(file));
			while ((line = read.readLine()) != null) {
				if (line.matches(patt)) {
					String firstName, lastName;
					Date birthDate;
					String[] wholeLine = line.split(" ");
					firstName = wholeLine[0];
					lastName = wholeLine[1];

					try {
						birthDate = dateFormat.parse(wholeLine[2]);

						personList.add(new Person(firstName, lastName, birthDate));
					} catch (ParseException e) {
						e.printStackTrace();
					}

				}
			}
			read.close();
		} catch (IOException e) {

			e.printStackTrace();

		}

		return personList;

	}
}