package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * <xs:complexType name="reactionType">
 * <xs:annotation>
 * <xs:documentation>Describes a chemical reaction.</xs:documentation>
 * </xs:annotation>
 * <xs:sequence>
 * <xs:element name="text" type="xs:string"/>
 * <xs:element name="dbReference" type="dbReferenceType" minOccurs="1" maxOccurs="unbounded"/>
 * </xs:sequence>
 * <xs:attribute name="evidence" type="intListType" use="optional"/>
 * </xs:complexType>
 *
 * @author Zheng Fang 2019/7/8
 * @since V1.0.0
 */
public class Reaction {
	@XmlElement
	private String text;
	@XmlElement
	private List<DBReference> dbReference;
	@XmlAttribute
	private List<Integer> evidence;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<DBReference> getDbReference() {
		if (this.dbReference==null){
			this.dbReference=new ArrayList<>();
		}
		return dbReference;
	}

	public void setDbReference(List<DBReference> dbReference) {
		this.dbReference = dbReference;
	}

	public List<Integer> getEvidence() {
		return evidence;
	}

	public void setEvidence(List<Integer> evidence) {
		this.evidence = evidence;
	}
}
