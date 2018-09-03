package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * Describes the source organism.
 *
 * <pre>
 *      &lt;complexType name="organismType">
 *          &lt;annotation>
 *              &lt;documentation>Describes the source organism. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;sequence>
 *              &lt;element name="name" type="organismNameType" maxOccurs="unbounded">
 *                  &lt;annotation>
 *                      &lt;documentation>Describes the names of the source organism.
 *                     Equivalent to the flat file OS-line. &lt;/documentation>
 *                  &lt;/annotation>
 *              &lt;/element>
 *              &lt;element name="dbReference" type="dbReferenceType" maxOccurs="unbounded">
 *                  &lt;annotation>
 *                      &lt;documentation>Describes a cross-reference to the NCBI taxonomy database.
 *                     Equivalent to the flat file OX-line. &lt;/documentation>
 *                  &lt;/annotation>
 *              &lt;/element>
 *              &lt;element name="lineage" minOccurs="0">
 *                  &lt;annotation>
 *                      &lt;documentation>Describes the lineage of the source organism.
 *                     Equivalent to the flat file OC-line. &lt;/documentation>
 *                  &lt;/annotation>
 *                  &lt;complexType>
 *                      &lt;sequence>
 *                          &lt;element name="taxon" type="xs:string" maxOccurs="unbounded"/>
 *                      &lt;/sequence>
 *                  &lt;/complexType>
 *              &lt;/element>
 *          &lt;/sequence>
 *          &lt;attribute name="evidence" type="intListType" use="optional"/>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/26
 * @since V1.0
 */
@XmlType(name = "organism",propOrder = {
		"name",
		"dbReference",
		"lineage",
})
public class Organism {
	/**
	 * Describes the names of the source organism.
	 * Equivalent to the flat file OS-line.
	 */
	@XmlElement
	private List<OrganismName> name;
	/**
	 * Describes a cross-reference to the NCBI taxonomy database.
	 * Equivalent to the flat file OX-line.
	 */
	@XmlElement
	private List<DBReference> dbReference;
	/**
	 * Describes the lineage of the source organism.
	 * Equivalent to the flat file OC-line.
	 */
	@XmlElement
	private List<Taxon> lineage;

	@XmlAttribute
	@XmlJavaTypeAdapter(IntListAdapter.class)
	private List<Integer> evidence;

	public List<OrganismName> getName() {
		return name;
	}

	public void setName(List<OrganismName> name) {
		this.name = name;
	}

	public List<DBReference> getDbReference() {
		return dbReference;
	}

	public void setDbReference(List<DBReference> dbReference) {
		this.dbReference = dbReference;
	}

	public List<Taxon> getLineage() {
		return lineage;
	}

	public void setLineage(List<Taxon> lineage) {
		this.lineage = lineage;
	}

	public List<Integer> getEvidence() {
		return evidence;
	}

	public void setEvidence(List<Integer> evidence) {
		this.evidence = evidence;
	}

	public static class Taxon {
		@XmlElement
		private String taxon;

		public String getTaxon() {
			return taxon;
		}

		public void setTaxon(String taxon) {
			this.taxon = taxon;
		}
	}
}
