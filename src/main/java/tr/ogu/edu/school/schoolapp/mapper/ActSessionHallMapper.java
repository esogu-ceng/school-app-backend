package tr.ogu.edu.school.schoolapp.mapper;

import tr.ogu.edu.school.schoolapp.dto.ActSessionHallDto;
import tr.ogu.edu.school.schoolapp.model.ActSessionHall;

public class ActSessionHallMapper {
	private ActSessionHallMapper() {
	}

	public static ActSessionHallDto toActSessionHallDto(ActSessionHall entity) {
		ActSessionHallDto dto = new ActSessionHallDto();
		dto.setId(entity.getId());
		dto.setBlockedTime(entity.getBlockedTime());
		dto.setStatus(entity.getStatus());
		dto.setActSeat(ActSeatMapper.toDto(entity.getActSeat()));
		return dto;
	}

	public static ActSessionHall fromActSessionHallDto(ActSessionHallDto dto) {
		ActSessionHall entity = new ActSessionHall();
		entity.setId(dto.getId());
		entity.setBlockedTime(dto.getBlockedTime());
		entity.setStatus(dto.getStatus());
		entity.setActSeat(ActSeatMapper.fromDto(dto.getActSeat()));
		return entity;
	}
}
