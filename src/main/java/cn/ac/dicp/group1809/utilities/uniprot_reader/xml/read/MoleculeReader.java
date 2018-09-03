package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.read;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.Molecule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author ZhengFang 2018/5/29
 * @since V1.0
 */
class MoleculeReader {
	private static Logger logger = LoggerFactory.getLogger(MoleculeReader.class);

	static Molecule read(XMLStreamReader reader) throws XMLStreamException {
		Molecule molecule = new Molecule();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "id":
					molecule.setId(attributeValue);
					break;
				default:
					logger.error("Failed to recognize the attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
			}
			i++;
		}
		molecule.setValue(reader.getElementText());
		return molecule;
	}
}
