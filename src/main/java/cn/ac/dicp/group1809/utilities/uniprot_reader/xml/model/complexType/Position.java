package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * Describes the names for the protein and parts thereof.
 * Equivalent to the flat file DE-line.
 *
 * <pre>
 *      &lt;complexType name="positionType">
 *          &lt;attribute name="position" type="xs:unsignedLong" use="optional"/>
 *          &lt;attribute name="status" use="optional" default="certain">
 *              &lt;simpleType>
 *                  &lt;restriction base="xs:string">
 *                      &lt;enumeration value="certain"/>
 *                      &lt;enumeration value="uncertain"/>
 *                      &lt;enumeration value="less than"/>
 *                      &lt;enumeration value="greater than"/>
 *                      &lt;enumeration value="unknown"/>
 *                  &lt;/restriction>
 *              &lt;/simpleType>
 *          &lt;/attribute>
 *          &lt;attribute name="evidence" type="intListType" use="optional"/>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
public class Position {
	@XmlAttribute
	private long position;
	@XmlAttribute
	private Status status = Status.CERTAIN;
	@XmlAttribute
	@XmlJavaTypeAdapter(IntListAdapter.class)
	private List<Integer> evidence;

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Integer> getEvidence() {
		return evidence;
	}

	public void setEvidence(List<Integer> evidence) {
		this.evidence = evidence;
	}

	public enum Status {
		CERTAIN("certain"),
		UNCERTAIN("uncertain"),
		LESS_THAN("less than"),
		GREATER_THAN("greater than"),
		UNKNOWN("unknown");

		private String status;

		Status(String type) {
			this.status = type;
		}

		public static Status forStatus(String s) {
			for (Status status : Status.values()) {
				if (status.getStatus().equals(s)) {
					return status;
				}
			}
			throw new IllegalArgumentException("Invalid Position Status: " + s);
		}

		public String getStatus() {
			return status;
		}
	}

}
