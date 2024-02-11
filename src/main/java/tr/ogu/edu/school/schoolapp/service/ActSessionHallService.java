package tr.ogu.edu.school.schoolapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.model.ActSessionHallSeat;
import tr.ogu.edu.school.schoolapp.repository.ActSessionHallSeatRepository;

@Service
@RequiredArgsConstructor
public class ActSessionHallService {

	private final ActSessionHallSeatRepository actSessionHallRepository;

	public List<ActSessionHallSeat> getSeatsByHallId(Long sessionId) {
		return actSessionHallRepository.findAllByActSessionInfoId(sessionId);
	}

}
