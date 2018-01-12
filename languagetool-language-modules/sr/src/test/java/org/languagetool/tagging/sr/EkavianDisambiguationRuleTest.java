/* LanguageTool, a natural language style checker
 * Copyright (C) 2005 Daniel Naber (http://www.danielnaber.de)
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
package org.languagetool.tagging.sr;

import org.junit.Before;
import org.junit.Test;
import org.languagetool.TestTools;
import org.languagetool.language.SerbianSerbian;
import org.languagetool.tagging.disambiguation.MultiWordChunker;
import org.languagetool.tagging.disambiguation.rules.DisambiguationRuleTest;
import org.languagetool.tokenizers.SRXSentenceTokenizer;
import org.languagetool.tokenizers.SentenceTokenizer;
import org.languagetool.tokenizers.WordTokenizer;

import java.io.IOException;


public class EkavianDisambiguationRuleTest extends DisambiguationRuleTest {

  private EkavianTagger tagger;
  private WordTokenizer tokenizer;
  private SentenceTokenizer sentenceTokenizer;
  private MultiWordChunker disambiguator;

  @Before
  public void setUp() {
    tagger = new EkavianTagger();
    tokenizer = new WordTokenizer();
    sentenceTokenizer = new SRXSentenceTokenizer( new SerbianSerbian() );
    disambiguator = new MultiWordChunker("/sr/multiwords.txt");
  }

  @Test
  public void testChunker() throws IOException {
    //TestTools.myAssert("Нови Сад, мој град.", "", tokenizer, sentenceTokenizer, tagger, disambiguator);
    //TestTools.myAssert("Остао сам у Новом Саду.", "", tokenizer, sentenceTokenizer, tagger, disambiguator);
  }
}
