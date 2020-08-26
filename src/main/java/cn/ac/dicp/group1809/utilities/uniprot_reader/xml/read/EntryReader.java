package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.read;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.DateAdapter;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.*;
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
class EntryReader {
	private static final Logger LOGGER = LoggerFactory.getLogger(EntryReader.class);
	private static DateAdapter dateAdapter = new DateAdapter();

	static Entry read(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		Entry entry = new Entry();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "dataset":
					Entry.Dataset dataset = Entry.Dataset.forDataset(attributeValue);
					entry.setDataset(dataset);
					break;
				case "created":
					String createdDate = dateAdapter.unmarshal(attributeValue);
					entry.setCreate(createdDate);
					break;
				case "modified":
					String modifiedDate = dateAdapter.unmarshal(attributeValue);
					entry.setModified(modifiedDate);
					break;
				case "version":
					entry.setVersion(Integer.valueOf(attributeValue));
					break;
				default:
					throw new IllegalArgumentException("Invalid Attribute Local Name: " + attributeLocalName);
			}
			i++;
		}
		List<String> accession = new ArrayList<>();
		List<String> names = new ArrayList<>();
		List<Gene> gene = new ArrayList<>();
		List<GeneLocation> geneLocation = new ArrayList<>();
		List<Reference> reference = new ArrayList<>();
		List<Comment> comment = new ArrayList<>();
		List<DBReference> dbReference = new ArrayList<>();
		List<Keyword> keyword = new ArrayList<>();
		List<Feature> feature = new ArrayList<>();
		List<Evidence> evidence = new ArrayList<>();
		String localName;
		l1:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					switch (localName) {
						case "accession":
							String text = reader.getElementText();
							if (accession.size() == 0) {
								LOGGER.debug("Reading Accession: {}", text);
							}
							accession.add(text);
							entry.setAccession(accession);
							break;
						case "name":
							String nameText = reader.getElementText();
							names.add(nameText);
							entry.setName(names);
							break;
						case "protein":
							Protein protein = ProteinReader.read(reader);
							entry.setProtein(protein);
							break;
						case "gene":
							Gene geneTemp = GeneReader.read(reader);
							gene.add(geneTemp);
							entry.setGene(gene);
							break;
						case "organism":
							Organism organism = OrganismReader.read(reader);
							entry.setOrganism(organism);
							break;
						case "organismHost":
							Organism org = OrganismReader.read(reader);
							List<Organism> organismHost = entry.getOrganismHost();
							organismHost.add(org);
							break;
						case "geneLocation":
							GeneLocation geneLocation1 = GeneLocationReader.read(reader);
							geneLocation.add(geneLocation1);
							entry.setGeneLocation(geneLocation);
							break;
						case "reference":
							Reference reference1 = ReferenceReader.read(reader);
							reference.add(reference1);
							entry.setReference(reference);
							break;
						case "comment":
							Comment comment1 = CommentReader.read(reader);
							comment.add(comment1);
							entry.setComment(comment);
							break;
						case "dbReference":
							DBReference dbReference1 = DBReferenceReader.read(reader);
							dbReference.add(dbReference1);
							entry.setDbReference(dbReference);
							break;
						case "proteinExistence":
							ProteinExistence proteinExistence = ProteinExistenceReader.read(reader);
							entry.setProteinExistence(proteinExistence);
							break;
						case "keyword":
							Keyword keyword1 = KeywordReader.read(reader);
							keyword.add(keyword1);
							entry.setKeyword(keyword);
							break;
						case "feature":
							Feature feature1 = FeatureReader.read(reader);
							feature.add(feature1);
							entry.setFeature(feature);
							break;
						case "evidence":
							Evidence evidence1 = EvidenceReader.read(reader);
							evidence.add(evidence1);
							entry.setEvidence(evidence);
							break;
						case "sequence":
							Sequence sequence = SequenceReader.read(reader);
							entry.setSequence(sequence);
							break;
						default:
							throw new IllegalArgumentException("Invalid Element Local Name: " + localName);
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
		return entry;
	}
}
