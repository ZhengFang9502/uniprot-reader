package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * Describes different types of gene designations.
 * Equivalent to the flat file GN-line.
 *
 * <pre>
 *      &lt;complexType name="geneNameType">
 *          &lt;annotation>
 *              &lt;documentation>Describes different types of gene designations.
 *             Equivalent to the flat file GN-line. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;simpleContent>
 *              &lt;extension base="xs:string">
 *                  &lt;attribute name="evidence" type="intListType" use="optional"/>
 *                  &lt;attribute name="type" use="required">
 *                      &lt;simpleType>
 *                          &lt;restriction base="xs:string">
 *                              &lt;enumeration value="primary"/>
 *                              &lt;enumeration value="synonym"/>
 *                              &lt;enumeration value="ordered locus"/>
 *                              &lt;enumeration value="ORF"/>
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
public class GeneName extends ValueItem {
	@XmlAttribute
	private Type type;
	@XmlAttribute(required = true)
	@XmlJavaTypeAdapter(IntListAdapter.class)
	private List<Integer> evidence;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<Integer> getEvidence() {
		return evidence;
	}

	public void setEvidence(List<Integer> evidence) {
		this.evidence = evidence;
	}

	public enum Type {
		PRIMARY("primary"),
		SYNONYM("synonym"),
		ORDERED_LOCUS("ordered locus"),
		ORF("ORF");

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
			throw new IllegalArgumentException("Invalid gene name type: " + s);
		}

		public String getType() {
			return type;
		}
	}
}
