package tr.ogu.edu.school.schoolapp.exception;

import org.hibernate.service.spi.ServiceException;

@SuppressWarnings("serial")
public class PasswordsNotMatchedException extends ServiceException {

	public PasswordsNotMatchedException() {
		super("Passwords do not match!");
	}
}