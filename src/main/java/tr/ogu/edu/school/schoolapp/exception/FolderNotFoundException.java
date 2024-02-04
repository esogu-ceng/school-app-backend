package tr.ogu.edu.school.schoolapp.exception;

import org.hibernate.service.spi.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FolderNotFoundException extends ServiceException {

	private static final long serialVersionUID = 1L;

	private static String message = "Dizin bulunamadı";

	public FolderNotFoundException() {
		super(message);
	}

	public FolderNotFoundException(Exception e) {
		this();
		log.error(message, e);
	}
}
