package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.sourceData;

import java.util.List;

/**
 * @author ZhengFang 2018/5/29
 * @since V1.0
 */
public interface SourceDataInf {
		String getValue();

		void setValue(String value);

		List<Integer> getEvidence();

		void setEvidence(List<Integer> evidence);
}
