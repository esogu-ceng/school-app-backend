package tr.ogu.edu.school.schoolapp.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ActCategoryDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;

	private String name;

	private List<ActSeatDto> actSeats;

}