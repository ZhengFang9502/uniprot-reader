package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.sourceData;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Describes the source of the sequence according to the citation.
 * Equivalent to the flat file RC-line.
 *
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
public class SourceData {
	@XmlElement
	private List<SourceDataInf> sourceData;

	public List<SourceDataInf> getSourceData() {
		return sourceData;
	}

	public void setSourceData(List<SourceDataInf> sourceData) {
		this.sourceData = sourceData;
	}

	public static class Plasmid extends SourceDataType implements SourceDataInf {
	}

	public static class Strain extends SourceDataType implements SourceDataInf {
	}

	public static class Transposon extends SourceDataType implements SourceDataInf {
	}

	public static class Tissue extends SourceDataType implements SourceDataInf {
	}
}
