package tr.ogu.edu.school.schoolapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.dto.TermDto;
import tr.ogu.edu.school.schoolapp.mapper.TermMapper;
import tr.ogu.edu.school.schoolapp.model.Term;
import tr.ogu.edu.school.schoolapp.repository.TermRepository;

@Service
@RequiredArgsConstructor
public class TermService {

	private final TermRepository termRepository;

	public List<TermDto> getAllTerms() {
		List<Term> terms = termRepository.findAll();
		return terms.stream().map(TermMapper::toTermDto).collect(Collectors.toList());
	}

	public TermDto getTermById(Long id) {
		Term term = termRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Term not found with id: " + id));
		return TermMapper.toTermDto(term);
	}

	@Transactional
	public TermDto createTerm(TermDto termDto) {
		Term term = TermMapper.fromTermDto(termDto);
		term = termRepository.save(term);
		return TermMapper.toTermDto(term);
	}

	@Transactional
	public TermDto updateTerm(TermDto termDto) {
		Long id = termDto.getId();
		Term existingTerm = termRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Term not found with id: " + id));
		existingTerm.setTermName(termDto.getTermName());
		existingTerm.setStartDate(termDto.getStartDate());
		existingTerm.setEndDate(termDto.getEndDate());
		existingTerm = termRepository.save(existingTerm);
		return TermMapper.toTermDto(existingTerm);
	}

	public boolean deleteTerm(Long id) {
		if (id == null || !termRepository.existsById(id)) {
			return false;
		}
		termRepository.deleteById(id);
		return true;
	}
}
