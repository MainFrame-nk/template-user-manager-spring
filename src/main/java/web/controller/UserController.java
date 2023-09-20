package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping()
	public String getAllUsers(Model model) {
		model.addAttribute("userList", userService.findAllUsers());
		return "user/users";
	}

	@GetMapping("/search")
	public String getUserById(@RequestParam(required = false) Long id, Model model) {
		User user = userService.findUserById(id);
		if (user == null) {
			return "redirect:/users";
		}
		model.addAttribute("user", user);
		return "user/search";
	}

	@GetMapping("/new")
	public String newUser(Model model) {
		model.addAttribute("newUser", new User());
		return "user/new_user";
	}

	@PostMapping("")
	public String saveUser(@ModelAttribute("user") User user) {
		userService.saveUser(user);
		return "redirect:/users";
	}

	@GetMapping("/{id}/edit")
	public String editUser(@PathVariable("id") Long id, Model model) {
		User user = userService.findUserById(id);
		if (user == null) {
			return "redirect:/users";
		}
		model.addAttribute("user", user);
		return "user/edit_user";
	}

	@PatchMapping("/{id}")
	public String updateUser(@ModelAttribute User user) {
		userService.updateUser(user);
		return "redirect:/users";
	}

	@DeleteMapping("/{id}/delete")
	public String del(@PathVariable("id") Long id) {
		userService.deleteUser(userService.findUserById(id));
		return "redirect:/users";
	}

//	@GetMapping("/{id}")
//	public String getOneUser(@PathVariable("id") Long id, Model model){
//		model.addAttribute("userOne", userService.findUserById(id));
//		return "user/users";
//	}
}