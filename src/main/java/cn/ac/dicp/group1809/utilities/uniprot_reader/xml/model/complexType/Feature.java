package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private static Logger logger = LoggerFactory.getLogger(Feature.class);
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
		ActiveSite("active site"),
		BindingSite("binding site"),
		CalciumBindingRegion("calcium-binding region"),
		Chain("chain"),
		CoiledCoilRegion("coiled-coil region"),
		CompositionallyBiasedRegion("compositionally biased region"),
		CrossLink("cross-link"),
		DisulfideBond("disulfide bond"),
		DNABindingRegion("DNA-binding region"),
		Domain("domain"),
		GlycosylationSite("glycosylation site"),
		Helix("helix"),
		InitiatorMethionine("initiator methionine"),
		LipidMoietyBindingRegion("lipid moiety-binding region"),
		MetalIonBindingSite("metal ion-binding site"),
		ModifiedResidue("modified residue"),
		MutagenesisSite("mutagenesis site"),
		NonConsecutiveResidues("non-consecutive residues"),
		NonTerminalResidue("non-terminal residue"),
		NucleotidePhosphateBindingRegion("nucleotide phosphate-binding region"),
		Peptide("peptide"),
		Propeptide("propeptide"),
		RegionOfInterest("region of interest"),
		Repeat("repeat"),
		NonStandardAminoAcid("non-standard amino acid"),
		SequenceConflict("sequence conflict"),
		SequenceVariant("sequence variant"),
		ShortSequenceMotif("short sequence motif"),
		SignalPeptide("signal peptide"),
		Site("site"),
		SpliceVariant("splice variant"),
		Strand("strand"),
		TopologicalDomain("topological domain"),
		TransitPeptide("transit peptide"),
		TransmembraneRegion("transmembrane region"),
		Turn("turn"),
		UnsureResidue("unsure residue"),
		ZincFingerRegion("zinc finger region"),
		IntramembraneRegion("intramembrane region");
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
			logger.error("Invalid feature type: " + s);
			throw new IllegalArgumentException("Invalid feature type: " + s);
		}
	}

	public enum Status {
		BySimilarity("by similarity"),
		Probable("probable"),
		Potential("potential");
		private String status;

		Status(String status) {
			this.status = status;
		}

		public String getStatus() {
			return status;
		}

		public static Status forStatus(String s) {
			for (Status status : Status.values()) {
				if (status.getStatus().equals(s)) {
					return status;
				}
			}
			logger.error("Invalid feature status: " + s);
			throw new IllegalArgumentException("Invalid feature status: " + s);
		}
	}
}
