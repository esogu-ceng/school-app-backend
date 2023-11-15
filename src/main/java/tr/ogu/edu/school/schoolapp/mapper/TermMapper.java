package tr.ogu.edu.school.schoolapp.mapper;

import tr.ogu.edu.school.schoolapp.dto.TermDto;
import tr.ogu.edu.school.schoolapp.model.Term;

public class TermMapper {
	private TermMapper() {
	}

	public static TermDto toTermDto(Term term) {
		TermDto termDto = new TermDto();
		termDto.setId(term.getId());
		termDto.setTermName(term.getTermName());
		termDto.setStartDate(term.getStartDate());
		termDto.setEndDate(term.getEndDate());
		return termDto;
	}

	public static Term fromTermDto(TermDto termDto) {
		Term term = new Term();
		term.setId(termDto.getId());
		term.setTermName(termDto.getTermName());
		term.setStartDate(termDto.getStartDate());
		term.setEndDate(termDto.getEndDate());
		return term;
	}
}
