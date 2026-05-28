package web.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Business logic to handle login functions.
 * 
 * @author Ahsan.
 */
public class LoginService {
	
	public static final int MAX_FIELD_LENGTH = 64;

	/**
	 * Static method returns true for successful login, false otherwise.
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean login(String username, String password, String dob) {

		String validUsername = "ahsan";
		String validPassword = "ahsan_pass";
		String validDob = "2000-01-15";
		if (username == null || password == null || dob == null)
			return false;
		if (username.isBlank() || password.isBlank() || dob.isBlank())
			return false;
		if (username.length() > MAX_FIELD_LENGTH
                || password.length() > MAX_FIELD_LENGTH
                || dob.length() > MAX_FIELD_LENGTH) {
            return false;
        }
		
		LocalDate inputDob;
		try {
			inputDob = LocalDate.parse(dob);
		} catch (DateTimeParseException e) {
			return false;
		}
		LocalDate storedDob = LocalDate.parse(validDob);
		return username.equals(validUsername) && password.equals(validPassword) && inputDob.equals(storedDob);
	}

}
