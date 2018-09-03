package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Describes the evidence for an annotation.
 * No flat file equivalent.
 *
 * <pre>
 *      &lt;complexType name="evidenceType">
 *          &lt;annotation>
 *              &lt;documentation>Describes the evidence for an annotation.
 *             No flat file equivalent. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;sequence>
 *              &lt;element name="source" type="sourceType" minOccurs="0"/>
 *              &lt;element name="importedFrom" type="importedFromType" minOccurs="0"/>
 *          &lt;/sequence>
 *          &lt;attribute name="type" type="xs:string" use="required">
 *              &lt;annotation>
 *                  &lt;documentation>Describes the type of an evidence using the Evidence Code Ontology (http://www.obofoundry.org/cgi-bin/detail.cgi?id=evidence_code). &lt;/documentation>
 *              &lt;/annotation>
 *          &lt;/attribute>
 *          &lt;attribute name="key" type="xs:integer" use="required">
 *              &lt;annotation>
 *                  &lt;documentation>A unique key to link annotations (via 'evidence' attributes) to evidences. &lt;/documentation>
 *              &lt;/annotation>
 *          &lt;/attribute>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
public class Evidence {
	@XmlElement
	private Source source;
	@XmlElement
	private ImportedFrom importedFrom;

	/**
	 * Describes the type of an evidence using the Evidence Code Ontology (http://www.obofoundry.org/cgi-bin/detail.cgi?id=evidence_code).
	 */
	@XmlAttribute(required = true)
	private String type;
	/**
	 * A unique key to link annotations (via 'evidence' attributes) to evidences.
	 */
	@XmlAttribute(required = true)
	private int key;

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public ImportedFrom getImportedFrom() {
		return importedFrom;
	}

	public void setImportedFrom(ImportedFrom importedFrom) {
		this.importedFrom = importedFrom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
}
