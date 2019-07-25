package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.group.bpcCommentGroup.BpcComment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * Describes different types of general annotations.
 * Equivalent to the flat file CC-line.
 *
 * <pre>
 *      &lt;complexType name="commentType">
 *          &lt;annotation>
 *              &lt;documentation>Describes different types of general annotations.
 *             Equivalent to the flat file CC-line. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;sequence>
 *              &lt;element name="molecule" type="moleculeType" minOccurs="0"/>
 *              &lt;choice minOccurs="0">
 *                  &lt;group ref="bpcCommentGroup"/>
 *
 *                  &lt;sequence>
 *                      &lt;annotation>
 *                          &lt;documentation>Used in 'cofactor' annotations. &lt;/documentation>
 *                      &lt;/annotation>
 *                      &lt;element name="cofactor" type="cofactorType" maxOccurs="unbounded"/>
 *                  &lt;/sequence>
 *
 *                  &lt;sequence>
 *                      &lt;annotation>
 *                          &lt;documentation>Used in 'subcellular location' annotations. &lt;/documentation>
 *                      &lt;/annotation>
 *                      &lt;element name="subcellularLocation" type="subcellularLocationType" maxOccurs="unbounded"/>
 *                  &lt;/sequence>
 *
 *                  &lt;element name="conflict">
 *                      &lt;annotation>
 *                          &lt;documentation>Used in 'sequence caution' annotations. &lt;/documentation>
 *                      &lt;/annotation>
 *                      &lt;complexType>
 *                          &lt;sequence>
 *                              &lt;element name="sequence" minOccurs="0">
 *                                  &lt;complexType>
 *                                      &lt;attribute name="resource" use="required">
 *                                          &lt;simpleType>
 *                                              &lt;restriction base="xs:string">
 *                                                  &lt;enumeration value="EMBL-CDS"/>
 *                                                  &lt;enumeration value="EMBL"/>
 *                                              &lt;/restriction>
 *                                          &lt;/simpleType>
 *                                      &lt;/attribute>
 *                                      &lt;attribute name="id" type="xs:string" use="required"/>
 *                                      &lt;attribute name="version" type="xs:int" use="optional"/>
 *                                  &lt;/complexType>
 *                              &lt;/element>
 *                          &lt;/sequence>
 *                          &lt;attribute name="type" use="required">
 *                              &lt;simpleType>
 *                                  &lt;restriction base="xs:string">
 *                                      &lt;enumeration value="frameshift"/>
 *                                      &lt;enumeration value="erroneous initiation"/>
 *                                      &lt;enumeration value="erroneous termination"/>
 *                                      &lt;enumeration value="erroneous gene model prediction"/>
 *                                      &lt;enumeration value="erroneous translation"/>
 *                                      &lt;enumeration value="miscellaneous discrepancy"/>
 *                                  &lt;/restriction>
 *                              &lt;/simpleType>
 *                          &lt;/attribute>
 *                          &lt;attribute name="ref" type="xs:string" use="optional">
 *                              &lt;annotation>
 *                                  &lt;documentation>Refers to the 'key' attribute of a 'reference' element. &lt;/documentation>
 *                              &lt;/annotation>
 *                          &lt;/attribute>
 *                      &lt;/complexType>
 *                  &lt;/element>
 *
 *                  &lt;sequence>
 *                      &lt;element name="link" minOccurs="0" maxOccurs="unbounded">
 *                          &lt;annotation>
 *                              &lt;documentation>Used in 'online information' annotations. &lt;/documentation>
 *                          &lt;/annotation>
 *                          &lt;complexType>
 *                              &lt;attribute name="uri" type="xs:anyURI" use="required"/>
 *                          &lt;/complexType>
 *                      &lt;/element>
 *                  &lt;/sequence>
 *
 *                  &lt;sequence>
 *                      &lt;annotation>
 *                          &lt;documentation>Used in 'alternative products' annotations. &lt;/documentation>
 *                      &lt;/annotation>
 *                      &lt;element name="event" type="eventType" maxOccurs="4"/>
 *                      &lt;element name="isoform" type="isoformType" minOccurs="0" maxOccurs="unbounded"/>
 *                  &lt;/sequence>
 *
 *                  &lt;sequence>
 *                      &lt;annotation>
 *                          &lt;documentation>Used in 'interaction' annotations. &lt;/documentation>
 *                      &lt;/annotation>
 *                      &lt;element name="interactant" type="interactantType" minOccurs="2" maxOccurs="2"/>
 *                      &lt;element name="organismsDiffer" type="xs:boolean" default="false"/>
 *                      &lt;element name="experiments" type="xs:int"/>
 *                  &lt;/sequence>
 *
 *                  &lt;element name="disease">
 *                      &lt;annotation>
 *                          &lt;documentation>Used in 'disease' annotations. &lt;/documentation>
 *                      &lt;/annotation>
 *                      &lt;complexType>
 *                          &lt;sequence>
 *                              &lt;element name="name" type="xs:string"/>
 *                              &lt;element name="acronym" type="xs:string"/>
 *                              &lt;element name="description" type="xs:string"/>
 *                              &lt;element name="dbReference" type="dbReferenceType"/>
 *                          &lt;/sequence>
 *                          &lt;attribute name="id" type="xs:string" use="required"/>
 *                      &lt;/complexType>
 *                  &lt;/element>
 *
 *              &lt;/choice>
 *
 *              &lt;element name="location" type="locationType" minOccurs="0" maxOccurs="unbounded">
 *                  &lt;annotation>
 *                      &lt;documentation>Used in 'mass spectrometry' and 'sequence caution' annotations. &lt;/documentation>
 *                  &lt;/annotation>
 *              &lt;/element>
 *
 *              &lt;element name="text" type="evidencedStringType" minOccurs="0" maxOccurs="unbounded"/>
 *
 *          &lt;/sequence>
 *
 *          &lt;attribute name="type" use="required">
 *              &lt;annotation>
 *                  &lt;documentation>Describes the type of a general annotation.
 *                 Equivalent to the flat file CC comment topics (except for "DATABASE" which is translated to "online information"). &lt;/documentation>
 *              &lt;/annotation>
 *              &lt;simpleType>
 *                  &lt;restriction base="xs:string">
 *                      &lt;enumeration value="allergen"/>
 *                      &lt;enumeration value="alternative products"/>
 *                      &lt;enumeration value="biotechnology"/>
 *                      &lt;enumeration value="biophysicochemical properties"/>
 *                      &lt;enumeration value="catalytic activity"/>
 *                      &lt;enumeration value="caution"/>
 *                      &lt;enumeration value="cofactor"/>
 *                      &lt;enumeration value="developmental stage"/>
 *                      &lt;enumeration value="disease"/>
 *                      &lt;enumeration value="domain"/>
 *                      &lt;enumeration value="disruption phenotype"/>
 *                      &lt;enumeration value="enzyme regulation"/>
 *                      &lt;enumeration value="function"/>
 *                      &lt;enumeration value="induction"/>
 *                      &lt;enumeration value="miscellaneous"/>
 *                      &lt;enumeration value="pathway"/>
 *                      &lt;enumeration value="pharmaceutical"/>
 *                      &lt;enumeration value="polymorphism"/>
 *                      &lt;enumeration value="PTM"/>
 *                      &lt;enumeration value="RNA editing"/>
 *                      &lt;enumeration value="similarity"/>
 *                      &lt;enumeration value="subcellular location"/>
 *                      &lt;enumeration value="sequence caution"/>
 *                      &lt;enumeration value="subunit"/>
 *                      &lt;enumeration value="tissue specificity"/>
 *                      &lt;enumeration value="toxic dose"/>
 *                      &lt;enumeration value="online information"/>
 *                      &lt;enumeration value="mass spectrometry"/>
 *                      &lt;enumeration value="interaction"/>
 *                  &lt;/restriction>
 *              &lt;/simpleType>
 *          &lt;/attribute>
 *
 *          &lt;attribute name="locationType" type="xs:string" use="optional">
 *              &lt;annotation>
 *                  &lt;documentation>Describes the type of sequence location in 'RNA editing' annotations. Common values are "Not_applicable" and "Undetermined". &lt;/documentation>
 *              &lt;/annotation>
 *          &lt;/attribute>
 *
 *          &lt;attribute name="name" type="xs:string" use="optional">
 *              &lt;annotation>
 *                  &lt;documentation>Describes an optional name for an 'online information'. &lt;/documentation>
 *              &lt;/annotation>
 *          &lt;/attribute>
 *
 *          &lt;attribute name="mass" type="xs:float" use="optional">
 *              &lt;annotation>
 *                  &lt;documentation>Describes the molecular mass in 'mass spectrometry' annotations. &lt;/documentation>
 *              &lt;/annotation>
 *          &lt;/attribute>
 *          &lt;attribute name="error" type="xs:string" use="optional">
 *              &lt;annotation>
 *                  &lt;documentation>Describes the error of the mass measurement in 'mass spectrometry' annotations. &lt;/documentation>
 *              &lt;/annotation>
 *          &lt;/attribute>
 *          &lt;attribute name="method" type="xs:string" use="optional">
 *              &lt;annotation>
 *                  &lt;documentation>Describes the experimental method in 'mass spectrometry' annotations. &lt;/documentation>
 *              &lt;/annotation>
 *          &lt;/attribute>
 *
 *          &lt;attribute name="evidence" type="intListType" use="optional"/>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/26
 * @since V1.0
 */
