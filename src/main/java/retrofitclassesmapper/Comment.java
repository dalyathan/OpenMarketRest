package retrofitclassesmapper;

import lombok.Data;

@Data
public class Comment {
	private long id;
	private String commentdata;
	private String dateOfComment;
	private String userName;
	public Comment(models.Comment commentModel) {
		this.id=commentModel.getId();
		this.commentdata=commentModel.getCommentdata();
		this.dateOfComment=commentModel.getDateOfComment();
		this.userName=commentModel.getUserName();
	}
}
