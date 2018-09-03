package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * <pre>
 * 	&lt;complexType name="nameListType">
 * 		&lt;choice maxOccurs="unbounded">
 * 			&lt;element name="consortium" type="consortiumType"/>
 * 			&lt;element name="person" type="personType"/>
 * 		&lt;/choice>
 * 	&lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
public class NameList {
	@XmlElement
	private List<Consortium> consortium ;
	@XmlElement
	private List<Person> person ;

	public List<Consortium> getConsortium() {
		return consortium;
	}

	public void setConsortium(List<Consortium> consortium) {
		this.consortium = consortium;
	}

	public List<Person> getPerson() {
		return person;
	}

	public void setPerson(List<Person> person) {
		this.person = person;
	}
}
