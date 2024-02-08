package tr.ogu.edu.school.schoolapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.model.ActSessionHall;
import tr.ogu.edu.school.schoolapp.repository.ActSessionHallRepository;

@Service
@RequiredArgsConstructor
public class ActSessionHallService {

	private final ActSessionHallRepository actSessionHallRepository;

	public List<ActSessionHall> getSeatsByHallId(Long sessionId) {
		return actSessionHallRepository.findAllByActSessionInfoId(sessionId);
	}

}
