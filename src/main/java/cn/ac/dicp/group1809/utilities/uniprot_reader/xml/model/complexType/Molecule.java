package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Describes a molecule by name or unique identifier.
 *
 * <pre>
 *      &lt;complexType name="moleculeType">
 *          &lt;annotation>
 *              &lt;documentation>Describes a molecule by name or unique identifier. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;simpleContent>
 *              &lt;extension base="xs:string">
 *                  &lt;attribute name="id" type="xs:string" use="optional"/>
 *              &lt;/extension>
 *          &lt;/simpleContent>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/26
 * @since V1.0
 */
public class Molecule extends ValueItem {
	@XmlAttribute
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
