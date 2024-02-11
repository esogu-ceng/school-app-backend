package tr.ogu.edu.school.schoolapp.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ActSessionInfoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Date activityDate;

	private String activityName;

	private double fee;

	private List<ActSessionHallSeatDto> actSessionHalls;

	private ActHallDto actHall;

}