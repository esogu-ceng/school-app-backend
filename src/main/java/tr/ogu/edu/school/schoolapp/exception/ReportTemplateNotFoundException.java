package tr.ogu.edu.school.schoolapp.exception;

import org.hibernate.service.spi.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReportTemplateNotFoundException extends ServiceException {

	private static final long serialVersionUID = 1L;

	private static String message = "Rapor şablon dosyası bulunamadı";

	public ReportTemplateNotFoundException() {
		super(message);
	}

	public ReportTemplateNotFoundException(Exception e) {
		this();
		log.error(message, e);
	}
}
