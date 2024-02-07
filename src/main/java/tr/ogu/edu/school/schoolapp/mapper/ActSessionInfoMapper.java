package tr.ogu.edu.school.schoolapp.mapper;

import tr.ogu.edu.school.schoolapp.dto.ActSessionInfoDto;
import tr.ogu.edu.school.schoolapp.model.ActSessionInfo;

public class ActSessionInfoMapper {
	private ActSessionInfoMapper() {
	}

	public static ActSessionInfoDto toActSessionInfoDto(ActSessionInfo actSessionInfo) {
		ActSessionInfoDto dto = new ActSessionInfoDto();
		dto.setId(actSessionInfo.getId());
		dto.setActivityDate(actSessionInfo.getActivityDate());
		dto.setActivityName(actSessionInfo.getActivityName());
		dto.setFee(actSessionInfo.getFee());
		return dto;
	}

	public static ActSessionInfo fromActSessionInfoDto(ActSessionInfo dto) {
		ActSessionInfo actSessionInfo = new ActSessionInfo();
		actSessionInfo.setId(dto.getId());
		actSessionInfo.setActivityDate(dto.getActivityDate());
		actSessionInfo.setActivityName(dto.getActivityName());
		actSessionInfo.setFee(dto.getFee());
		return actSessionInfo;
	}
}
