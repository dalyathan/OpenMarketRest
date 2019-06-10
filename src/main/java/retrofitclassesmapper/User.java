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
	
	
	public User(models.User userModel) {
		this.id=userModel.getId();
		this.fullName=userModel.getFullName();
		this.username=userModel.getUsername();
		this.email=userModel.getEmail();
		this.phoneNo=userModel.getPhoneNo();
		this.pictureId=userModel.getPictureId();
		this.locationId=userModel.getLocationId();
	}
}
