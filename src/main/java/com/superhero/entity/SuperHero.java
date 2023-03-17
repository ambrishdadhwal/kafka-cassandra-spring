package com.superhero.entity;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(value = "super_hero")
public class SuperHero
{

	@PrimaryKey
	private Long id;

	private String name;

	@Column("super_name")
	private String superName;

	private String profession;

	private int age;

	@Column("super_powers")
	private SuperPowers superPowers;

}
