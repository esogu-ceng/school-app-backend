package tr.ogu.edu.school.schoolapp.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * The persistent class for the settings database table.
 * 
 */
@Entity
@Data
@Table(name = "settings")
public class Setting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "create_date")
	private Timestamp createDate;

	@Column(name = "key", nullable = false, unique = true)
	private String key;

	@Column(name = "update_date")
	private LocalDateTime updateDate;

	private String value;

}