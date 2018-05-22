package edu.mum.ea.config;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import edu.mum.ea.domain.User;
import edu.mum.ea.service.UserService;

@Component("sessionListener")
public class SessionListener {

	@Autowired
	UserService service;

	@Autowired
	HttpSession session;

	public User getUser() {
		if (session.getAttribute("loggedUser") != null) {
			return (User) session.getAttribute("loggedUser");
		}
		
		User user = service.findByEmail(getUserName());
		session.setAttribute("loggedUser", user);
		return user;
	}

	private String getUserName() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		
		return userName;
	}

}
