package edu.stonybrook.parking.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import edu.stonybrook.parking.dao.LocationDAO;
import edu.stonybrook.parking.dto.Location;

public class SlotServiceImpl implements SlotService{

	final static Logger logger = Logger.getLogger(SlotServiceImpl.class);
	final static Integer RANGE = 30;
	
	@Autowired
	LocationDAO locationDAO;
	

	public void setLocationDAO(LocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}

	@Override
	public List<Location> getSlots(Location loc){
		logger.debug("Start-Method:" + "getSlots");
		List<Location> locations = locationDAO.getSlots(loc.getLatitude() - RANGE, loc.getLatitude() + RANGE, loc.getLongitude() - RANGE, loc.getLongitude() + RANGE); 
		logger.debug("End-Method:" + "getSlots");
		return locations;
	}
	
	@Override
	public boolean addSlots(List<Location> newLocs){
		return locationDAO.addSlots(newLocs);
	}

	@Override
	public boolean updateSlot(Location location) {
		return locationDAO.updateSlot(location);
	}

	@Override
	public boolean addSlot(Location location) {
		return locationDAO.addSlot(location);
	}
}
