package sba.core.dbms.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class CatalogInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;	
	
	private CatalogType catalogType;
	
	private String accessName;
	
	private String password;
	
	private String path;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CatalogType getCatalogType() {
		return catalogType;
	}

	public void setCatalogType(CatalogType catalogType) {
		this.catalogType = catalogType;
	}

	public String getAccessName() {
		return accessName;
	}

	public void setAccessName(String accessName) {
		this.accessName = accessName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
