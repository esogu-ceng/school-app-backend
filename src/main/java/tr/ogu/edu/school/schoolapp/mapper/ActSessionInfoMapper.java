package tr.ogu.edu.school.schoolapp.mapper;

import tr.ogu.edu.school.schoolapp.dto.ActSessionInfoDto;
import tr.ogu.edu.school.schoolapp.model.ActSessionInfo;

public final class ActSessionInfoMapper extends DtoEntityMapper<ActSessionInfo, ActSessionInfoDto> {

	public static final ActSessionInfoMapper INSTANCE = new ActSessionInfoMapper();

	@Override
	public ActSessionInfoDto toDto(ActSessionInfo actSessionInfo) {
		ActSessionInfoDto dto = new ActSessionInfoDto();
		dto.setId(actSessionInfo.getId());
		dto.setActivityDate(actSessionInfo.getActivityDate());
		dto.setActivityName(actSessionInfo.getActivityName());
		dto.setFee(actSessionInfo.getFee());
		return dto;
	}

	@Override
	public ActSessionInfo toEntity(ActSessionInfoDto dto) {
		ActSessionInfo entity = new ActSessionInfo();
		entity.setId(dto.getId());
		entity.setActivityDate(dto.getActivityDate());
		entity.setActivityName(dto.getActivityName());
		entity.setFee(dto.getFee());
		return entity;
	}

}
