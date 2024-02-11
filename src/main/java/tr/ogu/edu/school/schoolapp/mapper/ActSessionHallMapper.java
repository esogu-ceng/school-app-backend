package tr.ogu.edu.school.schoolapp.mapper;

import tr.ogu.edu.school.schoolapp.dto.ActSessionHallSeatDto;
import tr.ogu.edu.school.schoolapp.model.ActSessionHallSeat;

public class ActSessionHallMapper extends DtoEntityMapper<ActSessionHallSeat, ActSessionHallSeatDto> {

	public static final ActSessionHallMapper INSTANCE = new ActSessionHallMapper();

	@Override
	public ActSessionHallSeatDto toDto(ActSessionHallSeat entity) {
		ActSessionHallSeatDto dto = new ActSessionHallSeatDto();
		dto.setId(entity.getId());
		dto.setBlockedTime(entity.getBlockedTime());
		dto.setStatus(entity.getStatus());
		dto.setActSeat(ActSeatMapper.INSTANCE.toDto(entity.getActSeat()));
		return dto;
	}

	@Override
	public ActSessionHallSeat toEntity(ActSessionHallSeatDto dto) {
		ActSessionHallSeat entity = new ActSessionHallSeat();
		entity.setId(dto.getId());
		entity.setBlockedTime(dto.getBlockedTime());
		entity.setStatus(dto.getStatus());
		entity.setActSeat(ActSeatMapper.INSTANCE.toEntity(dto.getActSeat()));
		return entity;
	}

}
