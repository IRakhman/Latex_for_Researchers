package tex61;

import java.util.ArrayList;

import static tex61.FormatException.error;

/** An object that receives a sequence of words of text and formats
 *  the words into filled and justified text lines that are sent to a receiver.
 *  @author
 */
class LineAssembler {
    private ArrayList<String> _curList;
    
    private StringBuffer _curWord; 
    private int _parindent;
    private int _parskip;
    private int _indent;
    private int _textHeight;
    private int _textWidth;
    private boolean _fillMode, _justMode, lastLine, firstLine;
    
    /** A new, empty line assembler with default settings of all
     *  parameters, sending finished lines to PAGES. */
    LineAssembler(PageAssembler pages) {
        _pages = pages;
        pages.setTextHeight(Defaults.TEXT_HEIGHT);
        this.setDefaults();

        _curWord = new StringBuffer();
        _curList = new ArrayList<String>();
        

    }
    /** Finishes the current line, with LASTLINE determining if LASTLINE,
     *  then the current word is added, the line with single spaces, and the line is added
     */
    
    
    ArrayList<String> getCurList() {
        return _curList;
    }
    
    void finishLine(boolean lastLine) {
    	this.finishWord();
    	if (lastLine || !_fillMode) {
    		setJustify(false);//SHOULD PROCESS JUSTIFICATION
    		addLine(null);//FIX the argument
    		if (lastLine) {
    			firstLine = true;
    		}
    		
    	}
    }

    /** Add TEXT to the word currently being built. */
    void addText(String text) {
        //if (_out._endNoteMode) {
    		//_endNote += text;
    	//} else {
    	//_curWord += text;
    	//_curWord.append(text);
    	_curWord.append(text);
    }
    
    void setDefaults() {
        _parindent = Defaults.PARAGRAPH_INDENTATION;
        _parskip = Defaults.PARAGRAPH_SKIP;
        _indent = Defaults.INDENTATION;
        _textWidth = Defaults.TEXT_WIDTH;
        _textHeight = Defaults.TEXT_HEIGHT;
    }        
    
    /** Finish the current word, if any, and add to words being accumulated. */
    void finishWord() {
        if (_curWord.length() > 0) {
            this.addWord(_curWord.toString());
            _curWord = null;
        }
    }

    /** Add WORD to the formatted text. */
    void addWord(String _curWord2) {
        _curList.add(_curWord2.toString());
    }

    /** Add LINE to our output, with no preceding paragraph skip.  There must
     *  not be an unfinished line pending. */
    void addLine(String line) {
        
    }

    /** Set the current indentation to VAL. VAL >= 0. */
    void setIndentation(int val) {
        if (val >= 0) {
            _indent = val;
        } else {
            _indent = Defaults.INDENTATION;
        }
    }

    /** Set the current paragraph indentation to VAL. VAL >= 0. */
    void setParIndentation(int val) {
        if (val >= 0) {
            _parindent = val;
        } else {
            _parindent = Defaults.PARAGRAPH_INDENTATION;
        }
    }

    /** Set the text width to VAL, where VAL >= 0. */
    void setTextWidth(int val) {
        if (val >= 0) {
            _textWidth = val;
        } else {
            _textWidth = Defaults.TEXT_WIDTH;
        }
    }

    /** Iff ON, set fill mode. */
    void setFill(boolean on) {
        _fillMode = on;
    }

    /** Iff ON, set justify mode (which is active only when filling is
     *  also on). */
    void setJustify(boolean on) {
        if (_fillMode) {
            _justMode = on;
        } else {
            _justMode = false;
        }
    }

    /** Set paragraph skip to VAL.  VAL >= 0. */
    void setParSkip(int val) {
        if (val >= 0) {
            _parskip = val;
        } else {
            _parskip = Defaults.PARAGRAPH_SKIP;
        }
    }

    /** Set page height to VAL > 0. */
    void setTextHeight(int val) {
        if (val >= 0) {
            _textHeight = val;
        } else {
            _textHeight = Defaults.TEXT_HEIGHT;
        }
    }

    /** Process the end of the current input line.  No effect if
     *  current line accumulator is empty or in fill mode.  Otherwise,
     *  adds a new complete line to the finished line queue and clears
     *  the line accumulator. */
    void newLine() {
        if ((_curList.size() != 0) || (_fillMode != true)) {
            //process the end of the current input line
            
        }
    }

    /** If there is a current unfinished paragraph pending, close it
     *  out and start a new one. */
    void endParagraph() {
        // FIXME
    }

    /** Transfer contents of _words to _pages, adding INDENT characters of
     *  indentation, and a total of SPACES spaces between words, evenly
     *  distributed.  Assumes _words is not empty.  Clears _words and _chars. */
    private void emitLine(int indent, int spaces) {
        // Calls addLine
    }

    /** If the line accumulator is non-empty, justify its current
     *  contents, if needed, add a new complete line to _pages,
     *  and clear the line accumulator. LASTLINE indicates the last line
     *  of a paragraph. */
    private void outputLine(boolean lastLine) {
        if (lastLine || !_fillon) {
            String endline = "";
            int temp = _indent;
            while (temp != 0) {
                endline += " ";
            }
            for (int i = 0; i<_line.size(); i++) {
                if (i != _line.size() - 1) {
                    endline += _line.get(i) + " ";
                } else {
                    endline += _line.get(i);
                }
            }
            addLine(endline);
            _line.clear();
            
        } else {
            emitLine(_indent, _textWidth - numCharinLine(_line));
        }
    }

    /** Destination given in constructor for formatted lines. */
    private final PageAssembler _pages;
    
    
    //THE REST IS UNDER BIG Question
    public int numCharinLine(ArrayList<String> line) {
        int charCount = 0;
        for (String x: line) {
            charCount += x.length();
        }
        return charCount;
    }

    /** Destination given in constructor for formatted lines. */
    private final PageAssembler _pages;
    
    private String _word = "";
    
    private String _finishedLine;
    
    private ArrayList<String> _line = new ArrayList<String>();
    
    private String _endnote;
    
    private boolean _mode;
    
    private boolean _fillon;
    
    private boolean _justifyon;
    
    private boolean _EOP = false;
    
    /** Default setting for \textheight. */
    private int _textHeight = Defaults.TEXT_HEIGHT;
    /** Default setting for \parskip. */
    private int _regParskip = Defaults.PARAGRAPH_SKIP;
    /** Default setting for \indent. */
    private int _regIndent = Defaults.INDENTATION;
    /** Default setting for \parindent. */
    private int _regParIndent = Defaults.PARAGRAPH_INDENTATION;
    /** Default setting+ for \textwidth. */
    private int _regTextWidth = Defaults.TEXT_WIDTH;

    /** Default setting for \parskip in endnotes. */
    private int _endnoteParskip = Defaults.ENDNOTE_PARAGRAPH_SKIP;
    /** Default setting for \indent in endnotes. */
    private int _endnoteIndent = Defaults.ENDNOTE_INDENTATION;
    /** Default setting for \parindent in endnotes. */
    private int _endnoteParIndent = Defaults.ENDNOTE_PARAGRAPH_INDENTATION;
    /** Default setting for \textwidth in endnotes. */
    private int _endnoteTextWidth = Defaults.ENDNOTE_TEXT_WIDTH;

    private int _parskip = _regParskip;
    
    private int _indent = _regIndent;
    
    private int _parIndent = _regParIndent;
    
    private int _textWidth = _regTextWidth;
    
    private int _charLeft = _textWidth;
    
    private int _totalindent = _regIndent;

}
