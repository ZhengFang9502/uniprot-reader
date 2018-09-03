package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.group.proteinNameGroup.ProteinNameGroup;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Describes the names for the protein and parts thereof.
 * Equivalent to the flat file DE-line.
 *
 * <pre>
 *      &lt;complexType name="proteinType">
 *          &lt;annotation>
 *              &lt;documentation>Describes the names for the protein and parts thereof.
 *             Equivalent to the flat file DE-line. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;sequence>
 *              &lt;group ref="proteinNameGroup"/>
 *              &lt;element name="domain" minOccurs="0" maxOccurs="unbounded">
 *                  &lt;annotation>
 *                      &lt;documentation>Describes names of "domains".
 *                     Equivalent to the flat file DE-line Includes: section. &lt;/documentation>
 *                  &lt;/annotation>
 *                  &lt;complexType>
 *                      &lt;group ref="proteinNameGroup"/>
 *                  &lt;/complexType>
 *              &lt;/element>
 *              &lt;element name="component" minOccurs="0" maxOccurs="unbounded">
 *                  &lt;annotation>
 *                      &lt;documentation>Describes names of processed products.
 *                     Equivalent to the flat file DE-line Contains: section. &lt;/documentation>
 *                  &lt;/annotation>
 *                  &lt;complexType>
 *                      &lt;group ref="proteinNameGroup"/>
 *                  &lt;/complexType>
 *              &lt;/element>
 *          &lt;/sequence>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/26
 * @since V1.0
 */
public class Protein {
	@XmlElement
	private ProteinNameGroup proteinNameGroup;
	/**
	 * Describes names of "domains".
	 * Equivalent to the flat file DE-line Includes: section.
	 */
	@XmlElement
	private List<ProteinNameGroup> domain;
	/**
	 * Describes names of processed products.
	 * Equivalent to the flat file DE-line Contains: section.
	 */
	@XmlElement
	private List<ProteinNameGroup> component;

	public ProteinNameGroup getProteinNameGroup() {
		return proteinNameGroup;
	}

	public void setProteinNameGroup(ProteinNameGroup proteinNameGroup) {
		this.proteinNameGroup = proteinNameGroup;
	}

	public List<ProteinNameGroup> getDomain() {
		return domain;
	}

	public void setDomain(List<ProteinNameGroup> domain) {
		this.domain = domain;
	}

	public List<ProteinNameGroup> getComponent() {
		return component;
	}

	public void setComponent(List<ProteinNameGroup> component) {
		this.component = component;
	}
}