public class Comment {
	private static Logger logger = LoggerFactory.getLogger(Comment.class);
	@XmlElement
	private Molecule molecule;
	@XmlElement
	private BpcComment bpcComment;

	/**
	 * Used in 'catalytic activity' annotations.
	 */
	@XmlElement
	private List<Reaction> reaction;
	@XmlElement
	private List<PhysiologicalReaction> physiologicalReaction;
	/**
	 * Used in 'cofactor' annotations.
	 */
	@XmlElement
	private List<Cofactor> cofactor;
	/**
	 * Used in 'subcellular location' annotations.
	 */
	@XmlElement
	private List<SubcellularLocation> subcellularLocation;
	/**
	 * Used in 'sequence caution' annotations.
	 */
	@XmlElement
	private Conflict conflict;
	/**
	 * Used in 'online information' annotations.
	 */
	@XmlElement
	private List<Link> link;
	/**
	 * Used in 'alternative products' annotations.
	 */
	@XmlElement
	private List<Event> event;
	@XmlElement
	private List<Isoform> isoform;
	/**
	 * Used in 'interaction' annotations.
	 */
	@XmlElement
	private List<Interactant> interactant;
	@XmlElement
	private boolean organismsDiffer = false;
	@XmlElement
	private int experiments = -1;
	/**
	 * Used in 'disease' annotations.
	 */
	@XmlElement
	private Disease disease;

