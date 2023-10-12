package tr.ogu.edu.school.schoolapp.exception;

import org.hibernate.service.spi.ServiceException;

@SuppressWarnings("serial")
public class EntityNotFoundException extends ServiceException {

	public EntityNotFoundException() {
		super("Entity Not Found");
	}
}