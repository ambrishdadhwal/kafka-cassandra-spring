package com.superhero.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.superhero.entity.WikiMedia;
import com.superhero.kafka.KafkaEventStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.superhero.entity.SuperHero;
import com.superhero.service.SuperHeroService;

@RestController
@RequestMapping("/super-heroes")
public class SuperHeroController
{

	@Autowired
	SuperHeroService superHeroService;

	@Autowired
	KafkaEventStream kafkaEventStream;

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

	@GetMapping("/wiki")
	public ResponseEntity<List<WikiMedia>> findAllWiki()
	{

		List<WikiMedia> list = superHeroService.findAllWiki();

		return ResponseEntity.ok().body(list);
	}

	@PostMapping("/new-hero")
	public ResponseEntity<SuperHero> addHero(@RequestBody SuperHero superHero)
	{
		SuperHero hero = superHeroService.addHero(superHero);
		return ResponseEntity.ok().body(hero);
	}

	@PostMapping("/")
	public ResponseEntity startProducer() throws InterruptedException {

		CompletableFuture.runAsync(()-> {
            try {
                kafkaEventStream.wikiMediaEventStream();
            } catch (Exception e) {
            }
        });

		return new ResponseEntity(HttpStatus.OK);
	}
}
