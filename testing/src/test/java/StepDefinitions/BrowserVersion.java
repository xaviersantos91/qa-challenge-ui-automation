package StepDefinitions;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BrowserVersion
{

	private String browser_version;

	// Getter for browser_version
	public String getBrowserVersion()
	{
		try
		{
			// Command to query the registry for Edge version
			String command = "reg query \"HKEY_CURRENT_USER\\Software\\Microsoft\\Edge\\BLBeacon\" /v version";

			// Execute the command
			Process process = Runtime.getRuntime().exec(command);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			String version = null;

			// Read the output of the command
			while ((line = reader.readLine()) != null)
			{
				if (line.contains("version"))
				{
					version = line.split("\\s+")[line.split("\\s+").length - 1];
					break;
				}
			}

			reader.close();

			if (version != null)
			{
				browser_version = version;
				System.out.println("Microsoft Edge Version: " + version);
			} else
			{
				System.out.println("Edge version not found in the registry.");
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return browser_version;
	}

}
