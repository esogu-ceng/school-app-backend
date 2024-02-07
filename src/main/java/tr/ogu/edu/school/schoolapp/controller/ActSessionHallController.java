package tr.ogu.edu.school.schoolapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.dto.ActSessionHallDto;
import tr.ogu.edu.school.schoolapp.mapper.ActSessionHallMapper;
import tr.ogu.edu.school.schoolapp.model.ActSessionHall;
import tr.ogu.edu.school.schoolapp.service.ActSessionHallService;

@RestController
@RequestMapping(value = "/session-seats")
@RequiredArgsConstructor
public class ActSessionHallController {

	private final ActSessionHallService actSessionHallService;

	@GetMapping("/{session_id}")
	public ResponseEntity<List<ActSessionHallDto>> getActivities(@PathVariable Long sessionId) {
		List<ActSessionHall> activities = actSessionHallService.getSeatsByHallId(sessionId);
		List<ActSessionHallDto> activityDtos = activities.stream().map(ActSessionHallMapper::toActSessionHallDto)
				.collect(Collectors.toList());
		return ResponseEntity.ok(activityDtos);
	}

}
