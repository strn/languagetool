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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * JAXB-based LT configuration file parser
 *
 * @author Zoltán Csala
 *
 * @since 4.1
 */
public class ConfigurationFileParser {

  private List<String> ruleFiles;
  private String morfologikWordFile;
  private JAXBContext jaxbContext;
  private Unmarshaller jaxbUnmarshaller;
  private Ltconfiguration ltConf;


  public ConfigurationFileParser() {
    ruleFiles = new ArrayList<>();
    morfologikWordFile = null;
    try {
      jaxbContext = JAXBContext.newInstance(LTObjectFactory.class);
      jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    } catch (JAXBException je) {
      System.err.println(je.getMessage());
    }
  }

  /**
   * Parses configuration file, adding items as required
   *
   * @param configurationFile
   */
  public void parse(final File configurationFile) {
    try {
      ltConf = (Ltconfiguration) jaxbUnmarshaller.unmarshal(configurationFile);
      // Get additional Morfologik dictionary file if it exists
      if (cfgFileExists(ltConf.getMorfologik().getWordfile())) {
        morfologikWordFile = ltConf.getMorfologik().getWordfile();
      }
      // Check if grammar files are labelled as active, then add them
      for ( RulefileType ruleFile : ltConf.getGrammar().getRulefiles().getRulefile()) {
        if ("yes".equals(ruleFile.getActive()) && cfgFileExists(ruleFile.getValue())) {
          // TODO: Validate grammar file before adding it
          ruleFiles.add(ruleFile.getValue());
          //System.out.println("Added user's rule file " + ruleFile.getValue());
        }
      }
    } catch (JAXBException je) {
      System.err.println(je);
    }
  }

  public String getMorfologikWordFile() {
    return morfologikWordFile;
  }

  public List<String> getRuleFiles() {
    return ruleFiles;
  }

  /**
   * Checks if file exists and is a real file
   *
   * @param filePath
   * @return
   */
  private boolean cfgFileExists(String filePath) {
    File file = new File(filePath);
    return (file.exists() && file.isFile());
  }
}
