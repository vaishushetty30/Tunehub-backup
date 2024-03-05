package com.kodnest.tunehub.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.tunehub.Entity.Song;
import com.kodnest.tunehub.Entity.User;
import com.kodnest.tunehub.Serviceimpl.SongServiceimpl;
import com.kodnest.tunehub.Serviceimpl.UserServiceimpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UserServiceimpl serviceimpl;

	@Autowired
	SongServiceimpl songService;

	@PostMapping("/register")
	public String addUser(@ModelAttribute User user)
	{
		//email taken from registration form
		String email = user.getEmail();

		//checking if email as entered in registration form
		//is present in DB or not
		boolean status=serviceimpl.emailExists(email);

		if(status==false) {

			serviceimpl.addUser(user);
			System.out.println("User added");
		}
		else {
			System.out.println("User already exists");
		}
		return "login";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}


	@PostMapping("/validate")
	String validate(@RequestParam("email")String email,@RequestParam("password")String password,HttpSession session,Model model) {
		if(serviceimpl.validateUser(email,password)== true) {
			String role=serviceimpl.getRole(email);
			session.setAttribute("email", email);
			if(role.equalsIgnoreCase("admin")) {
				return "admin";
			}
			else {
				User user=serviceimpl.getUser(email);
				boolean userstatus=user.isIspremium();


				List<Song> fetchAllSongs=songService.fetchAllSongs();
				model.addAttribute("songs",fetchAllSongs);
				model.addAttribute("ispremium",userstatus);
				return "customer";
			}
		}
		else {
			return "login";
		}
	}


	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}










}
