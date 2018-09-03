package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.group.proteinNameGroup;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.EvidencedString;

import java.util.List;

/**
 * @author ZhengFang 2018/5/29
 * @since V1.0
 */
public interface NameGroupInf {
	EvidencedString getFullName();

	void setFullName(EvidencedString fullName);

	List<EvidencedString> getShortName();

	void setShortName(List<EvidencedString> shortName);

	List<EvidencedString> getEcNumber();

	void setEcNumber(List<EvidencedString> ecNumber);
}
