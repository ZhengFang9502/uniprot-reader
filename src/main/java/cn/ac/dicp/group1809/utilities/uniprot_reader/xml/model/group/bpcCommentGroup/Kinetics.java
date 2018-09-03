package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.group.bpcCommentGroup;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.EvidencedString;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author ZhengFang 2018/5/29
 * @since V1.0
 */
public class Kinetics extends Text{
	@XmlElement
	private List<EvidencedString> KM;
	@XmlElement
	private List<EvidencedString> Vmax;

	public List<EvidencedString> getKM() {
		return KM;
	}

	public void setKM(List<EvidencedString> KM) {
		this.KM = KM;
	}

	public List<EvidencedString> getVmax() {
		return Vmax;
	}

	public void setVmax(List<EvidencedString> vmax) {
		Vmax = vmax;
	}
}
