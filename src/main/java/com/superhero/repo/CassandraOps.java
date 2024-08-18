package com.superhero.repo;

import com.superhero.entity.SuperHero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CassandraOps {

    @Autowired
    CassandraOperations template;

    public void saveHero(SuperHero hero) {
        template.insert(hero);
    }

    public SuperHero getHero(Long heroId) {
        return template.selectOneById(heroId, SuperHero.class);
    }

    public List<SuperHero> getAllHero() {
        return template.select(Query.empty(), SuperHero.class);
    }

    public List<SuperHero> getSuperHeroByNameLike(String name)
	{
        return template.select(Query.query(Criteria.where("name").like("spider")).withAllowFiltering(), SuperHero.class);
    }

}
