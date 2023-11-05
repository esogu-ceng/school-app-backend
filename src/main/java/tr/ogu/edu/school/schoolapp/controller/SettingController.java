package tr.ogu.edu.school.schoolapp.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.model.Setting;
import tr.ogu.edu.school.schoolapp.service.SettingService;

@RestController
@RequestMapping("/settings")
@RequiredArgsConstructor
public class SettingController {

	private final SettingService settingService;

	@GetMapping("/{key}")
	public ResponseEntity<Setting> getSettingByKey(@PathVariable String key) {
		return settingService.getSettingByKey(key).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{key}")
	public ResponseEntity<Setting> updateSetting(@PathVariable String key, @RequestBody Setting setting) {
		try {
			Setting updatedSetting = settingService.updateSetting(key, setting.getValue());
			return ResponseEntity.ok(updatedSetting);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
