package us.joederose.ParseAccountingFiles;
import java.util.regex.*;

abstract class ProcessXxx {

	abstract String processContent( String content );
	
	String regexReplace( String searchString, String regex, String replacementText, String replacementDescription ) {
		// Output debugging text:
		ParseAccountingFiles.printDebugText(
			2,
			true,
			replacementDescription,
			"Replacing RegEx:  /" + regex + "/",
			"With:             \"" + replacementText + "\""
		);
		ParseAccountingFiles.printDebugText( 3, "BEFORE" );
		ParseAccountingFiles.printDebugText( 4, searchString );
		// Set up the regular expression:
		Pattern p = Pattern.compile( regex );
		Matcher m = p.matcher( searchString );
		searchString = m.replaceAll( replacementText );
		// Output debugging text:
		ParseAccountingFiles.printDebugText( 3, "AFTER" );
		ParseAccountingFiles.printDebugText( 4, searchString );
		// Return the result:
		return searchString;
	}
	
	String regexFriendlyReplace( String searchString, String regex, String replacementDescription ) {
		// Instantiate FriendlyNames for access to its hash table:
		// (FriendlyNames.java is ignored in the git repository because it contains account-specific information,
		// but FriendlyNames_Example.java has the same structure.)
		FriendlyNames fn = new FriendlyNames();
		// Output debugging text:
		ParseAccountingFiles.printDebugText(
			2,
			true,
			replacementDescription,
			"Replacing RegEx:  /" + regex + "/",
			"With friendly text"
		);
		ParseAccountingFiles.printDebugText( 3, "BEFORE" );
		ParseAccountingFiles.printDebugText( 4, searchString );
		// Set up the regular expression to find the regex in the full content:
		Pattern p = Pattern.compile( regex );
		Matcher m = p.matcher( searchString );
		while ( m.find() == true ) {
			// Set up the regular expression to find the regex in the current match:
			String currentMatch = m.group();
			Matcher m1 = p.matcher( currentMatch );
			// This regex should contain a match group, which it assigns to currentKey:
			String currentKey = m1.replaceAll( "$1" );
			// Get the friendly name for that key from the HashMap in FriendlyNames:
			String currentValue = fn.getFriendlyName( currentKey ) + " (" + currentKey + ")";
			// Replace the current match with the value from FriendlyNames:
			searchString = searchString.replace( currentMatch, currentValue );
		}
		// Output debugging text:
		ParseAccountingFiles.printDebugText( 3, "AFTER" );
		ParseAccountingFiles.printDebugText( 4, searchString );
		// Return the result:
		return searchString;
	}
	
	String addHeader( String content, String header ) {
		ParseAccountingFiles.printDebugText( 2, "Add Header" );
		ParseAccountingFiles.printDebugText( 3, "BEFORE" );
		ParseAccountingFiles.printDebugText( 4, content );
		content = header + "\n" + content;
		ParseAccountingFiles.printDebugText( 3, "AFTER" );
		ParseAccountingFiles.printDebugText( 4, content );
		return content;
	}
	
	String removeBlankLines( String content ) {
		// 
		content = regexReplace( content, "^[ \r\n]+", "", "Remove blank lines at beginning" );
		// 
		content = regexReplace( content, "[ \r\n]+$", "", "Remove blank lines at end" );
		return content;
	}

}