	/**
	 * Used in 'mass spectrometry' and 'sequence caution' annotations.
	 */
	@XmlElement
	private List<Location> location;
	@XmlElement
	private List<EvidencedString> text;

	/**
	 * Describes the type of a general annotation.
	 * Equivalent to the flat file CC comment topics (except for "DATABASE" which is translated to "online information").
	 */
	@XmlAttribute(required = true)
	private Type type;
	/**
	 * Describes the type of sequence location in 'RNA editing' annotations. Common values are "Not_applicable" and "Undetermined".
	 */
	@XmlAttribute
	private String locationType;
	/**
	 * Describes an optional name for an 'online information'.
	 */
	@XmlAttribute
	private String name;
	/**
	 * Describes the molecular mass in 'mass spectrometry' annotations.
	 */
	@XmlAttribute
	private float mass;
	/**
	 * Describes the error of the mass measurement in 'mass spectrometry' annotations.
	 */
	@XmlAttribute
	private String error;
	/**
	 * Describes the experimental method in 'mass spectrometry' annotations.
	 */
	@XmlAttribute
	private String method;
	@XmlAttribute
	@XmlJavaTypeAdapter(IntListAdapter.class)
	private List<Integer> evidence;

	public Molecule getMolecule() {
		return molecule;
	}

