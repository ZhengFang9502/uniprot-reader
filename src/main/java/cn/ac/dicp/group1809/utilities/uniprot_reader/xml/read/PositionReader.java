package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.read;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.Position;

import javax.xml.stream.XMLStreamReader;
import java.util.List;

/**
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
class PositionReader {
	static Position read(XMLStreamReader reader) {
		Position position = new Position();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "position":
					position.setPosition(Long.valueOf(attributeValue));
					break;
				case "status":
					Position.Status s = Position.Status.forStatus(attributeValue);
					position.setStatus(s);
					break;
				case "evidence":
					List<Integer> unmarshal = new IntListAdapter().unmarshal(attributeValue);
					position.setEvidence(unmarshal);
					break;
				default:
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
			}
			i++;
		}
		return position;
	}
}
