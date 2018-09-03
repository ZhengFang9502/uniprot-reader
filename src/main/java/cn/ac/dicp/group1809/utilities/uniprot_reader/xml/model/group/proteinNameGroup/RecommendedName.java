package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.group.proteinNameGroup;

import javax.xml.bind.annotation.XmlType;

/**
 * @author ZhengFang 2018/5/26
 * @since V1.0
 */
@XmlType(name = "recommendedName", propOrder = {
		"fullName",
		"shortName",
		"ecNumber",
})
public class RecommendedName extends NameGroup implements NameGroupInf {

}
