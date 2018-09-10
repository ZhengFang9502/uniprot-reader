package cn.ac.dicp.group1809.utilities.uniprot_reader.fasta;

import cn.ac.dicp.group1809.utilities.proteomics_framework.model.definition.proteomics.Protein;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ZhengFang 2018/9/3
 * @since V1.0
 */
public class FastaParser {
	private Logger logger = LoggerFactory.getLogger(FastaParser.class);
	private final Pattern propertyPattern = Pattern.compile("(OS|OX|GN|PE|SV)=([\\w\\-]+ )*[\\w\\-]+");
	private static final FastaParser instance = new FastaParser();

	public static FastaParser getInstance() {
		return instance;
	}

	private FastaParser() {
	}

	public HashMap<String, Protein> read(String path) throws IOException {
		logger.info("Try to read fasta file: " + path);
		if (!path.endsWith(".fasta")) {
			logger.error("Failed to read the file, cause not dasta format: " + path);
			throw new IllegalArgumentException("Invalid file format!");
		}
		HashMap<String, Protein> proteinDatabase = new HashMap<>();
		File file = new File(path);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
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
				protein.setDatabase(split[0].replace(">", ""));
				protein.setAccession(split[1]);
				setProteinAttributes(protein, split[2]);
				content = new StringBuilder();
			} else {
				String lineSeperator = "\r\n";
				s = s.replaceAll(lineSeperator, "");
				content.append(s);
			}
		}
		protein.setSequence(content.toString());
		addProtein(proteinDatabase, protein);
		logger.info("Finish reading fasta file: " + path);
		return proteinDatabase;
	}

	private void addProtein(HashMap<String, Protein> proteinDatabase, Protein protein) {
		String accession = protein.getAccession();
		if (proteinDatabase.containsKey(accession)) {
			logger.error("Failed to add protein to the protein database, cause duplicate accession number happens in the fasta file");
			throw new IllegalArgumentException("Duplicate protein accession is forbidden!");
		}
		proteinDatabase.put(accession, protein);
	}

	private void setProteinAttributes(Protein protein, String description) {
		Matcher matcher = propertyPattern.matcher(description);
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
					logger.error("Invalid description property: " + split[0]);
					throw new IllegalArgumentException("Invalid description property: " + split[0]);
			}
			description = matcher.replaceFirst(end);
			matcher = propertyPattern.matcher(description);
		}
		String[] split = description.split(" ");
		protein.setEntryName(split[0].trim());
		protein.setProteinName(description.replace(split[0], "").trim());
	}

	public void write(Map<String, Protein> proteinMap, String outputPath) throws IOException {
		logger.info("Try to write fasta file: " + outputPath);
		File file = new File(outputPath);
		FileWriter writer = new FileWriter(file);
		StringBuilder stringBuilder = new StringBuilder();
		TreeSet<String> accessions = new TreeSet<>(proteinMap.keySet());
		for (String accession : accessions) {
			Protein protein = proteinMap.get(accession);
			stringBuilder.append(protein).append(System.lineSeparator());
		}
		writer.write(stringBuilder.toString());
		writer.close();
	}
}
