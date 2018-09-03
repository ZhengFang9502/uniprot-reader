package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhengFang 2018/9/2
 * @since V1.0
 */
public class IntListAdapter extends XmlAdapter<String, List<Integer>> {

	@Override
	public List<Integer> unmarshal(String v) {
		String[] split = v.split(" ");
		List<Integer> intList = new ArrayList<>();
		for (String s : split) {
			intList.add(Integer.valueOf(s));
		}
		return intList;
	}

	@Override
	public String marshal(List<Integer> v) {
		StringBuilder stringBuilder = new StringBuilder();
		int size = v.size();
		int n = 0;
		for (Integer integer : v) {
			stringBuilder.append(integer);
			if (n < size - 1) {
				stringBuilder.append(" ");
			}
			n++;
		}
		return stringBuilder.toString();
	}
}
