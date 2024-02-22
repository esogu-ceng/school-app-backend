package tr.ogu.edu.school.schoolapp.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TicketInvalidException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MESSAGE = "Bilet geçersiz veya tanımlanamadı.";

	public TicketInvalidException() {
		super(DEFAULT_MESSAGE);
	}

	public TicketInvalidException(String message) {
		super(message);
	}

	public TicketInvalidException(Throwable cause) {
		super(DEFAULT_MESSAGE, cause);
	}

	public TicketInvalidException(String message, Throwable cause) {
		super(message, cause);
	}
}