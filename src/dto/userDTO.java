package dto;

import java.util.UUID;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class userDTO {
	private UUID id ;
	private String fullname;
	private String username;
	private String email;
	private String password;
	private String phone;
}
