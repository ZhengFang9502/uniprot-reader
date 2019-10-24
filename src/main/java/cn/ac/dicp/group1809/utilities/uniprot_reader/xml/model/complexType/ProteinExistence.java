package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

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
	@XmlAttribute(required = true)
	private Type type;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public enum Type {
		EVIDENCE_AT_PROTEIN_LEVEL("evidence at protein level", 1),
		EVIDENCE_AT_TRANSCRIPT_LEVEL("evidence at transcript level", 2),
		INFERRED_FROM_HOMOLOGY("inferred from homology", 3),
		PREDICTED("predicted", 4),
		UNCERTAIN("uncertain", 5);

		private String type;
		private int number;

		Type(String type, int number) {
			this.type = type;
			this.number = number;
		}

		public static Type forType(String s) {
			for (Type type : Type.values()) {
				if (type.getType().equals(s)) {
					return type;
				}
			}
			throw new IllegalArgumentException("Invalid Protein Existence Type: " + s);
		}

		public String getType() {
			return type;
		}

		public int getNumber() {
			return number;
		}
	}
}
