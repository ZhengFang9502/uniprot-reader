package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * Describes different types of sequence annotations.
 * Equivalent to the flat file FT-line.
 *
 * <pre>
 *      &lt;complexType name="featureType">
 *          &lt;annotation>
 *              &lt;documentation>Describes different types of sequence annotations.
 *             Equivalent to the flat file FT-line. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;sequence>
 *              &lt;element name="original" type="xs:string" minOccurs="0">
 *                 &lt;annotation>
 *                      &lt;documentation>Describes the original sequence in annotations that describe natural or artifical sequence variations. &lt;/documentation>
 *                  &lt;/annotation>
 *              &lt;/element>
 *              &lt;element name="variation" type="xs:string" minOccurs="0" maxOccurs="unbounded">
 *                &lt;annotation>
 *                      &lt;documentation>Describes the variant sequence in annotations that describe natural or artifical sequence variations. &lt;/documentation>
 *                  &lt;/annotation>
 *              &lt;/element>
 *              &lt;element name="location" type="locationType">
 *                  &lt;annotation>
 *                      &lt;documentation>Describes the sequence coordinates of the annotation. &lt;/documentation>
 *                  &lt;/annotation>
 *              &lt;/element>
 *          &lt;/sequence>
 *          &lt;attribute name="type" use="required">
 *              &lt;annotation>
 *                  &lt;documentation>Describes the type of a sequence annotation.
 *                 Equivalent to the flat file FT feature keys, but using full terms instead of acronyms. &lt;/documentation>
 *              &lt;/annotation>
 *              &lt;simpleType>
 *                  &lt;restriction base="xs:string">
 *                      &lt;enumeration value="active site"/>
 *                      &lt;enumeration value="binding site"/>
 *                      &lt;enumeration value="calcium-binding region"/>
 *                      &lt;enumeration value="chain"/>
 *                      &lt;enumeration value="coiled-coil region"/>
 *                      &lt;enumeration value="compositionally biased region"/>
 *                      &lt;enumeration value="cross-link"/>
 *                      &lt;enumeration value="disulfide bond"/>
 *                      &lt;enumeration value="DNA-binding region"/>
 *                      &lt;enumeration value="domain"/>
 *                      &lt;enumeration value="glycosylation site"/>
 *                      &lt;enumeration value="helix"/>
 *                      &lt;enumeration value="initiator methionine"/>
 *                      &lt;enumeration value="lipid moiety-binding region"/>
 *                      &lt;enumeration value="metal ion-binding site"/>
 *                      &lt;enumeration value="modified residue"/>
 *                      &lt;enumeration value="mutagenesis site"/>
 *                      &lt;enumeration value="non-consecutive residues"/>
 *                      &lt;enumeration value="non-terminal residue"/>
 *                      &lt;enumeration value="nucleotide phosphate-binding region"/>
 *                      &lt;enumeration value="peptide"/>
 *                      &lt;enumeration value="propeptide"/>
 *                      &lt;enumeration value="region of interest"/>
 *                      &lt;enumeration value="repeat"/>
 *                      &lt;enumeration value="non-standard amino acid"/>
 *                      &lt;enumeration value="sequence conflict"/>
 *                      &lt;enumeration value="sequence variant"/>
 *                      &lt;enumeration value="short sequence motif"/>
 *                      &lt;enumeration value="signal peptide"/>
 *                      &lt;enumeration value="site"/>
 *                      &lt;enumeration value="splice variant"/>
 *                      &lt;enumeration value="strand"/>
 *                      &lt;enumeration value="topological domain"/>
 *                      &lt;enumeration value="transit peptide"/>
 *                      &lt;enumeration value="transmembrane region"/>
 *                      &lt;enumeration value="turn"/>
 *                      &lt;enumeration value="unsure residue"/>
 *                      &lt;enumeration value="zinc finger region"/>
 *                      &lt;enumeration value="intramembrane region"/>
 *                  &lt;/restriction>
 *              &lt;/simpleType>
 *          &lt;/attribute>
 *          &lt;attribute name="status" use="optional">
 *              &lt;simpleType>
 *                  &lt;restriction base="xs:string">
 *                      &lt;enumeration value="by similarity"/>
 *                      &lt;enumeration value="probable"/>
 *                      &lt;enumeration value="potential"/>
 *                  &lt;/restriction>
 *              &lt;/simpleType>
 *          &lt;/attribute>
 *          &lt;attribute name="id" type="xs:string" use="optional"/>
 *          &lt;attribute name="description" type="xs:string" use="optional"/>
 *          &lt;attribute name="evidence" type="intListType" use="optional"/>
 *          &lt;attribute name="ref" type="xs:string" use="optional"/>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/26
 * @since V1.0
 */
