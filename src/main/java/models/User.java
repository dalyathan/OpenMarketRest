package models;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Data
public class User implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	private String fullName;
	private String username;
	private String email;
	private String password;
	private String phoneNo;
	private String pictureId;
	private String locationId;
	@ManyToMany
	private Set<Product> myProducts;
	
	public User(retrofitclassesmapper.User user) {
		this.id=user.getId();
		setAttributes(user);
	}
	public void updateUser(retrofitclassesmapper.User user) {
		setAttributes(user);
	}
	private void setAttributes(retrofitclassesmapper.User user) {
		this.username=user.getUsername();
		this.email=user.getEmail();
		this.password=user.getPassword();
		this.phoneNo=user.getPhoneNo();
		this.pictureId=user.getPictureId();
		this.locationId=user.getLocationId();
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
