package dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDTO {
	private int id;
	private String title;
	private String description;
	private int duration; // in minutes
	private LocalDate releaseDate;
	private String country;
	private String url;
	private String directorName;
	private String categoryName;
	private String actorName;

}
