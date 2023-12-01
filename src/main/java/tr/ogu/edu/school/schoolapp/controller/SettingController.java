package tr.ogu.edu.school.schoolapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.dto.SettingDto;
import tr.ogu.edu.school.schoolapp.mapper.SettingMapper;
import tr.ogu.edu.school.schoolapp.model.Setting;
import tr.ogu.edu.school.schoolapp.service.SettingService;

@RestController
@RequestMapping("/settings")
@RequiredArgsConstructor
public class SettingController {

	private final SettingService settingService;

	@GetMapping("/{key}")
	public ResponseEntity<SettingDto> getSetting(@PathVariable(name = "key") String key) {
		Setting setting = settingService.getSetting(key);
		return ResponseEntity.ok(SettingMapper.toSettingDto(setting));
	}

	@PutMapping("/{key}")
	public ResponseEntity<SettingDto> updateSetting(@PathVariable String key, @RequestBody SettingDto settingDto) {
		Setting updatedSetting = settingService.updateSetting(key, settingDto.getValue());
		return ResponseEntity.ok(SettingMapper.toSettingDto(updatedSetting));
	}
}
