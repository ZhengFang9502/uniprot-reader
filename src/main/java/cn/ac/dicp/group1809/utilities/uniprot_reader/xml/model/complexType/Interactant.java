package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.group.InteractantGroup;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * <pre>
 *      &lt;group name="interactantGroup">
 *          &lt;sequence>
 *              &lt;element name="id" type="xs:string"/>
 *              &lt;element name="label" type="xs:string" minOccurs="0"/>
 *          &lt;/sequence>
 *      &lt;/group>
 * </pre>
 *
 * <pre>
 *      &lt;complexType name="interactantType">
 *          &lt;group ref="interactantGroup" minOccurs="0"/>
 *          &lt;attribute name="intactId" type="xs:string" use="required"/>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
public class Interactant {
	@XmlElement
	private String id;
	@XmlElement
	private String label;

	@XmlAttribute(required = true)
	private String intactId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIntactId() {
		return intactId;
	}

	public void setIntactId(String intactId) {
		this.intactId = intactId;
	}
}
