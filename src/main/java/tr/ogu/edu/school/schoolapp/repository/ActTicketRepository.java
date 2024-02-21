package tr.ogu.edu.school.schoolapp.repository;

import java.util.List;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tr.ogu.edu.school.schoolapp.model.ActTicket;

@Repository
public interface ActTicketRepository extends JpaRepository<ActTicket, Long> {
	ActTicket findByVerificationCode(String verificationCode);
	List<ActTicket> findBySessionDateAfter(Date date);
}
