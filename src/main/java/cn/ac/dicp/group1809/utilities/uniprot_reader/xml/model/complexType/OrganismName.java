package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Describes different types of source organism names.
 *
 * <pre>
 *      &lt;complexType name="organismNameType">
 *          &lt;annotation>
 *              &lt;documentation>Describes different types of source organism names. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;simpleContent>
 *              &lt;extension base="xs:string">
 *                  &lt;attribute name="type" use="required">
 *                      &lt;simpleType>
 *                          &lt;restriction base="xs:string">
 *                              &lt;enumeration value="common"/>
 *                              &lt;enumeration value="full"/>
 *                              &lt;enumeration value="scientific"/>
 *                              &lt;enumeration value="synonym"/>
 *                              &lt;enumeration value="abbreviation"/>
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
@XmlType(name = "name")
public class OrganismName extends ValueItem {
	@XmlAttribute(required = true)
	private Type type;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public enum Type {
		COMMON("common"),
		FULL("full"),
		SCIENTIFIC("scientific"),
		SYNONYM("synonym"),
		ABBREVIATION("abbreviation");

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
			throw new IllegalArgumentException("Invalid Organism Name Type: " + s);
		}

		public String getType() {
			return type;
		}
	}
}
