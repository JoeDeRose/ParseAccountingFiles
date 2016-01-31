// This file has the same structure as FriendlyNames.java,
// which is ignored in the git repository because it contains account-specific information.
package us.joederose.ParseAccountingFiles;
import java.util.HashMap;

class FriendlyNames_Example {

	private HashMap<String, String> friendlyName = new HashMap<>();
	
	FriendlyNames_Example() {
		friendlyName.put( "0001", "Account1" );
		friendlyName.put( "0002", "Account2" );
		friendlyName.put( "0003", "Account3" );
	}
	
	String getFriendlyName( String keyValue ) {
		return friendlyName.get( keyValue );
	}
	
}
