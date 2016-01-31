package us.joederose.ParseAccountingFiles;
import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

class ParseAccountingFiles {
	
	static boolean debugOutput = false;
	
	private final String sourcePath = "C:/Users/Joe DeRose/Desktop";
	private final String targetPath = "C:/Users/Joe DeRose/Documents/Joe's Personal Stuff/Access/Linked Tables";

	public static void main( String[] args ) throws IOException, FileNotFoundException {
		// Iterate through all values in the enum:
		for ( FileNamesEnum fileName : FileNamesEnum.values() ) {
			ParseAccountingFiles.printDebugText( 1, "Analyzing " + fileName );
			ParseAccountingFiles paf = new ParseAccountingFiles();
			Path sourceFile = Paths.get( paf.getSourcePath(), fileName.getSourceFile() );
			// Take action only if a file exists at the download path:
			if ( Files.exists( sourceFile ) ) {
				ParseAccountingFiles.printDebugText( 0, "File found: " + sourceFile );
				// Get the appropriate class for this account from the enum constructor:
				ProcessXxx processingClass = fileName.getProcessingClass();
				// Get content:
				String allLines = paf.getFileContents( sourceFile );
				// Process content:
				allLines = processingClass.processContent( allLines );
				ParseAccountingFiles.printDebugText( 3, "FINAL" );
				ParseAccountingFiles.printDebugText( 4, allLines );
				ParseAccountingFiles.printDebugText( 3 );
				if ( ParseAccountingFiles.debugOutput == false ) {
					// Write the modified content to the target path:
					Path targetFile = Paths.get( paf.getTargetPath(), fileName.getTargetFile() );
					paf.writeTargetFile( targetFile, allLines );
					// Delete the source file:
					paf.deleteSourceFile( sourceFile );
					// Display confirmation:
					System.out.println( "\"" + fileName.getSourceFile() + "\" has been processed." );
				}
			} else {
				ParseAccountingFiles.printDebugText( 0, "File NOT found: " + sourceFile );
			}
		}
		ParseAccountingFiles.printDebugText( 1 );
		
		// Example for friendly names. To be actualized in ProcessXxx
		FriendlyNames fn = new FriendlyNames();
		System.out.println( fn.getFriendlyName( "5588" ) );
	}
	
	public String getSourcePath() {
		return this.sourcePath;
	}
	
	public String getTargetPath() {
		return this.targetPath;
	}
	
	private String getFileContents( Path sourceFile ) throws IOException {
		String output = "";
		List<String> allLinesList = Files.readAllLines( sourceFile, Charset.forName( "UTF-8" ) );
		for ( String line : allLinesList ) {
			output += line + "\r\n";
		}
		return output;
	}
	
	private void writeTargetFile( Path targetFile, String content ) throws IOException, FileNotFoundException {
		Files.deleteIfExists( targetFile );
		try ( PrintWriter pw = new PrintWriter( targetFile.toFile() ) ) {
			pw.write( content + "\r\n" );
		}
	}
	
	private void deleteSourceFile( Path sourceFile ) throws IOException {
		Files.deleteIfExists( sourceFile );
	}
	
	// Standard print of debugging text (passes false to convertLineFeedChars):
	static void printDebugText( int headingLevel, String... debugLines ) {
		printDebugText( headingLevel, false, debugLines );
	}
	// Print debugging text with option to convert line feed characters so they are human-readable:
	static void printDebugText( int headingLevel, boolean convertLineFeedChars, String... debugLines ) {
		if ( debugOutput == true ) {
			switch ( headingLevel ) {
				case 1:
					System.out.println();
					System.out.println( "██████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████" );
					break;
				case 2:
					System.out.println( "▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀" );
					break;
				case 3:
					System.out.println( "▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔" );
					break;
				case 4:
					System.out.println( "▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁" );
					break;
			}
			for ( String debugLine : debugLines ) {
				if ( convertLineFeedChars == true ) {
					debugLine = debugLine.replaceAll( "\r", "\\\\r" );
					debugLine = debugLine.replaceAll( "\n", "\\\\n" );
				}
				System.out.println( debugLine );
			}
		}
	}

}
