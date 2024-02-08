package tr.ogu.edu.school.schoolapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tr.ogu.edu.school.schoolapp.model.ActSessionHall;

@Repository
public interface ActSessionHallRepository extends JpaRepository<ActSessionHall, Long> {

	List<ActSessionHall> findAllByActSessionInfoId(Long sessionId);

}