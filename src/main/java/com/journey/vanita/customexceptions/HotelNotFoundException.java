package com.journey.vanita.customexceptions;

public class HotelNotFoundException extends RuntimeException {

	public HotelNotFoundException() {
	}

	public HotelNotFoundException(String arg0) {
		super(arg0);
	}

	public HotelNotFoundException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public HotelNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public HotelNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
