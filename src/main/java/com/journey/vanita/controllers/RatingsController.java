package com.journey.vanita.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.journey.vanita.model.Hotel;
import com.journey.vanita.model.HotelRating;
import com.journey.vanita.services.RatingService;
import com.journey.vanita.web.RatingAssembler;
import com.journey.vanita.web.dtos.RatingDTO;

@RestController(value = "/ratings")
public class RatingsController {

	private RatingService ratingService;
	
	private RatingAssembler assembler;
	
	@Autowired
	 public RatingsController(RatingService ratingService, RatingAssembler ratingAssembler) {
		this.ratingService = ratingService;
		this.assembler = ratingAssembler;
	}

	@RequestMapping(method = RequestMethod.GET, value="/ratings")
	public List<RatingDTO> getAllRatings(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "limit", defaultValue = "30") int limit) {
		return assembler.toResources(ratingService.getAllRatings(page, limit));
	}
	@RequestMapping(method = RequestMethod.GET, value="/ratings/{ratingId}")
	public RatingDTO getRatingById(
			@PathVariable("ratingId") int ratingId
			) {
		return assembler.toResource(ratingService.getRatingById(ratingId).orElseThrow(() -> new NoSuchElementException()));
		
	}

}
