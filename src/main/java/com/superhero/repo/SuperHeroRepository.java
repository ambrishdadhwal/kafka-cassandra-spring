package com.superhero.repo;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.superhero.entity.SuperHero;

import java.util.Optional;

@Repository
public interface SuperHeroRepository extends CassandraRepository<SuperHero, Long>
{

    @AllowFiltering
    Optional<SuperHero> findByName(String name);
}
