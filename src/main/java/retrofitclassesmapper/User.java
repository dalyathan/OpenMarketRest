package retrofitclassesmapper;

import lombok.Data;

@Data
public class User {
	private long id;
	private String fullName;
	private String username;
	private String email;
	private String password;
	private String phoneNo;
	private String pictureId;
	private String locationId;
}
