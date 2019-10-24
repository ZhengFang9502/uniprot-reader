package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * Describes a UniProtKB entry.
 *
 * <pre>
 *      &lt;element name="entry">
 *          &lt;annotation>
 *              &lt;documentation>Describes a UniProtKB entry. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;complexType>
 *              &lt;sequence>
 *                  &lt;element name="accession" type="xs:string" maxOccurs="unbounded"/>
 *                  &lt;element name="name" type="xs:string" maxOccurs="unbounded"/>
 *                  &lt;element name="protein" type="proteinType"/>
 *                  &lt;element name="gene" type="geneType" minOccurs="0" maxOccurs="unbounded"/>
 *                  &lt;element name="organism" type="organismType" />
 *                  &lt;element name="organismHost" type="organismType" minOccurs="0" maxOccurs="unbounded"/>
 *                  &lt;element name="geneLocation" type="geneLocationType" minOccurs="0" maxOccurs="unbounded"/>
 *                  &lt;element name="reference" type="referenceType" maxOccurs="unbounded"/>
 *                  &lt;element name="comment" type="commentType" nillable="true" minOccurs="0" maxOccurs="unbounded"/>
 *                  &lt;element name="dbReference" type="dbReferenceType" minOccurs="0" maxOccurs="unbounded"/>
 *                  &lt;element name="proteinExistence" type="proteinExistenceType"/>
 *                  &lt;element name="keyword" type="keywordType" minOccurs="0" maxOccurs="unbounded"/>
 *                  &lt;element name="feature" type="featureType" minOccurs="0" maxOccurs="unbounded"/>
 *                  &lt;element name="evidence" type="evidenceType" minOccurs="0" maxOccurs="unbounded"/>
 *                  &lt;element name="sequence" type="sequenceType"/>
 *              &lt;/sequence>
 *              &lt;attribute name="dataset" use="required">
 *                  &lt;simpleType>
 *                      &lt;restriction base="xs:string">
 *                          &lt;enumeration value="Swiss-Prot"/>
 *                          &lt;enumeration value="TrEMBL"/>
 *                      &lt;/restriction>
 *                  &lt;/simpleType>
 *              &lt;/attribute>
 *              &lt;attribute name="created" type="xs:date" use="required"/>
 *              &lt;attribute name="modified" type="xs:date" use="required"/>
 *              &lt;attribute name="version" type="xs:int" use="required"/>
 *          &lt;/complexType>
 *      &lt;/element>
 * </pre>
 *
 * @author ZhengFang 2018/5/26
 * @since V1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "entry", propOrder = {
		"accession",
		"name",
		"protein",
		"gene",
		"organism",
		"organismHost",
		"geneLocation",
		"reference",
		"comment",
		"dbReference",
		"proteinExistence",
		"keyword",
		"feature",
		"evidence",
		"sequence",
})
public class Entry {
	@XmlElement
	private List<String> accession;
	@XmlElement
	private List<String> name;
	@XmlElement
	private Protein protein;
	@XmlElement
	private List<Gene> gene;
	@XmlElement
	private Organism organism;
	@XmlElement
	private List<Organism> organismHost;
	@XmlElement
	private List<GeneLocation> geneLocation;
	@XmlElement
	private List<Reference> reference;
	@XmlElement
	private List<Comment> comment;
	@XmlElement
	private List<DBReference> dbReference;
	@XmlElement
	private ProteinExistence proteinExistence;
	@XmlElement
	private List<Keyword> keyword;
	@XmlElement
	private List<Feature> feature;
	@XmlElement
	private List<Evidence> evidence;
	@XmlElement
	private Sequence sequence;

	@XmlAttribute(required = true)
	private Dataset dataset;
	@XmlAttribute(required = true)
	@XmlJavaTypeAdapter(DateAdapter.class)
	private String create;
	@XmlAttribute(required = true)
	@XmlJavaTypeAdapter(DateAdapter.class)
	private String modified;
	@XmlAttribute(required = true)
	private int version;

	public List<String> getAccession() {
		return accession;
	}

	public void setAccession(List<String> accession) {
		this.accession = accession;
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public Protein getProtein() {
		return protein;
	}

	public void setProtein(Protein protein) {
		this.protein = protein;
	}

	public List<Gene> getGene() {
		return gene;
	}

	public void setGene(List<Gene> gene) {
		this.gene = gene;
	}

	public Organism getOrganism() {
		return organism;
	}

	public void setOrganism(Organism organism) {
		this.organism = organism;
	}

	public List<Organism> getOrganismHost() {
		return organismHost;
	}

	public void setOrganismHost(List<Organism> organismHost) {
		this.organismHost = organismHost;
	}

	public List<GeneLocation> getGeneLocation() {
		return geneLocation;
	}

	public void setGeneLocation(List<GeneLocation> geneLocation) {
		this.geneLocation = geneLocation;
	}

	public List<Reference> getReference() {
		return reference;
	}

	public void setReference(List<Reference> reference) {
		this.reference = reference;
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	public List<DBReference> getDbReference() {
		return dbReference;
	}

	public void setDbReference(List<DBReference> dbReference) {
		this.dbReference = dbReference;
	}

	public ProteinExistence getProteinExistence() {
		return proteinExistence;
	}

	public void setProteinExistence(ProteinExistence proteinExistence) {
		this.proteinExistence = proteinExistence;
	}

	public List<Keyword> getKeyword() {
		return keyword;
	}

	public void setKeyword(List<Keyword> keyword) {
		this.keyword = keyword;
	}

	public List<Feature> getFeature() {
		return feature;
	}

	public void setFeature(List<Feature> feature) {
		this.feature = feature;
	}

	public List<Evidence> getEvidence() {
		return evidence;
	}

	public void setEvidence(List<Evidence> evidence) {
		this.evidence = evidence;
	}

	public Sequence getSequence() {
		return sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	public Dataset getDataset() {
		return dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}

	public String getCreate() {
		return create;
	}

	public void setCreate(String create) {
		this.create = create;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public enum Dataset {
		SP("Swiss-Prot", "sp"),
		TR("TrEMBL", "tr");

		private String dataset;
		private String abbreviation;

		Dataset(String dataset, String abbreviation) {
			this.dataset = dataset;
			this.abbreviation = abbreviation;
		}

		public static Dataset forDataset(String name) {
			for (Dataset dataset : Dataset.values()) {
				if (dataset.getDataset().equals(name)) {
					return dataset;
				}
			}
			throw new IllegalArgumentException("Invalid Dataset Name: " + name);
		}

		public String getDataset() {
			return dataset;
		}

		public String getAbbreviation() {
			return abbreviation;
		}
	}
}
