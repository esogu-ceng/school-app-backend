package tr.ogu.edu.school.schoolapp.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tr.ogu.edu.school.schoolapp.model.Installment;
import tr.ogu.edu.school.schoolapp.model.Student;
import tr.ogu.edu.school.schoolapp.model.Term;

@Repository
public interface TermRepository extends JpaRepository<Term, Long> {
	@Query("SELECT i FROM Installment i WHERE i.term.id = :termId AND i.student.id IN :studentIds")
    List<Installment> findInstallmentsByTermIdAndStudentIds(@Param("termId") Long termId, @Param("studentIds") Set<Long> studentIds);
}
