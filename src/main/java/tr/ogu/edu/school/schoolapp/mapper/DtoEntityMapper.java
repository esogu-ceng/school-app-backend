package tr.ogu.edu.school.schoolapp.mapper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class DtoEntityMapper<E, D> {

	public abstract D toDto(E entity);

	public abstract E toEntity(D dto);

	public List<D> toDtoList(List<E> entityList) {
		List<D> dtoList = entityList.stream().map(e -> toDto(e)).collect(Collectors.toList());
		return dtoList;
	}

	public List<E> toEntityList(List<D> dtoList) {
		List<E> entityList = dtoList.stream().map(d -> toEntity(d)).collect(Collectors.toList());
		return entityList;
	}

}
