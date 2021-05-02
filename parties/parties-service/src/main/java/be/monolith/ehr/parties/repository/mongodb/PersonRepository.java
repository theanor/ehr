package be.monolith.ehr.parties.repository.mongodb;

import java.util.List;

import org.springframework.data.rest.core.annotation.RestResource;

import be.monolith.ehr.parties.entity.Person;

public interface PersonRepository<T extends Person> extends PartyRepository<T> {

	@Override
	@RestResource(exported = false)
	void deleteById(String id);

	@Override
	@RestResource(exported = false)
	void delete(Person entity);

	@Override
	@RestResource(exported = false)
	void deleteAll(Iterable<? extends T> entities);

	@Override
	@RestResource(exported = false)
	void deleteAll();

	@Override
	@RestResource(exported = false)
	<S extends T> S save(S entity);

	@Override
	@RestResource(exported = false)
	<S extends T> List<S> saveAll(Iterable<S> entities);

}
