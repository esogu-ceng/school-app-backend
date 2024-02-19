package tr.ogu.edu.school.schoolapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.dto.ActSessionHallSeatDto;
import tr.ogu.edu.school.schoolapp.mapper.ActSessionHallMapper;
import tr.ogu.edu.school.schoolapp.model.ActSessionHallSeat;
import tr.ogu.edu.school.schoolapp.service.ActSessionHallService;

@RestController
@RequestMapping(value = "/session-seats")
@RequiredArgsConstructor
public class ActSessionHallSeatController {

	private final ActSessionHallService actSessionHallService;

	@GetMapping("/{sessionId}")
	public ResponseEntity<List<ActSessionHallSeatDto>> getSessionSeats(@PathVariable Long sessionId) {
		List<ActSessionHallSeat> seats = actSessionHallService.getSeatsByHallId(sessionId);
		List<ActSessionHallSeatDto> seatDtos = ActSessionHallMapper.INSTANCE.toDtoList(seats);
		return ResponseEntity.ok(seatDtos);
	}

}
