package tr.ogu.edu.school.schoolapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tr.ogu.edu.school.schoolapp.model.Setting;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {
	Setting findByKey(String key);

	@Query("SELECT s from Setting s WHERE s.key = 'mailFrom'")
	Setting getMailFrom();

	@Query("SELECT s from Setting s WHERE s.key = 'mailPassword'")
	Setting getMailPassword();

	@Query("SELECT s from Setting s WHERE s.key = 'mailUsername'")
	Setting getMailUsername();

	@Query("SELECT s from Setting s WHERE s.key = 'mailHost'")
	Setting getMailHost();

	@Query("SELECT s from Setting s WHERE s.key = 'mailSender'")
	Setting getMailSender();

	@Query("SELECT s from Setting s WHERE s.key = 'mailPort'")
	Setting getMailPort();

	@Query("SELECT s from Setting s WHERE s.key = 'mailContent'")
	Setting getMailContent();
}
