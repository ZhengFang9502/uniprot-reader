package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.read;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.Keyword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.List;

/**
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
class KeywordReader {
	private static Logger logger = LoggerFactory.getLogger(KeywordReader.class);

	static Keyword read(XMLStreamReader reader) throws XMLStreamException {
		Keyword keyword = new Keyword();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "evidence":
					List<Integer> unmarshal = new IntListAdapter().unmarshal(attributeValue);
					keyword.setEvidence(unmarshal);
					break;
				case "id":
					keyword.setId(attributeValue);
					break;
				default:
					logger.error("Failed to recognize the attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
			}
			i++;
		}
		String elementText = reader.getElementText();
		keyword.setValue(elementText);
		return keyword;
	}
}
