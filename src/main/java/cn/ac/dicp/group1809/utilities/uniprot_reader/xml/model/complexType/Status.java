package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Indicates whether the name of a plasmid is known or unknown.
 *
 * <pre>
 *      &lt;complexType name="statusType">
 *          &lt;annotation>
 *              &lt;documentation>Indicates whether the name of a plasmid is known or unknown. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;simpleContent>
 *              &lt;extension base="xs:string">
 *                  &lt;attribute name="status" use="optional" default="known">
 *                      &lt;simpleType>
 *                          &lt;restriction base="xs:string">
 *                              &lt;enumeration value="known"/>
 *                              &lt;enumeration value="unknown"/>
 *                          &lt;/restriction>
 *                      &lt;/simpleType>
 *                  &lt;/attribute>
 *              &lt;/extension>
 *          &lt;/simpleContent>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/26
 * @since V1.0
 */
public class Status extends ValueItem {
	@XmlAttribute
	private Type status;

	public Type getStatus() {
		return status;
	}

	public void setStatus(Type status) {
		this.status = status;
	}

	public enum Type {
		KNOWN("known"),
		UNKNOWN("unknown");
		private String type;

		Type(String type) {
			this.type = type;
		}

		public static Type forType(String s) {
			for (Type type : Type.values()) {
				if (type.getType().equals(s)) {
					return type;
				}
			}
			throw new IllegalArgumentException("Invalid statue type: " + s);
		}

		public String getType() {
			return type;
		}
	}
}
