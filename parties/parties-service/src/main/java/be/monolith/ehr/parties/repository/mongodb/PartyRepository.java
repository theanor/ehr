package be.monolith.ehr.parties.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import be.monolith.ehr.parties.entity.Party;
import be.monolith.ehr.value.Identifier;

public interface PartyRepository<P extends Party> extends MongoRepository<P, String> {

	@RestResource(path = "identifiers")
	P findByIdentifiers(Identifier id);

}
