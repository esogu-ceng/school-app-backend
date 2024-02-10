package tr.ogu.edu.school.schoolapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tr.ogu.edu.school.schoolapp.model.Installment;

@Repository
public interface InstallmentRepository extends JpaRepository<Installment, Long> {

	@Query("SELECT i FROM Installment i JOIN i.student s JOIN s.users u WHERE u.id = :userId")
	List<Installment> findInstallmentsByUserId(Long userId);
	// Ödenen taksitleri listelemek için
    @Query("SELECT i FROM Installment i WHERE i.amount <= (SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE i.payment.id = p.id)")
    List<Installment> findPaidInstallments();

    // Ödenmeyen taksitleri listelemek için
    @Query("SELECT i FROM Installment i WHERE i.amount > (SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE i.payment.id= p.id)")
    List<Installment> findUnpaidInstallments();

}