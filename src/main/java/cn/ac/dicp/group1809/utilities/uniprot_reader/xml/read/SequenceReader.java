package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.read;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.DateAdapter;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.Sequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
class SequenceReader {
	private static Logger logger = LoggerFactory.getLogger(SequenceReader.class);
	private static DateAdapter dateAdapter = new DateAdapter();

	static Sequence read(XMLStreamReader reader) throws XMLStreamException {
		Sequence sequence = new Sequence();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			switch (attributeLocalName) {
				case "length":
					sequence.setLength(Integer.valueOf(attributeValue));
					break;
				case "mass":
					sequence.setMass(Integer.valueOf(attributeValue));
					break;
				case "checksum":
					sequence.setChechsum(attributeValue);
					break;
				case "modified":
					String modifiedDate = dateAdapter.unmarshal(attributeValue);
					sequence.setModified(modifiedDate);
					break;
				case "version":
					sequence.setVersion(Integer.valueOf(attributeValue));
					break;
				case "precursor":
					sequence.setPrecursor(attributeValue.equals("true"));
					break;
				case "fragment":
					Sequence.Fragment fragment = Sequence.Fragment.forFragment(attributeValue);
					sequence.setFragment(fragment);
					break;
				default:
					logger.error("Failed to recognize the attribute local name: " + attributeLocalName);
					throw new IllegalArgumentException("Invalid attribute local name: " + attributeLocalName + "\t" + attributeValue);
			}
			i++;
		}
		String sequenceText = reader.getElementText();
		sequence.setValue(sequenceText.replaceAll("\\s", ""));
		return sequence;
	}
}
