package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.group.proteinNameGroup;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.EvidencedString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @author ZhengFang 2018/5/26
 * @since V1.0
 */
@XmlType(name = "alternativeName", propOrder = {
		"fullName",
		"shortName",
		"ecNumber",
})
public class AlternativeName extends NameGroup implements NameGroupInf{
}
