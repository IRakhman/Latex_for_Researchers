package tex61;

import java.io.PrintWriter;

/** A PageAssembler that sends lines immediately to a PrintWriter, with
 *  terminating newlines.
 *  @author
 */
class PagePrinter extends PageAssembler {

    /** A new PagePrinter that sends lines to OUT. */
    PagePrinter(PrintWriter out) {
        // FIXME
        _out = out;
    }

    /** Print LINE to my output. */
    @Override
    void write(String line) {
        // FIXME
        _out.println(line);
    }

    // FIXME
    private PrintWriter _out;
}
