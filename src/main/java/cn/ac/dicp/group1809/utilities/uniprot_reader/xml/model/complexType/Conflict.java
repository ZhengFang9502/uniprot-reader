package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private static Logger logger = LoggerFactory.getLogger(Conflict.class);
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

	public enum Resource {
		EMBL_CDS("EMBL-CDS"),
		EMBL("EMBL");
		private String resource;

		Resource(String resource) {
			this.resource = resource;
		}

		public String getResource() {
			return resource;
		}

		public static Resource forResource(String s) {
			for (Resource resource : Resource.values()) {
				if (resource.getResource().equals(s)) {
					return resource;
				}
			}
			logger.error("Invalid sequence resource: " + s);
			throw new IllegalArgumentException("Invalid sequence resource: " + s);
		}
	}

	public enum Type {
		Frameshift("frameshift"),
		ErroneousInitiation("erroneous initiation"),
		ErroneousTermination("erroneous termination"),
		ErroneousGeneModelPrediction("erroneous gene model prediction"),
		ErroneousTranslation("erroneous translation"),
		MiscellaneousDiscrepancy("miscellaneous discrepancy");
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
			logger.error("Invalid conflict type: " + s);
			throw new IllegalArgumentException("Invalid conflict type: " + s);
		}
	}
}
