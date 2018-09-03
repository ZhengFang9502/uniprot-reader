package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

/**
 * @author ZhengFang 2018/5/26
 * @since V1.0
 */
public abstract class ValueItem {
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
