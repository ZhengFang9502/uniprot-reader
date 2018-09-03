package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Describes the subcellular location and optionally the topology and orientation of a molecule.
 *
 * <pre>
 *      &lt;complexType name="subcellularLocationType">
 *          &lt;annotation>
 *              &lt;documentation>Describes the subcellular location and optionally the topology and orientation of a molecule. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;sequence>
 *              &lt;element name="location" type="evidencedStringType" maxOccurs="unbounded"/>
 *              &lt;element name="topology" type="evidencedStringType" minOccurs="0" maxOccurs="unbounded"/>
 *              &lt;element name="orientation" type="evidencedStringType" minOccurs="0" maxOccurs="unbounded"/>
 *          &lt;/sequence>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
public class SubcellularLocation {
	@XmlElement
	private List<EvidencedString> location;
	@XmlElement
	private List<EvidencedString> topology;
	@XmlElement
	private List<EvidencedString> orientation;

	public List<EvidencedString> getLocation() {
		return location;
	}

	public void setLocation(List<EvidencedString> location) {
		this.location = location;
	}

	public List<EvidencedString> getTopology() {
		return topology;
	}

	public void setTopology(List<EvidencedString> topology) {
		this.topology = topology;
	}

	public List<EvidencedString> getOrientation() {
		return orientation;
	}

	public void setOrientation(List<EvidencedString> orientation) {
		this.orientation = orientation;
	}
}
