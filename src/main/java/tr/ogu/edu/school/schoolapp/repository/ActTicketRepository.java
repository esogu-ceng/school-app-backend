package tr.ogu.edu.school.schoolapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tr.ogu.edu.school.schoolapp.model.ActTicket;

@Repository
public interface ActTicketRepository extends JpaRepository<ActTicket, Long>{
    // Sorgular

    @Query("SELECT a FROM ActTicket a ORDER BY a.id ASC")
    List<ActTicket> getAllActTickets();

}