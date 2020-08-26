package cn.ac.dicp.group1809.utilities.uniprot_reader.fasta;

import cn.ac.dicp.group1809.utilities.proteomics_framework.model.definition.proteomics.Protein;
import org.slf4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author ZhengFang 2018/9/3
 * @since V1.0
 */
public class FastaParser {
	private static final Pattern PROPERTY_PATTERN = Pattern.compile("(OS|OX|GN|PE|SV)=([\\w\\-]+ )*[\\w\\-]+");
	private static final Logger LOGGER = getLogger(FastaParser.class);

	public FastaParser() {

	}

	public HashMap<String, Protein> read(String path) throws IOException {
		LOGGER.debug("Reading fasta file: {}.", path);
		if (!path.endsWith(".fasta")) {
			throw new IllegalArgumentException("Invalid Fasta File Format: " + path);
		}
		HashMap<String, Protein> proteinDatabase = new HashMap<>();
		File file = new File(path);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String s;
		StringBuilder content = new StringBuilder();
		Protein protein = new Protein();
		while ((s = bufferedReader.readLine()) != null) {
			if (s.startsWith(">")) {
				if (protein.getAccession() != null) {
					protein.setSequence(content.toString());
					addProtein(proteinDatabase, protein);
				}
				protein = new Protein();
				String[] split = s.split("\\|");
				String firstPart = split[0].replace(">", "");
				if (firstPart.startsWith("CON_")) {
					protein.setAccession(firstPart);
					StringBuilder entryName = new StringBuilder();
					entryName.append(split[1]);
					for (int i = 2; i < split.length; i++) {
						entryName.append("|").append(split[i]);
					}
					protein.setEntryName(entryName.toString());
					content = new StringBuilder();
				} else {
					protein.setDatabase(firstPart);
					protein.setAccession(split[1]);
					setProteinAttributes(protein, split[2]);
					content = new StringBuilder();
				}
			} else {
				String lineSeperator = "\r\n";
				s = s.replaceAll(lineSeperator, "");
				content.append(s);
			}
		}
		protein.setSequence(content.toString());
		addProtein(proteinDatabase, protein);
		LOGGER.debug("Finish reading fasta file: {}, and get {} proteins.", path, proteinDatabase.size());
		return proteinDatabase;
	}

	private void addProtein(HashMap<String, Protein> proteinDatabase, Protein protein) {
		String accession = protein.getAccession();
		if (proteinDatabase.containsKey(accession)) {
			LOGGER.warn("Duplicate Accession Number in the Fasta File: {}", accession);
//			throw new IllegalArgumentException("Duplicate Accession Number in the Fasta File: " + accession);
		}
		proteinDatabase.put(accession, protein);
	}

	public void setProteinAttributes(Protein protein, String description) {
		Matcher matcher = PROPERTY_PATTERN.matcher(description);
		while (matcher.find()) {
			String group = matcher.group();
			String end = "";
			int length = group.length();
			if (group.endsWith(" OX") | group.endsWith(" GN") | group.endsWith(" PE") | group.endsWith(" SV")) {
				end = group.substring(length - 2, length);
			}
			group = group.replace(end, "").trim();
			String[] split = group.split("=");
			switch (split[0]) {
				case "OS":
					protein.setOrganismName(split[1]);
					break;
				case "OX":
					protein.setOrganismIdentifier(split[1]);
					break;
				case "GN":
					protein.setGeneName(split[1]);
					break;
				case "PE":
					protein.setProteinExistence(Integer.valueOf(split[1]));
					break;
				case "SV":
					protein.setSequenceVersion(Integer.valueOf(split[1]));
					break;
				default:
					throw new IllegalArgumentException("Invalid Description Property: " + split[0]);
			}
			description = matcher.replaceFirst(end);
			matcher = PROPERTY_PATTERN.matcher(description);
		}
		if (protein.getAccession().startsWith("CON_")){
			protein.setEntryName(description);
		}else {
			String[] split = description.split(" ");
			protein.setEntryName(split[0].trim());
			protein.setProteinName(description.replace(split[0], "").trim());
		}
	}

	public void write(Map<String, Protein> proteinMap, String outputPath) throws IOException {
		LOGGER.debug("Try to write fasta file: {}.", outputPath);
		File file = new File(outputPath);
		FileWriter writer = new FileWriter(file);
		StringBuilder stringBuilder = new StringBuilder();
		for (String accession : proteinMap.keySet()) {
			Protein protein = proteinMap.get(accession);
			stringBuilder.append(protein).append(System.lineSeparator());
		}
		writer.write(stringBuilder.toString());
		writer.close();
		LOGGER.debug("Finish writing {} proteins into the fasta file: {}.", proteinMap.size(), outputPath);
	}
}
