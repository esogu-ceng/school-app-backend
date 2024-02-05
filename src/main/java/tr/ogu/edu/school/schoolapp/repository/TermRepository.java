package tr.ogu.edu.school.schoolapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tr.ogu.edu.school.schoolapp.model.Installment;
import tr.ogu.edu.school.schoolapp.model.Student;
import tr.ogu.edu.school.schoolapp.model.Term;

@Repository
public interface TermRepository extends JpaRepository<Term, Long> {
	@Query("SELECT i FROM Installment i WHERE i.student = :student AND i.term = :term")
    List<Installment> findInstallmentsByStudentAndTerm(@Param("student") Student student, @Param("term") Term term);

}
