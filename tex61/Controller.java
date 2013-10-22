package tex61;

import java.io.PrintWriter;
import java.util.ArrayList;

import static tex61.FormatException.reportError;

/** Receives (partial) words and commands, performs commands, and
 *  accumulates and formats words into lines of text, which are sent to a
 *  designated PageAssembler.  At any given time, a Controller has a
 *  current word, which may be added to by addText, a current list of
 *  words that are being accumulated into a line of text, and a list of
 *  lines of endnotes.
 *  @author Iskander Rakhmanberdiyev
 */
class Controller {
        private PageAssembler _forMaintext;
        private PageAssembler _forEndnotes;
        private LineAssembler _lAssmbMain;
        private LineAssembler _lAssmbEnd;
        
        
        private LineAssembler _finalassembler;
        
        
        private ArrayList<String> _collection; //Collection of List<String> that feeds PageCollector
        
        private PrintWriter _out;                               
        private boolean _endNoteMode;
        private int refNum;
        
    /** A new Controller that sends formatted output to OUT. */
    Controller(PrintWriter out) {
        //_curWord = new StringBuffer();
        //_curList = new ArrayList<String>();
        //_endNoteList = new ArrayList<String>();
        
        _collection = new ArrayList<String>();
        
        
        _endNoteMode = false;
        refNum = 0;
        _lAssmbMain = new LineAssembler(_forMaintext);
        _lAssmbEnd = new LineAssembler(_forEndnotes);
        _forMaintext = new PagePrinter(out); 
        _forEndnotes = new PageCollector(_lAssmbEnd.getCurList());
    }
    
        
        
  
        //if (_endnoteMode) 
        //_finalassembler = _forEndnotes;
        // else 
        //  _finalassembler = _forMaintext;
         
        
    /** Add TEXT to the end of the word of formatted text currently
     *  being accumulated. */
    void addText(String text) {

        _lAssmbMain.addText(text);
    }

    /** Finish any current word of text and, if present, add to the
     *  list of words for the next line.  Has no effect if no unfinished
     *  word is being accumulated. */
    void endWord() {
        // FIXME
    }

    /** Finish any current word of formatted text and process an end-of-line
     *  according to the current formatting parameters. */
    void addNewline() {
        // FIXME
    }
    /** Finish any current word of formatted text and process an end-of-line
     *  according to the current formatting parameters. */
    void finishLine(boolean lastLine) {
    	_lAssmbMain.finishLine(lastLine);
    }
    
    /** Finish any current word of formatted text, format and output any
     *  current line of text, and start a new paragraph. */
    void endParagraph() {
        // FIXME
    }

    /** If valid, process TEXT into an endnote, first appending a reference
     *  to it to the line currently being accumulated. */
    void formatEndnote(String text) {
        refNum += 1;
        _curWord.append("[" + refNum + "]");
        _endNoteList.add(text);
    }

    /** Set the current text height (number of lines per page) to VAL, if
     *  it is a valid setting.  Ignored when accumulating an endnote. */
    void setTextHeight(int val) {
        if (!_endNoteMode) {
           _lAssmbMain.setTextHeight(val); //lineAssembler
        }
    }
    /** Set the current text width (width of lines including indentation)
     *  to VAL, if it is a valid setting. */
    void setTextWidth(int val) {
        // FIXME
    }

    /** Set the current text indentation (number of spaces inserted before
     *  each line of formatted text) to VAL, if it is a valid setting. */
    void setIndentation(int val) {
        // FIXME
    }

    /** Set the current paragraph indentation (number of spaces inserted before
     *  first line of a paragraph in addition to indentation) to VAL, if it is
     *  a valid setting. */
    void setParIndentation(int val) {
        // FIXME
    }

    /** Set the current paragraph skip (number of blank lines inserted before
     *  a new paragraph, if it is not the first on a page) to VAL, if it is
     *  a valid setting. */
    void setParSkip(int val) {
        // FIXME
    }

    /** Iff ON, begin filling lines of formatted text. */
    void setFill(boolean on) {
        // FIXME
    }

    /** Iff ON, begin justifying lines of formatted text whenever filling is
     *  also on. */
    void setJustify(boolean on) {
        // FIXME
    }

    /** Finish the current formatted document or endnote (depending on mode).
     *  Formats and outputs all pending text. */
    void close() {
        if (_endNoteMode) { //fixit
            return;
        }
        _lAssmb1.finishLine(true);
        setEndnoteMode();
        writeEndnotes();
		for (String s: _output) {//all the Strings of Formatted Lines in 
        	_forMaintext.write(s);
		}
    }

    /** Start directing all formatted text to the endnote assembler. */
    protected void setEndnoteMode() {
        _endNoteMode = true;
        setParSkip(Defaults.ENDNOTE_PARAGRAPH_SKIP);
        setParIndentation(Defaults.ENDNOTE_PARAGRAPH_INDENTATION);
        setIndentation(Defaults.ENDNOTE_INDENTATION);
        setTextWidth(Defaults.ENDNOTE_TEXT_WIDTH);
        //Originally, they were in the lineAssembler under setEndnoteMode
        /**_mode = mode;
        if (_mode) {
            _parskip = _endnoteParskip;
            _indent = _endnoteIndent;
            _parIndent = _endnoteParIndent;
            _textWidth = _endnoteTextWidth;
            _charLeft = _textWidth;

        } else {
            _parskip = _regParskip;
            _indent = _regIndent;
            _parIndent = _regParIndent;
            _textWidth = _regTextWidth;
            _charLeft = _textWidth;
        }*/
    }

    /** Return to directing all formatted text to _mainText. */
    protected void setNormalMode() {
        _endNoteMode = false;
        
    }

    /** Write all accumulated endnotes to _mainText. */
    private void writeEndnotes() {
    	int i = 1;
    	for (String s : _endNoteList) {
    		InputParser form = new InputParser(s, this);
    		addText("[" + i "]");
    		form.process();
    		finishLine(true);
    		i += 1;
    	}
    }

    /** True iff we are currently processing an endnote. */
    private boolean _endnoteMode;
    /** Number of next endnote. */
    private int _refNum;
    // FIXME
}

