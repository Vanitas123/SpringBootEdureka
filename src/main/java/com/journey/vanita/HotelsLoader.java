/*package com.journey.vanita;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent; 
import org.springframework.stereotype.Component;

import com.journey.vanita.model.Hotel;
import com.journey.vanita.repositories.HotelRepository;

@Component
public class HotelsLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private  HotelRepository hotelRepo;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		for(int i=0;i<10;i++) {
		Hotel h1 = new Hotel();
		h1.setHotelName(i+". Silver Spoon");
		h1.setAddress("Wakad");
		h1.setDescription("This is test hotel");
		h1.setRating(3);
		h1.setTotalRooms(50);
		hotelRepo.save(h1);
		}
		
	}
}*/