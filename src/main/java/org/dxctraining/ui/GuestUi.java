package org.dxctraining.ui;

import org.dxctraining.JavaConfig;
import org.dxctraining.entities.*;
import org.dxctraining.exceptions.*;
import org.dxctraining.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class GuestUi {
	
	@Autowired
	private IGuestService guestservice;

	@PostConstruct
	private void runapp() {
		try {
			Guest guest1 = new Guest("preethi", 2000);
			Guest guest2 = new Guest("isu", 3000);
			Guest guest3 = new Guest("chinnu", 4000);
			Guest guest4 = new Guest("lavanya", 5000);

			guestservice.register(guest1);
			guestservice.register(guest2);
			guestservice.register(guest3);
			guestservice.register(guest4);

			Guest data = guestservice.findById("1");
			guestservice.delete("3");
			guestservice.updateCost("2", 2500);
			guestservice.updateName(guest1.getId(), "sai");
			display(data);
			displayAll();
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		catch (GuestNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("some thing went wrong");
		}
	}

	public void displayAll() {
		List<Guest> list = guestservice.findAll();
		for (Guest guest : list) {
			display(guest);
		}
	}

	public void display(Guest guest) {
		System.out.println("Guest id :" + guest.getId() + " Guest name : " + guest.getName() + " Room Cost : "
				+ guest.getRoomCost());

	}
}