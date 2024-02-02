package tr.ogu.edu.school.schoolapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tr.ogu.edu.school.schoolapp.dto.StudentDto;
import tr.ogu.edu.school.schoolapp.mapper.StudentMapper;
import tr.ogu.edu.school.schoolapp.model.Student;
import tr.ogu.edu.school.schoolapp.service.AuthenticationService;
import tr.ogu.edu.school.schoolapp.service.StudentService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/students")
public class StudentController {
	private final StudentService studentService;
	private final AuthenticationService authenticationService;

	@GetMapping("/my-students")
	public ResponseEntity<List<StudentDto>> getMyStudents() {
		try {
			List<Student> students = studentService.getMyStudents();
			List<StudentDto> studentDtos = students.stream().map(StudentMapper::toStudentDto)
					.collect(Collectors.toList());
			return ResponseEntity.ok(studentDtos);
		} catch (IllegalStateException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}

	@PostMapping
	public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
		Student student = StudentMapper.fromStudentDto(studentDto);
		Student createdStudent = studentService.createStudent(student);
		return ResponseEntity.ok(StudentMapper.toStudentDto(createdStudent));
	}

	@PutMapping
	public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto) {
		Student student = StudentMapper.fromStudentDto(studentDto);
		Student updatedStudent = studentService.updateStudent(student);
		return ResponseEntity.ok(StudentMapper.toStudentDto(updatedStudent));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteStudent(@PathVariable Long id) {
		boolean result = studentService.deleteStudent(id);
		return ResponseEntity.ok(result);
	}
}
