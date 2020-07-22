package org.leecode.controller;

import org.leecode.service.LeeCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class leecodeControl {

	@Autowired
	LeeCodeService leeCodeService;

	@RequestMapping("/result/{index}")
	public String result(@PathVariable("index") int index) {
		return leeCodeService.getResult(0, index);
	}

	@RequestMapping("/gerneral/{index}")
	public String gerneral(@PathVariable("index") int index) {
		return leeCodeService.getResult(1, index);
	}
}
