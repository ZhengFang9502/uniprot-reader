package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.read;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.GeneLocation;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.Status;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
class GeneLocationReader {
	static GeneLocation read(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		GeneLocation geneLocation = new GeneLocation();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "type":
					GeneLocation.Type t = GeneLocation.Type.forType(attributeValue);
					geneLocation.setType(t);
					break;
				case "evidence":
					List<Integer> unmarshal = new IntListAdapter().unmarshal(attributeValue);
					geneLocation.setEvidence(unmarshal);
					break;
				default:
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
			}
			i++;
		}
		List<Status> statuses = new ArrayList<>();
		String localName;
		loop:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					if ("name".equals(localName)) {
						statuses.add(readStatus(reader));
						geneLocation.setName(statuses);
					} else {
						throw new IllegalArgumentException("Invalid element local name: " + localName);
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					localName = reader.getLocalName();
					if (name.equals(localName)) {
						break loop;
					}
				default:
					break;
			}
		}
		return geneLocation;
	}

	private static Status readStatus(XMLStreamReader reader) throws XMLStreamException {
		Status status = new Status();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			if ("status".equals(attributeLocalName)) {
				Status.Type type = Status.Type.forType(attributeValue);
				status.setStatus(type);
			} else {
				throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
			}
			i++;
		}
		status.setValue(reader.getElementText());
		return status;
	}
}
