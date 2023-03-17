package com.superhero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.superhero.entity.SuperHero;
import com.superhero.service.SuperHeroService;

@RestController
@RequestMapping("/super-heroes")
public class SuperHeroController
{

	@Autowired
	SuperHeroService superHeroService;

	@GetMapping("/save")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void save()
	{
		List<SuperHero> list = superHeroService.save();
	}

	@GetMapping("/")
	public ResponseEntity<List<SuperHero>> findAll()
	{

		List<SuperHero> list = superHeroService.findAll();

		return ResponseEntity.ok().body(list);
	}
}
