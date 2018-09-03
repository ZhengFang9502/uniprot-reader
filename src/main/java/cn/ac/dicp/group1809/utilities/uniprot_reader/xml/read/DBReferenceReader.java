package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.read;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.DBReference;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
class DBReferenceReader {
	private static Logger logger = LoggerFactory.getLogger(DBReferenceReader.class);

	static DBReference read(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		DBReference dbReference = new DBReference();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "type":
					dbReference.setType(attributeValue);
					break;
				case "id":
					dbReference.setId(attributeValue);
					break;
				case "evidence":
					List<Integer> unmarshal = new IntListAdapter().unmarshal(attributeValue);
					dbReference.setEvidence(unmarshal);
					break;
				default:
					logger.error("Failed to recognize the attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
			}
			i++;
		}

		String localName;
		List<Property> property = new ArrayList<>();
		loop:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					switch (localName) {
						case "molecule":
							dbReference.setMolecule(MoleculeReader.read(reader));
							break;
						case "property":
							property.add(readProperty(reader));
							dbReference.setProperty(property);
							break;
						default:
							logger.error("Failed to recognize the element local name: " + localName);
							throw new IllegalArgumentException("Invalid element local name: " + localName);
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					localName = reader.getLocalName();
					if (name.equals(localName)) {
						break loop;
					}
			}
		}
		return dbReference;
	}


	private static Property readProperty(XMLStreamReader reader) {
		Property property = new Property();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "type":
					property.setType(attributeValue);
					break;
				case "value":
					property.setValue(attributeValue);
					break;
				default:
					logger.error("Failed to recognize the attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
			}
			i++;
		}
		return property;
	}
}
