package us.joederose.ParseAccountingFiles;
class ProcessAmericanExpress extends ProcessXxx {
	
	String processContent( String content ) {
		content = addHeader( content, "Date,Reference,Amount,Description,AdditionalInfo" );
		content = removeBlankLines( content );
		return content;
	}

}
