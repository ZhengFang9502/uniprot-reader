package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Describes the type of events that cause alternative products.
 *
 * <pre>
 *      &lt;complexType name="eventType">
 *          &lt;annotation>
 *              &lt;documentation>Describes the type of events that cause alternative products. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;attribute name="type" use="required">
 *              &lt;simpleType>
 *                  &lt;restriction base="xs:string">
 *                      &lt;enumeration value="alternative splicing"/>
 *                      &lt;enumeration value="alternative initiation"/>
 *                      &lt;enumeration value="alternative promoter"/>
 *                      &lt;enumeration value="ribosomal frameshifting"/>
 *                  &lt;/restriction>
 *              &lt;/simpleType>
 *          &lt;/attribute>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
public class Event {
	@XmlAttribute(required = true)
	private Type type;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public enum Type {
		ALTERNATIVE_SPLICING("alternative splicing"),
		ALTERNATIVE_INITIATION("alternative initiation"),
		ALTERNATIVE_PROMOTER("alternative promoter"),
		RIBOSOMAL_FRAMESHIFTING("ribosomal frameshifting");
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
			throw new IllegalArgumentException("Invalid Event Type: " + s);
		}

		public String getType() {
			return type;
		}
	}

}
