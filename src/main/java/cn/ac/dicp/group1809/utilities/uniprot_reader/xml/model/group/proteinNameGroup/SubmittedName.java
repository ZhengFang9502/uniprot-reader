package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.group.proteinNameGroup;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.EvidencedString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private Logger logger = LoggerFactory.getLogger(SubmittedName.class);

	@Override
	public List<EvidencedString> getShortName() {
		return null;
	}

	@Override
	public void setShortName(List<EvidencedString> shortName) {
		logger.error("Can not set short name for submitted name.");
		throw new IllegalArgumentException("Can not set short name for submitted name.");
	}
}
