package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * <pre>
 *      &lt;complexType name="evidencedStringType">
 *          &lt;simpleContent>
 *              &lt;extension base="xs:string">
 *                  &lt;attribute name="evidence" type="intListType" use="optional"/>
 *                  &lt;attribute name="status" use="optional">
 *                      &lt;simpleType>
 *                          &lt;restriction base="xs:string">
 *                              &lt;enumeration value="by similarity"/>
 *                              &lt;enumeration value="probable"/>
 *                              &lt;enumeration value="potential"/>
 *                          &lt;/restriction>
 *                      &lt;/simpleType>
 *                  &lt;/attribute>
 *              &lt;/extension>
 *          &lt;/simpleContent>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/26
 * @since V1.0
 */
public class EvidencedString extends ValueItem {
	private static Logger logger = LoggerFactory.getLogger(EvidencedString.class);
	@XmlAttribute
	@XmlJavaTypeAdapter(IntListAdapter.class)
	private List<Integer> evidence;
	@XmlAttribute
	private Status status;

	public List<Integer> getEvidence() {
		return evidence;
	}

	public void setEvidence(List<Integer> evidence) {
		this.evidence = evidence;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
			logger.error("Invalid evidenced string status: " + s);
			throw new IllegalArgumentException("Invalid evidenced string status: " + s);
		}
	}
}
