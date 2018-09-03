package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.group.proteinNameGroup;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.EvidencedString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * <pre>
 *      &lt;group name="proteinNameGroup">
 *          &lt;sequence>
 *              &lt;element name="recommendedName" minOccurs="0">
 *                  &lt;complexType>
 *                      &lt;sequence>
 *                          &lt;element name="fullName" type="evidencedStringType"/>
 *                          &lt;element name="shortName" type="evidencedStringType" minOccurs="0" maxOccurs="unbounded"/>
 *                          &lt;element name="ecNumber" type="evidencedStringType" minOccurs="0" maxOccurs="unbounded"/>
 *                      &lt;/sequence>
 *                     <!-- xs:attribute name="ref" type="xs:string" use="optional"/ -->
 *                  &lt;/complexType>
 *              &lt;/element>
 *              &lt;element name="alternativeName" minOccurs="0" maxOccurs="unbounded">
 *                  &lt;complexType>
 *                      &lt;sequence>
 *                          &lt;element name="fullName" type="evidencedStringType" minOccurs="0"/>
 *                          &lt;element name="shortName" type="evidencedStringType" minOccurs="0" maxOccurs="unbounded"/>
 *                          &lt;element name="ecNumber" type="evidencedStringType" minOccurs="0" maxOccurs="unbounded"/>
 *                      &lt;/sequence>
 *                     <!-- xs:attribute name="ref" type="xs:string" use="optional"/ -->
 *                  &lt;/complexType>
 *              &lt;/element>
 *              &lt;element name="submittedName" minOccurs="0" maxOccurs="unbounded">
 *                  &lt;complexType>
 *                      &lt;sequence>
 *                          &lt;element name="fullName" type="evidencedStringType"/>
 *                          &lt;element name="ecNumber" type="evidencedStringType" minOccurs="0" maxOccurs="unbounded"/>
 *                      &lt;/sequence>
 *                     <!-- xs:attribute name="ref" type="xs:string" use="optional"/ -->
 *                  &lt;/complexType>
 *              &lt;/element>
 *              &lt;element name="allergenName" type="evidencedStringType" minOccurs="0"/>
 *              &lt;element name="biotechName" type="evidencedStringType" minOccurs="0"/>
 *              &lt;element name="cdAntigenName" type="evidencedStringType" minOccurs="0" maxOccurs="unbounded"/>
 *              &lt;element name="innName" type="evidencedStringType" minOccurs="0" maxOccurs="unbounded"/>
 *          &lt;/sequence>
 *      &lt;/group>
 * </pre>
 *
 * @author ZhengFang 2018/5/26
 * @since V1.0
 */
@XmlType(propOrder = {
		"recommendedName",
		"alternativeName",
		"submittedName",
		"allergenName",
		"biotechName",
		"cdAntigenName",
		"innName",
})
public class ProteinNameGroup {
	@XmlElement
	private RecommendedName recommendedName;
	@XmlElement
	private List<AlternativeName> alternativeName;
	@XmlElement
	private List<SubmittedName> submittedName;
	@XmlElement
	private EvidencedString allergenName;
	@XmlElement
	private EvidencedString biotechName;
	@XmlElement
	private List<EvidencedString> cdAntigenName;
	@XmlElement
	private List<EvidencedString> innName;


	public RecommendedName getRecommendedName() {
		return recommendedName;
	}

	public void setRecommendedName(RecommendedName recommendedName) {
		this.recommendedName = recommendedName;
	}

	public List<AlternativeName> getAlternativeName() {
		return alternativeName;
	}

	public void setAlternativeName(List<AlternativeName> alternativeName) {
		this.alternativeName = alternativeName;
	}

	public List<SubmittedName> getSubmittedName() {
		return submittedName;
	}

	public void setSubmittedName(List<SubmittedName> submittedName) {
		this.submittedName = submittedName;
	}

	public EvidencedString getAllergenName() {
		return allergenName;
	}

	public void setAllergenName(EvidencedString allergenName) {
		this.allergenName = allergenName;
	}

	public EvidencedString getBiotechName() {
		return biotechName;
	}

	public void setBiotechName(EvidencedString biotechName) {
		this.biotechName = biotechName;
	}

	public List<EvidencedString> getCdAntigenName() {
		return cdAntigenName;
	}

	public void setCdAntigenName(List<EvidencedString> cdAntigenName) {
		this.cdAntigenName = cdAntigenName;
	}

	public List<EvidencedString> getInnName() {
		return innName;
	}

	public void setInnName(List<EvidencedString> innName) {
		this.innName = innName;
	}
}
