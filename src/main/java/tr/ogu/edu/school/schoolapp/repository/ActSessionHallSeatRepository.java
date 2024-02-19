package tr.ogu.edu.school.schoolapp.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;
import tr.ogu.edu.school.schoolapp.enums.SeatStatus;
import tr.ogu.edu.school.schoolapp.model.ActSessionHallSeat;

@Repository
public interface ActSessionHallSeatRepository extends JpaRepository<ActSessionHallSeat, Long> {

	List<ActSessionHallSeat> findAllByActSessionInfoId(Long sessionId);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	List<ActSessionHallSeat> findAllByStatusAndIdGreaterThanOrderByIdAsc(SeatStatus status, Long id, Pageable pageable);

}