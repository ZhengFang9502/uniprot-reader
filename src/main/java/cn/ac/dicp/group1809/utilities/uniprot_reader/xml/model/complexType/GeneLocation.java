package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * Describes non-nuclear gene locations (organelles and plasmids).
 * Equivalent to the flat file OG-line.
 *
 * <pre>
 *      &lt;complexType name="geneLocationType">
 *          &lt;annotation>
 *              &lt;documentation>Describes non-nuclear gene locations (organelles and plasmids).
 *             Equivalent to the flat file OG-line. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;sequence>
 *              &lt;element name="name" type="statusType" minOccurs="0" maxOccurs="unbounded"/>
 *          &lt;/sequence>
 *          &lt;attribute name="type" use="required">
 *              &lt;simpleType>
 *                  &lt;restriction base="xs:string">
 *                      &lt;enumeration value="apicoplast"/>
 *                      &lt;enumeration value="chloroplast"/>
 *                      &lt;enumeration value="organellar chromatophore"/>
 *                      &lt;enumeration value="cyanelle"/>
 *                      &lt;enumeration value="hydrogenosome"/>
 *                      &lt;enumeration value="mitochondrion"/>
 *                      &lt;enumeration value="non-photosynthetic plastid"/>
 *                      &lt;enumeration value="nucleomorph"/>
 *                      &lt;enumeration value="plasmid"/>
 *                      &lt;enumeration value="plastid"/>
 *                  &lt;/restriction>
 *              &lt;/simpleType>
 *          &lt;/attribute>
 *          &lt;attribute name="evidence" type="intListType" use="optional"/>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/26
 * @since V1.0
 */
public class GeneLocation {
	@XmlElement
	private List<Status> name;

	@XmlAttribute(required = true)
	private Type type;
	@XmlAttribute
	@XmlJavaTypeAdapter(IntListAdapter.class)
	private List<Integer> evidence;

	public List<Status> getName() {
		return name;
	}

	public void setName(List<Status> name) {
		this.name = name;
	}

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
		APICOPLAST("apicoplast"),
		CHLOROPLAST("chloroplast"),
		ORGANELLAR_CHROMATOPHORE("organellar chromatophore"),
		CYANELLE("cyanelle"),
		HYDROGENOSOME("hydrogenosome"),
		MITOCHONDRION("mitochondrion"),
		NON_PHOTOSYNTHETIC_PLASTID("non-photosynthetic plastid"),
		NUCLEOMORPH("nucleomorph"),
		PLASMID("plasmid"),
		PLASTID("plastid");

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
			throw new IllegalArgumentException("Invalid gene location type: " + s);
		}

		public String getType() {
			return type;
		}
	}
}
