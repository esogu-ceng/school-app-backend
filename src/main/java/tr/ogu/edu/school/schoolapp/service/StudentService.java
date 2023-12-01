package tr.ogu.edu.school.schoolapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.dto.StudentDto;
import tr.ogu.edu.school.schoolapp.mapper.StudentMapper;
import tr.ogu.edu.school.schoolapp.model.Student;
import tr.ogu.edu.school.schoolapp.model.User;
import tr.ogu.edu.school.schoolapp.repository.StudentRepository;

@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentRepository studentRepository;

	public List<StudentDto> getAllStudents() {
		List<Student> students = studentRepository.findAll();
		return students.stream().map(StudentMapper::toStudentDto).collect(Collectors.toList());
  }
  
	public List<Student> getByUserId(Long userId) {
		return studentRepository.getByUserId(userId);
	}

	public StudentDto getStudentById(Long id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
		return StudentMapper.toStudentDto(student);
	}

	@Transactional
	public StudentDto createStudent(StudentDto studentDto) {
		Student student = StudentMapper.fromStudentDto(studentDto);
		student = studentRepository.save(student);
		return StudentMapper.toStudentDto(student);
	}

	@Transactional
	public StudentDto updateStudent(StudentDto studentDto) {
		Long id = studentDto.getId();
		Student existingStudent = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
		existingStudent.setName(studentDto.getName());
		existingStudent.setSurname(studentDto.getSurname());
		existingStudent.setGrade(studentDto.getGrade());
		existingStudent = studentRepository.save(existingStudent);
		return StudentMapper.toStudentDto(existingStudent);
	}

	public boolean deleteStudent(Long id) {
		if (id == null || !studentRepository.existsById(id)) {
			return false;
		}
		studentRepository.deleteById(id);
		return true;
	}
}
