package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * <pre>
 *      &lt;complexType name="propertyType">
 *          &lt;attribute name="type" type="xs:string" use="required"/>
 *          &lt;attribute name="value" type="xs:string" use="required"/>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/26
 * @since V1.0
 */
public class Property {
	@XmlAttribute(required = true)
	private String type;
	@XmlAttribute(required = true)
	private String value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
