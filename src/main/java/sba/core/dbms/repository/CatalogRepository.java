package sba.core.dbms.repository;

import org.springframework.data.repository.CrudRepository;

import sba.core.dbms.data.CatalogInfo;

public interface CatalogRepository extends CrudRepository<CatalogInfo, Integer>  {
	
	CatalogInfo findByAccessName(String accessName);

}
