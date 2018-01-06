/* LanguageTool, a natural language style checker
 * Copyright (C) 2017 Daniel Naber (http://www.danielnaber.de)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.configuration;

import org.languagetool.configuration.macosx.MacOSXConfigurationDirectory;
import org.languagetool.configuration.unix.UNIXConfigurationDirectory;
import org.languagetool.configuration.windows.WindowsConfigurationDirectory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zoltán Csala
 *
 * @since 4.1
 */
public class LocalConfiguration {

  private final List<String> dirList = new ArrayList<>();
  private List<String> configFilesList;
  private List<String> ruleFiles;
  private String morfologikWordFile;
  private ConfigurationDirectory configDir;
  private String languageCode;
  private ConfigurationFileParser cfParser;


  public LocalConfiguration(final String languageCode) {

    this.languageCode = languageCode;
    morfologikWordFile = null;
    configFilesList = null;
    ruleFiles = null;
    cfParser = new ConfigurationFileParser();
    String osName = System.getProperty("os.name");

    if (osName.contains("Linux") || osName.contains("SunOS") || osName.contains("FreeBSD")) {
      // Do we need special case for Android?
      configDir = new UNIXConfigurationDirectory();
    } else if (osName.contains("Windows")) {
      configDir = new WindowsConfigurationDirectory();
    } else if (osName.contains("Mac OS")) {
        configDir = new MacOSXConfigurationDirectory();
    } else {
      // Could not determine OS, add dummy directory
      dirList.add("dummy");
      System.err.println("Unsupported operating system: " + osName);
      return;
    }
    dirList.addAll( configDir.getConfigurationDirectories() );
  }

  /**
   *
   * @return List of local configuration files
   */
  public List<String> getConfigFilesList() {
    if (configFilesList == null) {
      configFilesList = new ArrayList<>();
      String filePath;
      for (final String directory: dirList) {
        filePath = String.format("%s%sconfiguration-%s.xml", directory,
                File.separator, languageCode);
        configFilesList.add(filePath);
      }
    }
    return configFilesList;
  }

  public List<String> getRuleFileNames() {
    if (areNameListsEmpty()) {
      parseConfigurationFiles();
    }
    return ruleFiles;
  }

  public String getMorfologikWordFile() {
    if (areNameListsEmpty()) {
      parseConfigurationFiles();
    }
    return morfologikWordFile;
  }

  private void parseConfigurationFiles() {
    getConfigFilesList();
    for (final String filePath: configFilesList) {
      File configFile = new File( filePath );
      if (configFile.exists() && configFile.isFile()) {
        cfParser.parse(configFile);
      }
    }
    ruleFiles = cfParser.getRuleFiles();
    morfologikWordFile = cfParser.getMorfologikWordFile();
  }

  private boolean areNameListsEmpty() {
    return (ruleFiles == null || morfologikWordFile == null);
  }
}
