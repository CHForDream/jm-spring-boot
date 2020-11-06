package com.game.core.eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.game.global.Globals;

@RestController
public class BallanceController {
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/balance-home")
	public String balanceHome() {
		return this.restTemplate.getForObject("http://eureka-client-home/findUrl", String.class);
	}

	@GetMapping("/balance-battle")
	public String balanceBattle() {
		return this.restTemplate.getForObject("http://eureka-client-battle/findUrl", String.class);
	}

	@GetMapping("/findUrl")
	public String hello() {
		return Globals.getAppConfigBean().getChatServerIp();
	}
}