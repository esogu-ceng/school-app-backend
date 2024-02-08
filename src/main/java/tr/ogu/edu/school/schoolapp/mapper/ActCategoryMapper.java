package tr.ogu.edu.school.schoolapp.mapper;

import tr.ogu.edu.school.schoolapp.dto.ActCategoryDto;
import tr.ogu.edu.school.schoolapp.model.ActCategory;

public class ActCategoryMapper extends DtoEntityMapper<ActCategory, ActCategoryDto> {

	public static final ActCategoryMapper INSTANCE = new ActCategoryMapper();

	@Override
	public ActCategoryDto toDto(ActCategory entity) {
		ActCategoryDto dto = new ActCategoryDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	@Override
	public ActCategory toEntity(ActCategoryDto dto) {
		ActCategory entity = new ActCategory();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}
}
