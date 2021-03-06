package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.read;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.EvidencedString;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhengFang 2018/5/29
 * @since V1.0
 */
class EvidencedStringReader {
	static EvidencedString read(XMLStreamReader reader) throws XMLStreamException {
		EvidencedString evidencedString = new EvidencedString();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "evidence":
					List<Integer> evidence = new ArrayList<>();
					String[] split = attributeValue.split(" ");
					for (String s : split) {
						evidence.add(Integer.valueOf(s));
					}
					evidencedString.setEvidence(evidence);
					break;
				case "status":
					EvidencedString.Status s = EvidencedString.Status.forStatus(attributeValue);
					evidencedString.setStatus(s);
					break;
				default:
					throw new IllegalArgumentException("Invalid Attribute Local Name: " + attributeLocalName);
			}
			i++;
		}
		evidencedString.setValue(reader.getElementText());
		return evidencedString;
	}
}
