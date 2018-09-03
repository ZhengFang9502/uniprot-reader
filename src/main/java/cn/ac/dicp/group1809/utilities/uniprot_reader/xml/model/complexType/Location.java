package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Describes a sequence location as either a range with a begin and end or as a position.
 * The 'sequence' attribute is only used when the location is not on the canonical sequence displayed in the current entry.
 *
 * <pre>
 *      &lt;complexType name="locationType">
 *          &lt;annotation>
 *              &lt;documentation>Describes a sequence location as either a range with a begin and end or as a position. The 'sequence' attribute is only used when the location is not on the canonical sequence displayed in the current entry. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;choice>
 *              &lt;sequence>
 *                  &lt;element name="begin" type="positionType"/>
 *                  &lt;element name="end" type="positionType"/>
 *              &lt;/sequence>
 *              &lt;element name="position" type="positionType"/>
 *          &lt;/choice>
 *          &lt;attribute name="sequence" type="xs:string" use="optional"/>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
public class Location {
	@XmlElement
	private Position begin;
	@XmlElement
	private Position end;
	@XmlElement
	private Position position;
	@XmlAttribute
	private String sequence;

	public Position getBegin() {
		return begin;
	}

	public void setBegin(Position begin) {
		this.begin = begin;
	}

	public Position getEnd() {
		return end;
	}

	public void setEnd(Position end) {
		this.end = end;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
}
