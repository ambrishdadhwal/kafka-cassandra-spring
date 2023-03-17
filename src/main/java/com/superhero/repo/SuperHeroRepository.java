package com.superhero.repo;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.superhero.entity.SuperHero;

@Repository
public interface SuperHeroRepository extends CassandraRepository<SuperHero, Long>
{

}
