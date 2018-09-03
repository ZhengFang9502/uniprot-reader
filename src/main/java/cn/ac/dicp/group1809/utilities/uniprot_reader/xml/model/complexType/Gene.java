package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Describes a gene.
 * Equivalent to the flat file GN-line.
 *
 * <pre>
 *      &lt;complexType name="geneType">
 *          &lt;annotation>
 *              &lt;documentation>Describes a gene.
 *             Equivalent to the flat file GN-line. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;sequence>
 *              &lt;element name="name" type="geneNameType" maxOccurs="unbounded"/>
 *          &lt;/sequence>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/26
 * @since V1.0
 */
public class Gene {
	@XmlElement
	private List<GeneName> name;

	public List<GeneName> getName() {
		return name;
	}

	public void setName(List<GeneName> name) {
		this.name = name;
	}
}
