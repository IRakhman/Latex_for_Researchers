package tex61;

import java.util.ArrayList;
import java.util.List;

/** A PageAssembler that collects its lines into a designated List.
 *  @author
 */
class PageCollector extends PageAssembler {

    /** A new PageCollector that stores lines in OUT. */
    PageCollector(List<String> out) {
        // FIXME
    }

    /** Add LINE to my List. */
    @Override
    void write(String line) {
        // FIXME
    }

    // Take this with a grain of salt
    private List<String> _lines = new ArrayList<String>();
    
    // Take this with a grain of salt
    private int _lineCount = 0;
}
