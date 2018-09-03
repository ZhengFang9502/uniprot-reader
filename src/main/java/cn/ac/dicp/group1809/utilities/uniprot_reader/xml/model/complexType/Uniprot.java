package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Describes a collection of UniProtKB entries.
 *
 * <pre>
 *      &lt;element name="uniprot">
 *          &lt;annotation>
 *              &lt;documentation>Describes a collection of UniProtKB entries. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;complexType>
 *              &lt;sequence>
 *                  &lt;element ref="entry" maxOccurs="unbounded"/>
 *                  &lt;element ref="copyright" minOccurs="0"/>
 *              &lt;/sequence>
 *          &lt;/complexType>
 *      &lt;/element>
 * </pre>
 *
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mzMLType",propOrder = {
		"entry",
		"copyright"
})
public class Uniprot {
	@XmlElement
	private List<Entry> entry;
	@XmlElement
	private String copyright;

	public List<Entry> getEntry() {
		return entry;
	}

	public void setEntry(List<Entry> entry) {
		this.entry = entry;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
}
