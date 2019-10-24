package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Used in 'sequence caution' annotations.
 *
 * @author ZhengFang 2018/9/1
 * @since V1.0
 */
@XmlType(name = "conflict")
public class Conflict {
	@XmlElement
	private Sequence sequence;

	@XmlAttribute(required = true)
	private Type type;
	/**
	 * Refers to the 'key' attribute of a 'reference' element.
	 */
	@XmlAttribute
	private String ref;

	public Sequence getSequence() {
		return sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

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

	public enum Resource {
		EMBL_CDS("EMBL-CDS"),
		EMBL("EMBL");
		private String resource;

		Resource(String resource) {
			this.resource = resource;
		}

		public static Resource forResource(String s) {
			for (Resource resource : Resource.values()) {
				if (resource.getResource().equals(s)) {
					return resource;
				}
			}
			throw new IllegalArgumentException("Invalid Sequence Resource: " + s);
		}

		public String getResource() {
			return resource;
		}
	}

	public enum Type {
		FRAMESHIFT("frameshift"),
		ERRONEOUS_INITIATION("erroneous initiation"),
		ERRONEOUS_TERMINATION("erroneous termination"),
		ERRONEOUS_GENE_MODEL_PREDICTION("erroneous gene model prediction"),
		ERRONEOUS_TRANSLATION("erroneous translation"),
		MISCELLANEOUS_DISCREPANCY("miscellaneous discrepancy");
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
			throw new IllegalArgumentException("Invalid Conflict Type: " + s);
		}

		public String getType() {
			return type;
		}
	}

	public static class Sequence {
		@XmlAttribute
		private Resource resource;
		@XmlAttribute
		private String id;
		@XmlAttribute
		private int version;

		public Resource getResource() {
			return resource;
		}

		public void setResource(Resource resource) {
			this.resource = resource;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public int getVersion() {
			return version;
		}

		public void setVersion(int version) {
			this.version = version;
		}
	}
}
