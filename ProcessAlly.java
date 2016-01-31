package us.joederose.ParseAccountingFiles;
class ProcessAlly extends ProcessXxx {
	
	String processContent( String content ) {
		content = regexFriendlyReplace(
			content,
			"LEWIS J DEROSE \\(STATE FARM BANK FSB Checking XXXXXX(\\d{4})\\)",
			"Replace account identifier with friendly reference"
		);
		content = removeBlankLines( content );
		return content;
	}
	
}
