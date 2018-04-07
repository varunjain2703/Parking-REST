package edu.stonybrook.parking.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import edu.stonybrook.parking.dto.Location;
import edu.stonybrook.parking.service.SlotService;

@Path("/slots")
public class SlotResource {

	final static Logger logger = Logger.getLogger(SlotResource.class);
	@Autowired
	private SlotService slotService;

	@GET
	@Path("/{lt}/{lg}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Location> getSlots(@PathParam("lt") double lt, @PathParam("lg") double lg) {
		logger.debug("Start-Method:" + "getSlots");
		List<Location> locations = slotService.getSlots(new Location(lt, lg));
		logger.debug("End-Method:" + "getSlots");
		return locations;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Location> getSlots(List<Location> request) {
		logger.debug("Start-Method:" + "getSlots");
		logger.debug(request);
		List<Location> locations = null;
		if(!request.isEmpty()) {
			locations = slotService.getSlots(request.get(0));
		}
		logger.debug("End-Method:" + "getSlots");
		return locations;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSlot(Location request) {
		logger.debug("Start-Method:" + "updateSlot");
		boolean response = slotService.updateSlot(request);
		logger.debug("End-Method:" + "updateSlot");
		if (response)
			return Response.status(Response.Status.OK).entity("Location Updated").build();
		return Response.status(Response.Status.BAD_REQUEST).entity("UPDATION FAILED").build();
	}

	@Path ("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSlots(List<Location> request) {
		logger.debug("Start-Method:" + "addSlots");
		boolean response = slotService.addSlots(request);
		logger.debug("End-Method:" + "addSlots");
		if (response)
			return Response.status(Response.Status.CREATED).entity("Locations Added").build();
		return Response.status(Response.Status.BAD_REQUEST).entity("ADDITION FAILED").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSlot() {
		logger.debug("Start-Method:" + "getSlot");
		Location location = new Location(70.85, 78.25);
		//boolean response = service.addSlot(location);
		boolean response = true;
		logger.debug("End-Method:" + "getSlot");
		if (response)
			return Response.status(Response.Status.CREATED).entity(location).build();
		return Response.status(Response.Status.BAD_REQUEST).entity("OPERATION FAILED").build();
	}
}
