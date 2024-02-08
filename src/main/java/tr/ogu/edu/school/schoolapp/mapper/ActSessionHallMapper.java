package tr.ogu.edu.school.schoolapp.mapper;

import tr.ogu.edu.school.schoolapp.dto.ActSessionHallDto;
import tr.ogu.edu.school.schoolapp.model.ActSessionHall;

public class ActSessionHallMapper extends DtoEntityMapper<ActSessionHall, ActSessionHallDto> {

	public static final ActSessionHallMapper INSTANCE = new ActSessionHallMapper();

	@Override
	public ActSessionHallDto toDto(ActSessionHall entity) {
		ActSessionHallDto dto = new ActSessionHallDto();
		dto.setId(entity.getId());
		dto.setBlockedTime(entity.getBlockedTime());
		dto.setStatus(entity.getStatus());
		dto.setActSeat(ActSeatMapper.INSTANCE.toDto(entity.getActSeat()));
		return dto;
	}

	@Override
	public ActSessionHall toEntity(ActSessionHallDto dto) {
		ActSessionHall entity = new ActSessionHall();
		entity.setId(dto.getId());
		entity.setBlockedTime(dto.getBlockedTime());
		entity.setStatus(dto.getStatus());
		entity.setActSeat(ActSeatMapper.INSTANCE.toEntity(dto.getActSeat()));
		return entity;
	}

}
