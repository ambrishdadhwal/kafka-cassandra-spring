package com.superhero.service;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.superhero.entity.WikiMedia;
import com.superhero.repo.WikiMediaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superhero.config.HelperUtil;
import com.superhero.entity.SuperHero;
import com.superhero.repo.SuperHeroRepository;

@Service
@Slf4j
public class SuperHeroService
{

	@Autowired
	private SuperHeroRepository repository;

	@Autowired
	private WikiMediaRepository wikiMediaRepository;

	@Autowired
	ObjectMapper mapper;

	public void saveWikiMediaData(String obj)
	{
		try {
			WikiMedia wikiMedia = WikiMedia.builder().id(UUID.randomUUID().toString()).name(obj).build();
			log.info("Saving wikiMedia data: {}", wikiMedia);
			wikiMediaRepository.save(wikiMedia);
		}
		catch (Exception e) {
			log.error("Error in saving wikiMedia data: {}", e.getMessage());
		}

	}

	public List<SuperHero> save()
	{

		List<SuperHero> superHeroes = repository.findAll();
		if (superHeroes.isEmpty())
			repository.saveAll(HelperUtil.getSuperHeroesData());

		return repository.findAll();
	}

	public List<SuperHero> findAll()
	{
		return repository.findAll();
	}

	public List<WikiMedia> findAllWiki()
	{
		return wikiMediaRepository.findAll();
	}

	/*	public List<SuperHero> findAllPage()
		{
			Pageable pageable = PageRequest.of(0, 5);
	
			Page<SuperHero> pagedResult = superRepo.findAll(pageable);
	
			if (pagedResult.hasContent())
			{
				return pagedResult.getContent();
			}
	
			return Collections.EMPTY_LIST;
		}*/

	public SuperHero findById(Long id)
	{
		return repository.findById(id).orElse(SuperHero.builder().build());
	}

	public SuperHero save(SuperHero superHero)
	{
		return repository.save(superHero);
	}

	public SuperHero update(SuperHero superHero)
	{
		return repository.save(superHero);
	}

	public void delete(Long id)
	{
		repository.findById(id).ifPresent(superHero -> repository.delete(superHero));
	}

	public SuperHero addHero(SuperHero superHero) {
		return repository.save(superHero);
	}
}
