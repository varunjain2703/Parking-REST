package edu.stonybrook.parking.dao;

import java.util.List;

import edu.stonybrook.parking.dto.Location;

public interface LocationDAO {
	
	public List<Location> getSlots(double x1, double x2, double y1, double y2);
	
	public boolean addSlots(List<Location> newLocs);
	
	public boolean updateSlot(Location location);
	
	public boolean addSlot(Location location);

}
