package tr.ogu.edu.school.schoolapp.mapper;

import tr.ogu.edu.school.schoolapp.dto.ActCategoryDto;
import tr.ogu.edu.school.schoolapp.model.ActCategory;

public class ActCategoryMapper {
	private ActCategoryMapper() {
	}

	public static ActCategoryDto toActCategoryDto(ActCategory entity) {
		ActCategoryDto dto = new ActCategoryDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	public static ActCategory fromActCategoryDto(ActCategoryDto dto) {
		ActCategory entity = new ActCategory();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}
}
