package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.group.proteinNameGroup;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.EvidencedString;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author ZhengFang 2018/5/29
 * @since V1.0
 */
public abstract class NameGroup implements NameGroupInf {
	@XmlElement(required = true)
	private EvidencedString fullName;
	@XmlElement
	private List<EvidencedString> shortName;
	@XmlElement
	private List<EvidencedString> ecNumber;

	public EvidencedString getFullName() {
		return fullName;
	}

	public void setFullName(EvidencedString fullName) {
		this.fullName = fullName;
	}

	public List<EvidencedString> getShortName() {
		return shortName;
	}

	public void setShortName(List<EvidencedString> shortName) {
		this.shortName = shortName;
	}

	public List<EvidencedString> getEcNumber() {
		return ecNumber;
	}

	public void setEcNumber(List<EvidencedString> ecNumber) {
		this.ecNumber = ecNumber;
	}
}
