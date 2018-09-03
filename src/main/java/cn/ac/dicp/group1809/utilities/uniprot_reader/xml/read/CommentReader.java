package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.read;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.URIAdapter;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.*;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.group.bpcCommentGroup.*;
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
class CommentReader {
	private static Logger logger = LoggerFactory.getLogger(CommentReader.class);

	static Comment read(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		Comment comment = new Comment();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "type":
					Comment.Type t = Comment.Type.forType(attributeValue);
					comment.setType(t);
					break;
				case "locationType":
					comment.setLocationType(attributeValue);
					break;
				case "name":
					comment.setName(attributeValue);
					break;
				case "mass":
					comment.setMass(Float.valueOf(attributeValue));
					break;
				case "error":
					comment.setError(attributeValue);
					break;
				case "method":
					comment.setMethod(attributeValue);
					break;
				case "evidence":
					List<Integer> unmarshal = new IntListAdapter().unmarshal(attributeValue);
					comment.setEvidence(unmarshal);
					break;
				default:
					logger.error("Failed to recognize the attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
			}
			i++;
		}

		BpcComment bpcComment = new BpcComment();
		List<Cofactor> cofactor = new ArrayList<>();
		List<SubcellularLocation> subcellularLocation = new ArrayList<>();
		List<Link> link = new ArrayList<>();
		List<Event> event = new ArrayList<>();
		List<Isoform> isoform = new ArrayList<>();
		List<Interactant> interactant = new ArrayList<>();
		List<Location> location = new ArrayList<>();
		List<EvidencedString> text = new ArrayList<>();

		String localName;
		loop:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					switch (localName) {
						case "molecule":
							Molecule molecule = MoleculeReader.read(reader);
							comment.setMolecule(molecule);
							comment.setBpcComment(bpcComment);
							break;
						case "absorption":
							Absorption absorption = readAbsorption(reader);
							bpcComment.setAbsorption(absorption);
							comment.setBpcComment(bpcComment);
							break;
						case "kinetics":
							Kinetics kinetics = readKinetics(reader);
							bpcComment.setKinetics(kinetics);
							comment.setBpcComment(bpcComment);
							break;
						case "phDependence":
							PhDependence phDependence = new PhDependence();
							readText(reader, phDependence);
							bpcComment.setPhDependence(phDependence);
							comment.setBpcComment(bpcComment);
							break;
						case "redoxPotential":
							RedoxPotential redoxPotential = new RedoxPotential();
							readText(reader, redoxPotential);
							bpcComment.setRedoxPotential(redoxPotential);
							comment.setBpcComment(bpcComment);
							break;
						case "temperatureDependence":
							TemperatureDependence temperatureDependence = new TemperatureDependence();
							readText(reader, temperatureDependence);
							bpcComment.setTemperatureDependence(temperatureDependence);
							comment.setBpcComment(bpcComment);
							break;
						case "cofactor":
							cofactor.add(readCofactor(reader));
							comment.setCofactor(cofactor);
							break;
						case "subcellularLocation":
							subcellularLocation.add(readSubcellularLocation(reader));
							comment.setSubcellularLocation(subcellularLocation);
							break;
						case "conflict":
							Conflict conflict = readConflict(reader);
							comment.setConflict(conflict);
							break;
						case "link":
							link.add(readLink(reader));
							comment.setLink(link);
							break;
						case "event":
							event.add(readEvent(reader));
							if (event.size() < 5) {
								comment.setEvent(event);
								break;
							}
							logger.error("Event size greater than 4!");
							throw new IllegalArgumentException("Event size greater than 4!");
						case "isoform":
							isoform.add(readIsoform(reader));
							comment.setIsoform(isoform);
							break;
						case "interactant":
							interactant.add(readInteractant(reader));
							if (interactant.size() < 3) {
								comment.setInteractant(interactant);
								break;
							}
							logger.error("Interactant size greater than 2!");
							throw new IllegalArgumentException("Interactant size greater than 2!");
						case "organismsDiffer":
							comment.setOrganismsDiffer(reader.getElementText().equals("true"));
							break;
						case "experiments":
							comment.setExperiments(Integer.valueOf(reader.getElementText()));
							break;
						case "disease":
							Disease disease = readDisease(reader);
							comment.setDisease(disease);
							break;
						case "location":
							location.add(LocationReader.read(reader));
							comment.setLocation(location);
							break;
						case "text":
							text.add(EvidencedStringReader.read(reader));
							comment.setText(text);
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
		return comment;
	}

