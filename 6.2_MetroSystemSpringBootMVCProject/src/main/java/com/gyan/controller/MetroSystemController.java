package com.gyan.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gyan.beans.Card;
import com.gyan.beans.Station;
import com.gyan.model.service.CardService;
import com.gyan.model.service.JourneyService;
import com.gyan.model.service.StationService;

@Controller
public class MetroSystemController {

	@Autowired
	private CardService cardService;
	@Autowired
	private StationService stationService;
	@Autowired
	private JourneyService journeyService;

	// home page
	@RequestMapping("/home")
	public ModelAndView homePageController() {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("command", new Card());
		return mv;
	}
	@RequestMapping("/")
	public ModelAndView homePageController(HttpSession session) {
		List<Station> stations = stationService.getStations();
		session.setAttribute("stations", stations);
		return homePageController();
	}

	// user home page
	@RequestMapping("/userHome")
	public String userHomePage() {
		return "userHome";
	}
	
	public ModelAndView userHomePageController(String message) {
		return new ModelAndView("userHome", "message", message);
	}

	// new registration page
	@RequestMapping("/registerUser")
	public ModelAndView registerUserPageController() {
		return new ModelAndView("userRegistered", "command", new Card());
	}

	// new registration check
	@RequestMapping("/registerationCheck")
	public ModelAndView registerUserController(@ModelAttribute("command") Card card) {
		int id = 0;
		if (card.getUserName() == null || card.getUserName().length() == 0)
			return new ModelAndView("userNotRegistered", "message", "Please input valid user name");
		try {
			id = cardService.registerUser(card.getUserName(), card.getBalance());
			card.setId(id);
		} catch (NumberFormatException e) {
			return new ModelAndView("userNotRegistered", "message", "Please input balance greater than 100");
		}
		if (id != 0)
			return new ModelAndView("userRegistered", "user", card);
		else
			return new ModelAndView("userNotRegistered", "message", "Please input balance greater than 100");
	}
	
	//login page
		public ModelAndView loginPageController(String message) {
			ModelAndView mv = new ModelAndView("login");
			mv.addObject("command", new Card());
			mv.addObject("message", message);
			return mv;
		}

	// login authorization
	@RequestMapping("/loginCheck")
	public ModelAndView loginUserController(@ModelAttribute("command") Card card, HttpSession session) {
		Card userCard = null;
		userCard = cardService.userLogin(card.getId());
		if (userCard != null) {
			boolean isJourneyOn = journeyService.isJourneyOngoing(card.getId());	
			session.setAttribute("user", userCard);
			session.setAttribute("isJourneyOn", isJourneyOn);
			return new ModelAndView("userHome", "user", userCard);
		} else
			return loginPageController("Please input valid user ID");
	}

	// logout
	@RequestMapping("/logout")
	public ModelAndView logoutController(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("isJourneyOn");
		return this.loginPageController("Thankyou for using Metro Service");
	}

	// Show user card balance
	@RequestMapping("/showBalance")
	public ModelAndView getBalanceController(HttpSession session) {
		Card card = (Card) session.getAttribute("user");
		double balance = cardService.viewBalance(card.getId());
		return this.userHomePageController("Balance : " + balance);
	}

	// add user card balance
	@RequestMapping("/inputBalance")
	public ModelAndView addBalancePageController(HttpSession session) {
		Card card = (Card) session.getAttribute("user");
		return new ModelAndView("addBalance", "command", card);
	}

	@RequestMapping("/addBalance")
	public ModelAndView addBalanceController(@RequestParam("balance") double balance, HttpSession session) {
		Card card = (Card) session.getAttribute("user");
		boolean addBalance = cardService.addCardBalance(card.getId(), balance);
		if (addBalance)
			return this.userHomePageController("Balance added Successfully");
		return this.userHomePageController("Something went wrong, contact to admin !");
	}

	// Add Station
	@RequestMapping("/inputStation")
	public ModelAndView addStationPageController() {
		return new ModelAndView("addStation");
	}

	@RequestMapping("/addStation")
	public ModelAndView addStationController(@RequestParam("name") String stationName) {
		ModelAndView mv = new ModelAndView("addStation");
		if(stationName==null || stationName.length()==0) {
			mv.addObject("message", "Invalid Station Name!");
			return mv;
		}
		Station station = stationService.addStation(stationName);
		if (station != null) {
			mv.addObject("station", station);
			mv.addObject("message", "Congratulations, your station has been successfully added.");
		} else
			mv.addObject("message", "Something went wrong, contact to admin !");
		return mv;
	}
	
	@RequestMapping("/Onboarding")
	public ModelAndView userOnBoardingPage() {
		return new ModelAndView("Onboarding","command",new Station());
	}
	
	@RequestMapping("/boarding")
	public ModelAndView userOnBoardingPageController(@ModelAttribute("command") Station station, HttpSession session) {
		Card card = (Card) session.getAttribute("user");
		boolean board = journeyService.swipeIn(card.getId(), station.getNumber());
		if(board) {
			session.setAttribute("isJourneyOn", true);
			return this.userHomePageController("On-boarded from station: "+station.getNumber());
		}
		return this.userHomePageController("Something went wrong, contact to admin !");
	}
	
	@RequestMapping("/offboarding")
	public ModelAndView userOffBoardingPageController(@ModelAttribute("command") Station station, HttpSession session) {
		Card card = (Card) session.getAttribute("user");
		boolean board = journeyService.swipeOut(card.getId(), station.getNumber());
		if(board) {
			double fare = journeyService.getFareForMostRecentJourney(card.getId());
			session.setAttribute("isJourneyOn", false);
			return this.userHomePageController("Off-boarded, Fare: "+fare);
		}
		return this.userHomePageController("Something went wrong, contact to admin !");
	}

	@RequestMapping("/check")
	public ModelAndView userLoginController() {
		return new ModelAndView("demo");
	}
}
