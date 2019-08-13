package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * Describes isoforms in 'alternative products' annotations.
 *
 * <pre>
 *      &lt;complexType name="isoformType">
 *          &lt;annotation>
 *              &lt;documentation>Describes isoforms in 'alternative products' annotations. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;sequence>
 *              &lt;element name="id" type="xs:string" maxOccurs="unbounded"/>
 *              &lt;element name="name" maxOccurs="unbounded">
 *                  &lt;complexType>
 *                      &lt;simpleContent>
 *                          &lt;extension base="xs:string">
 *                              &lt;attribute name="evidence" type="intListType" use="optional"/>
 *                          &lt;/extension>
 *                      &lt;/simpleContent>
 *                  &lt;/complexType>
 *              &lt;/element>
 *              &lt;element name="sequence">
 *                  &lt;complexType>
 *                      &lt;attribute name="type" use="required">
 *                          &lt;simpleType>
 *                              &lt;restriction base="xs:string">
 *                                  &lt;enumeration value="not described"/>
 *                                  &lt;enumeration value="described"/>
 *                                  &lt;enumeration value="displayed"/>
 *                                  &lt;enumeration value="external"/>
 *                              &lt;/restriction>
 *                          &lt;/simpleType>
 *                      &lt;/attribute>
 *                      &lt;attribute name="ref" type="xs:string" use="optional"/>
 *                  &lt;/complexType>
 *              &lt;/element>
 *              &lt;element name="text" type="evidencedStringType" minOccurs="0" maxOccurs="unbounded"/>
 *          &lt;/sequence>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
public class Isoform {
	@XmlElement
	private List<String> id;
	@XmlElement
	private List<Name> name;
	@XmlElement
	private Sequence sequence;
	@XmlElement
	private List<EvidencedString> text;

	public List<String> getId() {
		return id;
	}

	public void setId(List<String> id) {
		this.id = id;
	}

	public List<Name> getName() {
		return name;
	}

	public void setName(List<Name> name) {
		this.name = name;
	}

	public Sequence getSequence() {
		return sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	public List<EvidencedString> getText() {
		return text;
	}

	public void setText(List<EvidencedString> text) {
		this.text = text;
	}

	public enum Type {
		NOT_DESCRIBED("not described"),
		DESCRIBED("described"),
		DISPLAYED("displayed"),
		EXTERNAL("external");
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
			throw new IllegalArgumentException("Invalid isoform type: " + s);
		}

		public String getType() {
			return type;
		}
	}

	public static class Name extends ValueItem {

		@XmlAttribute
		@XmlJavaTypeAdapter(IntListAdapter.class)
		private List<Integer> evidence;

		public List<Integer> getEvidence() {
			return evidence;
		}

		public void setEvidence(List<Integer> evidence) {
			this.evidence = evidence;
		}

	}

	public static class Sequence extends ValueItem {

		@XmlAttribute(required = true)
		private Type type;
		@XmlAttribute
		private String ref;

		public Type getType() {
			return type;
		}

		public void setType(Type type) {
			this.type = type;
		}

		public String getRef() {
			return ref;
		}

		public void setRef(String ref) {
			this.ref = ref;
		}
	}
}
