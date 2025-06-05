package inser.spring.spring3_oauth2_web_login_google_github.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Table(name = "users")
@Entity
public class Users {
	
	@Id
	@Column(name = "id_user", nullable = false)
	String idUser;

	@Convert(converter = PasswordConverterEncoder.class) // To encode password
	@Column(name = "password", nullable = false)
	String password;

	@Column(name = "role", nullable = false)
	String role;

	@Column(name = "timestamp", nullable = false)
	Timestamp timestamp = Timestamp.from(Instant.now());
	
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		return "Users [id_user=" + idUser + ", password=" + password + ", role=" + role
				+ ", timestamp=" + timestamp + "]";
	}
}
