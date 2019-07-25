package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.read;

import cn.ac.dicp.group1809.utilities.proteomics_framework.model.definition.proteomics.Protein;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
public class UniProtXmlReader {
	private Logger logger = LoggerFactory.getLogger(UniProtXmlReader.class);

	public UniProtXmlReader() {
	}

	public Uniprot read(String path) {
		if (!path.endsWith("xml")) {
			logger.error("Failed to read UniProt xml file, cause invalid file format: " + path);
			throw new IllegalArgumentException("Invalid file format: " + path);
		}
		logger.info("Starting reading the uniprot database: " + path);
		Uniprot uniprot = new Uniprot();
		List<Entry> entry = new ArrayList<>();
		XMLInputFactory factory = XMLInputFactory.newInstance();
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(path));
		} catch (FileNotFoundException e) {
			logger.error("File Not Found!");
			logger.error(e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
		}
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		XMLStreamReader reader;
		try {
			reader = factory.createXMLStreamReader(inputStreamReader);
		} catch (XMLStreamException e) {
			logger.error(e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
		}
		try {
			String localName;
			l1:
			while (reader.hasNext()) {
				int next = reader.next();
				switch (next) {
					case XMLStreamReader.START_ELEMENT:
						localName = reader.getLocalName();
						switch (localName) {
							case "entry":
								Entry read = EntryReader.read(reader);
								entry.add(read);
								uniprot.setEntry(entry);
								break;
							case "copyright":
								String elementText = reader.getElementText();
								uniprot.setCopyright(elementText);
								break;
						}
						break;
					case XMLStreamConstants.END_ELEMENT:
						localName = reader.getLocalName();
						if ("uniprot".equals(localName)) {
							break l1;
						}
				}
			}
		} catch (XMLStreamException e) {
			logger.error(e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
		} finally {
			try {
				reader.close();
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
		}
		return uniprot;
	}

	public HashMap<String, Protein> getProteinMap(Uniprot uniprot) {
		HashMap<String, Protein> proteinMap = new HashMap<>();
		for (Entry entry : uniprot.getEntry()) {
			String accession = entry.getAccession().get(0);
			String dataset = entry.getDataset().getAbbreviation();
			String name = entry.getName().get(0);
			String recommendedFullName = entry.getProtein().getProteinNameGroup().getRecommendedName().getFullName().getValue();
			String primaryGeneName = "";
			List<Gene> gene = entry.getGene();
			if (gene != null) {
				for (GeneName geneName : gene.get(0).getName()) {
					if (geneName.getType().equals(GeneName.Type.Primary)) {
						primaryGeneName = geneName.getValue();
					}
				}
			}
			Organism organism = entry.getOrganism();
			String scientificName = "";
			if (organism.getName() != null) {
				for (OrganismName organismName : organism.getName()) {
					if (organismName.getType().equals(OrganismName.Type.Scientific)) {
						scientificName = organismName.getValue();
					}
				}
			}
			String taxonomy = "";
			if (organism.getDbReference() != null) {
				for (DBReference dbReference : organism.getDbReference()) {
					if (dbReference.getType().contains("Taxonomy")) {
						taxonomy = dbReference.getId();
					}
				}
			}
			int number = 0;
			ProteinExistence proteinExistence = entry.getProteinExistence();
			if (proteinExistence != null) {
				number = proteinExistence.getType().getNumber();
			}
			Sequence sequence = entry.getSequence();
			int sequenceVersion = sequence.getVersion();
			String sequenceValue = sequence.getValue();
			Protein protein = new Protein();
			protein.setDatabase(dataset);
			protein.setAccession(accession);
			protein.setEntryName(name);
			protein.setProteinName(recommendedFullName);
			protein.setGeneName(primaryGeneName);
			protein.setOrganismName(scientificName);
			if (!taxonomy.equals("")) {
				protein.setOrganismIdentifier(taxonomy);
			}
			if (number != 0) {
				protein.setProteinExistence(number);
			}
			if (sequenceVersion != 0) {
				protein.setSequenceVersion(sequenceVersion);
			}
			protein.setSequence(sequenceValue);
			proteinMap.put(accession, protein);
		}
		return proteinMap;
	}
}
