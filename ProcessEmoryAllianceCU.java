package us.joederose.ParseAccountingFiles;
class ProcessEmoryAllianceCU extends ProcessXxx {
	
	String processContent( String content ) {
		content = regexReplace(
			content,
			"\t",
			",",
			"Change tabs to commas"
		);
		content = removeBlankLines( content );
		return content;
	}

}
