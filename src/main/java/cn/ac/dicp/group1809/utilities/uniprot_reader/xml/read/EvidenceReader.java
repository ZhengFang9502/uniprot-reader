package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.read;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.DBReference;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.Evidence;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.ImportedFrom;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.Source;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
class EvidenceReader {
	static Evidence read(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		Evidence evidence = new Evidence();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "type":
					evidence.setType(attributeValue);
					break;
				case "key":
					evidence.setKey(Integer.valueOf(attributeValue));
					break;
				default:
					throw new IllegalArgumentException("Invalid Attribute Local Name: " + attributeLocalName);
			}
			i++;
		}

		String localName;
		loop:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					switch (localName) {
						case "source":
							Source source = readSource(reader);
							evidence.setSource(source);
							break;
						case "importedFrom":
							ImportedFrom importedFrom = readImportedFrom(reader);
							evidence.setImportedFrom(importedFrom);
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
		return evidence;
	}

	private static Source readSource(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		Source source = new Source();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			if ("ref".equals(attributeLocalName)) {
				source.setRef(Integer.valueOf(attributeValue));
			} else {
				throw new IllegalArgumentException("Invalid Attribute Local Name: " + attributeLocalName);
			}
			i++;
		}
		String localName;
		loop:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					if ("dbReference".equals(localName)) {
						DBReference dbReference = DBReferenceReader.read(reader);
						source.setDbReference(dbReference);
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
		return source;
	}

	private static ImportedFrom readImportedFrom(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		ImportedFrom importedFrom = new ImportedFrom();
		String localName;
		loop:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					if ("dbReference".equals(localName)) {
						DBReference dbReference = DBReferenceReader.read(reader);
						importedFrom.setDbReference(dbReference);
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
		return importedFrom;
	}
}
