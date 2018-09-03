package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Describes the authors of a citation when these are represented by a consortium.
 * Equivalent to the flat file RG-line.
 *
 * <pre>
 * 	  &lt;complexType name="consortiumType">
 *          &lt;annotation>
 *              &lt;documentation>Describes the authors of a citation when these are represented by a consortium.
 *             Equivalent to the flat file RG-line. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;attribute name="name" type="xs:string" use="required"/>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
public class Consortium implements NameInf {
	@XmlAttribute(required = true)
	private String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}
