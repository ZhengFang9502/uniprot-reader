package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import javax.xml.bind.annotation.XmlElement;

/**
 * Describes the source of the evidence, when it is not assigned by UniProt, but imported from an external database.
 *
 * <pre>
 *      &lt;complexType name="importedFromType">
 *          &lt;annotation>
 *              &lt;documentation>Describes the source of the evidence, when it is not assigned by UniProt, but imported from an external database. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;sequence>
 *              &lt;element name="dbReference" type="dbReferenceType"/>
 *          &lt;/sequence>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
public class ImportedFrom {
	@XmlElement
	private DBReference dbReference;

	public DBReference getDbReference() {
		return dbReference;
	}

	public void setDbReference(DBReference dbReference) {
		this.dbReference = dbReference;
	}
}
