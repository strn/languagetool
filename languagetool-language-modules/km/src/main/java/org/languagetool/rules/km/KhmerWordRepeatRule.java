/* LanguageTool, a natural language style checker 
 * Copyright (C) 2011 Daniel Naber (http://www.danielnaber.de)
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
package org.languagetool.rules.km;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.languagetool.AnalyzedSentence;
import org.languagetool.AnalyzedTokenReadings;
import org.languagetool.Language;
import org.languagetool.rules.Category;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;

/**
 * Check if a word is repeated in Khmer, e.g. the equivalent of "the the".
 *   
 * @author Daniel Naber and Lee Nakamura
 */
public class KhmerWordRepeatRule extends Rule {

  public KhmerWordRepeatRule(final ResourceBundle messages, final Language language) {
    super(messages);
    super.setCategory(new Category(messages.getString("category_misc")));
  }

  /**
   * @deprecated will be made non-public (deprecated since 3.0)
   */
  public boolean ignore(final AnalyzedSentence sentence, final AnalyzedTokenReadings[] tokensWithWhiteSpace, final int position) {
    final int origPos = sentence.getOriginalPosition(position);
    if (position >= 1 && "\u0020".equals(tokensWithWhiteSpace[origPos-1].getToken())) {
      return true;
    }
    return false;
  }

  @Override
  public String getId() {
    return "KM_WORD_REPEAT_RULE";
  }

  @Override
  public String getDescription() {
    return messages.getString("desc_repetition");
  }

  @Override
  public RuleMatch[] match(final AnalyzedSentence sentence) {
    final List<RuleMatch> ruleMatches = new ArrayList<>();
    final AnalyzedTokenReadings[] tokens = sentence.getTokensWithoutWhitespace();
    final AnalyzedTokenReadings[] tokensWithWS = sentence.getTokens();

    String prevToken = "";
    // we start from token 1, token no. 0 is guaranteed to be SENT_START 
    for (int i = 1; i < tokens.length; i++) {
      final String token = tokens[i].getToken();
      if (isWord(token) && prevToken.equalsIgnoreCase(token) && !ignore(sentence, tokensWithWS, i)) {
        final String msg = messages.getString("repetition");
        final int prevPos = tokens[i - 1].getStartPos();
        final int pos = tokens[i].getStartPos();
        final RuleMatch ruleMatch = new RuleMatch(this, prevPos, pos+prevToken.length(), msg,
                messages.getString("desc_repetition_short"));
        final List<String> replacements = new ArrayList<>();
        replacements.add(prevToken + " " + token); // case 1: replace zero-width space w/ real space 
        replacements.add(prevToken);               // case 2: remove repeated word - same as original suggestion 
        replacements.add(prevToken + "ៗ");        // case 3: same as case 2, just add "repetition character"
        ruleMatch.setSuggestedReplacements(replacements);
        ruleMatches.add(ruleMatch);
      }
      prevToken = token;
    }

    return toRuleMatchArray(ruleMatches);
  }

  // avoid "..." etc. to be matched:
  private boolean isWord(String token) {
    boolean isWord = true;
    if (token.length() == 1) {
      final char c = token.charAt(0);
      if (!Character.isLetter(c)) {
        isWord = false;
      }
    }
    return isWord;
  }

  @Override
  public void reset() {
    // nothing
  }

}