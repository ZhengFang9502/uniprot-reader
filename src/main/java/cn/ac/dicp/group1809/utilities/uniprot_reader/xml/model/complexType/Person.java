package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * <pre>
 * 	&lt;complexType name="personType">
 * 		&lt;attribute name="name" type="xs:string" use="required"/>
 * 	&lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
public class Person implements NameInf {
	@XmlAttribute(required = true)
	private String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}
