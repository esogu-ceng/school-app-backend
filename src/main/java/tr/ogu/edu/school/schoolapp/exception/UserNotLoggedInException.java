package tr.ogu.edu.school.schoolapp.exception;

import org.hibernate.service.spi.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserNotLoggedInException extends ServiceException {

	private static final long serialVersionUID = 1L;
	private static String message = "Kullanıcı oturumu bulunamadı.";

	public UserNotLoggedInException() {
		super(message);
	}

	public UserNotLoggedInException(Exception e) {
		this();
		log.error(message, e);
	}

}