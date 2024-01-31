package tr.ogu.edu.school.schoolapp.exception;

import org.hibernate.service.spi.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserNotAuthorizedException extends ServiceException {

	private static final long serialVersionUID = 1L;

	private static String message = "Yetki Yok";

	public UserNotAuthorizedException() {
		super(message);
	}

	public UserNotAuthorizedException(Exception e) {
		this();
		log.error(message, e);
	}
}
