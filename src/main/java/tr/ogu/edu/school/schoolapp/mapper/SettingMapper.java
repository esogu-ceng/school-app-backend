package tr.ogu.edu.school.schoolapp.mapper;

import tr.ogu.edu.school.schoolapp.dto.SettingDto;
import tr.ogu.edu.school.schoolapp.model.Setting;

public class SettingMapper {
	private SettingMapper() {
	}

	public static SettingDto toSettingDto(Setting setting) {
		return new SettingDto(setting.getKey(), setting.getValue());
	}

	public static Setting fromSettingDto(SettingDto settingDto) {
		Setting setting = new Setting();
		setting.setKey(settingDto.getKey());
		setting.setValue(settingDto.getValue());
		return setting;
	}
}
