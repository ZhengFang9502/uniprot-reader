package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.read;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.Feature;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.Location;
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
class FeatureReader {
	private static Logger logger = LoggerFactory.getLogger(FeatureReader.class);

	static Feature read(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		Feature feature = new Feature();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "type":
					Feature.Type t = Feature.Type.forType(attributeValue);
					feature.setType(t);
					break;
				case "status":
					Feature.Status s = Feature.Status.forStatus(attributeValue);
					feature.setStatus(s);
					break;
				case "id":
					feature.setId(attributeValue);
					break;
				case "description":
					feature.setDescription(attributeValue);
					break;
				case "evidence":
					List<Integer> unmarshal = new IntListAdapter().unmarshal(attributeValue);
					feature.setEvidence(unmarshal);
					break;
				case "ref":
					feature.setRef(attributeValue);
					break;
			}
			i++;
		}

		List<String> variation = new ArrayList<>();

		String localName;
		l1:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					switch (localName) {
						case "original":
							String originalText = reader.getElementText();
							feature.setOriginal(originalText);
							break;
						case "variation":
							String variationText = reader.getElementText();
							variation.add(variationText);
							feature.setVariation(variation);
							break;
						case "location":
							Location location = LocationReader.read(reader);
							feature.setLocation(location);
							break;
						default:
							logger.error("Failed to read the feature element local name: " + localName);
							throw new IllegalArgumentException("Invalid feature element local name: " + localName);
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					localName = reader.getLocalName();
					if (name.equals(localName)) {
						break l1;
					}
			}
		}
		return feature;
	}
}