	private static Absorption readAbsorption(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		Absorption absorption = new Absorption();
		List<EvidencedString> text = new ArrayList<>();
		String localName;
		loop:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					switch (localName) {
						case "max":
							EvidencedString max = EvidencedStringReader.read(reader);
							absorption.setMax(max);
							break;
						case "text":
							text.add(EvidencedStringReader.read(reader));
							absorption.setText(text);
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
		return absorption;
	}

	private static Kinetics readKinetics(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		Kinetics kinetics = new Kinetics();
		List<EvidencedString> KM = new ArrayList<>();
		List<EvidencedString> Vmax = new ArrayList<>();
		List<EvidencedString> text = new ArrayList<>();
		String localName;
		loop:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					switch (localName) {
						case "KM":
							KM.add(EvidencedStringReader.read(reader));
							kinetics.setKM(KM);
							break;
						case "Vmax":
							Vmax.add(EvidencedStringReader.read(reader));
							kinetics.setVmax(Vmax);
							break;
						case "text":
							text.add(EvidencedStringReader.read(reader));
							kinetics.setText(text);
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
		return kinetics;
	}

	private static void readText(XMLStreamReader reader, Text text) throws XMLStreamException {
		String name = reader.getLocalName();
		List<EvidencedString> texts = new ArrayList<>();
		String localName;
		loop:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					switch (localName) {
						case "text":
							texts.add(EvidencedStringReader.read(reader));
							text.setText(texts);
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
	}

	private static Cofactor readCofactor(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		Cofactor cofactor = new Cofactor();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "evidence":
					List<Integer> unmarshal = new IntListAdapter().unmarshal(attributeValue);
					cofactor.setEvidence(unmarshal);
					break;
				default:
					logger.error("Failed to recognize the attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
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
						case "name":
							cofactor.setName(reader.getElementText());
							break;
						case "dbReference":
							DBReference dbReference = DBReferenceReader.read(reader);
							cofactor.setDbReference(dbReference);
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
		return cofactor;
	}

	private static SubcellularLocation readSubcellularLocation(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		SubcellularLocation subcellularLocation = new SubcellularLocation();
		List<EvidencedString> location = new ArrayList<>();
		List<EvidencedString> topology = new ArrayList<>();
		List<EvidencedString> orientation = new ArrayList<>();
		String localName;
		loop:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					switch (localName) {
						case "location":
							location.add(EvidencedStringReader.read(reader));
							subcellularLocation.setLocation(location);
							break;
						case "topology":
							topology.add(EvidencedStringReader.read(reader));
							subcellularLocation.setTopology(topology);
							break;
						case "orientation":
							orientation.add(EvidencedStringReader.read(reader));
							subcellularLocation.setOrientation(orientation);
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
		return subcellularLocation;
	}

	private static Conflict readConflict(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		Conflict conflict = new Conflict();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "type":
					Conflict.Type type = Conflict.Type.forType(attributeValue);
					conflict.setType(type);
					break;
				case "ref":
					conflict.setRef(attributeValue);
					break;
				default:
					logger.error("Failed to recognize the attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
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
						case "sequence":
							Conflict.Sequence sequence = readConflictSequence(reader);
							conflict.setSequence(sequence);
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
		return conflict;
	}

	private static Conflict.Sequence readConflictSequence(XMLStreamReader reader) {
		Conflict.Sequence sequence = new Conflict.Sequence();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "resource":
					Conflict.Resource resource = Conflict.Resource.forResource(attributeValue);
					sequence.setResource(resource);
					break;
				case "id":
					sequence.setId(attributeValue);
					break;
				case "version":
					sequence.setVersion(Integer.valueOf(attributeValue));
					break;
				default:
					logger.error("Failed to recognize the attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
			}
			i++;
		}
		return sequence;
	}

	private static Link readLink(XMLStreamReader reader) {
		Link link = new Link();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "uri":
					link.setUri(new URIAdapter().unmarshal(attributeValue));
					break;
				default:
					logger.error("Failed to recognize the attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
			}
			i++;
		}
		return link;
	}

	private static Event readEvent(XMLStreamReader reader) {
		Event event = new Event();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "type":
					Event.Type t = Event.Type.forType(attributeValue);
					event.setType(t);
					break;
				default:
					logger.error("Failed to recognize the attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
			}
			i++;
		}
		return event;
	}

	private static Isoform readIsoform(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		Isoform isoform = new Isoform();
		List<String> id = new ArrayList<>();
		List<Isoform.Name> isoformName = new ArrayList<>();
		List<EvidencedString> text = new ArrayList<>();
		String localName;
		loop:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					switch (localName) {
						case "id":
							String text1 = reader.getElementText();
							id.add(text1);
							isoform.setId(id);
							break;
						case "name":
							isoformName.add(readName(reader));
							isoform.setName(isoformName);
							break;
						case "sequence":
							Isoform.Sequence sequence = readIsoformSequence(reader);
							isoform.setSequence(sequence);
							break;
						case "text":
							text.add(EvidencedStringReader.read(reader));
							isoform.setText(text);
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
		return isoform;
	}

	private static Isoform.Sequence readIsoformSequence(XMLStreamReader reader) {
		Isoform.Sequence sequence = new Isoform.Sequence();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "type":
					Isoform.Type t = Isoform.Type.forType(attributeValue);
					sequence.setType(t);
					break;
				case "ref":
					sequence.setRef(attributeValue);
					break;
				default:
					logger.error("Failed to recognize the attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
			}
			i++;
		}
		return sequence;
	}

	private static Isoform.Name readName(XMLStreamReader reader) throws XMLStreamException {
		Isoform.Name name = new Isoform.Name();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "evidence":
					List<Integer> unmarshal = new IntListAdapter().unmarshal(attributeValue);
					name.setEvidence(unmarshal);
					break;
				default:
					logger.error("Failed to recognize the attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
			}
			i++;
		}
		name.setValue(reader.getElementText());
		return name;
	}

	private static Interactant readInteractant(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		Interactant interactant = new Interactant();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "intactId":
					interactant.setIntactId(attributeValue);
					break;
				default:
					logger.error("Failed to recognize the attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
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
						case "id":
							String id = reader.getElementText();
							interactant.setId(id);
							break;
						case "label":
							String label = reader.getElementText();
							interactant.setLabel(label);
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
		return interactant;
	}

	private static Disease readDisease(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		Disease disease = new Disease();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			switch (attributeLocalName) {
				case "id":
					String attributeValue = reader.getAttributeValue(i);
					disease.setId(attributeValue);
					break;
				default:
					logger.error("Failed to recognize the attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName);
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
						case "name":
							String diseaseName = reader.getElementText();
							disease.setName(diseaseName);
							break;
						case "acronym":
							String acronym = reader.getElementText();
							disease.setAcronym(acronym);
							break;
						case "description":
							String description = reader.getElementText();
							disease.setAcronym(description);
							break;
						case "dbReference":
							DBReference dbReference = DBReferenceReader.read(reader);
							disease.setDbReference(dbReference);
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
		return disease;
	}

}
