package tr.ogu.edu.school.schoolapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.model.ActSessionInfo;
import tr.ogu.edu.school.schoolapp.repository.ActSessionInfoRepository;

@Service
@RequiredArgsConstructor
public class ActSessionInfoService {

	private final ActSessionInfoRepository actSessionInfoRepository;

	public List<ActSessionInfo> getActivities() {
		return actSessionInfoRepository.findAll();
	}

}
