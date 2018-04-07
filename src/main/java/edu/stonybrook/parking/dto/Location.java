package edu.stonybrook.parking.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity 
@Table (name = "LOCATIONS", uniqueConstraints=@UniqueConstraint(columnNames={"LATITUDE", "LONGITUDE"}))
public class Location {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotNull
	@Column (name="LATITUDE")
	private double latitude;
	
	@NotNull
	@Column (name="LONGITUDE")
	private double longitude;
	
	@NotNull
	@Column (name="AVAILABLE")
	private boolean available = true;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Location(){}

	public Location(double latitude, double longitude){
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Location(double latitude, double longitude, boolean avaliable) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.available = avaliable;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public boolean isAvaliable() {
		return available;
	}

	public void setAvaliable(boolean avaliable) {
		this.available = avaliable;
	}

}
