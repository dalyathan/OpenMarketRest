package retrofitclassesmapper;

import lombok.Data;

@Data
public class Comment {
	private long id;
	private String commentdata;
	private String dateOfComment;
	private String userName;
}
