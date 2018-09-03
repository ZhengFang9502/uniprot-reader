package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.read;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.EvidencedString;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.Protein;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.group.proteinNameGroup.*;
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
class ProteinReader {
	private static Logger logger = LoggerFactory.getLogger(ProteinReader.class);

	static Protein read(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		Protein protein = new Protein();
		ProteinNameGroup proteinName = readProteinNameGroup(reader);
		protein.setProteinNameGroup(proteinName);
		String localName = reader.getLocalName();
		if (name.equals(localName)) {
			return protein;
		}
		List<ProteinNameGroup> domain = new ArrayList<>();
		List<ProteinNameGroup> component = new ArrayList<>();
		loop:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					switch (localName) {
						case "domain":
							domain.add(readProteinNameGroup(reader));
							protein.setDomain(domain);
							break;
						case "component":
							component.add(readProteinNameGroup(reader));
							protein.setComponent(component);
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
		return protein;
	}


	private static ProteinNameGroup readProteinNameGroup(XMLStreamReader reader) throws XMLStreamException {
		Protein protein = new Protein();
		List<AlternativeName> alternativeName = new ArrayList<>();
		List<SubmittedName> submittedName = new ArrayList<>();
		List<EvidencedString> cdAntigenName = new ArrayList<>();
		List<EvidencedString> innName = new ArrayList<>();
		List<ProteinNameGroup> domain = new ArrayList<>();
		List<ProteinNameGroup> component = new ArrayList<>();

		ProteinNameGroup proteinName = new ProteinNameGroup();

		loop:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					String localName = reader.getLocalName();
					switch (localName) {
						case "recommendedName":
							proteinName.setRecommendedName((RecommendedName) readName(reader, new RecommendedName()));
							protein.setProteinNameGroup(proteinName);
							break;
						case "alternativeName":
							AlternativeName alternativeName2 = (AlternativeName) readName(reader, new AlternativeName());
							alternativeName.add(alternativeName2);
							proteinName.setAlternativeName(alternativeName);
							protein.setProteinNameGroup(proteinName);
							break;
						case "submittedName":
							SubmittedName submittedName1 = (SubmittedName) readName(reader, new SubmittedName());
							submittedName.add(submittedName1);
							proteinName.setSubmittedName(submittedName);
							protein.setProteinNameGroup(proteinName);
							break;
						case "allergenName":
							EvidencedString allergenName = EvidencedStringReader.read(reader);
							proteinName.setAllergenName(allergenName);
							protein.setProteinNameGroup(proteinName);
							break;
						case "biotechName":
							EvidencedString biotechName = EvidencedStringReader.read(reader);
							proteinName.setBiotechName(biotechName);
							protein.setProteinNameGroup(proteinName);
							break;
						case "cdAntigenName":
							EvidencedString cdAntigenName1 = EvidencedStringReader.read(reader);
							cdAntigenName.add(cdAntigenName1);
							proteinName.setCdAntigenName(cdAntigenName);
							protein.setProteinNameGroup(proteinName);
							break;
						case "innName":
							EvidencedString innName1 = EvidencedStringReader.read(reader);
							innName.add(innName1);
							proteinName.setInnName(innName);
							protein.setProteinNameGroup(proteinName);
							break;
						case "domain":
							domain.add(groupReader(reader));
							protein.setDomain(domain);
							break;
						case "component":
							component.add(groupReader(reader));
							protein.setComponent(component);
							break;
						default:
							logger.error("Failed to recognize the element local name: " + localName);
							throw new IllegalArgumentException("Invalid element local name: " + localName);
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					String endName = reader.getLocalName();
					if ("protein".equals(endName)) {
						break loop;
					}
			}
		}
		return proteinName;
	}

	private static ProteinNameGroup groupReader(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		List<AlternativeName> alternativeName = new ArrayList<>();
		List<SubmittedName> submittedName = new ArrayList<>();
		List<EvidencedString> cdAntigenName = new ArrayList<>();
		List<EvidencedString> innName = new ArrayList<>();

		ProteinNameGroup proteinName = new ProteinNameGroup();

		String localName;
		loop:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					switch (localName) {
						case "recommendedName":
							proteinName.setRecommendedName((RecommendedName) readName(reader, new RecommendedName()));
							break;
						case "alternativeName":
							AlternativeName alternativeName2 = (AlternativeName) readName(reader, new AlternativeName());
							alternativeName.add(alternativeName2);
							proteinName.setAlternativeName(alternativeName);
							break;
						case "submittedName":
							SubmittedName submittedName1 = (SubmittedName) readName(reader, new SubmittedName());
							submittedName.add(submittedName1);
							proteinName.setSubmittedName(submittedName);
							break;
						case "allergenName":
							EvidencedString allergenName = EvidencedStringReader.read(reader);
							proteinName.setAllergenName(allergenName);
							break;
						case "biotechName":
							EvidencedString biotechName = EvidencedStringReader.read(reader);
							proteinName.setBiotechName(biotechName);
							break;
						case "cdAntigenName":
							EvidencedString cdAntigenName1 = EvidencedStringReader.read(reader);
							cdAntigenName.add(cdAntigenName1);
							proteinName.setCdAntigenName(cdAntigenName);
							break;
						case "innName":
							EvidencedString innName1 = EvidencedStringReader.read(reader);
							innName.add(innName1);
							proteinName.setInnName(innName);
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
		return proteinName;
	}


	private static NameGroupInf readName(XMLStreamReader reader, NameGroupInf nameGroupInf) throws XMLStreamException {
		String name = reader.getLocalName();
		List<EvidencedString> shortName = new ArrayList<>();
		List<EvidencedString> ecNumber = new ArrayList<>();
		String localName;
		loop:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					switch (localName) {
						case "fullName":
							EvidencedString fullName = EvidencedStringReader.read(reader);
							nameGroupInf.setFullName(fullName);
							break;
						case "shortName":
							shortName.add(EvidencedStringReader.read(reader));
							nameGroupInf.setShortName(shortName);
							break;
						case "ecNumber":
							ecNumber.add(EvidencedStringReader.read(reader));
							nameGroupInf.setEcNumber(ecNumber);
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
		return nameGroupInf;
	}
}
