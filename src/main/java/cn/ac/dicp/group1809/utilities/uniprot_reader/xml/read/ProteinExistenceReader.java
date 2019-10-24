package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.read;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.ProteinExistence;

import javax.xml.stream.XMLStreamReader;

/**
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
class ProteinExistenceReader {
	static ProteinExistence read(XMLStreamReader reader) {
		ProteinExistence proteinExistence = new ProteinExistence();
		int attributeCount = reader.getAttributeCount();
		int i = 0;
		while (i < attributeCount) {
			String attributeLocalName = reader.getAttributeLocalName(i);
			String attributeValue = reader.getAttributeValue(i);
			if ("type".equals(attributeLocalName)) {
				ProteinExistence.Type t = ProteinExistence.Type.forType(attributeValue);
				proteinExistence.setType(t);
			} else {
				throw new IllegalArgumentException("Invalid Attribute Local Name: " + attributeLocalName);
			}
			i++;
		}
		return proteinExistence;
	}
}
