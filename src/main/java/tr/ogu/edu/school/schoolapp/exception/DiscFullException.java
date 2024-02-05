package tr.ogu.edu.school.schoolapp.exception;

import org.hibernate.service.spi.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DiscFullException extends ServiceException {

	private static final long serialVersionUID = 1L;
	private static String message = "Disk Dolu";

	public DiscFullException() {
		super(message);
	}

	public DiscFullException(Exception e) {
		this();
		log.error(message, e);
	}

}
