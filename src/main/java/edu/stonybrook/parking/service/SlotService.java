package edu.stonybrook.parking.service;

import java.util.List;

import edu.stonybrook.parking.dto.Location;

public interface SlotService {
	
	public List<Location> getSlots(Location loc);
	
	public boolean addSlots(List<Location> newLocs);
	
	public boolean updateSlot(Location location);
	
	public boolean addSlot(Location location);

}
