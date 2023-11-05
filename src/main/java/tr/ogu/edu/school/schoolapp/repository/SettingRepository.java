package tr.ogu.edu.school.schoolapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tr.ogu.edu.school.schoolapp.model.Setting;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {
	Setting findByKey(String key);
}
