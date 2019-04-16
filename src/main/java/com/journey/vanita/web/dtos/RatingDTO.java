package com.journey.vanita.web.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RatingDTO extends ResourceSupport{
    @Min(0)
    @Max(5)
    @JsonProperty(value="rating")
    private Integer rating;

    @Size(max = 255)
    @JsonProperty(value="comment")
    private String comment;

    @NotNull
    @JsonProperty(value="customerId")
    private Integer customerId;

    /**
     * Constructor to fully initialize the RatingDto
     *
     * @param rating
     * @param comment
     * @param customerId
     */
    public RatingDTO(Integer score, String comment, Integer customerId) {
        this.rating = score;
        this.comment = comment;
        this.customerId = customerId;
    }

    protected RatingDTO() {}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
    
}
