package tr.ogu.edu.school.schoolapp.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.model.Setting;
import tr.ogu.edu.school.schoolapp.repository.SettingRepository;

@Service
@RequiredArgsConstructor
public class SettingService {

	private final SettingRepository settingRepository;

	@Transactional(readOnly = true)
	public Optional<Setting> getSettingByKey(String key) {
		return Optional.ofNullable(settingRepository.findByKey(key));
	}

	@Transactional
	public Setting updateSetting(String key, String value) {
		Setting setting = settingRepository.findByKey(key);
		if (setting != null) {
			setting.setValue(value);
			setting.setUpdateDate(LocalDateTime.now());
			return settingRepository.save(setting);
		} else {
			throw new EntityNotFoundException("Setting with key " + key + " not found.");
		}
	}
}
