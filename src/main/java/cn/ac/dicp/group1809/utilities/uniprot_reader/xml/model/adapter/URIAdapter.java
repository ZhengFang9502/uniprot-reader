package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.net.URI;

/**
 * @author ZhengFang 2018/9/2
 * @since V1.0
 */
public class URIAdapter extends XmlAdapter<String,URI> {

	@Override
	public URI unmarshal(String v) {
		return URI.create(v);
	}

	@Override
	public String marshal(URI v) {
		return v.toString();
	}
}
