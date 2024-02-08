package tr.ogu.edu.school.schoolapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import tr.ogu.edu.school.schoolapp.model.Installment;
import tr.ogu.edu.school.schoolapp.model.Student;
import tr.ogu.edu.school.schoolapp.model.Term;
import tr.ogu.edu.school.schoolapp.model.User;
import tr.ogu.edu.school.schoolapp.repository.TermRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TermServiceTest {

    private TermService termService;
    private TermRepository termRepository;
    private AuthenticationService authenticationService;
    private StudentService studentService;
    private UserService userService;

    @BeforeEach
    void setUp() {
        termRepository = Mockito.mock(TermRepository.class);
        authenticationService = Mockito.mock(AuthenticationService.class);
        studentService = Mockito.mock(StudentService.class);
        userService = Mockito.mock(UserService.class);

        termService = new TermService(termRepository, authenticationService, studentService, userService);
    }

    @Test
    public void testGetInstallmentsByTermAndStudent() {
        // Mock data
        Long termId = 1L;
        Long studentId = 1L;

        // Mock authentication service
        User user = new User();
        Mockito.when(authenticationService.getAuthenticatedUser()).thenReturn(user);

        // Mock user's students
        List<Student> userStudents = new ArrayList<>();
        Student student = new Student();
        student.setId(studentId);
        userStudents.add(student);
        Mockito.when(studentService.getMyStudents()).thenReturn(userStudents);

        // Mock requested student
        Student requestedStudent = new Student();
        requestedStudent.setId(studentId);
        Mockito.when(studentService.getStudentById(studentId)).thenReturn(requestedStudent);

        // Mock term
        Term term = new Term();
        Mockito.when(termRepository.findById(termId)).thenReturn(Optional.of(term));

        // Mock installment list
        List<Installment> expectedInstallments = new ArrayList<>();
        Mockito.when(termRepository.findInstallmentsByStudentAndTerm(requestedStudent, term)).thenReturn(expectedInstallments);

        // Test
        List<Installment> actualInstallments = termService.getInstallmentsByTermAndStudent(termId, studentId);

        // Verify
        assertEquals(expectedInstallments, actualInstallments);
    }

    @Test
    public void testGetInstallmentsByTermAndStudent_InvalidStudent() {
        // Mock data
        Long termId = 1L;
        Long studentId = 1L;

        // Mock authentication service
        User user = new User();
        Mockito.when(authenticationService.getAuthenticatedUser()).thenReturn(user);

        // Mock user's students
        List<Student> userStudents = new ArrayList<>();
        Student student = new Student();
        student.setId(2L); // Different student ID
        userStudents.add(student);
        Mockito.when(studentService.getMyStudents()).thenReturn(userStudents);

        // Mock requested student
        Student requestedStudent = new Student();
        requestedStudent.setId(studentId);
        Mockito.when(studentService.getStudentById(studentId)).thenReturn(requestedStudent);

        // Test and verify
        assertThrows(IllegalArgumentException.class, () -> {
            termService.getInstallmentsByTermAndStudent(termId, studentId);
        });
    }
}
