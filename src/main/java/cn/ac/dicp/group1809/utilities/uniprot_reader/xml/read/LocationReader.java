package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.read;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.Location;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.Position;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author ZhengFang 2018/9/1
 * @since V1.0
 */
class LocationReader {
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
					throw new IllegalArgumentException("Invalid Local Attribute Name: " + attributeLocalName);
				}
			} else {
				throw new IllegalArgumentException("More Than 1 Attribute in Location Element: " + name);
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
							throw new IllegalArgumentException("Unable to Read Start Element Local Name :" + localName);
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					localName = reader.getLocalName();
					if (name.equals(localName)) {
						break l1;
					}
				default:
					break;
			}
		}
		return location;
	}
}
