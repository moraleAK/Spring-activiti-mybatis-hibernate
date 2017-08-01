package po;


import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {
	@Id
	@GeneratedValue
	private long id;
	private String city;
	@Column(name = "lastUpdate")
	private String lastUpdate;
	@Transient
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

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