	public void setMolecule(Molecule molecule) {
		this.molecule = molecule;
	}

	public BpcComment getBpcComment() {
		return bpcComment;
	}

	public void setBpcComment(BpcComment bpcComment) {
		this.bpcComment = bpcComment;
	}

	public List<Reaction> getReaction() {
		return reaction;
	}

	public void setReaction(List<Reaction> reaction) {
		this.reaction = reaction;
	}

	public List<PhysiologicalReaction> getPhysiologicalReaction() {
		return physiologicalReaction;
	}

	public void setPhysiologicalReaction(List<PhysiologicalReaction> physiologicalReaction) {
		this.physiologicalReaction = physiologicalReaction;
	}

	public List<Cofactor> getCofactor() {
		return cofactor;
	}

	public void setCofactor(List<Cofactor> cofactor) {
		this.cofactor = cofactor;
	}

	public List<SubcellularLocation> getSubcellularLocation() {
		return subcellularLocation;
	}

	public void setSubcellularLocation(List<SubcellularLocation> subcellularLocation) {
		this.subcellularLocation = subcellularLocation;
	}

	public Conflict getConflict() {
		return conflict;
	}

	public void setConflict(Conflict conflict) {
		this.conflict = conflict;
	}

	public List<Link> getLink() {
		return link;
	}

	public void setLink(List<Link> link) {
		this.link = link;
	}

	public List<Event> getEvent() {
		return event;
	}

	public void setEvent(List<Event> event) {
		this.event = event;
	}

	public List<Isoform> getIsoform() {
		return isoform;
	}

	public void setIsoform(List<Isoform> isoform) {
		this.isoform = isoform;
	}

	public List<Interactant> getInteractant() {
		return interactant;
	}

	public void setInteractant(List<Interactant> interactant) {
		this.interactant = interactant;
	}

	public boolean isOrganismsDiffer() {
		return organismsDiffer;
	}

	public void setOrganismsDiffer(boolean organismsDiffer) {
		this.organismsDiffer = organismsDiffer;
	}

	public int getExperiments() {
		return experiments;
	}

	public void setExperiments(int experiments) {
		this.experiments = experiments;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public List<Location> getLocation() {
		return location;
	}

	public void setLocation(List<Location> location) {
		this.location = location;
	}

	public List<EvidencedString> getText() {
		return text;
	}

	public void setText(List<EvidencedString> text) {
		this.text = text;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<Integer> getEvidence() {
		return evidence;
	}

	public void setEvidence(List<Integer> evidence) {
		this.evidence = evidence;
	}

	public enum Type {
		Allergen("allergen"),
		AlternativeProducts("alternative products"),
		Biotechnology("biotechnology"),
		BiophysicochemicalProperties("biophysicochemical properties"),
		CatalyticActivity("catalytic activity"),
		Caution("caution"),
		Cofactor("cofactor"),
		DevelopmentalStage("developmental stage"),
		Disease("disease"),
		Domain("domain"),
		DisruptionPhenotype("disruption phenotype"),
		ActivityRegulation("activity regulation"),
		Function("function"),
		Induction("induction"),
		Miscellaneous("miscellaneous"),
		Pathway("pathway"),
		Pharmaceutical("pharmaceutical"),
		Polymorphism("polymorphism"),
		PTM("PTM"),
		RNAEditing("RNA editing"),
		Similarity("similarity"),
		SubcellularLocation("subcellular location"),
		SequenceCaution("sequence caution"),
		Subunit("subunit"),
		TissueSpecificity("tissue specificity"),
		ToxicDose("toxic dose"),
		OnlineInformation("online information"),
		MassSpectrometry("mass spectrometry"),
		Interaction("interaction");
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
			logger.error("Invalid comment type: " + s);
			throw new IllegalArgumentException("Invalid comment type: " + s);
		}

		public String getType() {
			return type;
		}
	}
}
