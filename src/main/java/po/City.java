package po;


import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {
	@Id
	@GeneratedValue
	private long id;
	private String city;
	private String last_update;
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLast_update() {
		return last_update;
	}

	public void setLast_update(String last_update) {
		this.last_update = last_update;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
