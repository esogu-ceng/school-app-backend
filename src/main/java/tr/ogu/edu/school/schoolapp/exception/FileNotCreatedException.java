package tr.ogu.edu.school.schoolapp.exception;

import org.hibernate.service.spi.ServiceException;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;

@Slf4j
public class FileNotCreatedException extends ServiceException {

	private static final long serialVersionUID = 1L;
	private static String message = "Dosya oluşturulamadı";

	public FileNotCreatedException() {
		super(message);
	}

	public FileNotCreatedException(JRException e) {
		this();
		log.error(message, e);
	}

}
