package tr.ogu.edu.school.schoolapp.mapper;

import tr.ogu.edu.school.schoolapp.dto.TermDto;
import tr.ogu.edu.school.schoolapp.model.Term;

public class TermMapper {
	private TermMapper() {
	}

	public static TermDto toTermDto(Term term) {
		TermDto termDto = new TermDto();
		return termDto;
	}

	public static Term fromTermDto(TermDto termDto) {
		Term term = new Term();
		return term;
	}
}
