package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.read;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.Gene;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.GeneName;
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
class GeneReader {
	private static Logger logger = LoggerFactory.getLogger(GeneReader.class);

	static Gene read(XMLStreamReader reader) throws XMLStreamException {
		String name = reader.getLocalName();
		Gene gene = new Gene();
		List<GeneName> geneName = new ArrayList<>();
		String localName;
		loop:
		while (reader.hasNext()) {
			int next = reader.next();
			switch (next) {
				case XMLStreamReader.START_ELEMENT:
					localName = reader.getLocalName();
					switch (localName) {
						case "name":
							geneName.add(readGeneName(reader));
							gene.setName(geneName);
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
		return gene;
	}


	private static GeneName readGeneName(XMLStreamReader reader) throws XMLStreamException {
		GeneName geneName = new GeneName();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "evidence":
					List<Integer> unmarshal = new IntListAdapter().unmarshal(attributeValue);
					geneName.setEvidence(unmarshal);
					break;
				case "type":
					GeneName.Type geneNameType = GeneName.Type.forType(attributeValue);
					geneName.setType(geneNameType);
					break;
				default:
					logger.error("Failed to recognize the element local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid element local name: " + attributeLocalName);
			}
			i++;
		}
		geneName.setValue(reader.getElementText());
		return geneName;
	}
}
