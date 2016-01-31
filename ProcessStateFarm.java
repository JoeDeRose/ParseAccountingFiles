package us.joederose.ParseAccountingFiles;
class ProcessStateFarm extends ProcessXxx {
	
	String processContent( String content ) {
		content = regexReplace(
			content,
			"(?i)<Date>,<CheckNum>,<Description>,<Withdrawal Amount>,<Deposit Amount>,<Additional Info>",
			"Date,CheckNum,Description,WithdrawalAmount,DepositAmount,AdditionalInfo",
			"Replace header"
		);
		content = regexFriendlyReplace(
			content,
			"LEWIS JOSEPH DEROS ACCOUNT # XXXXXXXXX(\\d{4}) AT EXT BANK ABA # \\w{9}",
			"Replace account identifier with friendly reference"
		);
		content = regexReplace(
			content,
			",\r\n",
			",---\r\n",
			"Put hyphens in AdditionalInfo field when empty to prevent \"#Type!\" error in Microsoft Access"
		);
		content = removeBlankLines( content );
		return content;
	}

}
