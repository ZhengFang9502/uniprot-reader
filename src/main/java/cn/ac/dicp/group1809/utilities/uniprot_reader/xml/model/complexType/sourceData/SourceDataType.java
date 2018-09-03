package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.sourceData;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.adapter.IntListAdapter;
import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.ValueItem;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * <pre>
 *      &lt;complexType name="sourceDataType">
 *          &lt;annotation>
 *              &lt;documentation>Describes the source of the sequence according to the citation.
 *             Equivalent to the flat file RC-line. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;choice maxOccurs="unbounded">
 *              &lt;element name="strain">
 *                  &lt;complexType>
 *                      &lt;simpleContent>
 *                          &lt;extension base="xs:string">
 *                              &lt;attribute name="evidence" type="intListType" use="optional"/>
 *                          &lt;/extension>
 *                      &lt;/simpleContent>
 *                  &lt;/complexType>
 *              &lt;/element>
 *              &lt;element name="plasmid">
 *                  &lt;complexType>
 *                      &lt;simpleContent>
 *                          &lt;extension base="xs:string">
 *                              &lt;attribute name="evidence" type="intListType" use="optional"/>
 *                          &lt;/extension>
 *                      &lt;/simpleContent>
 *                  &lt;/complexType>
 *              &lt;/element>
 *              &lt;element name="transposon">
 *                  &lt;complexType>
 *                      &lt;simpleContent>
 *                          &lt;extension base="xs:string">
 *                              &lt;attribute name="evidence" type="intListType" use="optional"/>
 *                          &lt;/extension>
 *                      &lt;/simpleContent>
 *                  &lt;/complexType>
 *              &lt;/element>
 *              &lt;element name="tissue">
 *                  &lt;complexType>
 *                      &lt;simpleContent>
 *                          &lt;extension base="xs:string">
 *                              &lt;attribute name="evidence" type="intListType" use="optional"/>
 *                          &lt;/extension>
 *                      &lt;/simpleContent>
 *                  &lt;/complexType>
 *              &lt;/element>
 *          &lt;/choice>
 *      &lt;/complexType>
 * </pre>
 *
 * @author ZhengFang 2018/5/29
 * @since V1.0
 */
public abstract class SourceDataType extends ValueItem {
	@XmlAttribute
	@XmlJavaTypeAdapter(IntListAdapter.class)
	private List<Integer> evidence;

	public List<Integer> getEvidence() {
		return evidence;
	}

	public void setEvidence(List<Integer> evidence) {
		this.evidence = evidence;
	}
}
