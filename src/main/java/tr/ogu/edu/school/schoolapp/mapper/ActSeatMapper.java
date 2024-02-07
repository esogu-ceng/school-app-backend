package tr.ogu.edu.school.schoolapp.mapper;

import tr.ogu.edu.school.schoolapp.dto.ActSeatDto;
import tr.ogu.edu.school.schoolapp.model.ActSeat;

public class ActSeatMapper {
	private ActSeatMapper() {
	}

	public static ActSeatDto toDto(ActSeat entity) {
		ActSeatDto dto = new ActSeatDto();
		dto.setId(entity.getId());
		dto.setActCategory(ActCategoryMapper.toActCategoryDto(entity.getActCategory()));
		dto.setLine(entity.getLine());
		dto.setNo(entity.getNo());
		return dto;
	}

	public static ActSeat fromDto(ActSeatDto dto) {
		ActSeat entity = new ActSeat();
		entity.setId(dto.getId());
		entity.setLine(dto.getLine());
		entity.setNo(dto.getNo());
		entity.setActCategory(ActCategoryMapper.fromActCategoryDto(dto.getActCategory()));
		return entity;
	}
}
