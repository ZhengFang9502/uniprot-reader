package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.read;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.DateAdapter;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.*;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.sourceData.SourceData;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.sourceData.SourceDataInf;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.group.sptrCitationGroup.SptrCitation;
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
class ReferenceReader {
	private static Logger logger = LoggerFactory.getLogger(ReferenceReader.class);
	private static DateAdapter dateAdapter = new DateAdapter();

	static Reference read(XMLStreamReader reader) throws XMLStreamException {
		Reference reference = new Reference();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "evidence":
					List<Integer> evidence = new ArrayList<>();
					String[] split = attributeValue.split(" ");
					for (String s : split) {
						evidence.add(Integer.valueOf(s));
					}
					reference.setEvidence(evidence);
					break;
				case "key":
					reference.setKey(attributeValue);
					break;
				default:
					logger.error("Failed to recognize the attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
			}
			i++;
		}

		SptrCitation sptrCitation = new SptrCitation();
		List<String> scope = new ArrayList<>();
		loop:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					String localName = reader.getLocalName();
					switch (localName) {
						case "citation":
							Citation citation = readCitation(reader);
							reference.setCitation(citation);
							break;
						case "scope":
							scope.add(reader.getElementText());
							sptrCitation.setScope(scope);
							break;
						case "source":
							SourceData sourceData = readSourceData(reader);
							sptrCitation.setSource(sourceData);
							break;
						default:
							logger.error("Failed to recognize the element local name: " + localName);
							throw new IllegalArgumentException("Invalid element local name: " + localName);
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					String endName = reader.getLocalName();
					if ("reference".equals(endName)) {
						break loop;
					}
			}
		}
		reference.setSptrCitation(sptrCitation);
		return reference;
	}


	private static Citation readCitation(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		Citation citation = new Citation();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "type":
					Citation.Type t = Citation.Type.forType(attributeValue);
					citation.setType(t);
					break;
				case "date":
					String date = dateAdapter.unmarshal(attributeValue);
					citation.setDate(date);
					break;
				case "name":
					citation.setName(attributeValue);
					break;
				case "volume":
					citation.setVolume(attributeValue);
					break;
				case "first":
					citation.setFirst(attributeValue);
					break;
				case "last":
					citation.setLast(attributeValue);
					break;
				case "publisher":
					citation.setPublisher(attributeValue);
					break;
				case "city":
					citation.setCity(attributeValue);
					break;
				case "db":
					citation.setDb(attributeValue);
					break;
				case "number":
					citation.setNumber(attributeValue);
					break;
				case "institute":
					citation.setInstitute(attributeValue);
					break;
				case "country":
					citation.setCountry(attributeValue);
					break;
				default:
					logger.error("Failed to recognize the attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
			}
			i++;
		}

		List<DBReference> dbReference = new ArrayList<>();
		String localName;
		loop:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					switch (localName) {
						case "title":
							String title = reader.getElementText();
							citation.setTitle(title);
							break;
						case "editorList":
							NameList editorList = readNameList(reader);
							citation.setEditorList(editorList);
							break;
						case "authorList":
							NameList authorList = readNameList(reader);
							citation.setAuthorList(authorList);
							break;
						case "locator":
							String locator = reader.getElementText();
							citation.setTitle(locator);
							break;
						case "dbReference":
							dbReference.add(DBReferenceReader.read(reader));
							citation.setDbReference(dbReference);
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
		return citation;
	}


	private static NameList readNameList(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		NameList nameList = new NameList();
		List<Person> person = new ArrayList<>();
		List<Consortium> consortium = new ArrayList<>();
		String localName;
		loop:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					switch (localName) {
						case "person":
							Person aPerson = new Person();
							readName(reader, aPerson);
							person.add(aPerson);
							nameList.setPerson(person);
							break;
						case "consortium":
							Consortium aConsortium = new Consortium();
							readName(reader, aConsortium);
							consortium.add(aConsortium);
							nameList.setConsortium(consortium);
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
		return nameList;
	}

	private static void readName(XMLStreamReader reader, NameInf nameInf) {
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "name":
					nameInf.setName(attributeValue);
					break;
				default:
					logger.error("Failed to recognize the attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
			}
			i++;
		}
	}

	private static SourceData readSourceData(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		SourceData sourceData = new SourceData();
		List<SourceDataInf> sourceDataInf = new ArrayList<>();
		String localName;
		loop:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					switch (localName) {
						case "strain":
							SourceData.Strain strain = (SourceData.Strain) setSourceData(reader, new SourceData.Strain());
							sourceDataInf.add(strain);
							sourceData.setSourceData(sourceDataInf);
							break;
						case "plasmid":
							SourceData.Plasmid plasmid = (SourceData.Plasmid) setSourceData(reader, new SourceData.Plasmid());
							sourceDataInf.add(plasmid);
							sourceData.setSourceData(sourceDataInf);
							break;
						case "transposon":
							SourceData.Transposon transposon = (SourceData.Transposon) setSourceData(reader, new SourceData.Transposon());
							sourceDataInf.add(transposon);
							sourceData.setSourceData(sourceDataInf);
							break;
						case "tissue":
							SourceData.Tissue tissue = (SourceData.Tissue) setSourceData(reader, new SourceData.Tissue());
							sourceDataInf.add(tissue);
							sourceData.setSourceData(sourceDataInf);
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
		return sourceData;
	}


	private static SourceDataInf setSourceData(XMLStreamReader reader, SourceDataInf sourceDataInf) throws XMLStreamException {
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "evidence":
					List<Integer> unmarshal = new IntListAdapter().unmarshal(attributeValue);
					sourceDataInf.setEvidence(unmarshal);
					break;
				default:
					logger.error("Failed to recognize the attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
			}
			i++;
		}
		sourceDataInf.setValue(reader.getElementText());
		return sourceDataInf;
	}
}
