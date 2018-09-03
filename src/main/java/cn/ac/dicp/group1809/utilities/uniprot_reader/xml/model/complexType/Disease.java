package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author ZhengFang 2018/5/29
 * @since V1.0
 */
public class Disease {
	@XmlElement
	private String name;
	@XmlElement
	private String acronym;
	@XmlElement
	private String description;
	@XmlElement
	private DBReference dbReference;
	@XmlAttribute
	private String id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DBReference getDbReference() {
		return dbReference;
	}

	public void setDbReference(DBReference dbReference) {
		this.dbReference = dbReference;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
