package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author ZhengFang 2018/9/1
 * @since V1.0
 */
public class DateAdapter extends XmlAdapter<String, String> {
	@Override
	public String unmarshal(String v) {
		return v;
	}

	@Override
	public String marshal(String v) {
		return v;
	}
}
