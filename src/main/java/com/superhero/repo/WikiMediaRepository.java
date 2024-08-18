package com.superhero.repo;

import com.superhero.entity.SuperHero;
import com.superhero.entity.WikiMedia;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface WikiMediaRepository  extends CassandraRepository<WikiMedia, Long> {
}
