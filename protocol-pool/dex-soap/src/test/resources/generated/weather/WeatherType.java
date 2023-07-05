import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * This class was generated by the CHOReVOLUTION BindingComponent Generator using com.sun.codemodel 2.6
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "WeatherType")
public class WeatherType {

    @XmlElement(name = "latitude", required = true)
    private Double latitude;
    @XmlElement(name = "longitude", required = true)
    private Double longitude;
    @XmlElement(name = "temperature", required = true)
    private Double temperature;
    @XmlElement(name = "pressure", required = true)
    private Double pressure;
    @XmlElement(name = "observation_time", required = true)
    private String observation_time;
    @XmlElement(name = "tag", required = true)
    private String tag;
    @XmlElement(name = "__CLASS__", required = true)
    private String __CLASS__;
    @XmlElement(name = "collection", required = true)
    private String collection;
    @XmlElement(name = "id", required = true)
    private String id;
    @XmlElement(name = "_id", required = true)
    private String _id;
    @XmlElement(name = "location", required = true)
    private Location location;
    @XmlElement(name = "status", required = true)
    private Double status;
    @XmlElement(name = "humidity", required = true)
    private Integer humidity;
    @XmlElement(name = "creation_date", required = true)
    private String creation_date;
    @XmlElement(name = "elevation", required = true)
    private Double elevation;
    @XmlElement(name = "rain", required = true)
    private String rain;

    public Double getlatitude() {
        return latitude;
    }

    public void setlatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getlongitude() {
        return longitude;
    }

    public void setlongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double gettemperature() {
        return temperature;
    }

    public void settemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getpressure() {
        return pressure;
    }

    public void setpressure(Double pressure) {
        this.pressure = pressure;
    }

    public String getobservation_time() {
        return observation_time;
    }

    public void setobservation_time(String observation_time) {
        this.observation_time = observation_time;
    }

    public String gettag() {
        return tag;
    }

    public void settag(String tag) {
        this.tag = tag;
    }

    public String get__CLASS__() {
        return __CLASS__;
    }

    public void set__CLASS__(String __CLASS__) {
        this.__CLASS__ = __CLASS__;
    }

    public String getcollection() {
        return collection;
    }

    public void setcollection(String collection) {
        this.collection = collection;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Location getlocation() {
        return location;
    }

    public void setlocation(Location location) {
        this.location = location;
    }

    public Double getstatus() {
        return status;
    }

    public void setstatus(Double status) {
        this.status = status;
    }

    public Integer gethumidity() {
        return humidity;
    }

    public void sethumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public String getcreation_date() {
        return creation_date;
    }

    public void setcreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public Double getelevation() {
        return elevation;
    }

    public void setelevation(Double elevation) {
        this.elevation = elevation;
    }

    public String getrain() {
        return rain;
    }

    public void setrain(String rain) {
        this.rain = rain;
    }

}
