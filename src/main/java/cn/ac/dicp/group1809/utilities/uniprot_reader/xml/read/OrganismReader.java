package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.read;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.DBReference;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.Organism;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.OrganismName;
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
class OrganismReader {
	private static Logger logger = LoggerFactory.getLogger(OrganismReader.class);

	static Organism read(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		Organism organism = new Organism();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "evidence":
					List<Integer> unmarshal = new IntListAdapter().unmarshal(attributeValue);
					organism.setEvidence(unmarshal);
					break;
				default:
					logger.error("Failed to recognize the attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
			}
			i++;
		}

		List<OrganismName> organismName = new ArrayList<>();
		List<DBReference> dbReference = new ArrayList<>();
		String localName;
		loop:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					switch (localName) {
						case "name":
							organismName.add(readOrganismName(reader));
							organism.setName(organismName);
							break;
						case "dbReference":
							dbReference.add(DBReferenceReader.read(reader));
							organism.setDbReference(dbReference);
							break;
						case "lineage":
							List<Organism.Taxon> lineage = readLineage(reader);
							organism.setLineage(lineage);
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
		return organism;
	}


	private static OrganismName readOrganismName(XMLStreamReader reader) throws XMLStreamException {
		OrganismName organismName = new OrganismName();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "type":
					OrganismName.Type organismNameType = OrganismName.Type.forType(attributeValue);
					organismName.setType(organismNameType);
					break;
				default:
					logger.error("Failed to recognize the attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
			}
			i++;
		}
		organismName.setValue(reader.getElementText());
		return organismName;
	}


	private static List<Organism.Taxon> readLineage(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		List<Organism.Taxon> lineage = new ArrayList<>();
		String localName;
		loop:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					switch (localName) {
						case "taxon":
							Organism.Taxon taxon = new Organism.Taxon();
							taxon.setTaxon(reader.getElementText());
							lineage.add(taxon);
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
		return lineage;
	}
}
