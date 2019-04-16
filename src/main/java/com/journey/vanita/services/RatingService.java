package com.journey.vanita.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.journey.vanita.model.Hotel;
import com.journey.vanita.model.HotelRating;
import com.journey.vanita.repositories.HotelRatingRepository;

@Service
@Transactional
public class RatingService {

	@Autowired
	private HotelRatingRepository hotelRatingRepo;
	
	public List<HotelRating> getAllRatings(int page, int limit) {

		List<HotelRating> ratings = new ArrayList<>();
		Pageable pageRequest = PageRequest.of(page, limit);
		Page<HotelRating> hotelsPage = hotelRatingRepo.findAll(pageRequest);
		ratings = hotelsPage.getContent();
		return ratings;
	}
	
	public Optional<HotelRating> getRatingById(int ratingId) {
		return hotelRatingRepo.findById(ratingId);
	}
}
