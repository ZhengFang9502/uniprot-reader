package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private static Logger logger = LoggerFactory.getLogger(Status.class);
	@XmlAttribute
	private Type status;

	public Type getStatus() {
		return status;
	}

	public void setStatus(Type status) {
		this.status = status;
	}

	public enum Type {
		Known("known"),
		Unknown("unknown");
		private String type;

		Type(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}

		public static Type forType(String s) {
			for (Type type : Type.values()) {
				if (type.getType().equals(s)) {
					return type;
				}
			}
			logger.error("Invalid statue type: " + s);
			throw new IllegalArgumentException("Invalid statue type: " + s);
		}
	}
}
