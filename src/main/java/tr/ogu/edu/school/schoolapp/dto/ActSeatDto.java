package tr.ogu.edu.school.schoolapp.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ActSeatDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String line;

	private Integer no;

	private ActCategoryDto actCategory;

	private ActHallDto actHall;

	private List<ActSessionHallSeatDto> actSessionHalls;

}