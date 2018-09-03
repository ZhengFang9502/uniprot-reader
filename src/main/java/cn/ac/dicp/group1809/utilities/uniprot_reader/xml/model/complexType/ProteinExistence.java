package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Describes the evidence for the protein's existence.
 * Equivalent to the flat file PE-line.
 *
 * <pre>
 *      &lt;complexType name="proteinExistenceType">
 *          &lt;annotation>
 *              &lt;documentation>Describes the evidence for the protein's existence.
 *             Equivalent to the flat file PE-line. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;attribute name="type" use="required">
 *              &lt;simpleType>
 *                  &lt;restriction base="xs:string">
 *                      &lt;enumeration value="evidence at protein level"/>
 *                      &lt;enumeration value="evidence at transcript level"/>
 *                      &lt;enumeration value="inferred from homology"/>
 *                      &lt;enumeration value="predicted"/>
 *                      &lt;enumeration value="uncertain"/>
 *                  &lt;/restriction>
 *              &lt;/simpleType>
 *          &lt;/attribute>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/26
 * @since V1.0
 */
public class ProteinExistence {
	private static Logger logger = LoggerFactory.getLogger(ProteinExistence.class);
	@XmlAttribute(required = true)
	private Type type;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public enum Type {
		EvidenceAtProteinLevel("evidence at protein level", 1),
		EvidenceAtTranscriptLevel("evidence at transcript level", 2),
		InferredFromHomology("inferred from homology", 3),
		Predicted("predicted", 4),
		Uncertain("uncertain", 5);

		private String type;
		private int number;

		Type(String type, int number) {
			this.type = type;
			this.number = number;
		}

		public String getType() {
			return type;
		}

		public int getNumber() {
			return number;
		}

		public static Type forType(String s) {
			for (Type type : Type.values()) {
				if (type.getType().equals(s)) {
					return type;
				}
			}
			logger.error("Invalid protein existence type: " + s);
			throw new IllegalArgumentException("Invalid protein existence type: " + s);
		}
	}
}
