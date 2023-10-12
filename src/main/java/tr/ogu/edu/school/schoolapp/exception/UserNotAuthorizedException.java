package tr.ogu.edu.school.schoolapp.exception;

import org.hibernate.service.spi.ServiceException;

@SuppressWarnings("serial")
public class UserNotAuthorizedException extends ServiceException {

	public UserNotAuthorizedException() {
		super("Authentication error!");
	}
}
