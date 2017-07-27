package po;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "country")
    private String country;
    @Column(name = "last_update")
    private String lastUpdate;
    @OneToMany(fetch = FetchType.EAGER/*, cascade = CascadeType.PERSIST*/)
    @JoinTable(
            name = "country_city",
            joinColumns = @JoinColumn(name = "country", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="city", referencedColumnName = "id")
    )
    private List<City> citys;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<City> getCitys() {
        return citys;
    }

    public void setCitys(List<City> citys) {
        this.citys = citys;
    }
}
