package us.joederose.ParseAccountingFiles;
class ProcessBankOfAmerica extends ProcessXxx {
	
	String processContent( String content ) {
		content = removeBlankLines( content );
		return content;
	}
	
}
