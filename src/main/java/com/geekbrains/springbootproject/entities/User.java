package com.geekbrains.springbootproject.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_roles",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;

	public User() {
	}

	public User(String userName, String password, String firstName, String lastName, String email) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public User(String userName, String password, String firstName, String lastName, String email,
			Collection<Role> roles) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", userName='" + userName + '\'' + ", password='" + "*********" + '\''
				+ ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\''
				+ ", roles=" + roles + '}';
	}
}
