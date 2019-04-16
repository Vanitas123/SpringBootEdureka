package com.journey.vanita.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.journey.vanita.model.Hotel;
import com.journey.vanita.model.HotelRating;
import com.journey.vanita.services.HotelServices;
import com.journey.vanita.web.RatingAssembler;
import com.journey.vanita.web.dtos.GlobalErrorResponse;
import com.journey.vanita.web.dtos.RatingDTO;


@RestController(value="/hotels")
public class HotelsController {

	private HotelServices hotelService;

	private RatingAssembler assembler;
	
	private GlobalErrorResponse error;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HotelsController.class);
	
	@Autowired
	 public HotelsController(HotelServices hotelService, RatingAssembler assembler) {
		 this.hotelService=hotelService;
		 this.assembler = assembler;
	}
	
	@RequestMapping(method = RequestMethod.GET , value="/hotels")
	public ResponseEntity<List<Hotel>> getHotels(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "limit", defaultValue = "30") int limit) {
		LOGGER.info("Getting all Hotels..");
		List<Hotel> hotels = hotelService.getAllHotels(page, limit);
		ResponseEntity<List<Hotel>> response = new ResponseEntity<>(hotels, HttpStatus.OK);
		LOGGER.info("Getting all Hotels, size:"+hotels.size());
		return response;

	}

	@RequestMapping(method = RequestMethod.GET , value="hotels/{hotelId}")
	public ResponseEntity<Hotel> getHotelById(
			@PathVariable(name="hotelId") int hotelId) {
		Hotel hotel = hotelService.findHotelById(hotelId);

		ResponseEntity<Hotel> response = new ResponseEntity<>(hotel, HttpStatus.OK);
		return response;

	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {
		ResponseEntity<Hotel> res = null;
		Hotel savedHotel = hotelService.addHotel(hotel);
		if (savedHotel.getId() != 0) {
			res = new ResponseEntity<>(savedHotel, HttpStatus.CREATED);
		}
		return res;
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", value="hotels/{hotelId}")
	public ResponseEntity<Hotel> bookHotelRoom(@Valid @PathVariable int hotelId, @RequestBody Hotel hotel) {
		ResponseEntity<Hotel> res = null;
		/*Hotel fetchHotel = hotelService.findHotelById(hotelId);
		if (fetchHotel.getTotalRooms() < (fetchHotel.getBooked() + hotel.getBooked())) {
			throw new BookingsFullException(" No of bookings is greater than available rooms. Available rooms : "
					+ (fetchHotel.getTotalRooms() - fetchHotel.getBooked()));
		}
		fetchHotel.setBooked(fetchHotel.getBooked() + hotel.getBooked());
*/		Hotel savedHotel = hotelService.bookHotelRoom(hotelId, hotel);

		if (savedHotel.getId() != 0) {
			res = new ResponseEntity<>(savedHotel, HttpStatus.CREATED);
		}
		return res;
	}

	@RequestMapping(method = RequestMethod.GET, value="hotels/{hotelId}/ratings")
	public PagedResources<RatingDTO> getHotelRatings(
			@PathVariable(value = "hotelId") int hotelId,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "limit", defaultValue = "5") int limit,
			PagedResourcesAssembler<HotelRating> pagedAssembler) {
		Pageable pageRequest = PageRequest.of(page, limit);
		Page<HotelRating> ratingsPage = hotelService.lookupRatings(hotelId, pageRequest);
		return pagedAssembler.toResource(ratingsPage, assembler);
	}

	/**
     * Exception handler if NoSuchElementException is thrown in this Controller
     * using custm error object
     * @param ex
     * @return Error message String.
     */
	/*@ResponseStatus(code=HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public @ResponseBody GlobalErrorResponse return400(NoSuchElementException ex) {
		error = new GlobalErrorResponse();
		error.setErrorCode(HttpStatus.NOT_FOUND);
		error.setErrorMessage(ex.getMessage());
        return error;
    }*/
	
	/**
     * Exception handler if NoSuchElementException is thrown in this Controller
     * using default error object
     * @param ex
     * @return Error message String.
     */
	/*@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="Hotel not found")
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
		 return ex.getMessage();
    }*/

}
