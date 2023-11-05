package tr.ogu.edu.school.schoolapp.mapper;

import tr.ogu.edu.school.schoolapp.dto.SettingDto;
import tr.ogu.edu.school.schoolapp.model.Setting;

public class SettingMapper {
	private SettingMapper() {
	}

	public static SettingDto toSettingDto(Setting setting) {
		SettingDto settingDto = new SettingDto();
		return settingDto;
	}

	public static Setting fromSettingDto(SettingDto settingDto) {
		Setting setting = new Setting();
		return setting;
	}
}
