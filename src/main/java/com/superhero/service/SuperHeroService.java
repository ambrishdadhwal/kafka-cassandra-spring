package com.superhero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superhero.HelperUtil;
import com.superhero.entity.SuperHero;
import com.superhero.repo.SuperHeroRepository;

@Service
public class SuperHeroService
{

	@Autowired
	private SuperHeroRepository repository;

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

}
