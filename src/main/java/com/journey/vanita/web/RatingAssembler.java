package com.journey.vanita.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.journey.vanita.controllers.RatingsController;
import com.journey.vanita.model.HotelRating;
import com.journey.vanita.repositories.HotelRepository;
import com.journey.vanita.web.dtos.RatingDTO;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
@Component
public class RatingAssembler  extends ResourceAssemblerSupport<HotelRating, RatingDTO>{

	   //Helper to fetch Spring Data Rest Repository links.
    private RepositoryEntityLinks entityLinks;

    public RatingAssembler( RepositoryEntityLinks entityLinks) {
        super(RatingsController.class, RatingDTO.class);
        this.entityLinks = entityLinks;
    }

	@Override
	public RatingDTO toResource(HotelRating hotelRating) {
		 RatingDTO rating = new RatingDTO(hotelRating.getRating(), hotelRating.getComment(), hotelRating.getCustomer_id());

	        // "self" : ".../ratings/{ratingId}"
	        ControllerLinkBuilder ratingLink = linkTo(methodOn(RatingsController.class).getRatingById(hotelRating.getId()));
	        rating.add(ratingLink.withSelfRel());

	        //"hotel" : ".../hotels/{hotelId}"
	       Link tourLink = entityLinks.linkToSingleResource(HotelRepository.class, hotelRating.getHotel().getId());
	       rating.add(tourLink.withRel("hotel"));
	       return rating;
	}

}
