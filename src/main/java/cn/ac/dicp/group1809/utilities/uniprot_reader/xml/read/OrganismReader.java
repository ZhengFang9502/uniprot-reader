package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.read;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.DBReference;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.Organism;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.OrganismName;

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
	static Organism read(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		Organism organism = new Organism();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			if ("evidence".equals(attributeLocalName)) {
				List<Integer> unmarshal = new IntListAdapter().unmarshal(attributeValue);
				organism.setEvidence(unmarshal);
			} else {
				throw new IllegalArgumentException("Invalid Attribute Local Name: " + attributeLocalName);
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
							throw new IllegalArgumentException("Invalid Element Local Name: " + localName);
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
		return organism;
	}


	private static OrganismName readOrganismName(XMLStreamReader reader) throws XMLStreamException {
		OrganismName organismName = new OrganismName();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			if ("type".equals(attributeLocalName)) {
				OrganismName.Type organismNameType = OrganismName.Type.forType(attributeValue);
				organismName.setType(organismNameType);
			} else {
				throw new IllegalArgumentException("Invalid Attribute Local Name: " + attributeLocalName);
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
					if ("taxon".equals(localName)) {
						Organism.Taxon taxon = new Organism.Taxon();
						taxon.setTaxon(reader.getElementText());
						lineage.add(taxon);
					} else {
						throw new IllegalArgumentException("Invalid Element Local Name: " + localName);
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
		return lineage;
	}
}
