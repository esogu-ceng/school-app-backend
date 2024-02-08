package tr.ogu.edu.school.schoolapp.mapper;

import tr.ogu.edu.school.schoolapp.dto.ActSeatDto;
import tr.ogu.edu.school.schoolapp.model.ActSeat;

public class ActSeatMapper extends DtoEntityMapper<ActSeat, ActSeatDto> {

	public static final ActSeatMapper INSTANCE = new ActSeatMapper();

	@Override
	public ActSeatDto toDto(ActSeat entity) {
		ActSeatDto dto = new ActSeatDto();
		dto.setId(entity.getId());
		dto.setActCategory(ActCategoryMapper.INSTANCE.toDto(entity.getActCategory()));
		dto.setLine(entity.getLine());
		dto.setNo(entity.getNo());
		return dto;
	}

	@Override
	public ActSeat toEntity(ActSeatDto dto) {
		ActSeat entity = new ActSeat();
		entity.setId(dto.getId());
		entity.setLine(dto.getLine());
		entity.setNo(dto.getNo());
		entity.setActCategory(ActCategoryMapper.INSTANCE.toEntity(dto.getActCategory()));
		return entity;
	}
}
