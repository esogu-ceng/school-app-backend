package tr.ogu.edu.school.schoolapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tr.ogu.edu.school.schoolapp.model.Installment;

@Repository
public interface InstallmentRepository extends JpaRepository<Installment, Long> {

}