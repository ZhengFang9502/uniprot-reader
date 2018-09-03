package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.read;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.Location;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author ZhengFang 2018/9/1
 * @since V1.0
 */
class LocationReader {
	private static Logger logger = LoggerFactory.getLogger(LocationReader.class);

	static Location read(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		Location location = new Location();
		int attributeCount = reader.getAttributeCount();
		if (attributeCount > 0) {
			if (attributeCount == 1) {
				String attributeLocalName = reader.getAttributeLocalName(0);
				String attributeValue = reader.getAttributeValue(0);
				if ("sequence".equals(attributeLocalName)) {
					location.setSequence(attributeValue);
				} else {
					logger.error("Failed to get sequence information of location, cause invalid attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid local attribute name: " + attributeLocalName);
				}
			} else {
				logger.error("More than 1 attribute in location element!");
				throw new IllegalArgumentException("More than 1 attribute in location element!");
			}
		}
		String localName;
		l1:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					switch (localName) {
						case "begin":
							Position begin = PositionReader.read(reader);
							location.setBegin(begin);
							break;
						case "end":
							Position end = PositionReader.read(reader);
							location.setEnd(end);
							break;
						case "position":
							Position position = PositionReader.read(reader);
							location.setPosition(position);
							break;
						default:
							logger.error("Failed to read start element: " + localName);
							throw new IllegalArgumentException("Unable to read start element local name :" + localName);
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					localName = reader.getLocalName();
					if (name.equals(localName)) {
						break l1;
					}
			}
		}
		return location;
	}
}
