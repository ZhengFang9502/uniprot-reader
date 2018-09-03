package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.group.sptrCitationGroup.SptrCitation;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * Describes a citation and a summary of its content.
 * Equivalent to the flat file RN-, RP-, RC-, RX-, RG-, RA-, RT- and RL-lines.
 *
 * <pre>
 *      &lt;complexType name="referenceType">
 *          &lt;annotation>
 *              &lt;documentation>Describes a citation and a summary of its content.
 *             Equivalent to the flat file RN-, RP-, RC-, RX-, RG-, RA-, RT- and RL-lines. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;sequence>
 *              &lt;element name="citation" type="citationType"/>
 *              &lt;group ref="sptrCitationGroup"/>
 *          &lt;/sequence>
 *          &lt;attribute name="evidence" type="intListType" use="optional"/>
 *          &lt;attribute name="key" type="xs:string" use="required"/>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/26
 * @since V1.0
 */
public class Reference {
	@XmlElement
	private Citation citation;
	@XmlElement
	private SptrCitation sptrCitation;
	@XmlAttribute
	@XmlJavaTypeAdapter(IntListAdapter.class)
	private List<Integer> evidence;
	@XmlAttribute(required = true)
	private String key;

	public Citation getCitation() {
		return citation;
	}

	public void setCitation(Citation citation) {
		this.citation = citation;
	}

	public SptrCitation getSptrCitation() {
		return sptrCitation;
	}

	public void setSptrCitation(SptrCitation sptrCitation) {
		this.sptrCitation = sptrCitation;
	}

	public List<Integer> getEvidence() {
		return evidence;
	}

	public void setEvidence(List<Integer> evidence) {
		this.evidence = evidence;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
