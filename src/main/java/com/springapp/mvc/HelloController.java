package com.springapp.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;
import websocket.SystemWebSocketHandler;
import websocket.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

	@Bean
	public SystemWebSocketHandler systemWebSocketHandler(){
		return new SystemWebSocketHandler();
	}

	@RequestMapping("/send")
	@ResponseBody
	public String auditing(HttpServletRequest request){
		//无关代码都省略了
		systemWebSocketHandler().sendMessageToUsers(new TextMessage("你好"));
		return "";
	}

	@RequestMapping(value = "/login")
	@ResponseBody
	public String login(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = new User();
		user.setUserid("1");
		user.setUserName("ywm");
		session.setAttribute("U", user);
		return "ok";
	}

	@RequestMapping("/a")
	public String a(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "a";
	}
	@RequestMapping("/b")
	public String b(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "b";
	}
}