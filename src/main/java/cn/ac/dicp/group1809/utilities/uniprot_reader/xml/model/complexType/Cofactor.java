package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * Describes a cofactor.
 *
 * <pre>
 *      &lt;complexType name="cofactorType">
 *          &lt;annotation>
 *              &lt;documentation>Describes a cofactor. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;sequence>
 *              &lt;element name="name" type="xs:string"/>
 *              &lt;element name="dbReference" type="dbReferenceType"/>
 *          &lt;/sequence>
 *          &lt;attribute name="evidence" type="intListType" use="optional"/>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
public class Cofactor {
	@XmlElement
	private String name;
	@XmlElement
	private DBReference dbReference;
	@XmlAttribute
	@XmlJavaTypeAdapter(IntListAdapter.class)
	private List<Integer> evidence;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DBReference getDbReference() {
		return dbReference;
	}

	public void setDbReference(DBReference dbReference) {
		this.dbReference = dbReference;
	}

	public List<Integer> getEvidence() {
		return evidence;
	}

	public void setEvidence(List<Integer> evidence) {
		this.evidence = evidence;
	}
}
