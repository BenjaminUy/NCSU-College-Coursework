package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Abstract class that is the parent class for a Registrar user and a Student user.
 * Contains similarities for both types of users including: first name, last name, id,
 * email, and password.
 * @author Hunt Tynch
 * @author Andrew Warren
 * @author Benjamin Uy
 */
public abstract class User {

	/** the user's first name. */
	private String firstName;
	/** The user's last name. */
	private String lastName;
	/** The user id */
	private String id;
	/** the user's email */
	private String email;
	/** The user's password */
	private String password;

	/**
	 * Method constructs a new User object with the given parameters
	 * @param firstname first name of the user
	 * @param lastname last name of the user
	 * @param id user id
	 * @param email user's email
	 * @param password user's password
	 */
	public User(String firstname, String lastname, String id, String email, String password) {
		super();
		setFirstName(firstname);
		setLastName(lastname);
		setId(id);
		setEmail(email);
		setPassword(password);
	}

	/**
	 * Returns user's email
	 * @return user email String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the user's email. 
	 * @param email the user email to set
	 * @throws IllegalArgumentException if email is null, blank, or has invalid formatting
	 */
	public void setEmail(String email) {
		if(email == null || email.length() == 0 || !email.contains("@") || !email.contains("."))
			throw new IllegalArgumentException("Invalid email");
		if(email.indexOf("@") > email.lastIndexOf("."))
			throw new IllegalArgumentException("Invalid email");
		this.email = email;
	}

	/**
	 * Returns the user password.
	 * @return the user password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the user's password.
	 * @param password the user password to set
	 * @throws IllegalArgumentException if password is null or length 0
	 */
	public void setPassword(String password) {
		if(password == null || password.length() == 0)
			throw new IllegalArgumentException("Invalid password");
		this.password = password;
	}

	/**
	 * Sets the user's firstName.
	 * @param firstName the firstName to set
	 * @throws IllegalArgumentException if firstName is null or length 0
	 */
	public void setFirstName(String firstName) {
		if(firstName == null || firstName.length() == 0)
			throw new IllegalArgumentException("Invalid first name");
		this.firstName = firstName;
	}

	/**
	 * Sets the user's lastName.
	 * @param lastName the user lastName to set
	 * @throws IllegalArgumentException if the lastName is null or length 0.
	 */
	public void setLastName(String lastName) {
		if(lastName == null || lastName.length() == 0)
			throw new IllegalArgumentException("Invalid last name");
		this.lastName = lastName;
	}

	/**
	 * Sets the user's Id.
	 * @param id the user id to set
	 * @throws IllegalArgumentException if the Id is null or length 0. 
	 */
	protected void setId(String id) {
		if(id == null || id.length() == 0)
			throw new IllegalArgumentException("Invalid id");
		this.id = id;
	}

	/**
	 * Returns the user firstName.
	 * @return the user firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Returns the user lastName.
	 * @return the user's lastName
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Returns user's Id.
	 * @return the user's Id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Method returns a hashCode of the fields of User
	 * @return integer representing a hashCode of the fields of User
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * Method determines if two User objects are equal
	 * @return true if the object being compared to is equal to this object, false if not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}