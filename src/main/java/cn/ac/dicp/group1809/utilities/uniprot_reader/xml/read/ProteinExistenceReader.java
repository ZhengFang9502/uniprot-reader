package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.read;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.ProteinExistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamReader;

/**
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
class ProteinExistenceReader {
	private static Logger logger = LoggerFactory.getLogger(ProteinExistenceReader.class);

	static ProteinExistence read(XMLStreamReader reader) {
		ProteinExistence proteinExistence = new ProteinExistence();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "type":
					ProteinExistence.Type t = ProteinExistence.Type.forType(attributeValue);
					proteinExistence.setType(t);
					break;
				default:
					logger.error("Failed to recognize the attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
			}
			i++;
		}
		return proteinExistence;
	}
}
