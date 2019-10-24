package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.DateAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * Describes different types of citations.
 * Equivalent to the flat file RX-, RG-, RA-, RT- and RL-lines.
 *
 * <pre>
 *       &lt;complexType name="citationType">
 *          &lt;annotation>
 *              &lt;documentation>Describes different types of citations.
 *             Equivalent to the flat file RX-, RG-, RA-, RT- and RL-lines. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;sequence>
 *              &lt;element name="title" type="xs:string" minOccurs="0">
 *                  &lt;annotation>
 *                      &lt;documentation>Describes the title of a citation.
 *                     Equivalent to the flat file RT-line. &lt;/documentation>
 *                  &lt;/annotation>
 *              &lt;/element>
 *              &lt;element name="editorList" type="nameListType" minOccurs="0">
 *                  &lt;annotation>
 *                      &lt;documentation>Describes the editors of a book (only used for books).
 *                     Equivalent to part of the flat file RL-line of books. &lt;/documentation>
 *                  &lt;/annotation>
 *              &lt;/element>
 *              &lt;element name="authorList" type="nameListType" minOccurs="0">
 *                  &lt;annotation>
 *                      &lt;documentation>Describes the authors of a citation.
 *                     Equivalent to the flat file RA-line. &lt;/documentation>
 *                  &lt;/annotation>
 *              &lt;/element>
 *              &lt;element name="locator" type="xs:string" minOccurs="0">
 *                  &lt;annotation>
 *                      &lt;documentation>Describes the location (URL) of an online journal article.
 *                     No flat file equivalent. &lt;/documentation>
 *                  &lt;/annotation>
 *              &lt;/element>
 *              &lt;element name="dbReference" type="dbReferenceType" minOccurs="0" maxOccurs="unbounded">
 *                  &lt;annotation>
 *                      &lt;documentation>Describes cross-references to bibliography databases (MEDLINE, PubMed, AGRICOLA) or other online resources (DOI).
 *                     Equivalent to the flat file RX-line. &lt;/documentation>
 *                  &lt;/annotation>
 *              &lt;/element>
 *          &lt;/sequence>
 *          &lt;attribute name="type" use="required">
 *              &lt;annotation>
 *                  &lt;documentation>Describes the type of a citation. &lt;/documentation>
 *              &lt;/annotation>
 *              &lt;simpleType>
 *                  &lt;restriction base="xs:string">
 *                      &lt;enumeration value="book"/>
 *                      &lt;enumeration value="journal article"/>
 *                      &lt;enumeration value="online journal article"/>
 *                      &lt;enumeration value="patent"/>
 *                      &lt;enumeration value="submission"/>
 *                      &lt;enumeration value="thesis"/>
 *                      &lt;enumeration value="unpublished observations"/>
 *                  &lt;/restriction>
 *              &lt;/simpleType>
 *          &lt;/attribute>
 *          &lt;attribute name="date" use="optional">
 *              &lt;simpleType>
 *                  &lt;union memberTypes="xs:date xs:gYearMonth xs:gYear"/>
 *              &lt;/simpleType>
 *          &lt;/attribute>
 *          &lt;attribute name="name" type="xs:string" use="optional">
 *              &lt;annotation>
 *                  &lt;documentation>Describes the name of an (online) journal or book. &lt;/documentation>
 *              &lt;/annotation>
 *          &lt;/attribute>
 *          &lt;attribute name="volume" type="xs:string" use="optional">
 *              &lt;annotation>
 *                  &lt;documentation>Describes the volume of a journal or book. &lt;/documentation>
 *              &lt;/annotation>
 *          &lt;/attribute>
 *          &lt;attribute name="first" type="xs:string" use="optional">
 *              &lt;annotation>
 *                  &lt;documentation>Describes the first page of an article. &lt;/documentation>
 *              &lt;/annotation>
 *          &lt;/attribute>
 *          &lt;attribute name="last" type="xs:string" use="optional">
 *              &lt;annotation>
 *                  &lt;documentation>Describes the last page of an article. &lt;/documentation>
 *              &lt;/annotation>
 *          &lt;/attribute>
 *          &lt;attribute name="publisher" type="xs:string" use="optional">
 *              &lt;annotation>
 *                  &lt;documentation>Describes the publisher of a book. &lt;/documentation>
 *              &lt;/annotation>
 *          &lt;/attribute>
 *          &lt;attribute name="city" type="xs:string" use="optional">
 *              &lt;annotation>
 *                  &lt;documentation>Describes the city where a book was published. &lt;/documentation>
 *              &lt;/annotation>
 *          &lt;/attribute>
 *          &lt;attribute name="db" type="xs:string" use="optional">
 *              &lt;annotation>
 *                  &lt;documentation>Describes the database name of submissions. &lt;/documentation>
 *              &lt;/annotation>
 *          &lt;/attribute>
 *          &lt;attribute name="number" type="xs:string" use="optional">
 *              &lt;annotation>
 *                  &lt;documentation>Describes a patent number. &lt;/documentation>
 *              &lt;/annotation>
 *          &lt;/attribute>
 *          &lt;attribute name="institute" type="xs:string" use="optional">
 *              &lt;annotation>
 *                  &lt;documentation>Describes the institute where a thesis was made. &lt;/documentation>
 *              &lt;/annotation>
 *          &lt;/attribute>
 *          &lt;attribute name="country" type="xs:string" use="optional">
 *              &lt;annotation>
 *                  &lt;documentation>Describes the country where a thesis was made. &lt;/documentation>
 *              &lt;/annotation>
 *          &lt;/attribute>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
