package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.DateAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <pre>
 *      &lt;complexType name="sequenceType">
 *          &lt;simpleContent>
 *              &lt;extension base="xs:string">
 *                  &lt;attribute name="length" type="xs:int" use="required"/>
 *                  &lt;attribute name="mass" type="xs:int" use="required"/>
 *                  &lt;attribute name="checksum" type="xs:string" use="required"/>
 *                  &lt;attribute name="modified" type="xs:date" use="required"/>
 *                  &lt;attribute name="version" type="xs:int" use="required"/>
 *                  &lt;attribute name="precursor" type="xs:boolean" use="optional"/>
 *                  &lt;attribute name="fragment" use="optional">
 *                      &lt;simpleType>
 *                          &lt;restriction base="xs:string">
 *                              &lt;enumeration value="single"/>
 *                              &lt;enumeration value="multiple"/>
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
public class Sequence extends ValueItem {
	private static Logger logger = LoggerFactory.getLogger(Sequence.class);
	@XmlAttribute(required = true)
	private int length;
	@XmlAttribute(required = true)
	private int mass;
	@XmlAttribute(required = true)
	private String chechsum;
	@XmlAttribute(required = true)
	@XmlJavaTypeAdapter(DateAdapter.class)
	private String modified;
	@XmlAttribute(required = true)
	private int version;
	@XmlAttribute(required = true)
	private boolean precursor;
	@XmlAttribute
	private Fragment fragment;

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getMass() {
		return mass;
	}

	public void setMass(int mass) {
		this.mass = mass;
	}

	public String getChechsum() {
		return chechsum;
	}

	public void setChechsum(String chechsum) {
		this.chechsum = chechsum;
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

	public boolean isPrecursor() {
		return precursor;
	}

	public void setPrecursor(boolean precursor) {
		this.precursor = precursor;
	}

	public Fragment getFragment() {
		return fragment;
	}

	public void setFragment(Fragment fragment) {
		this.fragment = fragment;
	}

	public enum Fragment {
		Single("single"),
		Multiple("multiple");

		private String type;

		Fragment(String fragment) {
			this.type = fragment;
		}

		public String getFragment() {
			return type;
		}

		public static Fragment forFragment(String s) {
			for (Fragment fragment : Fragment.values()) {
				if (fragment.getFragment().equals(s)) {
					return fragment;
				}
			}
			logger.error("Invalid fragment: " + s);
			throw new IllegalArgumentException("Invalid fragment: " + s);
		}
	}

}
