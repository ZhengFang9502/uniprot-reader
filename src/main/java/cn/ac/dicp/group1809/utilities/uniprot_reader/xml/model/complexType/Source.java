package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Describes the source of the data using a database cross-reference (or a 'ref' attribute when the source cannot be found in a public data source, such as PubMed, and is cited only within the UniProtKB entry).
 *
 * <pre>
 *      &lt;complexType name="sourceType">
 *          &lt;annotation>
 *              &lt;documentation>Describes the source of the data using a database cross-reference (or a 'ref' attribute when the source cannot be found in a public data source, such as PubMed, and is cited only within the UniProtKB entry). &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;sequence>
 *              &lt;element name="dbReference" type="dbReferenceType" minOccurs="0"/>
 *          &lt;/sequence>
 *          &lt;attribute name="ref" type="xs:integer" use="optional"/>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
public class Source {
	@XmlElement
	private DBReference dbReference;
	@XmlAttribute
	private int ref;

	public DBReference getDbReference() {
		return dbReference;
	}

	public void setDbReference(DBReference dbReference) {
		this.dbReference = dbReference;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}
}
