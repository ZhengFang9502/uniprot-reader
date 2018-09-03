package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * Describes a database cross-reference.
 * Equivalent to the flat file DR-line.
 *
 * <pre>
 *      &lt;complexType name="dbReferenceType">
 *          &lt;annotation>
 *              &lt;documentation>Describes a database cross-reference.
 *             Equivalent to the flat file DR-line.
 *              &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;sequence>
 *              &lt;element name="molecule" type="moleculeType" minOccurs="0"/>
 *              &lt;element name="property" type="propertyType" minOccurs="0" maxOccurs="unbounded"/>
 *          &lt;/sequence>
 *          &lt;attribute name="type" type="xs:string" use="required">
 *              &lt;annotation>
 *                  &lt;documentation>Describes the name of the database. &lt;/documentation>
 *              &lt;/annotation>
 *          &lt;/attribute>
 *          &lt;attribute name="id" type="xs:string" use="required">
 *              &lt;annotation>
 *                  &lt;documentation>Describes a unique database identifier. &lt;/documentation>
 *              &lt;/annotation>
 *          &lt;/attribute>
 *          &lt;attribute name="evidence" type="intListType" use="optional"/>
 *         <!-- xs:attribute name="key" type="xs:string" use="optional"/ -->
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/26
 * @since V1.0
 */
public class DBReference {
	@XmlElement
	private Molecule molecule;
	@XmlElement
	private List<Property> property;

	/**
	 * Describes the name of the database.
	 */
	@XmlAttribute
	private String type;
	/**
	 * Describes a unique database identifier.
	 */
	@XmlAttribute
	private String id;
	@XmlAttribute
	@XmlJavaTypeAdapter(IntListAdapter.class)
	private List<Integer> evidence;

	public Molecule getMolecule() {
		return molecule;
	}

	public void setMolecule(Molecule molecule) {
		this.molecule = molecule;
	}

	public List<Property> getProperty() {
		return property;
	}

	public void setProperty(List<Property> property) {
		this.property = property;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Integer> getEvidence() {
		return evidence;
	}

	public void setEvidence(List<Integer> evidence) {
		this.evidence = evidence;
	}
}
