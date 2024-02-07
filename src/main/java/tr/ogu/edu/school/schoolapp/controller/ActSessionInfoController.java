package tr.ogu.edu.school.schoolapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.dto.ActSessionInfoDto;
import tr.ogu.edu.school.schoolapp.mapper.ActSessionInfoMapper;
import tr.ogu.edu.school.schoolapp.model.ActSessionInfo;
import tr.ogu.edu.school.schoolapp.service.ActSessionInfoService;

@RestController
@RequestMapping(value = "/activities")
@RequiredArgsConstructor
public class ActSessionInfoController {

	private final ActSessionInfoService actSessionInfoService;

	@GetMapping
	public ResponseEntity<List<ActSessionInfoDto>> getActivities() {

		List<ActSessionInfo> activities = actSessionInfoService.getActivities();
		List<ActSessionInfoDto> activityDtos = activities.stream().map(ActSessionInfoMapper::toActSessionInfoDto)
				.collect(Collectors.toList());
		return ResponseEntity.ok(activityDtos);
	}

}
