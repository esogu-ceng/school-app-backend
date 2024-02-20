package tr.ogu.edu.school.schoolapp.exception;

import org.hibernate.service.spi.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidTicketException extends ServiceException{
    private static final long serialVersionUID = 1L;

	private static String message = "Geçersiz bilet ya da bilet bulunamadı";

    public InvalidTicketException() {
		super(message);
	}

	public InvalidTicketException(Exception e) {
		this();
		log.error(message, e);
	}
}