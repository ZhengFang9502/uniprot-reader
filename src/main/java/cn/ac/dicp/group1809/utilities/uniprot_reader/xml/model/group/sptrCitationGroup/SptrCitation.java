package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.group.sptrCitationGroup;

import cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.complexType.sourceData.SourceData;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Groups a citation's scope and source descriptions.
 *
 * <pre>
 *      &lt;group name="sptrCitationGroup">
 *          &lt;annotation>
 *              &lt;documentation>Groups a citation's scope and source descriptions. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;sequence>
 *              &lt;element name="scope" type="xs:string" maxOccurs="unbounded">
 *                  &lt;annotation>
 *                      &lt;documentation>Describes the scope of a citation.
 *                     Equivalent to the flat file RP-line. &lt;/documentation>
 *                  &lt;/annotation>
 *              &lt;/element>
 *              &lt;element name="source" type="sourceDataType" minOccurs="0"/>
 *          &lt;/sequence>
 *      &lt;/group>
 * </pre>
 *
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
public class SptrCitation {
	/**
	 * Describes the scope of a citation.
	 * Equivalent to the flat file RP-line.
	 */
	@XmlElement
	private List<String> scope ;
	@XmlElement
	private SourceData source ;

	public List<String> getScope() {
		return scope;
	}

	public void setScope(List<String> scope) {
		this.scope = scope;
	}

	public SourceData getSource() {
		return source;
	}

	public void setSource(SourceData source) {
		this.source = source;
	}
}
