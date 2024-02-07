package tr.ogu.edu.school.schoolapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tr.ogu.edu.school.schoolapp.model.ActSessionInfo;

@Repository
public interface ActSessionInfoRepository extends JpaRepository<ActSessionInfo, Long> {

}