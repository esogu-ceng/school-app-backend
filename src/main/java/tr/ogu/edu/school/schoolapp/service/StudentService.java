package tr.ogu.edu.school.schoolapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.model.Student;
import tr.ogu.edu.school.schoolapp.model.User;
import tr.ogu.edu.school.schoolapp.repository.StudentRepository;

@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentRepository studentRepository;
	private final AuthenticationService authenticationService;

	public List<Student> getMyStudents() {
		User currentUser = authenticationService.getAuthenticatedUser();
		if (currentUser == null) {
			throw new IllegalStateException("No authenticated user found.");
		}
		return studentRepository.findStudentsByUserId(currentUser.getId());
	}

	@Transactional
	public Student createStudent(Student student) {
		if (student.getName() == null || student.getName().isEmpty() || student.getSurname() == null
				|| student.getSurname().isEmpty() || student.getGrade() == null) {
			throw new IllegalArgumentException(
					"Missing or incorrect student information. Please fill in all required fields.");
		}
		return studentRepository.save(student);
	}

	@Transactional
	public Student updateStudent(Student student) {
		if (student.getId() == null) {
			throw new IllegalArgumentException("Student ID cannot be null for an update.");
		}
		Student existingStudent = studentRepository.findById(student.getId())
				.orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + student.getId()));

		if (student.getName() == null || student.getName().isEmpty() || student.getSurname() == null
				|| student.getSurname().isEmpty() || student.getGrade() == null) {
			throw new IllegalArgumentException(
					"Missing or incorrect student information. Please fill in all required fields.");
		}

		existingStudent.setName(student.getName());
		existingStudent.setSurname(student.getSurname());
		existingStudent.setGrade(student.getGrade());
		return studentRepository.save(existingStudent);
	}

	@Transactional
	public boolean deleteStudent(Long id) {
		if (id == null || !studentRepository.existsById(id)) {
			return false;
		}
		studentRepository.deleteById(id);
		return true;
	}

	public Student getStudentById(Long studentId) {
	    return studentRepository.findById(studentId).orElse(null);
	}

}