public class Feature {
	/**
	 * Describes the original sequence in annotations that describe natural or artifical sequence variations.
	 */
	@XmlElement
	private String original;
	/**
	 * Describes the variant sequence in annotations that describe natural or artifical sequence variations.
	 */
	@XmlElement
	private List<String> variation;
	/**
	 * Describes the sequence coordinates of the annotation.
	 */
	@XmlElement
	private Location location;
	/**
	 * Describes the type of a sequence annotation.
	 * Equivalent to the flat file FT feature keys, but using full terms instead of acronyms.
	 */
	@XmlAttribute(required = true)
	private Type type;
	@XmlAttribute
	private Status status;
	@XmlAttribute
	private String id;
	@XmlAttribute
	private String description;
	@XmlAttribute
	@XmlJavaTypeAdapter(IntListAdapter.class)
	private List<Integer> evidence;
	@XmlAttribute
	private String ref;

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public List<String> getVariation() {
		return variation;
	}

	public void setVariation(List<String> variation) {
		this.variation = variation;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Integer> getEvidence() {
		return evidence;
	}

	public void setEvidence(List<Integer> evidence) {
		this.evidence = evidence;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public enum Type {
		ACTIVE_SITE("active site"),
		BINDING_SITE("binding site"),
		CALCIUM_BINDING_REGION("calcium-binding region"),
		CHAIN("chain"),
		COILED_COIL_REGION("coiled-coil region"),
		COMPOSITIONALLY_BIASED_REGION("compositionally biased region"),
		CROSS_LINK("cross-link"),
		DISULFIDE_BOND("disulfide bond"),
		DNA_BINDING_REGION("DNA-binding region"),
		DOMAIN("domain"),
		GLYCOSYLATION_SITE("glycosylation site"),
		HELIX("helix"),
		INITIATOR_METHIONINE("initiator methionine"),
		LIPID_MOIETY_BINDING_REGION("lipid moiety-binding region"),
		METAL_ION_BINDING_SITE("metal ion-binding site"),
		MODIFIED_RESIDUE("modified residue"),
		MUTAGENESIS_SITE("mutagenesis site"),
		NON_CONSECUTIVE_RESIDUES("non-consecutive residues"),
		NON_TERMINAL_RESIDUE("non-terminal residue"),
		NUCLEOTIDE_PHOSPHATE_BINDING_REGION("nucleotide phosphate-binding region"),
		PEPTIDE("peptide"),
		PROPEPTIDE("propeptide"),
		REGION_OF_INTEREST("region of interest"),
		REPEAT("repeat"),
		NON_STANDARD_AMINO_ACID("non-standard amino acid"),
		SEQUENCE_CONFLICT("sequence conflict"),
		SEQUENCE_VARIANT("sequence variant"),
		SHORT_SEQUENCE_MOTIF("short sequence motif"),
		SIGNAL_PEPTIDE("signal peptide"),
		SITE("site"),
		SPLICE_VARIANT("splice variant"),
		STRAND("strand"),
		TOPOLOGICAL_DOMAIN("topological domain"),
		TRANSIT_PEPTIDE("transit peptide"),
		TRANSMEMBRANE_REGION("transmembrane region"),
		TURN("turn"),
		UNSURE_RESIDUE("unsure residue"),
		ZINC_FINGER_REGION("zinc finger region"),
		INTRAMEMBRANE_REGION("intramembrane region");
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
			throw new IllegalArgumentException("Invalid feature type: " + s);
		}

		public String getType() {
			return type;
		}
	}

	public enum Status {
		BY_SIMILARITY("by similarity"),
		PROBABLE("probable"),
		POTENTIAL("potential");
		private String status;

		Status(String status) {
			this.status = status;
		}

		public static Status forStatus(String s) {
			for (Status status : Status.values()) {
				if (status.getStatus().equals(s)) {
					return status;
				}
			}
			throw new IllegalArgumentException("Invalid feature status: " + s);
		}

		public String getStatus() {
			return status;
		}
	}
}
