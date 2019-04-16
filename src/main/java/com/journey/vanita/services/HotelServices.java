package com.journey.vanita.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.journey.vanita.aop.TrackTime;
import com.journey.vanita.controllers.HotelsController;
import com.journey.vanita.customexceptions.BookingsFullException;
import com.journey.vanita.model.Hotel;
import com.journey.vanita.model.HotelRating;
import com.journey.vanita.repositories.HotelRatingRepository;
import com.journey.vanita.repositories.HotelRepository;

@Service
@Transactional
public class HotelServices {

	private static final Logger LOGGER = LoggerFactory.getLogger(HotelServices.class);
	
	@Autowired
	private HotelRepository hotelRepo;

	@Autowired
	private HotelRatingRepository hotelRatingRepo;

	@TrackTime
	public List<Hotel> getAllHotels(int page, int limit) {

		List<Hotel> hotels = new ArrayList<>();
		Pageable pageRequest = PageRequest.of(page, limit);
		Page<Hotel> hotelsPage = hotelRepo.findAll(pageRequest);
		hotels = hotelsPage.getContent();
		return hotels;
	}

	public Hotel addHotel(Hotel hotel) {
		return hotelRepo.save(hotel);
	}

	public Hotel findHotelById(int hotelId) throws IllegalArgumentException, NoSuchElementException {
		return hotelRepo.findById(hotelId).get();
	}

	public Hotel bookHotelRoom(@Valid int hotelId, Hotel hotel) throws BookingsFullException {
		Hotel fetchHotel = hotelRepo.findById(hotelId)
				.orElseThrow(() -> new NoSuchElementException("Hotel does not exist " + hotelId));
		int availableRooms = fetchHotel.getTotalRooms() - fetchHotel.getBooked();
		LOGGER.info("Available rooms in hotel "+fetchHotel.getHotelName()+" are "+availableRooms);
		if (availableRooms < (hotel.getBooked())) {
			throw new BookingsFullException(" No of bookings is greater than available rooms. Available rooms : "
					+ availableRooms);
		}
		fetchHotel.setBooked(fetchHotel.getBooked() + hotel.getBooked());
		return hotelRepo.save(fetchHotel);
	}

	public Page<HotelRating> lookupRatings(int hotelId, Pageable pageable) throws NoSuchElementException {
		return hotelRatingRepo.findByHotelId(verifyHotel(hotelId).getId(), pageable);
	}

	private Hotel verifyHotel(int hotelId) throws NoSuchElementException {
		return hotelRepo.findById(hotelId)
				.orElseThrow(() -> new NoSuchElementException("Hotel does not exist " + hotelId));
	}
}
