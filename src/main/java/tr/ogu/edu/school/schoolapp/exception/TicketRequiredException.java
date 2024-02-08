package tr.ogu.edu.school.schoolapp.exception;

import org.hibernate.service.spi.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TicketRequiredException extends ServiceException{
    private static final long serialVersionUID = 1L;

	private static String message = "Bilet dosyası bulunamadı";

    public TicketRequiredException() {
		super(message);
	}

	public TicketRequiredException(Exception e) {
		this();
		log.error(message, e);
	}
}
