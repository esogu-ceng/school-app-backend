package tr.ogu.edu.school.schoolapp.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * The persistent class for the act_hall database table.
 * 
 */
@Data
public class ActHallDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private List<ActSeatDto> actSeats;

	private List<ActSessionInfoDto> actSessionInfos;

}