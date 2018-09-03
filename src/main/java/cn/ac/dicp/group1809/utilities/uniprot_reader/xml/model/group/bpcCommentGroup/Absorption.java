package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.group.bpcCommentGroup;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.EvidencedString;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author ZhengFang 2018/5/29
 * @since V1.0
 */
public class Absorption extends Text {
	@XmlElement
	private EvidencedString max;

	public EvidencedString getMax() {
		return max;
	}

	public void setMax(EvidencedString max) {
		this.max = max;
	}
}
