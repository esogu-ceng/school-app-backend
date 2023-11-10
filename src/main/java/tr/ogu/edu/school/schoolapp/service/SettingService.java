package tr.ogu.edu.school.schoolapp.service;

import java.time.LocalDateTime;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.dto.SettingDto;
import tr.ogu.edu.school.schoolapp.mapper.SettingMapper;
import tr.ogu.edu.school.schoolapp.model.Setting;
import tr.ogu.edu.school.schoolapp.repository.SettingRepository;

@Service
@RequiredArgsConstructor
public class SettingService {

	private final SettingRepository settingRepository;

	@Transactional(readOnly = true)
	public SettingDto getSetting(String key) throws EntityNotFoundException {
		Setting setting = settingRepository.findByKey(key);
		if (setting == null) {
			throw new EntityNotFoundException("Setting with key " + key + " not found.");
		}
		return SettingMapper.toSettingDto(setting);
	}

	@Transactional
	public SettingDto updateSetting(String key, SettingDto settingDto) {
		Setting setting = settingRepository.findByKey(key);
		if (setting != null) {
			setting.setValue(settingDto.getValue());
			setting.setUpdateDate(LocalDateTime.now());
			Setting updatedSetting = settingRepository.save(setting);
			return SettingMapper.toSettingDto(updatedSetting);
		} else {
			throw new EntityNotFoundException("Setting with key " + key + " not found.");
		}
	}
}
