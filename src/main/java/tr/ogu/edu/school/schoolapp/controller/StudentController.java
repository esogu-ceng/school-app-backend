package tr.ogu.edu.school.schoolapp.controller;

import java.util.List;

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
import tr.ogu.edu.school.schoolapp.service.StudentService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/students")
public class StudentController {

	private final StudentService studentService;

	@GetMapping
	public ResponseEntity<List<StudentDto>> getAllStudents() {
		return ResponseEntity.ok(studentService.getAllStudents());
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
		StudentDto studentDto = studentService.getStudentById(id);
		return ResponseEntity.ok(studentDto);
	}

	@PostMapping
	public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
		StudentDto createdStudentDto = studentService.createStudent(studentDto);
		return ResponseEntity.ok(createdStudentDto);
	}

	@PutMapping
	public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto) {
		StudentDto updatedStudentDto = studentService.updateStudent(studentDto);
		return ResponseEntity.ok(updatedStudentDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteStudent(@PathVariable Long id) {
		boolean result = studentService.deleteStudent(id);
		return ResponseEntity.ok(result);
	}
}
