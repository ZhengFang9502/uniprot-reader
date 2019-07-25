package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * <xs:complexType name="physiologicalReactionType">
 * <xs:annotation>
 * <xs:documentation>Describes a physiological reaction.</xs:documentation>
 * </xs:annotation>
 * <xs:sequence>
 * <xs:element name="dbReference" type="dbReferenceType"/>
 * </xs:sequence>
 * <xs:attribute name="direction" use="required">
 * <xs:simpleType>
 * <xs:restriction base="xs:string">
 * <xs:enumeration value="left-to-right"/>
 * <xs:enumeration value="right-to-left"/>
 * </xs:restriction>
 * </xs:simpleType>
 * </xs:attribute>
 * <xs:attribute name="evidence" type="intListType" use="optional"/>
 * </xs:complexType>
 *
 * @author Zheng Fang 2019/7/8
 * @since V1.0.0
 */
public class PhysiologicalReaction {
	@XmlElement
	private List<DBReference> dbReference;
	@XmlAttribute
	private Direction direction;
	@XmlAttribute
	private List<Integer> evidence;

	public List<DBReference> getDbReference() {
		if (this.dbReference == null) {
			this.dbReference = new ArrayList<>();
		}
		return dbReference;
	}

	public void setDbReference(List<DBReference> dbReference) {
		this.dbReference = dbReference;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public List<Integer> getEvidence() {
		return evidence;
	}

	public void setEvidence(List<Integer> evidence) {
		this.evidence = evidence;
	}

	public enum Direction {
		leftToRight("left-to-right"),
		rightToLeft("right-to-left");
		private String direction;

		Direction(String direction) {
			this.direction = direction;
		}

		public static Direction forDirection(String direction) {
			for (Direction value : Direction.values()) {
				if (value.getDirection().equals(direction)) {
					return value;
				}
			}
			throw new IllegalArgumentException("Invalid Physiological Reaction Direction: " + direction);
		}

		public String getDirection() {
			return direction;
		}

		public void setDirection(String direction) {
			this.direction = direction;
		}
	}
}
