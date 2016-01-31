package us.joederose.ParseAccountingFiles;
enum FileNamesEnum {
	ALLY( "Ally.txt", "Finance Import Ally.txt", new ProcessAlly() ),
	AMERICAN_EXPRESS( "AmEx.txt", "Finance Import AmEx.txt", new ProcessAmericanExpress() ),
	BANK_OF_AMERICA( "BoA.txt", "Finance Import BoA.txt", new ProcessBankOfAmerica() ),
	STATE_FARM( "State Farm.txt", "Finance Import State Farm.txt", new ProcessStateFarm() );
	// ACCOUNT_DESCRIPTION( Download Location, Location for Modified Content, Class Containing Account-Specific RegEx ) 
	
	FileNamesEnum( String sourceFile, String targetFile, ProcessXxx processingClass ) {
		this.sourceFile = sourceFile;
		this.targetFile = targetFile;
		this.processingClass = processingClass;
	}
	
	private String sourceFile;
	private String targetFile;
	private ProcessXxx processingClass;
	
	public String getSourceFile() {
		return this.sourceFile;
	}
	
	public String getTargetFile() {
		return this.targetFile;
	}
	
	public ProcessXxx getProcessingClass() {
		return this.processingClass;
	}
	
}
