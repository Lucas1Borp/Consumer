package com.inmetrics.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.inmetrics.domain.Sites;

public interface SiteRepository extends MongoRepository<Sites, String> {

	Sites findFirstByName(String name);

	@Query("{address: '?0'}")
	List<Sites> findCustomByAddress(String address);

	@Query("{address : {$regex: ?0}}")
	List<Sites> findCustomByRegExAddress(String domain);
	
	Sites findSecondByName(String address);
}
