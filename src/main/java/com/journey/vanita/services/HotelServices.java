package com.journey.vanita.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.journey.vanita.model.Hotel;
import com.journey.vanita.model.HotelRating;
import com.journey.vanita.repositories.HotelRatingRepository;
import com.journey.vanita.repositories.HotelRepository;

@Service
@Transactional
public class HotelServices {

	@Autowired
	private HotelRepository hotelRepo;

	@Autowired
	private HotelRatingRepository hotelRatingRepo;

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

	public Hotel bookHotelRoom(Hotel updatedHotel) {
		return hotelRepo.save(updatedHotel);
	}

	/**
	 * Get a page of hotel ratings for a tour.
	 *
	 * @param hotelId
	 *            tour identifier
	 * @param pageable
	 *            page parameters to determine which elements to fetch
	 * @return Page of TourRatings
	 * @throws NoSuchElementException
	 *             if no Tour found.
	 */
	public Page<HotelRating> lookupRatings(int hotelId, Pageable pageable) throws NoSuchElementException {
		return hotelRatingRepo.findByHotelId(verifyHotel(hotelId).getId(), pageable);
	}

	private Hotel verifyHotel(int tourId) throws NoSuchElementException {
		return hotelRepo.findById(tourId)
				.orElseThrow(() -> new NoSuchElementException("Hotel does not exist " + tourId));
	}
}
