package cn.ac.dicp.group1809.utilities.uniprot_reader.xml.model.group.bpcCommentGroup;

import javax.xml.bind.annotation.XmlElement;

/**
 * Describes different types of biophysicochemical properties.
 *
 * <pre>
 *      &lt;group name="bpcCommentGroup">
 *          &lt;annotation>
 *              &lt;documentation>Describes different types of biophysicochemical properties. &lt;/documentation>
 *          &lt;/annotation>
 *          &lt;sequence>
 *              &lt;element name="absorption" minOccurs="0">
 *                  &lt;complexType>
 *                      &lt;sequence>
 *                          &lt;element name="max" type="evidencedStringType" minOccurs="0"/>
 *                          &lt;element name="text" type="evidencedStringType" minOccurs="0" maxOccurs="unbounded"/>
 *                      &lt;/sequence>
 *                  &lt;/complexType>
 *              &lt;/element>
 *              &lt;element name="kinetics" minOccurs="0">
 *                  &lt;complexType>
 *                      &lt;sequence>
 *                          &lt;element name="KM" type="evidencedStringType" minOccurs="0" maxOccurs="unbounded"/>
 *                          &lt;element name="Vmax" type="evidencedStringType" minOccurs="0" maxOccurs="unbounded"/>
 *                          &lt;element name="text" type="evidencedStringType" minOccurs="0" maxOccurs="unbounded"/>
 *                      &lt;/sequence>
 *                  &lt;/complexType>
 *              &lt;/element>
 *              &lt;element name="phDependence" minOccurs="0">
 *                  &lt;complexType>
 *                      &lt;sequence>
 *                          &lt;element name="text" type="evidencedStringType" maxOccurs="unbounded"/>
 *                      &lt;/sequence>
 *                  &lt;/complexType>
 *              &lt;/element>
 *              &lt;element name="redoxPotential" minOccurs="0">
 *                  &lt;complexType>
 *                      &lt;sequence>
 *                          &lt;element name="text" type="evidencedStringType" maxOccurs="unbounded"/>
 *                      &lt;/sequence>
 *                  &lt;/complexType>
 *              &lt;/element>
 *              &lt;element name="temperatureDependence" minOccurs="0">
 *                  &lt;complexType>
 *                      &lt;sequence>
 *                          &lt;element name="text" type="evidencedStringType" maxOccurs="unbounded"/>
 *                      &lt;/sequence>
 *                  &lt;/complexType>
 *              &lt;/element>
 *          &lt;/sequence>
 *      &lt;/group>
 * </pre>
 *
 * @author ZhengFang 2018/5/28
 * @since V1.0
 */
public class BpcComment {
	@XmlElement
	private Absorption absorption;
	@XmlElement
	private Kinetics kinetics;
	@XmlElement
	private PhDependence phDependence;
	@XmlElement
	private RedoxPotential redoxPotential;
	@XmlElement
	private TemperatureDependence temperatureDependence;

	public Absorption getAbsorption() {
		return absorption;
	}

	public void setAbsorption(Absorption absorption) {
		this.absorption = absorption;
	}

	public Kinetics getKinetics() {
		return kinetics;
	}

	public void setKinetics(Kinetics kinetics) {
		this.kinetics = kinetics;
	}

	public PhDependence getPhDependence() {
		return phDependence;
	}

	public void setPhDependence(PhDependence phDependence) {
		this.phDependence = phDependence;
	}

	public RedoxPotential getRedoxPotential() {
		return redoxPotential;
	}

	public void setRedoxPotential(RedoxPotential redoxPotential) {
		this.redoxPotential = redoxPotential;
	}

	public TemperatureDependence getTemperatureDependence() {
		return temperatureDependence;
	}

	public void setTemperatureDependence(TemperatureDependence temperatureDependence) {
		this.temperatureDependence = temperatureDependence;
	}
}
