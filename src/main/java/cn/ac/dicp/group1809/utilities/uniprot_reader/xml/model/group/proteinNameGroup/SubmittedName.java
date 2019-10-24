package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.group.proteinNameGroup;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.EvidencedString;

import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @author ZhengFang 2018/5/26
 * @since V1.0
 */
@XmlType(name = "submittedName", propOrder = {
		"fullName",
		"ecNumber",
})
public class SubmittedName extends NameGroup implements NameGroupInf {

	@Override
	public List<EvidencedString> getShortName() {
		return null;
	}

	@Override
	public void setShortName(List<EvidencedString> shortName) {
		throw new RuntimeException("Can Not Set Short Name for Submitted Name.");
	}
}