@XmlType(name = "citation", propOrder = {
		"title",
		"editorList",
		"authorList",
		"locator",
		"dbReference",
})
public class Citation {
	/**
	 * Describes the title of a citation.
	 * Equivalent to the flat file RT-line.
	 */
	@XmlElement
	private String title;
	/**
	 * Describes the editors of a book (only used for books).
	 * Equivalent to part of the flat file RL-line of books.
	 */
	@XmlElement
	private NameList editorList;
	/**
	 * Describes the authors of a citation.
	 * Equivalent to the flat file RA-line.
	 */
	@XmlElement
	private NameList authorList;
	/**
	 * Describes the location (URL) of an online journal article.
	 * No flat file equivalent.
	 */
	@XmlElement
	private String locator;
	/**
	 * Describes cross-references to bibliography databases (MEDLINE, PubMed, AGRICOLA) or other online resources (DOI).
	 * Equivalent to the flat file RX-line.
	 */
	@XmlElement
	private List<DBReference> dbReference;
	/**
	 * Describes the type of a citation.
	 */
	@XmlAttribute
	private Type type;
	@XmlAttribute
	@XmlJavaTypeAdapter(DateAdapter.class)
	private String date;
	/**
	 * Describes the name of an (online) journal or book.
	 */
	@XmlAttribute
	private String name;
	/**
	 * Describes the volume of a journal or book.
	 */
	@XmlAttribute
	private String volume;
	/**
	 * Describes the first page of an article.
	 */
	@XmlAttribute
	private String first;
	/**
	 * Describes the last page of an article.
	 */
	@XmlAttribute
	private String last;
	/**
	 * Describes the publisher of a book.
	 */
	@XmlAttribute
	private String publisher;
	/**
	 * Describes the city where a book was published.
	 */
	@XmlAttribute
	private String city;
	/**
	 * Describes the database name of submissions.
	 */
	@XmlAttribute
	private String db;
	/**
	 * Describes a patent number.
	 */
	@XmlAttribute
	private String number;
	/**
	 * Describes the institute where a thesis was made.
	 */
	@XmlAttribute
	private String institute;
	/**
	 * Describes the country where a thesis was made.
	 */
	@XmlAttribute
	private String country;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public NameList getEditorList() {
		return editorList;
	}

	public void setEditorList(NameList editorList) {
		this.editorList = editorList;
	}

	public NameList getAuthorList() {
		return authorList;
	}

	public void setAuthorList(NameList authorList) {
		this.authorList = authorList;
	}

	public String getLocator() {
		return locator;
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}

	public List<DBReference> getDbReference() {
		return dbReference;
	}

	public void setDbReference(List<DBReference> dbReference) {
		this.dbReference = dbReference;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Describes the type of a citation.
	 *
	 * @author ZhengFang 2018/5/28
	 * @since V1.0
	 */
	public enum Type {
		BOOK("book"),
		JOURNAL_ARTICLE("journal article"),
		ONLINE_JOURNAL_ARTICLE("online journal article"),
		PATENT("patent"),
		SUBMISSION("submission"),
		THESIS("thesis"),
		UNPUBLISHED_OBSERVATIONS("unpublished observations");

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
			throw new IllegalArgumentException("Invalid Citation Type: " + s);
		}

		public String getType() {
			return type;
		}
	}
}
